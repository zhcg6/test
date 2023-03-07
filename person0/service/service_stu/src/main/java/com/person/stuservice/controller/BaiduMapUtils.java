package com.person.stuservice.controller;


import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 :
 * @createDate 创建时间：
 * 类说明 -- 百度地图经纬度和地址相互转换的工具类
 */
public class BaiduMapUtils {
    private static final  String AK = "i28SUEOuVYRl9oSbEu5pt9uPBLeAqshq";

    /**
     * 百度地图通过地址来获取经纬度，传入参数address
     * @param address
     * @return
     * Todo
     */
    public static Map<String,Double> getLngAndLat(String address){
        Map<String,Double> map=new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoding/v3/?address="+address+"&output=json&ak="+AK;
        String json = loadJSON(url);
        JSONObject obj = JSONObject.parseObject(json);
        if(obj.get("status").toString().equals("0")){
            double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);

        }else{
            System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
        }

        return json.toString();
    }
}

