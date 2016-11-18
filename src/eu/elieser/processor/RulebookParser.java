package eu.elieser.processor;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.elieser.Log;
import eu.elieser.data.*;
import eu.elieser.reader.ReadWriteTextFileJDK7;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bjorn on 11/16/2016.
 */
@SuppressWarnings("WeakerAccess")
public class RulebookParser
{
    private static final String CORE_RULEBOOK_SOLAR_CHARMS_FILE_NAME = "data/charms.txt";
    //private static final String CORE_RULEBOOK_SOLAR_CHARMS_OUTPUT_FILE_NAME = "data/charms_clean.txt";
    private static final String SOLAR_CHARMS_JSON_FILE_NAME = "data/charms_json";

    private static final String MA_FILE_NAME = "data/ma_charms.txt";
    //private static final String MA_FILE_OUTPUT_NAME = "data/ma_charms_clean.txt";
    private static final String MA_JSON_FILE_NAME = "data/ma_charms_json";

    private static final String SPELL_FILE_NAME = "data/spells.txt";
    //private static final String SPELL_FILE_OUTPUT_NAME = "data/spells_clean.txt";
    private static final String SPELL_JSON_FILE_NAME = "data/spells_json";

    private static final String MOTSE_SOLAR_CHARMS_FILE_NAME = "data/charms_motse.txt";
    //private static final String CORE_RULEBOOK_SOLAR_CHARMS_OUTPUT_FILE_NAME = "data/charms_clean.txt";
    private static final String MOTSE_SOLAR_CHARMS_JSON_FILE_NAME = "data/charms_motse_json";

    private final ReadWriteTextFileJDK7 text;
    private final CharmSplitter splitter;

    public RulebookParser()
    {
        splitter = new CharmSplitter();
        text = new ReadWriteTextFileJDK7();
    }

    //----------------------------------------
    // SOLAR CHARMS
    //----------------------------------------

    public Charms processCoreRulebookSolarCharms()
    {
        List<String> lines = loadRulebookText(CORE_RULEBOOK_SOLAR_CHARMS_FILE_NAME);
        Map<String, Integer> charmPages = getCharmPages(lines, 255);
        lines = cleanCoreRulebookSolarCharmsText(lines);
        Charms charms = parseCoreRulebookSolarCharmsText(lines);

        for (Charm charm :
                charms.getCharms())
        {
            charm.setPage(charmPages.get(charm.getName()));
            charm.setSource(SourceBook.CORE);
        }

        String json = exportToJson(charms);
        save(json, SOLAR_CHARMS_JSON_FILE_NAME);

        return charms;
    }

    public List<String> cleanCoreRulebookSolarCharmsText(List<String> lines)
    {
        lines = CharmTextCleaner.removeHeader(lines);
        lines = CharmTextCleaner.cleanEssence(lines);
        lines = CharmTextCleaner.manualWork(lines);

        return lines;
    }

    public Charms parseCoreRulebookSolarCharmsText(List<String> lines)
    {
        return splitter.splitCharms(lines);
    }

    //----------------------------------------
    // MARTIAL ARTS CHARMS
    //----------------------------------------

    public MartialArtsCharms processCoreRulebookMartialArtsCharms()
    {
        List<String> lines = loadRulebookText(MA_FILE_NAME);
        Map<String, Integer> charmPages = getCharmPages(lines, 427);
        lines = cleanCoreRulebookMartialArtsCharmsText(lines);
        MartialArtsCharms charms = parseCoreRulebookMartialArtsCharmsText(lines);

        for (Charm charm :
                charms.getCharms())
        {
            charm.setPage(charmPages.get(charm.getName()));
            charm.setSource(SourceBook.CORE);
        }

        String json = exportToJson(charms);
        save(json, MA_JSON_FILE_NAME);

        return charms;
    }

    public List<String> cleanCoreRulebookMartialArtsCharmsText(List<String> lines)
    {
        lines = CharmTextCleaner.removeMartialArtsHeader(lines);
        lines = CharmTextCleaner.cleanEssence(lines);
        lines = CharmTextCleaner.manualMartialArtsWork(lines);

        return lines;
    }

    public MartialArtsCharms parseCoreRulebookMartialArtsCharmsText(List<String> lines)
    {
        return splitter.splitMartialArtsCharms(lines);
    }

    //----------------------------------------
    // SPELLS
    //----------------------------------------

    public Spells processCoreRulebookSpells()
    {
        List<String> lines = loadRulebookText(SPELL_FILE_NAME);
        Map<String, Integer> spellPages = getCharmPages(lines, 471);
        lines = cleanCoreRulebookSpellText(lines);
        Spells spells = parseCoreRulebookSpellText(lines);

        for (Spell spell :
                spells.getSpells())
        {
            spell.setPage(spellPages.get(spell.getName()));
            spell.setSource(SourceBook.CORE);
        }

        String json = exportToJson(spells);
        save(json, SPELL_JSON_FILE_NAME);

        return spells;
    }

    public List<String> cleanCoreRulebookSpellText(List<String> lines)
    {
        lines = CharmTextCleaner.removeSpellHeader(lines);
        lines = CharmTextCleaner.manualSpellWork(lines);

        return lines;
    }

    public Spells parseCoreRulebookSpellText(List<String> lines)
    {
        return splitter.splitSpells(lines);
    }

    //----------------------------------------
    // SOLAR CHARMS FROM MIRACLES OF THE SOLAR EXALTED
    //----------------------------------------

    public Charms processMotseSolarCharms()
    {
        List<String> lines = loadRulebookText(MOTSE_SOLAR_CHARMS_FILE_NAME);
        Map<String, Integer> charmPages = getCharmPages(lines, 5);
        lines = cleanMotseSolarCharmsText(lines);
        Charms charms = parseMotseSolarCharmsText(lines);

        for (Charm charm :
                charms.getCharms())
        {
            charm.setPage(charmPages.get(charm.getName()));
            charm.setSource(SourceBook.MIRACLES_OF_THE_SOLAR_EXALTED);
        }

        String json = exportToJson(charms);
        save(json, MOTSE_SOLAR_CHARMS_JSON_FILE_NAME);

        return charms;
    }

    public List<String> cleanMotseSolarCharmsText(List<String> lines)
    {
        lines = CharmTextCleaner.removeHeader(lines, "New S olar Charms", "M I R AC L E S O F T H E S O L A R E X A LT E D");
        lines = CharmTextCleaner.cleanEssence(lines);
        lines = CharmTextCleaner.fixLongCharmNamesMotse(lines);

        return lines;
    }

    public Charms parseMotseSolarCharmsText(List<String> lines)
    {
        return splitter.splitCharms(lines);
    }


    //----------------------------------------
    // UTIL FUNCTIONS
    //----------------------------------------

    public List<String> loadRulebookText(String path)
    {
        return text.readTextFile(path);
    }

    public String exportToJson(Object object)
    {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
        return gson.toJson(object);
    }

    private void save(String text, String path)
    {
        this.text.write(text, path);
    }

    public Map<String, Integer> getCharmPages(List<String> charmText, int firstPage)
    {
        Map<String, Integer> pages = new HashMap<>();

        int currentPage = firstPage;

        for (int i = 0; i < charmText.size(); i++)
        {
            String line = charmText.get(i);

            if (StringUtils.isNumeric(line))
            {
                // We found a new page
                currentPage = Integer.valueOf(line);
                continue;
            }

            if (line.contains("Cost:"))
            {
                String name = charmText.get(i - 1);
                pages.put(name, currentPage);
            }
        }

        return pages;
    }
}
