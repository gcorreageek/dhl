package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gustavo on 4/12/16.
 */

public  class UtilDates {

    public static String convertDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
