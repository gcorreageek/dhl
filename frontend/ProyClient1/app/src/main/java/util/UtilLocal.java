package util;

/**
 * Created by Gustavo on 29/11/16.
 */

public class UtilLocal {






    public static String getShortNameLanguaje(String languaje){
        if("English".equals(languaje)){
            return "en";
        }else if("Español".equals(languaje)){
            return "es";
        }else if("Português".equals(languaje)){
            return "pt_br";
        }else{
            return "pt_br";
        }
    }
}
