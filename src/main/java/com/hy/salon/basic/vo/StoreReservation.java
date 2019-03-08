package com.hy.salon.basic.vo;


import com.hy.salon.basic.entity.Reservation;
import com.zhxh.core.data.TraceableEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StoreReservation  {
    private String salonName;
    private String salonId;
    private String reservationCount;
    //private String isHoliday;
/*    private String salonName;
    private Long parentId;
    private String tel;
    private Long cityId;
    private String address;
    private Boolean door2Door;
    private Integer bedNum;
    private Double area;
    private Date timeOpen;
    private Date timeClose;
    private String description;

    //門店
    private Long storeId;
    private String roomName;
    private Byte recordStatus;

    //预约
    private List<Reservation> reservationList;*/
}
