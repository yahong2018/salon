package com.hy.salon.basic.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;


public class JPush {


    // 设置好账号的app_key和masterSecret是必须的
    private static String APP_KEY = "3fa237cbeb4365d2d9ae6e60";
    private static String MASTER_SECRET = "47ea2228a418c8510e277547";



    //极光推送>>All所有平台
    public static void jpushAll(Map<String, String> parm) {

        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //创建option
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())  //所有平台的用户
                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder() //发送ios
                                .setAlert(parm.get("msg")) //消息体
                                .setBadge(+1)
                                .setSound("happy") //ios提示音
                                .addExtras(parm) //附加参数
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder() //发送android
                                .addExtras(parm) //附加参数
                                .setAlert(parm.get("msg")) //消息体
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build())//指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    //极光推送>>All所有平台
    public static PushResult jpushAllStuff(Map<String, Object> parm) {

        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //创建option
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag((String[])parm.get("tag")))
                .setNotification(Notification.android((String)parm.get("content"), (String)parm.get("title"), null))
                .build();
        PushResult pu=null;
        try {
            pu = jpushClient.sendPush(payload);
            System.out.println(pu.statusCode);
            System.out.println(pu.toString());

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return pu;
    }

//    public static void main(String[] args) {
//
//        //设置推送参数
//
//        Map<String, Object> parm = new HashMap<String, Object>();
//        parm.put("content","内容");
//        parm.put("title","标题");
//        parm.put("tag",new String[]{"tag2","tag3","tag10"});
//
//        JPush.jpushAllStuff(parm);
//
//    }




}
