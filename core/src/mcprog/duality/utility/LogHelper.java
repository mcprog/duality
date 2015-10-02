package mcprog.duality.utility;

import com.badlogic.gdx.Gdx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/1/2015.
 */
public class LogHelper {

    public static String getClassyTag(Object aClass) {
        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = formatter.format(date);
        return "[" + time + "] " + Reference.DUALITY_ID + " > " + aClass.getClass().getName();
    }
}
