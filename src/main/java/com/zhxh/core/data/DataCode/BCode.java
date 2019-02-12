package com.zhxh.core.data.DataCode;

public final class BCode {
    //
    //通用状态
    //
    public static final int STATUS_NORMAL = 0;                       //0:正常
    public final static int STATUS_ENABLED = 0;                      //0.启用
    public final static int STATUS_DISABLED = 1;                     //1:停用
    public final static int STATUS_OFFLINE = 0;                      //0.离线
    public final static int STATUS_ONLINE = 1;                       //1.在线

    //
    //订单
    //
    public final static int ORDER_STATUS_PLANNED = 0;                          //0.已计划
    public final static int ORDER_STATUS_RELEASED = 30;                        //30.已下达
    public final static int ORDER_STATUS_PREADY = 40;                          //40.产前准备：内部准备完成
    public final static int ORDER_STATUS_OUTSOURCED = 45;                      //45.产前准备：外协完成
    public final static int ORDER_STATUS_READIED = 99;                         //99.产前准备:已齐料
    public final static int ORDER_STATUS_ISSUED = 100;                         //100.已发料
    public final static int ORDER_STATUS_STARTED = 100;                        //100.已开始:已发料就表示已开始制造。
    public final static int ORDER_STATUS_CUTED = 200;                          //200.已裁剪
    public final static int ORDER_STATUS_HANG = 300;                           //300.已上吊挂线
    public final static int ORDER_STATUS_STITCHING = 400;                      //400.已缝制
    public final static int ORDER_STATUS_FINISHED = 500;                       //500.已结束

    //
    //C类编码类型
    //
    public final static String CODE_TYPE_C_MATERIAL_TYPE = "MATERIAL_TYPE";
    public final static String CODE_TYPE_C_MACHINE_TYPE = "MACHINE_TYPE";
    public final static String CODE_TYPE_C_DEFECT_TYPE = "DEFECT_TYPE";

    //
    //权限
    //
    public final static String PRIVILEGE_RUN = "RUN";
    public final static String PRIVILEGE_INSERT = "INSERT";
    public final static String PRIVILEGE_UPDATE = "UPDATE";
    public final static String PRIVILEGE_DELETE = "DELETE";

    public final static String PRIVILEGE_ENABLE_USER = "ENABLE_USER";
    public final static String PRIVILEGE_DISABLE_USER = "DISABLE_USER";
    public final static String PRIVILEGE_ASSIGN_ROLE = "ASSIGN_ROLE";
    public final static String PRIVILEGE_RESET_PASSWORD = "RESET_PASSWORD";

    //
    //方向
    //
    public final static int DIRECTION_LEFT = 0;                        //左边
    public final static int DIRECTION_RIGHT = 1;                       //右边

    //
    //旋转方向
    //
    public final static int RATION_CLOCKWISE = 0;                      //顺时针
    public final static int RATION_ANTI_CLOCKWISE = 1;                 //逆时针

    //
    //工位类型
    //
    public final static int WORK_STATION_TYPE_CUT = 0;                 //裁剪工位
    public final static int WORK_STATION_TYPE_STITCH = 1;              //缝制工位
    public final static int WORK_STATION_TYPE_HANG = 2;                //上吊挂工位
    public final static int WORK_STATION_TYPE_QUALITY = 3;             //质检工位
    public final static int WORK_STATION_TYPE_PRINT = 4;               //打印工位

    //
    //工艺路线单类型
    //
    public final static int OPERATION_ROUTING_ORDER_TYPE_MATERIAL = 0;            //物料工艺路线单
    public final static int OPERATION_ROUTING_ORDER_TYPE_PRODUCTION_ORDER = 1;    //生产订单工艺路线
    public final static int OPERATION_ROUTING_ORDER_TYPE_WORK_ORDER = 2;          //作业单工艺路线

    //
    //工艺状态
    //
    public final static int OPERATION_ROUTING_STATUS_PLANNED=0;
    public final static int OPERATION_ROUTING_STATUS_STARTED=1;
    public final static int OPERATION_ROUTING_STATUS_FINISHED=2;

    //
    //订单重复类型
    //
    public final static int REQUIREMENT_ORDER_REPEAT_TYPE_FIRST = 0;              //首单
    public final static int REQUIREMENT_ORDER_REPEAT_TYPE_REPEAT = 1;             //返单
    public final static int REQUIREMENT_ORDER_REPEAT_TYPE_SUPPLEMENT = 2;         //补单

    //
    //Bom类型
    //
    public final static int BOM_TYPE_PART = 0;                               //部件BOM
    public final static int BOM_TYPE_STANDARD = 1;                           //标准BOM
    public final static int BOM_TYPE_ORDER = 2;                              //订单BOM
    public final static int BOM_TYPE_DESIGN = 3;                             //设计BOM
    public final static int BOM_TYPE_MANUFACTURE = 4;                        //生产BOM
    public final static int BOM_TYPE_WORK = 5;                               //制造BOM

    private BCode() {
    }
}
