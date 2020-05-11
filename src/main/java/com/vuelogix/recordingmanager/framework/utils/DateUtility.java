package com.vuelogix.recordingmanager.framework.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtility {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtility.class);

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 17-Sep-2019
     */
    public static String concatDateAndTime(Date date, Time time, String pattern) {
        String todayAsString = null;
        if (date != null) {
            Calendar todayCal = Calendar.getInstance();
            todayCal.setTime(date);
            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(time);
            todayCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
            todayCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
            todayCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
            todayAsString = formatDate(todayCal.getTime(), pattern);
        } else {
            LOG.info("Date is empty or null");
        }
        return todayAsString;
    }

    /**
     * @return Date
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 18-Sep-2019
     */
    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 19-Sep-2019
     */
    public static String formatDate(Date date, String pattern) {
        String value = null;
        try {
            if (date != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                value = dateFormat.format(date);
            }
        } catch (Exception e) {
            LOG.info("formatDate::Exception - " + e.getMessage(), e);
        }
        return value;
    }

    /**
     * @return Date
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 19-Sep-2019
     */
    public static Date parseDate(String value, String pattern) {
        Date date = new Date();
        if (DateUtility.nullCheck(value)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                date = dateFormat.parse(value);
            } catch (ParseException e) {
                LOG.info("parseDate::ParseException - " + e.getMessage(), e);
            } catch (Exception e) {
                LOG.info("parseDate::Exception - " + e.getMessage(), e);
            }
        }
        return date;
    }

    /**
     * @param string
     * @return true if string is not null and not empty
     */
    public static boolean nullCheck(String string) {
        boolean status = false;
        if (string != null && string.trim().compareTo("") != 0) {
            status = true;
        }
        return status;
    }

    /**
     * @param objects
     * @return
     */
    public static boolean nullCheck(Object[] objects) {
        boolean status = false;
        if (objects != null && objects.length > 0) {
            status = true;
        }
        return status;
    }

    public static boolean nullCheck(Object object) {
        boolean status = false;
        if (object != null) {
            status = true;
        }
        return status;
    }

    public static boolean nullCheck(List<?> list) {
        boolean status = false;
        if (list != null && list.size() > 0) {
            status = true;
        }
        return status;
    }

    public static boolean nullCheck(Map<?, ?> map) {
        boolean status = false;
        if (map != null && map.size() > 0) {
            status = true;
        }
        return status;
    }

    public static boolean nullCheck(Set<?> set) {
        boolean status = false;
        if (set != null && set.size() > 0) {
            status = true;
        }
        return status;
    }

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 17-Sep-2019
     */
    public static Date concatDateAndTimeToDate(Date date, Time time, String pattern) {
        Calendar todayCal = Calendar.getInstance();
        if (date != null) {
            todayCal.setTime(date);
            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(time);
            todayCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
            todayCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
            todayCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
        } else {
            LOG.info("Date is empty or null");
        }
        return todayCal.getTime();
    }

    /**
     * @return String
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 17-Sep-2019
     */
    public static Time getCurrentTime(String pattern) {
        Time currentTime = null;
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String strCurrentTime = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":"
                + now.get(Calendar.SECOND);
            currentTime = new Time(sdf.parse(strCurrentTime).getTime());
        } catch (ParseException e) {
            LOG.info("getting current time " + e.getMessage());
        }
        return currentTime;
    }

    /**
     * @return List<Date> in between a startDate and endDate
     * @author Arun AP <arun.ap@vuelogix.com>
     * @since 22-Nov-2019
     */
    public static List<Date> getDatesBetweenDates(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }
}
