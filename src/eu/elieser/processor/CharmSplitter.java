package eu.elieser.processor;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.elieser.Log;
import eu.elieser.data.*;

import java.util.*;

/**
 * Created by bjorn on 20/04/16.
 */
public class CharmSplitter
{
    private enum State
    {
        NULL,
        DISCOVERED,
        TYPE,
        KEYWORDS,
        DURATION,
        PREREQUISITES,
        DESCRIPTION,
        MASTERY,
        TERRESTRIAL,
        SPECIAL_ACTIVATION_RULES
    }

    private static final List<String> abilityList = Arrays.asList("Archery", "Athletics", "Awareness", "Brawl", "Bureaucracy",
            "Craft", "Dodge", "Integrity", "Investigation", "Larceny", "Linguistics", "Lore", "Martial Arts", "Medicine",
            "Melee", "Occult", "Performance", "Presence", "Resistance", "Ride", "Sail", "Socialize", "Stealth", "Survival", "Thrown", "War");

    private static final List<String> maList = Arrays.asList("Snake Style", "Tiger Style", "Single Point Shining Into the Void Style", "White Reaper Style", "Ebon Shadow Style",
            "Crane Style", "Silver-Voiced Nightingale Style", "Righteous Devil Style", "Black Claw Style", "Dreaming Pearl Courtesan Style", "Steel Devil Style");

    private static final String MARTIAL_ARTS = "Martial Arts";

    private final Set<String> abilitySet;
    private final Set<String> maSet;

    public CharmSplitter()
    {
        abilitySet = new HashSet<>(abilityList);
        maSet = new HashSet<>(maList);
    }

    public String toJson(Charms charms)
    {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
        return gson.toJson(charms);
    }

    public String toJson(MartialArtsCharms charms)
    {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
        return gson.toJson(charms);
    }

    public List<Charm> splitCharms(List<String> charmLines)
    {
        List<Charm> charms = new ArrayList<>();

        boolean isFirstCharm = true;
        String currentAbility = "";
        State state = State.NULL;
        CharmBuilder charmBuilder = new CharmBuilder();

        final int size = charmLines.size();

        for (int i = 0; i < size; i++)
        {
            String line = charmLines.get(i);

            // Check if this is the start of a new MA
            if (abilitySet.contains(line))
            {
                currentAbility = line;
                continue;
            }

            if (line.contains("Cost:") && line.contains("Mins:"))
            {
                // Stamp and approve charm if not first charm
                if (isFirstCharm)
                {
                    isFirstCharm = false;
                }
                else
                {
                    // Remove last description line
                    charmBuilder.removeLastDescriptionLine();

                    Charm charm = charmBuilder.build();
                    charms.add(charm);
                }

                state = State.DISCOVERED;
            }

            if (state == State.DISCOVERED)
            {
                // Set ability
                charmBuilder.setAbility(currentAbility);

                // Retrieve charm name
                String name = charmLines.get(i - 1);
                charmBuilder.setName(name);

                // Set cost and mins in new charm
                int index = line.indexOf("Mins: ");

                String cost = line.substring(0, index).replace("Cost: ", "").trim().replace(";", "");
                String mins = line.substring(index).replace("Mins: ", "").trim();

                charmBuilder.setCost(cost);

                List<Aspect> aspects = splitAspectString(mins);
                charmBuilder.setMins(aspects);

                // Change state
                state = State.TYPE;
            }
            else if (state == State.TYPE)
            {
                charmBuilder.setType(line.replace("Type: ", "").trim());

                state = State.KEYWORDS;
            }
            else if (state == State.KEYWORDS)
            {
                line = line.replace("Keywords: ", "").trim();

                if (!line.equals("None"))
                {
                    List<String> split = split(line);
                    charmBuilder.setKeywords(split);
                }

                state = State.DURATION;
            }
            else if (state == State.DURATION)
            {
                line = line.replace("Duration: ", "").trim();

                charmBuilder.setDuration(line);
                state = State.PREREQUISITES;
            }
            else if (state == State.PREREQUISITES)
            {
                line = line.replace("Prerequisite Charms:", "").trim();

                if (!line.equals("None"))
                {
                    boolean isSplit = checkPrerequisiteSplit(charmLines.get(i + 1));

                    // Prerequisites are split between two lines
                    if (isSplit)
                    {
                        String line2 = charmLines.get(i + 1).trim();
                        line = line + " " + line2;

                        // we need to increment i here or else the second prerequisite line will be added to the description.
                        i++;
                    }

                    List<String> split = split(line);

                    charmBuilder.setPrerequisiteCharms(split);
                }

                state = State.DESCRIPTION;
            }
            else if (state == State.DESCRIPTION)
            {
                charmBuilder.addDescriptionLine(line);
            }
        }

        return charms;
    }

