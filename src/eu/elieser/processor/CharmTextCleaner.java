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
}
