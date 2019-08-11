package com.zyj.plugin.common;



/**
 * 存放全局常量
 */
public class Constants {

    public static final String upload_file_url = "http://10.4.136.187:8080/";
    public static final String PAY_TYPE_ONLINE = "1";
    public static final String PAY_TYPE_RESEVER = "2";

    public static final String PAY_TYPE_ALIPAY = "1";
    public static final String PAY_TYPE_WECHAT = "2";

    public static boolean isLatestVersion = true;//是否是最新版本

    public static final String PATH_AGREENEMT_MLF = "file:///android_asset/langfangDream.html";

    public static final String INTENT_EXTRA_NAME_DATA = "data";
    public static final String INTENT_EXTRA_NAME_DATA1 = "data1";
    public static final String INTENT_EXTRA_NAME_DATA2 = "data2";

    public static final int REQUEST_CODE_LOGIN = 1008;
    public static final int RESULT_CODE_LOGIN = 1009;
    //当前定位位置
    public static double mLat = 21.048132;
    public static double mLon = 109.122671;

    public static String SELECT_AREA;
    public static final String DATA_FORMAT_YYYYMMDD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final int LIMIT = 10;
    public static String orderCode;
    public static int orderType;
    public static int orderFrom;//三种来源：0.在线缴费 1.预定-下单界面 2.订单列表 3.订单详情


}
