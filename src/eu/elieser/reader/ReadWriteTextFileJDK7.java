package eu.elieser.reader;

/*
 *  Copyright (c) 2002-2009, Hirondelle Systems
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  * Neither the name of Hirondelle Systems nor the
 *  names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY HIRONDELLE SYSTEMS ''AS IS'' AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL HIRONDELLE SYSTEMS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import eu.elieser.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * From http://www.javapractices.com/topic/TopicAction.do?Id=42
 * with some changes
 */
public class ReadWriteTextFileJDK7
{

    public ReadWriteTextFileJDK7()
    {

    }

    private static final Charset ENCODING = StandardCharsets.UTF_8;

    //For larger files
    public List<String> readTextFile(String fileName)
    {
        Path path = Paths.get(fileName);
        List<String> lines = new ArrayList<>(15000);

        if (Files.exists(path))
        {
            try (Scanner scanner = new Scanner(path, ENCODING.name()))
            {
                while (scanner.hasNextLine())
                {
                    //process each line in some way
                    //log(scanner.nextLine());
                    lines.add(scanner.nextLine());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Log.d("File: " + fileName + " not found.");
        }

        return lines;
    }

    void readLargerTextFileAlternate(String aFileName)
    {
        Path path = Paths.get(aFileName);
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                //process each line in some way
                log(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeLargerTextFile(String aFileName, List<String> aLines)
    {
        Path path = Paths.get(aFileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING))
        {
            for (String line : aLines)
            {
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void log(Object aMsg)
    {
        System.out.println(String.valueOf(aMsg));
    }

    public void write(String output, String filename)
    {
        try
        {
            Path file = Paths.get(filename + ".json");
            Files.write(file, Collections.singletonList(output), Charset.forName("UTF-16"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
