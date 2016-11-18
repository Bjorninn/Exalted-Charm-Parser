package eu.elieser.processor;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjorn on 20/04/16.
 */
public class CharmTextCleaner
{
    public static List<String> removeHeader(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        int clock = -1;

        for (String line : charmText)
        {
            if (clock < 0)
            {
                if (line.equals("CHARMS"))
                {
                    clock = 1;
                }
                else if (line.equals("C H A P T E R 6"))
                {
                    clock = 1;
                }
                else
                {
                    cleanedCharmText.add(line);
                }
            }
            else
            {
                clock--;
            }
        }

        return cleanedCharmText;
    }

    public static List<String> removeHeader(List<String> charmText, String header1, String header2)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        int clock = -1;

        for (String line : charmText)
        {
            if (clock < 0)
            {
                if (line.equals(header1))
                {
                    clock = 1;
                }
                else if (line.equals(header2))
                {
                    clock = 1;
                }
                else
                {
                    cleanedCharmText.add(line);
                }
            }
            else
            {
                clock--;
            }
        }

        return cleanedCharmText;
    }

    public static List<String> cleanEssence(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        Pattern pattern = Pattern.compile(".+ Essence \\d:");
        Pattern pattern2 = Pattern.compile(".+ Essence \\d;");

        for (String line : charmText)
        {
            Matcher matcher = pattern.matcher(line);
            boolean b = matcher.matches();

            if (b)
            {
                line = StringUtils.chop(line);
            }

            matcher = pattern2.matcher(line);
            b = matcher.matches();

            if (b)
            {
                line = StringUtils.chop(line);
            }

            cleanedCharmText.add(line);
        }

        return cleanedCharmText;
    }

    public static List<String> fixLongCharmNamesMotse(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        String a1 = "Victorious Wreath";
        String a2 = "(Against the World Stance)";

        String b1 = "Voice-Empowering Aspect";
        String b2 = "(Aspect-Imbued Voice)";

        String c1 = "Divinity-Conferring Touch";
        String c2 = "(Celestial Exaltation Method)";

        for (int i = 0; i < charmText.size(); i++)
        {
            String line = charmText.get(i);

            if (line.trim().equals(a1))
            {
                line = line.trim() + " " + a2.trim();
                i++;
            }
            else if (line.trim().equals(b1))
            {
                line = line.trim() + " " + b2.trim();
                i++;
            }
            else if (line.trim().equals(c1))
            {
                line = line.trim() + " " + c2.trim();
                i++;
            }
            else if (line.equals("Mins: Investigation 3, Essence 1, Type: Supplemental"))
            {
                cleanedCharmText.add("Mins: Investigation 3, Essence 1");
                line = "Type: Supplemental";
            }

            cleanedCharmText.add(line);
        }



        return cleanedCharmText;
    }

    public static List<String> manualWork(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        String a1 = "Cost: 25sxp, 15gxp, 10wxp + all remaining wxp; Mins:";
        String a2 = "Craft 5, Essence 5";

        String b1 = "Cost: 4m per damage removed; Mins: Dodge 5,";
        String b2 = "Essence 2";

        String c1 = "Cost: -1 Initiative per success; Mins: Larceny 4,";
        String c2 = "Essence 2";

        String d1 = "Cost: 4m, 1wp, plus 3m per language; Mins: Linguistics";
        String d2 = "5, Essence 1";

        String e1 = "Cost: 6m; Mins: Linguistics 5,";
        String e2 = "Essence 3";

        String f1 = "Cost: 1m per work; Mins: Linguistics";
        String f2 = "5, Essence 3";

        String g1 = "Cost: 1i per success +4m or 4m or 4m, 1wp; Mins: Melee";
        String g2 = "5, Essence 2";

        String h1 = "Cost: 1m per damage die removed; Mins: Resistance 2,";
        String h2 = "Essence 1";

        for (int i = 0; i < charmText.size(); i++)
        {
            String line = charmText.get(i);

            if (line.equals(a1))
            {
                line = a1 + " " + a2;
                i++;
            }
            else if (line.equals(b1))
            {
                line = b1 + " " + b2;
                i++;
            }
            else if (line.equals(c1))
            {
                line = c1 + " " + c2;
                i++;
            }
            else if (line.equals(d1))
            {
                line = d1 + " " + d2;
                i++;
            }
            else if (line.equals(e1))
            {
                line = e1 + " " + e2;
                i++;
            }
            else if (line.equals(f1))
            {
                line = f1 + " " + f2;
                i++;
            }
            else if (line.equals(g1))
            {
                line = g1 + " " + g2;
                i++;
            }
            else if (line.equals(h1))
            {
                line = h1 + " " + h2;
                i++;
            }
            else if (line.equals("WHEN DO I NEED TO AIM?"))
            {
                i = i + 19;
                continue;
            }
            else if (line.equals("ON TEN OX MEDITATION"))
            {
                i = i + 15;
                continue;
            }
            else if (line.equals("ON SURPRISE ANTICIPATION METHOD"))
            {
                i = i + 10;
                continue;
            }
            else if (line.equals("SPACE-SAVING CONCESSION"))
            {
                i = i + 11;
                continue;
            }
            else if (line.equals("FELLING GIGANTIC FOES"))
            {
                i = i + 25;
                continue;
            }
            else if (line.equals("ON THUNDERCLAP RUSH ATTACK"))
            {
                i = i + 6;
                continue;
            }
            else if (line.equals("AN EXAMPLE OF FALLING HAMMER STRIKE"))
            {
                i = i + 7;
                continue;
            }
            else if (line.equals("ON POINT-GENERATING CHARMS"))
            {
                i = i + 13;
                continue;
            }
            else if (line.equals("ON HUNDRED SHADOW WAYS"))
            {
                i = i + 9;
                continue;
            }
            else if (line.equals("ON DESTINY-MANIFESTING METHOD"))
            {
                i = i + 20;
                continue;
            }
            else if (line.equals("If a spirit recognizes the Lawgiver as"))
            {
                i = i + 12;
                continue;
            }
            else if (line.equals("ON DIVINE MANTLE"))
            {
                i = i + 22;
                continue;
            }
            else if (line.equals("ON MASTER PLAN MEDITATION"))
            {
                i = i + 31;
                continue;
            }
            else if (line.equals("WRITTEN VS. SPOKEN EFFECTS"))
            {
                i = i + 23;
                continue;
            }
            else if (line.equals("ARTISTIC TALENT: LINGUISTICS OR CRAFT?"))
            {
                i = i + 18;
                continue;
            }
            else if (line.equals("THAT BURNING QUESTION"))
            {
                i = i + 3;
                continue;
            }
            else if (line.equals("ON TWISTED WORDS TECHNIQUE"))
            {
                i = i + 29;
                continue;
            }
            else if (line.equals("FATE OF THE MIDDLEMARCHES"))
            {
                i = i + 12;
                continue;
            }
            else if (line.equals("ON UNSTOPPABLE MAGNUS APPROACH"))
            {
                i = i + 6;
                continue;
            }
            else if (line.equals("ON FIVEFOLD BULWARK STANCE"))
            {
                i = i + 13;
                continue;
            }
            else if (line.equals("SCENE OF DESTRUCTION"))
            {
                i = i + 3;
                continue;
            }
            else if (line.equals("DON’T GET STAGE FRIGHT"))
            {
                i = i + 3;
                continue;
            }
            else if (line.equals("ON INFECTIOUS ZEALOTRY APPROACH"))
            {
                i = i + 7;
                continue;
            }
            else if (line.equals("REMEMBER THE RED RULE!"))
            {
                i = i + 8;
                continue;
            }
            else if (line.equals("EXAMPLE HAZARD"))
            {
                i = i + 12;
                continue;
            }
            else if (line.equals("SOCIETAL INFLUENCE AND GROUP DYNAMICS"))
            {
                i = i + 11;
                continue;
            }
            else if (line.equals("PERSONAS AND LIMIT BREAK"))
            {
                i = i + 3;
                continue;
            }
            else if (line.equals("PERSONAS: RULES AND LIMITATIONS"))
            {
                i = i + 46;
                continue;
            }
            else if (line.equals("ON VANISHING FROM MIND’S EYE METHOD"))
            {
                i = i + 4;
                continue;
            }
            else if (line.equals("ON SPIRIT-TIED PET"))
            {
                i = i + 2;
                continue;
            }
            else if (line.equals("ON BESTIAL TRAITS TECHNIQUE"))
            {
                i = i + 2;
                continue;
            }
            else if (line.equals("A FAMILIAR INVESTMENT"))
            {
                i = i + 7;
                continue;
            }
            else if (line.equals("ON SAGA BEAST VIRTUE"))
            {
                i = i + 2;
                continue;
            }
            else if (line.equals("ELECTED TARGETS"))
            {
                i = i + 12;
                continue;
            }
            else if (line.contains("Cost:—;"))
            {
                line = line.replace("Cost:—;", "Cost: —;");
            }

            cleanedCharmText.add(line);
        }

        return cleanedCharmText;
    }

    public static List<String> manualMartialArtsWork(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        String a1 = "Cost: 4m, 1 charge per success; Mins: Martial Arts 5,";
        String a2 = "Essence 3";

        String b1 = "Cost: 3m, 1 charge per success; Mins: Martial Arts 5,";
        String b2 = "Essence 3";

        for (int i = 0; i < charmText.size(); i++)
        {
            String line = charmText.get(i);

            if (line.equals(a1))
            {
                line = a1 + " " + a2;
                i++;
            }
            else if (line.equals(b1))
            {
                line = b1 + " " + b2;
                i++;
            }
            else if (line.equals("MIXING STYLES"))
            {
                i += 8;
                continue;
            }
            else if (line.equals("SILVER-VOICED NIGHTINGALE REMIX"))
            {
                i += 10;
                continue;
            }
            else if (line.equals("…WHAT?"))
            {
                i += 19;
                continue;
            }

            cleanedCharmText.add(line);
        }

        return cleanedCharmText;
    }

    public static List<String> removeMartialArtsHeader(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        int clock = -1;

        for (String line : charmText)
        {
            if (clock < 0)
            {
                if (line.equals("MART I A L A RT S A N D S O R C E RY"))
                {
                    clock = 1;
                }
                else if (line.equals("C H A P T E R 7"))
                {
                    clock = 1;
                }
                else
                {
                    cleanedCharmText.add(line);
                }
            }
            else
            {
                clock--;
            }
        }

        return cleanedCharmText;
    }

    public static List<String> removeSpellHeader(List<String> spellText)
    {
        return removeMartialArtsHeader(spellText);
    }

    public static List<String> manualSpellWork(List<String> charmText)
    {
        List<String> cleanedCharmText = new ArrayList<>(charmText.size());

        for (int i = 0; i < charmText.size(); i++)
        {
            String line = charmText.get(i);

            if (line.equals("EXAMPLE: TWO SORCERERS"))
            {
                i += 24;
                continue;
            }
            else if (line.equals("THE RIGHT DEMON FOR THE JOB"))
            {
                i += 4;
                continue;
            }
            else if (line.equals("SUMMONING AND BATTLE GROUPS"))
            {
                i += 7;
                continue;
            }
            else if (line.equals("NO SHORTCUTS IN HELL"))
            {
                i += 15;
                continue;
            }

            cleanedCharmText.add(line);
        }

        return cleanedCharmText;
    }
}
