package com.hy.salon.basic.util;

import com.hy.salon.basic.vo.RequestResult;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GaoDeUtil {
    //https://restapi.amap.com/v3/distance?origins=113.783836,23.033806&destination=113.783836,23.033806&output=xml&key=<key>&type=0
    //private static String API = "https://restapi.amap.com/v3/distance?origins=113.842223,22.899965&destination=113.835765,22.905955&output=xml&key=<key>&type=0";
    private static String API = "https://restapi.amap.com/v3/distance?origins=";
    private static String KEY = "4f071e21116c6cdd1747f67220913890";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");

    static {
        init();
    }

    private static void init() {
//        System.out.println("高德地图工具类初始化");
//        System.out.println("api: {}"+API);
//        System.out.println("key: {}"+KEY);
        API = API.replaceAll("<key>", KEY);
    }

    public   static Boolean getBooleanAddress(String myAddress, String GAddress){
        String temp = myAddress+"&destination=" +GAddress+
                "&output=xml&key=4f071e21116c6cdd1747f67220913890&type=0";
        String requestUrl = API+temp;
        Boolean flag  = false;
        RequestResult requestResult = RequestUtils.getJsonText(requestUrl, null);
        if (200 != requestResult.getCode()) {
            return false;
        }
        requestUrl = requestResult.getBody();
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(requestUrl).toString();
        JSONObject resultJson = JSONObject.fromObject(resutStr);
        String results = resultJson.getString("results");
        JSONObject resultStirngs = JSONObject.fromObject(results);


        String result = resultStirngs.getString("result");
        JSONObject resultStirng = JSONObject.fromObject(result);

        int distance = resultStirng.getInt("distance");
        if(distance<=200){
            flag = true;
        }
        return flag;
    }

    private static double[] getLatAndLonByAddress(String address) {
        try {
            String requestUrl = API;
            RequestResult requestResult = RequestUtils.getJsonText(requestUrl, null);
            if (200 != requestResult.getCode()) {
                return null;
            }
            requestUrl = requestResult.getBody();
            XMLSerializer xmlSerializer = new XMLSerializer();
            String resutStr = xmlSerializer.read(requestUrl).toString();
            JSONObject resultJson = JSONObject.fromObject(resutStr);
            String results = resultJson.getString("results");
            JSONObject resultStirngs = JSONObject.fromObject(results);


            String result = resultStirngs.getString("result");
            JSONObject resultStirng = JSONObject.fromObject(result);

            int distance = resultStirng.getInt("distance");
            if (requestUrl != null) {
                Matcher matcher = pattern.matcher(requestUrl);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    return gps;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


/*    public static void main(String[] args) {
        System.out.println(GaoDeUtil.getLatAndLonByAddress("广东省东莞市东城区莞樟路115-3"));
        double[] aaa = GaoDeUtil.getLatAndLonByAddress("广东省东莞市东城区莞樟路115-3");
        System.out.println(aaa);
        for (double cccc : aaa) {
            System.out.println(cccc);
        }
        System.out.println(GaoDeUtil.getLatAndLonByAddress("广东省深圳市福田区天安数码城创业科技大厦一期"));

    }*/
}

