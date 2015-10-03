package mcprog.duality.utility;

import java.util.Date;
import mcprog.duality.library.Reference;

/**
 * Created by mcprog on 10/1/2015.
 * Helper class of static methods to assist in debug logging using <code>Gdx.app.log()</code>.
 */
public class LogHelper {

    /**
     * Returns a String containing a timestamp,
     * and the name of the class that this method was called from.
     * Uses Date and <code>System.currentTimeMillis()</code>.
     * When using, pass the object you are currently in (<code>this</code>) as <code>aClass</code>.
     * @param aClass the class that is using this method.
     * @return the formatted String.
     */
    public static String getClassyTag(Object aClass) {
        // Date works with GWT, SimpleDate, etc. do not.
        Date date = new Date(System.currentTimeMillis());
        return "[" + date + "] " + Reference.DUALITY_ID + " > " + aClass.getClass().getName();
    }
}
