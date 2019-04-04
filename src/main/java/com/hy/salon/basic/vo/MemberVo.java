package com.hy.salon.basic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hy.salon.basic.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberVo  {

    private String tagName;

    private String stuffName;

    private String primaryName  ;   //负责美容师
    private String salonName  ;   //店名



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

    private Double balance;

    private Double integral;

    private Double debt;

    private Double amountConsumer;

    private Double amountCharge;
}
