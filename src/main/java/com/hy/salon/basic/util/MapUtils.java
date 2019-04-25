package com.hy.salon.basic.util;

import com.hy.salon.basic.vo.RequestResult;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取高德地图经纬度
 *
 * @author lixin.saho
 */
public class MapUtils {
    private static String API = "http://restapi.amap.com/v3/geocode/geo?key=<key>&address=<address>";

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

    public static double[] getLatAndLonByAddress(String address) {
        try {
            String requestUrl = API.replaceAll("<address>", URLEncoder.encode(address, "UTF-8"));
            RequestResult requestResult = RequestUtils.getJsonText(requestUrl, null);
            if (200 != requestResult.getCode()) {
                return null;
            }
            requestUrl = requestResult.getBody();
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


    public static void main(String[] args) {
        double[] aaa = MapUtils.getLatAndLonByAddress("广东省东莞市黄江镇政府");
        System.out.println(aaa);
        for (double cccc : aaa) {
            System.out.println(cccc);
        }


    }

}