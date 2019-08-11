package com.zyj.plugin.common.uitl;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 数据类型转换工具类
 */
public class ConverterUtils {
    public static String getAssetsJson(Context context, String fileName) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = context.getAssets().open(fileName);
            bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[4 * 1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            return new String(bos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }
    //*****************************************基础数据转换******************************************
    //*****************************************基础数据转换******************************************
    //*****************************************基础数据转换******************************************

    /**
     * @return obj转换为string
     */
    public static String toString(Object obj, String defaultVal) {
        return (obj != null) ? obj.toString() : defaultVal;
    }

    /**
     * @return 将对象转换为string的字符串
     */
    public static String toString(Object obj) {
        return toString(obj, "");
    }

    /**
     * string转int
     */
    public static int StringToInt(String str) {
        int b = 0;
        try {
            b = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * @return obj转换成的int值
     */
    public static Integer toInt(Object obj, Integer defaultVal) {
        try {
            return (obj != null) ? Integer.parseInt(toString(obj, "0")) : defaultVal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    /**
     * @return obj转换成的int值
     */
    public static Integer toInt(Object obj) {
        return toInt(obj, 0);
    }

    /**
     * @return obj转换成的Integer值
     */
    public static Integer toInteger(Object obj) {
        return toInt(obj, null);
    }

    /**
     * @return obj转换成的int值
     */
    public static Float toFloat(Object obj, float defaultVal) {
        return (obj != null) ? Float.parseFloat(toString(obj, "0")) : defaultVal;
    }

    /**
     * @return obj转换成的Float值
     */
    public static Float toFloat(Object obj) {
        return toFloat(obj, 0);
    }

    /**
     * @return 如果obj为空则返回默认，不为空则返回转换后的long结果
     */
    public static Long toLong(Object obj, long defaultVal) {
        return (obj != null) ? Long.parseLong(toString(obj)) : defaultVal;
    }

    /**
     * @return 如果obj为空则返回默认的0l，不为空则返回转换后的long结果
     */
    public static Long toLong(Object obj) {
        return toLong(obj, 0);
    }

    /**
     * 将object转换为double类型，如果出错则返回 defaultVal
     */
    public static Double toDouble(Object obj, Double defaultVal) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * 将object转换为double类型，如果出错则返回 0d
     */
    public static double toDouble(Object obj) {
        return toDouble(obj, 0d);
    }

    /**
     * 将List<Object>转换为List<Map<String, Object>>>
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> converterForMapList(List<Object> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object tempObj : list) {
            result.add((HashMap<String, Object>) tempObj);
        }
        return result;
    }

    /**
     * DecimalFormat转换最简便
     */
    public static String doubleto2(double aa) {
        try {
            if (aa == 0) {
                return "0.00";
            } else {
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println(df.format(aa));
                return "￥" + df.format(aa);
            }
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String doubleto4(double aa) {
        try {
            if (aa == 0) {
                return "0.00";
            } else {
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println(df.format(aa));
                return "" + df.format(aa);
            }
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * DecimalFormat转换最简便
     */
    public static String doubleto3(double aa) {
        try {
            if (aa == 0) {
                return "0";
            } else {
                DecimalFormat df = new DecimalFormat("#.0");
                System.out.println(df.format(aa));
                return "" + df.format(aa);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    // 使用String的split 方法
    public static String[] convertStrToArray(String str) {
        String[] strArray = new String[str.length()];
        try {
            if (str.contains(",")) {
                strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
                return strArray;
            } else {
                strArray[0] = str;
                return strArray;
            }
        } catch (Exception e) {
            return null;
        }
    }

    // 将下载的流转成string
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //*****************************************时间、距离等转换******************************************
    //*****************************************时间、距离等转换******************************************
    //*****************************************时间、距离等转换******************************************

    /**
     * 转换长度 单位:千米 米
     * 500返回500米
     * 5000返回5千米
     */
    public static String convertLength(double miter) {
        String length_str;
        if (miter >= 1000) {
            miter = miter / 1000;
            length_str = new DecimalFormat("#.0").format(miter) + "千米";
            length_str = length_str.replace(".0", "");
        } else {
            length_str = new DecimalFormat("#").format(miter) + "米";
        }
        return length_str;
    }

    /**
     * 转换长度 单位:km m
     * 500返回500m
     * 5000返回5km
     */
    public static String convertLengthEnglish(double miter) {
        String length_str;
        if (miter >= 1000) {
            miter = miter / 1000;
            length_str = new DecimalFormat("#.0").format(miter) + "km";
            length_str = length_str.replace(".0", "");
        } else {
            length_str = new DecimalFormat("#").format(miter) + "m" +
                    "";
        }
        return length_str;
    }

    /**
     * 转换时间单位
     * 20返回20分钟
     * 70返回1.1小时
     */
    public static String convertTime(double minute) {
        String time_str;
        if (minute >= 60) {
            minute = minute / 60;
            time_str = new DecimalFormat("#.0").format(minute) + "小时";
            time_str = time_str.replace(".0", "");
        } else {
            time_str = new DecimalFormat("#").format(minute) + "分钟";
        }
        return time_str;
    }

    /**
     * 毫秒转化时分秒
     * 200000返回02:30
     */
    public static String formatTime(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
//        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        //return strMinute + " 分钟 " + strSecond + " 秒";
        if ("00".equals(strHour)) {
            return strMinute + ":" + strSecond;
        } else {
            return strHour + ":" + strMinute + ":" + strSecond;
        }
    }

    /**
     * 日期格式重新修改
     * 2017-04-21 20:36:39
     * 转成
     * 2017.04.21
     */
    public static String convertDateStr(String dateStr) {
        String time = null;
        try {
            time = dateStr.split(" ")[0];
            time = time.replace("-", ".");
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
        return time;
    }

    /**
     * 日期转换为时间戳
     */
    public static long convertDateToStamp(String date_Str, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        try {
            //原文是"20130304",格式不对，但是还可以写为"2013-3-04"
            Date date = df.parse(date_Str);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过日期获取到时间字符串
     * 返回2018-12-20 10:34:29
     */
    public static String getTime(Date date, String format) {//可根据需要自行截取数据显示
        if (date == null) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 获取两个时间的时间差
     */
    public static long getBetweenTime(String start_time, String end_time, String format) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat(format, Locale.getDefault());
            Date begin = dfs.parse(start_time);
            Date end = dfs.parse(end_time);
            long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
            return between / 60;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * @param endTime 单位是s
     * @return 间隔时间
     */
    public static long getBetweenTime(long endTime) {
        String startTime1 = convertTimeToString(String.valueOf(new Date().getTime() / 1000), "yyyy-MM-dd HH:mm");//开始时间
        String endTime1 = convertTimeToString(String.valueOf(endTime), "yyyy-MM-dd HH:mm");//开始时间
        return getBetweenTime(startTime1, endTime1, "yyyy-MM-dd HH:mm");
    }

    /**
     * 时间戳转换成日期格式字符串
     * 1545273269
     * 转成
     * 2018-12-20 10:34:29
     */
    public static String convertTimeToString(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.format(new Date(Long.valueOf(seconds + "000")));
        } catch (Exception e) {
            LogUtils.e(e.toString());
            return "";
        }
    }

    public static void replaceListElement(ArrayList<String> list, String old, String element) {
        try {
            int position = list.indexOf(old);
            list.set(position, element);
        } catch (Exception e) {
            LogUtils.e(e.toString());
        }
    }

    public static String getStringFromTimeStr(String time, String oldPattern, String newPattern) {
        SimpleDateFormat oldSdf = new SimpleDateFormat(oldPattern, Locale.getDefault());
        SimpleDateFormat newSdf = new SimpleDateFormat(newPattern, Locale.getDefault());
        Date date;
        try {
            date = oldSdf.parse(time);
            return newSdf.format(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String nubmberAddNearestThousandth(double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###.##");
        return decimalFormat.format(bigDecimal);
    }

    public static String convertCarNumStyle(String carNumber) {
        if (carNumber != null && carNumber.length() > 2) {
            String start = carNumber.substring(0, 2);
            String end = carNumber.substring(2);
            carNumber = start + "·" + end;
        }
        return carNumber;
    }
}