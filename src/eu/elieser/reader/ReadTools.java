package eu.elieser.reader;

import eu.elieser.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by bjorn on 20/04/16.
 */
public class ReadTools
{
    public static void findFolder()
    {
        File folder = new File("data/");

        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles)
        {
            if (listOfFile.isFile())
            {
                System.out.println("File " + listOfFile.getName());
            }
            else if (listOfFile.isDirectory())
            {
                System.out.println("Directory " + listOfFile.getName());
            }
        }
    }
}
