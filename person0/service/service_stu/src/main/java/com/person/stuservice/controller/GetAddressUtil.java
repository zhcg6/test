package com.person.stuservice.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 :d
 * @createDate 创建时间：
 * 类说明 -- 百度地图经纬度和地址相互转换的工具类
 */
public class GetAddressUtil {
    public static void main(String[] args) {
        Map<String, String> qqq = getMap("重庆市南岸区");
        System.out.println(qqq);
    }
    private static final  String AK = "i28SUEOuVYRl9oSbEu5pt9uPBLeAqshq";
    //由于接口请求有时候无法得到响应，故循环调用五次
    public static Map<String,String> getMap(String address){
        Map<String,String> map = null;
        for(int i=0;i<20;i++){
            map =getGeocoderLatitude(address);
            if(map!= null){
                break;
            }
        }
        return map;
    }
    /**
     * 返回输入地址的经纬度坐标
     * key lng(经度),lat(纬度)
     */
    public static Map<String,String> getGeocoderLatitude(String address){
        BufferedReader in = null;
        try {
            //将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ AK);
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while((res = in.readLine())!=null){
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map<String,String> map = null;
            if(StringUtils.isNotEmpty(str)){
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
                    String lng = str.substring(lngStart+5, lngEnd);
                    String lat = str.substring(lngEnd+7, latEnd);
                    map = new HashMap<String,String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //经纬度转地址
    public static String getAddressByLngAndLat(String lat,String lng){
        String location = lat+","+lng;
        String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+AK+"&output=json&coordtype=bd09ll&location="+location;
        String res=acquire(url);

        //获取详细地址
        //   String addressLocation= JSON.parseObject(res).getJSONObject("result").getString("formatted_address");
        //获取省份
        String province="";
        if(res.contains("province")){
            province= JSON.parseObject(res).getJSONObject("result").getJSONObject("addressComponent").getString("province");
        }


        // System.out.println(addressLocation);
        return province;

    }
    //Http处理函数
    public static String acquire(String url){
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
            HttpEntity responseEntity=response.getEntity();
            if (responseEntity!=null){
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
                if (response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }






}


