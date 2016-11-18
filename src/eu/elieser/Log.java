package eu.elieser;

import java.util.List;

/**
 * Created by bjorn on 20/04/16.
 */
public class Log
{
    public static void d(String msg)
    {
        System.out.println(msg);
    }

    public static void d(List<String> msg)
    {
        for (String string :
                msg)
        {
            System.out.println(string);
        }
    }
}
