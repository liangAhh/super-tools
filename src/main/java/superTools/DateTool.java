package superTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateTool {

    public final static String DATE_PATTERN = "yyyyMMddHHmmss";
    public static final String PATTERN_YEAR_MONTH_DAY_HH = "yyyy-MM-dd HH";
    public static final String PATTERN_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String PATTERN_YEAR_MONTH = "yyyy-MM";
    public static final String PATTERN_YEAR_MONTH_DAY_CN = "yyyy年MM月dd日";
    public static final String PATTERN_YEAR_MONTH_DAY_CN_HH_MM = "yyyy年MM月dd日 HH:mm:ss";
    public static final String PATTERN_YEAR_MONTH_DAY_CN_HH_MM2 = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String PATTERN_YEAR_MONTH_DAY_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String PATTERN_yyyyMMddHHmmS = "yyyyMMddHHmmS";
    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String PATTERN_yyMMddHHmmss = "yyMMddHHmmss";
    public static final String PATTERN_yyMMdd = "yyMMdd";
    public static final String PATTERN_yyyyMM = "yyyyMM";
    public static final String PATTERN_yyMM_CN = "yy年MM月";
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    public static final String PATTERN_HHmm = "HHmm";
    public static final String PATTERN_HH = "HH";
    public static final String PATTERN_mm = "mm";
    public static final String PATTERN_YEAR_MONTH_DAY_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_yyyy = "yyyy";

    /**
     * 将日期类型转换为字符串表示，默认yyyy-MM-dd格式
     *
     * @param date 日期对象
     * @return 日期对象的字符串表示
     */
    public static String date2String(Date date) {
        return date2String(date, PATTERN_YEAR_MONTH_DAY);
    }

    /**
     * 将日期类型转换为字符串表示
     *
     * @param date 日期对象
     * @param pattern 日期格式
     * @return 日期对象的字符串表示
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateStr = "";
        if (date != null) {
            dateStr = df.format(date);
        }
        return dateStr;
    }

    public static Date string2Date(String dateStr, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        if (dateStr != null) {
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 日期增加或减少
     * @param date  初始日期
     * @param num   计算天数
     * @return
     */
    public static Date dayCalculation(Date date, int num) {
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(date);
        theCa.add(Calendar.DATE, num);
        return theCa.getTime();
    }
}