    public List<MartialArtsCharm> splitMartialArtsCharms(List<String> charmLines)
    {
        List<MartialArtsCharm> charms = new ArrayList<>();

        boolean isFirstCharm = true;
        String currentFormType = "";
        State state = State.NULL;
        CharmBuilder charmBuilder = new CharmBuilder();

        final int size = charmLines.size();

        for (int i = 0; i < size; i++)
        {
            String line = charmLines.get(i);

            // Check if this is the start of a new MA
            if (maSet.contains(line))
            {
                currentFormType = line;
                continue;
            }

            if (line.contains("Cost:") && line.contains("Mins:"))
            {
                // Stamp and approve charm if not first charm
                if (isFirstCharm)
                {
                    isFirstCharm = false;
                }
                else
                {
                    // Remove last entered line
                    if (state == State.DESCRIPTION)
                    {
                        charmBuilder.removeLastDescriptionLine();
                    }
                    else if (state == State.TERRESTRIAL)
                    {
                        charmBuilder.removeLastTerrestrialLine();
                    }
                    else if (state == State.MASTERY)
                    {
                        charmBuilder.removeLastMasteryLine();
                    }
                    else if (state == State.SPECIAL_ACTIVATION_RULES)
                    {
                        charmBuilder.removeLastSpecialActivationLine();
                    }

                    MartialArtsCharm charm = charmBuilder.buildMa();
                    charms.add(charm);
                }

                charmBuilder.setMartialArtStyle(currentFormType);

                state = State.DISCOVERED;
            }

            if (state == State.DISCOVERED)
            {
                // Set ability
                charmBuilder.setAbility(MARTIAL_ARTS);

                // Retrieve charm name
                String name = charmLines.get(i - 1);
                charmBuilder.setName(name);

                // Set cost and mins in new charm
                int index = line.indexOf("Mins: ");
                String cost = line.substring(0, index).replace("Cost: ", "").trim().replace(";", "");
                String mins = line.substring(index).replace("Mins: ", "").trim();

                charmBuilder.setCost(cost);

                Log.d(name);
                Log.d(mins);

                List<Aspect> aspects = splitAspectString(mins);
                charmBuilder.setMins(aspects);

                // Change state
                state = State.TYPE;
            }
            else if (state == State.TYPE)
            {
                charmBuilder.setType(line.replace("Type: ", "").trim());

                state = State.KEYWORDS;
            }
            else if (state == State.KEYWORDS)
            {
                line = line.replace("Keywords: ", "").trim();

                if (!line.equals("None"))
                {
                    List<String> split = split(line);
                    charmBuilder.setKeywords(split);
                }

                state = State.DURATION;
            }
            else if (state == State.DURATION)
            {
                line = line.replace("Duration: ", "").trim();

                charmBuilder.setDuration(line);
                state = State.PREREQUISITES;
            }
            else if (state == State.PREREQUISITES)
            {
                line = line.replace("Prerequisite Charms:", "").trim();

                if (!line.equals("None"))
                {
                    boolean isSplit = checkPrerequisiteSplit(charmLines.get(i + 1));

                    // Prerequisites are split between two line
                    if (isSplit)
                    {
                        String line2 = charmLines.get(i + 1).trim();
                        line = line + " " + line2;

                        // we need to increment i here or else the second prerequisite line will be added to the description.
                        i++;
                    }

                    List<String> split = split(line);

                    charmBuilder.setPrerequisiteCharms(split);
                }

                state = State.DESCRIPTION;
            }
            else if (state == State.DESCRIPTION)
            {
                if (line.contains("Terrestrial: "))
                {
                    line = line.replace("Terrestrial: ", "").trim();
                    charmBuilder.addTerrestrialLine(line);
                    state = State.TERRESTRIAL;
                }
                else if (line.contains("Mastery: "))
                {
                    line = line.replace("Mastery: ", "").trim();
                    charmBuilder.addMasteryLine(line);
                    state = State.MASTERY;
                }
                else if (line.contains("Special activation rules: "))
                {
                    line = line.replace("Special activation rules: ", "").trim();
                    charmBuilder.addSpecialActivationLine(line);
                    state = State.SPECIAL_ACTIVATION_RULES;
                }
                else
                {
                    charmBuilder.addDescriptionLine(line);
                }
            }
            else if (state == State.TERRESTRIAL)
            {
                if (line.contains("Mastery: "))
                {
                    line = line.replace("Mastery: ", "").trim();
                    charmBuilder.addMasteryLine(line);
                    state = State.MASTERY;
                }
                else if (line.contains("Special activation rules: "))
                {
                    line = line.replace("Special activation rules: ", "").trim();
                    charmBuilder.addSpecialActivationLine(line);
                    state = State.SPECIAL_ACTIVATION_RULES;
                }
                else
                {
                    charmBuilder.addTerrestrialLine(line);
                }
            }
            else if (state == State.MASTERY)
            {
                if (line.contains("Terrestrial: "))
                {
                    line = line.replace("Terrestrial: ", "").trim();
                    charmBuilder.addTerrestrialLine(line);
                    state = State.TERRESTRIAL;
                }
                else if (line.contains("Special activation rules: "))
                {
                    line = line.replace("Special activation rules: ", "").trim();
                    charmBuilder.addSpecialActivationLine(line);
                    state = State.SPECIAL_ACTIVATION_RULES;
                }
                else
                {
                    charmBuilder.addMasteryLine(line);
                }
            }
            else if (state == State.SPECIAL_ACTIVATION_RULES)
            {
                if (line.contains("Terrestrial: "))
                {
                    line = line.replace("Terrestrial: ", "").trim();
                    charmBuilder.addTerrestrialLine(line);
                    state = State.TERRESTRIAL;
                }
                else if (line.contains("Mastery: "))
                {
                    line = line.replace("Mastery: ", "").trim();
                    charmBuilder.addMasteryLine(line);
                    state = State.MASTERY;
                }
                else
                {
                    charmBuilder.addSpecialActivationLine(line);
                }
            }
        }

        return charms;
    }

    private boolean checkPrerequisiteSplit(String string)
    {
        int length = string.length();
        int index = string.indexOf(".");

        return length < 45 && index == -1;
    }

    private List<String> split(String string)
    {
        String[] split = string.split(", ");

        return Arrays.asList(split);
    }

    private List<Aspect> splitAspectString(String string)
    {
        String[] split = string.split(", ");
        List<Aspect> aspects = new ArrayList<>();

        for (String aspectString : split)
        {
            Aspect aspect = createAspect(aspectString);

            aspects.add(aspect);
        }

        return aspects;
    }

    private Aspect createAspect(String aspectString)
    {
        aspectString = aspectString.trim();
        String name = aspectString.substring(0, aspectString.length() - 1).trim();
        String number = aspectString.substring(aspectString.length() - 1).trim();

        Integer val = Integer.valueOf(number);

        Aspect aspect = new Aspect();
        aspect.setName(name);
        aspect.setValue(val);

        return aspect;
    }

}