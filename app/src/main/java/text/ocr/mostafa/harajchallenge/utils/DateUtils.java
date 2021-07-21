package text.ocr.mostafa.harajchallenge.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class DateUtils {

    private static final String TAG = "DateUtils";

    public static int getItemSinceStr(long endDate) {
        Calendar now = Calendar.getInstance();

        long diff = Math.abs(endDate - now.getTimeInMillis());

        System.out.println("endDate = " + endDate);
        System.out.println("now.getTimeInMillis() = " + now.getTimeInMillis());

        Log.e(TAG, "getItemSinceStr, diff: " + diff);
        Log.e(TAG, "getItemSinceStr: " + TimeUnit.MILLISECONDS.toMinutes(diff));
        return (int) TimeUnit.MILLISECONDS.toMinutes(diff);
    }

    public static Date getDateFromMilli(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.getTime();
    }
}
