package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("member")
@Getter
@Setter
public class Member extends TraceableEntity<Long> {
//    private Long recordId ;
//    private Long initialStoreId ;       //开卡美容院/门店(档案来源)
//    private String memberName ;         //姓名
//    private String tel    ;             //电话
//    private Integer gender  ;           //性别
//    private String weixin ;             // 微信
//    private Date birthday  ;            // 生日
//    private Integer zodiac   ;          //星座
//    private Long memberGradeId ;        //分类
//    private Integer bloodType ;         //血型
//    private Integer height       ;      //身高
//    private Integer weight       ;      //体重
//    private Date lastDayOfMenses ;      //上次月经时间
//    private Integer cycleOfMenses ;     //月经周期
//    private Integer periodOfMenses  ;   //经期长度
//    private String remarkOfMenses   ;   //月经备注
//    private String profession      ;    //职业
//    private Long cityId          ;      //城市（省市区）
//    private String address     ;        //地址
//    private String email       ;        //邮箱
//    private String memberCode     ;     //会员编码
//    private String introducer      ;    //介绍人
//    private Long primaryBeautician  ;   //负责美容师
//    private Date entryTime      ;       //入店时间
//    private Double balance  ;           //账户总余额
//    private Double integral  ;          //账户积分
//    private Double debt    ;            // 账户欠款
//    private Double amountConsumer ;     // 总消费
//    private Double amountCharge  ;      //总充值
//    //新增属性
    private String primaryName  ;   //负责美容师
    private String salonName  ;   //店名

    private String tagName;// 标签



    private Long initialStoreId;

    private String memberName;

    private String tel;

    private Byte gender;

    private String weixin;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private Byte zodiac;

    private Long memberGrade;

    private Byte bloodType;

    private Integer height;

    private Integer weight;

    private Date lastDayOfMenses;

    private Byte cycleOfMenses;

    private Byte periodOfMenses;

    private String remarkOfMenses;

    private String profession;

    private Long cityId;

    private String address;

    private String email;

    private String memberCode;

    private String introducer;

    private Long primaryBeautician;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date entryTime;

    private Double balanceCash;
    private Double balanceTotal;

    private Double cashCoupon;

    private Double integral;

    private Double debt;

    private Double amountConsumer;

    private Double amountCharge;
}
