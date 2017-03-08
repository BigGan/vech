/*package context.support.utilities;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;


import java.text.ParseException;
import java.util.Date;

public class DateUtil {

    private static Logger logger= LogUtil.getLogger(LogUtil.frameName);

    public static String formatDate(Date date,String format){
        return DateFormatUtils.format(date,format);
    }

    public static String formatDate(Long time,String format){
        return DateFormatUtils.format(time,format);
    }

    public static Date parseDate(String date,String format){
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date,format);
        } catch (ParseException e) {
            logger.error("日期时间解析错误.",e);
            return null;
        }
    }

}
*/