package eu.elieser;

import eu.elieser.data.Charm;
import eu.elieser.data.Charms;
import eu.elieser.data.MartialArtsCharm;
import eu.elieser.data.MartialArtsCharms;
import eu.elieser.processor.CharmSplitter;
import eu.elieser.processor.CharmTextCleaner;
import eu.elieser.reader.ReadWriteTextFileJDK7;

import java.io.IOException;
import java.util.List;

public class Main
{

    private static final String SOLAR_CHARMS_FILE_NAME = "data/charms.txt";
    private static final String SOLAR_CHARMS_OUTPUT_FILE_NAME = "data/charms_clean.txt";
    private static final String SOLAR_CHARMS_JSON_FILE_NAME = "data/charms_json";

    private static final String MA_FILE_NAME = "data/ma_charms.txt";
    private static final String MA_FILE_OUTPUT_NAME = "data/ma_charms_clean.txt";
    private static final String MA_JSON_FILE_NAME = "data/ma_charms_json";

    private static ReadWriteTextFileJDK7 text;

    public static void main(String[] args)
    {

        try
        {
            text = new ReadWriteTextFileJDK7();

            cleanSolarCharmsText();
            generateSolarCharmJson();

            cleanMartialArtsText();
            generateMartialArtsJson();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void generateSolarCharmJson() throws IOException
    {
        List<String> lines = text.readTextFile(SOLAR_CHARMS_OUTPUT_FILE_NAME);

        CharmSplitter splitter = new CharmSplitter();
        List<Charm> charmList = splitter.splitCharms(lines);
        Charms charms = new Charms();
        charms.setCharms(charmList);

        String json = splitter.toJson(charms);
        text.write(json, SOLAR_CHARMS_JSON_FILE_NAME);
    }

    private static void cleanSolarCharmsText() throws IOException
    {
        List<String> lines = text.readTextFile(SOLAR_CHARMS_FILE_NAME);

        lines = CharmTextCleaner.removeHeader(lines);
        lines = CharmTextCleaner.cleanEssence(lines);
        lines = CharmTextCleaner.manualWork(lines);

        text.writeLargerTextFile(SOLAR_CHARMS_OUTPUT_FILE_NAME, lines);
    }

    private static void generateMartialArtsJson() throws IOException
    {
        List<String> lines = text.readTextFile(MA_FILE_OUTPUT_NAME);

        CharmSplitter splitter = new CharmSplitter();
        List<MartialArtsCharm> charmList = splitter.splitMartialArtsCharms(lines);
        MartialArtsCharms charms = new MartialArtsCharms();
        charms.setCharms(charmList);

        String json = splitter.toJson(charms);
        text.write(json, MA_JSON_FILE_NAME);
    }

    private static void cleanMartialArtsText() throws IOException
    {
        List<String> lines = text.readTextFile(MA_FILE_NAME);

        lines = CharmTextCleaner.removeMartialArtsHeader(lines);
        lines = CharmTextCleaner.cleanEssence(lines);
        lines = CharmTextCleaner.manualMartialArtsWork(lines);

        text.writeLargerTextFile(MA_FILE_OUTPUT_NAME, lines);
    }
}
