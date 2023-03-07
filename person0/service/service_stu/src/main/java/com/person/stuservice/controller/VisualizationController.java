package com.person.stuservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.person.commonutils.R;
import com.person.stuservice.entity.Information;
import com.person.stuservice.entity.vo.Sex;
import com.person.stuservice.service.InformationService;
import com.person.stuservice.service.SexService;
//import com.sun.org.apache.xpath.internal.operations.String;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stuservice/visualization")
@CrossOrigin
public class VisualizationController {
    @Autowired
    private InformationService informationService;

    @ApiOperation(value = "性别统计")
    @GetMapping("getSexCount")
    private ArrayList<Map<String, Object>> getCount() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("sex","男");
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("sex","女");
        int count = informationService.count(wrapper);
        int count1 = informationService.count(wrapper1);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","男");
        list.add(map);
        ArrayList<Map<String, Object>> list1 = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","女");
        list.add(map1);
        return list;
    }
    @ApiOperation(value = "地域统计")
    @GetMapping("getRegionCount")
    private ArrayList<Map<String, Object>> getRegionCount() throws IOException{
        List<Information> list1= informationService.list(null);
        int countNum = informationService.count(null);
        String[] regions = new String[countNum];
        for (int i = 0; i < list1.size(); i++) {
            Information information = list1.get(i);
            String nowPlace = information.getNowPlace();
            regions[i] = nowPlace;
        }
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < regions.length; i++) {
            QueryWrapper<Information> wrapper = new QueryWrapper<>();
            wrapper.eq("now_place",regions[i]);
            int count = informationService.count(wrapper);
            HashMap<String, Object> map = new HashMap<>();
//            if (!map.containsValue(regions[i])) {
//                map.put("name",regions[i]);
//                list.add(map);
//            }
            ArrayList<Object> objects = new ArrayList<>();
//            Map<String, String> map1 = GetAddressUtil.getMap(regions[i]);
//            String[] s = new String[2];
//            s[0] = map1.get("lng");
//            s[1] = map1.get("lat");
//            System.out.println(s[0] + " " + s[1]);
//            double qq = Double.parseDouble(s[0]);
//            double qq1 = Double.parseDouble(s[1]);
            Map<String, Double> map2 = BaiduMapUtils.getLngAndLat(regions[i]);
            Double lng = map2.get("lng");
            Double lat = map2.get("lat");
            objects.add(lng);
            objects.add(lat);
            objects.add(count);
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (regions[j].equals(regions[i])) {
                    flag = true;
                }
            }
            if (!flag) {
                map.put("name",regions[i]);
                map.put("value",objects);
                list.add(map);
            }
        }
        return list;
    }

    @ApiOperation("年龄统计")
    @GetMapping("getAgeCount")
    private ArrayList<Map<String,Object>> getAgeCount(){
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.lt("age",17);
        int count = informationService.count(wrapper);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","17岁以下");
        list.add(map);
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.between("age",17,20);
        int count1 = informationService.count(wrapper1);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","17-20");
        list.add(map1);
        QueryWrapper<Information> wrapper2 = new QueryWrapper<>();
        wrapper2.gt("age",20);
        int count2 = informationService.count(wrapper2);
        List<Information> list1 = informationService.list(wrapper2);
        Information information = new Information();
        for (int i = 0; i < list1.size(); i++) {
            Information information1 = list1.get(i);
            System.out.println(information1);
        }
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("value",count2);
        map2.put("name","20岁以上");
        list.add(map2);
        return list;
    }

