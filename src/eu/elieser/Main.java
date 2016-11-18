package eu.elieser;

import eu.elieser.data.Charms;
import eu.elieser.data.MartialArtsCharms;
import eu.elieser.data.Spells;
import eu.elieser.processor.RulebookParser;
import eu.elieser.reader.ReadWriteTextFileJDK7;
import eu.elieser.tree.CharmTree;

import java.util.Map;

public class Main
{
    private static ReadWriteTextFileJDK7 text;

    private static Map<String, Integer> charmPages;

    public static void main(String[] args)
    {
        RulebookParser rulebookParser = new RulebookParser();
        Charms solarCharms = rulebookParser.processCoreRulebookSolarCharms();
        MartialArtsCharms martialArtsCharms = rulebookParser.processCoreRulebookMartialArtsCharms();
        Spells spells = rulebookParser.processCoreRulebookSpells();

        Charms motseSolarCharms = rulebookParser.processMotseSolarCharms();

        CharmTree charmTree = new CharmTree();
        charmTree.construct(solarCharms);
    }
}