//    @ApiOperation(value = "性别统计")
//    @GetMapping("getpp/{address}")
//    private R getpp(@PathVariable String address) throws IOException {
//        GetLatAndLngByBaidu getLatAndLngByBaidu = new GetLatAndLngByBaidu();
//        Object[] coordinate = getLatAndLngByBaidu.getCoordinate(address);
//        System.out.println(coordinate[0]);
//        System.out.println(coordinate[1]);
//        return R.ok().data("qqq",coordinate[0]).data("www",coordinate[1]);
//    }

    @ApiOperation("身高统计")
    @GetMapping("getHeightCount")
    private ArrayList<Map<String,Object>> getHeightCount(){
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.le("height",160);
        int count = informationService.count(wrapper);
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","160及以下");
        list.add(map);
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.between("height",160.1,165);
        int count1 = informationService.count(wrapper1);
        HashMap<String ,Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","161-165");
        list.add(map1);
        QueryWrapper<Information> wrapper2 = new QueryWrapper<>();
        wrapper2.between("height",165.1,170);
        int count2 = informationService.count(wrapper2);
        HashMap<String ,Object> map2 = new HashMap<>();
        map2.put("value",count2);
        map2.put("name","166-170");
        list.add(map2);
        QueryWrapper<Information> wrapper3 = new QueryWrapper<>();
        wrapper3.between("height",170.1,175);
        int count3 = informationService.count(wrapper3);
        HashMap<String ,Object> map3 = new HashMap<>();
        map3.put("value",count3);
        map3.put("name","171-175");
        list.add(map3);
        QueryWrapper<Information> wrapper4 = new QueryWrapper<>();
        wrapper4.between("height",175.1,180);
        int count4 = informationService.count(wrapper4);
        HashMap<String ,Object> map4 = new HashMap<>();
        map4.put("value",count4);
        map4.put("name","176-180");
        list.add(map4);
        QueryWrapper<Information> wrapper5 = new QueryWrapper<>();
        wrapper5.gt("height",180);
        int count5 = informationService.count(wrapper5);
        HashMap<String ,Object> map5 = new HashMap<>();
        map5.put("value",count5);
        map5.put("name","180及以上");
        list.add(map5);
        return list;
    }

    @ApiOperation("标准体重计算")
    @GetMapping("getWeightCount")
    private ArrayList<Map<String,Object>> getWeightCount(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("sex","男");
        List<Information> list1 = informationService.list(wrapper);
        int num = informationService.count(wrapper);
        int []count = new int[10];
        int t =0;
        for (int i = 0; i < num;) {
            Information information = list1.get(t++);
            if (information.equals(null)||information.equals("")){
                num--;
            }else {
                double w = information.getWeight();
                double h = information.getHeight();
                if (w/((h-80)*0.7)<0.8){
                    System.out.println("男生体重不足"+w+" "+h);
                    count[0]++;
                }else if (w/((h-80)*0.7)>=0.8&&w/((h-80)*0.7)<0.9){
                    System.out.println("男生过轻"+w+" "+h);
                    count[2]++;
                }else if (w/((h-80)*0.7)>=0.9&&w/((h-80)*0.7)<1.1){
                    System.out.println("男生标准体重"+w+" "+h);
                    count[4]++;
                }else if (w/((h-80)*0.7)>=1.1&&w/((h-80)*0.7)<1.2){
                    System.out.println("男生过重"+w+" "+h);
                    count[6]++;
                }else if (w/((h-80)*0.7)>=1.2){
                    System.out.println("男生肥胖"+w+" "+h);
                    count[8]++;
                }
                i++;
            }

        }
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("sex","女");
        List<Information> list2 = informationService.list(wrapper1);
        for (int i = 0; i < list2.size(); i++) {
            Information information = list2.get(i);
            double w = information.getWeight();
            double h = information.getHeight();
            if (w/((h-70)*0.6)<0.8){
                System.out.println("女生体重不足"+w+" "+h);
                count[1]++;
            }else if (w/((h-70)*0.6)>=0.8&&w/((h-70)*0.6)<0.9){
                System.out.println("女生过轻"+w+" "+h);
                count[3]++;
            }else if (w/((h-70)*0.6)>=0.9&&w/((h-70)*0.6)<1.1){
                System.out.println("女生标准体重"+w+" "+h);
                count[5]++;
            }else if (w/((h-70)*0.6)>=1.1&&w/((h-70)*0.6)<1.2){
                System.out.println("女生过重"+w+" "+h);
                count[7]++;
            }else if (w/((h-70)*0.6)>=1.2){
                System.out.println("女生肥胖"+w+" "+h);
                count[9]++;
            }
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("value",count[0]);
        map.put("name","男生体重不足");
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("value",count[1]);
        map1.put("name","女生体重不足");
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("value",count[2]);
        map2.put("name","男生过轻");
        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("value",count[3]);
        map3.put("name","女生过轻");
        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("value",count[4]);
        map4.put("name","男生标准体重");
        HashMap<String,Object> map5 = new HashMap<>();
        map5.put("value",count[5]);
        map5.put("name","女生标准体重");
        HashMap<String,Object> map6 = new HashMap<>();
        map6.put("value",count[6]);
        map6.put("name","男生过重");
        HashMap<String,Object> map7 = new HashMap<>();
        map7.put("value",count[7]);
        map7.put("name","女生过重");
        HashMap<String,Object> map8 = new HashMap<>();
        map8.put("value",count[8]);
        map8.put("name","男生肥胖");
        HashMap<String,Object> map9 = new HashMap<>();
        map9.put("value",count[9]);
        map9.put("name","女生肥胖");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);

        return list;
    }

    @ApiOperation("睡眠时间统计")
    @GetMapping("getSleepCount")
    private ArrayList<Map<String,Object>> getSleepCount(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.lt("sleep",6);
        int count = informationService.count(wrapper);
        HashMap<String,Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","小于6小时");
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.between("sleep",6,7);
        int count1 = informationService.count(wrapper1);
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","6-7小时");
        QueryWrapper<Information> wrapper2 = new QueryWrapper<>();
        wrapper2.between("sleep",7.1,8);
        int count2 = informationService.count(wrapper2);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("value",count2);
        map2.put("name","7-8小时");
        QueryWrapper<Information> wrapper3 = new QueryWrapper<>();
        wrapper3.between("sleep",8.1,9);
        int count3 = informationService.count(wrapper3);
        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("value",count3);
        map3.put("name","8-9小时");
        QueryWrapper<Information> wrapper4 = new QueryWrapper<>();
        wrapper4.gt("sleep",9);
        int count4 = informationService.count(wrapper4);
        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("value",count4);
        map4.put("name","大于9小时");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        return list;
    }
    @ApiOperation(value = "抽烟统计")
    @GetMapping("getSmokeCount")
    private ArrayList<Map<String, Object>> getSomkeCount() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq("smoke",0);
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("smoke",1);
        int count = informationService.count(wrapper);
        int count1 = informationService.count(wrapper1);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","不抽烟");
        list.add(map);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","抽烟");
        list.add(map1);
        return list;
    }
    @ApiOperation("每周运动时间统计")
    @GetMapping("getSportCount")
    private ArrayList<Map<String,Object>> getSportCount(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.lt("sport_time",1);
        int count = informationService.count(wrapper);
        HashMap<String,Object> map = new HashMap<>();
        map.put("value",count);
        map.put("name","小于1小时");
        QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
        wrapper1.between("sport_time",1,2);
        int count1 = informationService.count(wrapper1);
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("value",count1);
        map1.put("name","1-2小时");
        QueryWrapper<Information> wrapper2 = new QueryWrapper<>();
        wrapper2.between("sport_time",2.1,3);
        int count2 = informationService.count(wrapper2);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("value",count2);
        map2.put("name","2-3小时");
        QueryWrapper<Information> wrapper3 = new QueryWrapper<>();
        wrapper3.between("sport_time",3.1,4);
        int count3 = informationService.count(wrapper3);
        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("value",count3);
        map3.put("name","3-4小时");
        QueryWrapper<Information> wrapper4 = new QueryWrapper<>();
        wrapper4.gt("sport_time",4.1);
        int count4 = informationService.count(wrapper4);
        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("value",count4);
        map4.put("name","大于4小时");
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        return list;
    }

    @ApiOperation("专业人数")
    @GetMapping("getSpCount")
    private ArrayList<Map<String,Object>> getSpCount(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        List<Information> list1 = informationService.list(null);
        int count = informationService.count(null);
        String []sp = new String[count];
        int t = 0;
        for (int i = 0; i < count;) {
            Information information = list1.get(t++);
            if (information.getSpecialized().equals("")){
                count--;
            }else {
                sp[i] = information.getSpecialized();
                System.out.println(sp[i]);
                i++;
            }

        }
        for (int i = 0; i < count; i++) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (sp[i].equals(sp[j])){
                    flag = true;
                }
            }
            if (!flag){
                HashMap<String,Object> map = new HashMap<>();
                QueryWrapper<Information> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("specialized",sp[i]);
                int num = informationService.count(wrapper1);
                map.put("value",num);
                map.put("name",sp[i]);
                list.add(map);
            }
        }

        return list;
    }

    @ApiOperation("已报道人数")
    @GetMapping("getRePortCount")
    private Integer getRePortCount() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("height");
        int count = informationService.count(wrapper);
        return count;
    }

    @ApiOperation("未报道人数")
    @GetMapping("getNotRePortCount")
    private Integer getNotRePortCount() {
        int countTotal = informationService.count(null);
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("height");
        int count = informationService.count(wrapper);
        int res = countTotal - count;
        return res;
    }

    @ApiOperation("总人数")
    @GetMapping("getStuCount")
    private Integer getStuCount() {
        int countTotal = informationService.count(null);
        return countTotal;
    }

    @ApiOperation("报到率")
    @GetMapping("getRateOfRegistering")
    private String getRateOfRegistering() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("height");
        int count = informationService.count(wrapper);
        int countTotal = informationService.count(null);
        System.out.println(count + " " + countTotal);
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        double res = (double)count / (double)countTotal;
        return percentInstance.format(res);
    }
    @ApiOperation("未报到率")
    @GetMapping("getNotRateOfRegistering")
    private String getNotRateOfRegistering() {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("height");
        int count = informationService.count(wrapper);
        int countTotal = informationService.count(null);
        System.out.println(count + " " + countTotal);
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        double res =1 - (double)count / (double)countTotal;
        return percentInstance.format(res);
    }


}

