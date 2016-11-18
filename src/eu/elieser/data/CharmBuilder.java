package eu.elieser.data;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjorn on 20/04/16.
 */
public class CharmBuilder
{
    private static final boolean CONCAT_DESCRIPTION_LINES = false;

    private String ability;
    private String name;
    private String cost;
    private List<Aspect> mins;
    private String type;
    private String duration;
    private List<String> keywords;
    private List<String> prerequisiteCharms;
    private List<String> description;

    private String martialArtStyle;
    private List<String> mastery;
    private List<String> terrestrial;
    private List<String> specialActivation;

    public CharmBuilder()
    {
        mins = new ArrayList<>();
        keywords = new ArrayList<>();
        prerequisiteCharms = new ArrayList<>();
        description = new ArrayList<>();

        mastery = new ArrayList<>();
        terrestrial = new ArrayList<>();
        specialActivation = new ArrayList<>();
    }

    public Charm build()
    {
        Charm charm = new Charm();
        build(charm);

        clear();

        return charm;
    }

    public MartialArtsCharm buildMa()
    {
        MartialArtsCharm charm = new MartialArtsCharm();
        build(charm);

        charm.setMartialArtStyle(martialArtStyle);
        charm.setTerrestrial(concatList(this.terrestrial));
        charm.setMastery(concatList(this.mastery));

        clear();

        return charm;
    }

    private void build(Charm charm)
    {
        charm.setAbility(ability);
        charm.setName(name);
        charm.setCost(cost);
        charm.setType(type);
        charm.setKeywords(keywords);
        charm.setDuration(duration);
        charm.setPrerequisiteCharms(prerequisiteCharms);

        for (Aspect aspect : mins)
        {
            if (aspect.getName().equals("Essence"))
            {
                charm.setMinEssence(aspect);
            }
            else
            {
                charm.setMinAbility(aspect);
            }
        }

        charm.setDescription(concatList(this.description));
        charm.setSpecialActivation(concatList(specialActivation));
    }

    private String concatList(List<String> strings)
    {
        String string;

        if (CONCAT_DESCRIPTION_LINES)
        {
            string = StringUtils.join(strings, " ");
        }
        else
        {
            string = StringUtils.join(strings, "\n");
        }

        return string.trim();
    }

    private void clear()
    {
        ability = null;
        name = null;
        cost = null;
        mins.clear();
        type = null;
        keywords.clear();
        prerequisiteCharms.clear();
        description.clear();

        martialArtStyle = null;
        terrestrial.clear();
        mastery.clear();
        specialActivation.clear();
    }

    public void setAbility(String ability)
    {
        this.ability = ability;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public void setMins(List<Aspect> mins)
    {
        this.mins.addAll(mins);
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setKeywords(List<String> keywords)
    {
        for (String string :
                keywords)
        {
            this.keywords.add(string.trim());
        }
    }

    public void setPrerequisiteCharms(List<String> prerequisiteCharms)
    {
        for (String string :
                prerequisiteCharms)
        {
            this.prerequisiteCharms.add(string.trim());
        }
    }

    public void addDescriptionLine(String description)
    {
        this.description.add(description);
    }

    public void removeLastDescriptionLine()
    {
        this.description.remove(description.size() - 1);
    }

    public void removeLastTerrestrialLine()
    {
        this.terrestrial.remove(terrestrial.size() - 1);
    }

    public void removeLastMasteryLine()
    {
        this.mastery.remove(mastery.size() - 1);
    }

    public void removeLastSpecialActivationLine()
    {
        this.specialActivation.remove(specialActivation.size() - 1);
    }

    public void setMartialArtStyle(String martialArtStyle)
    {
        this.martialArtStyle = martialArtStyle;
    }

    public void addTerrestrialLine(String line)
    {
        this.terrestrial.add(line);
    }

    public void addMasteryLine(String line)
    {
        this.mastery.add(line);
    }

    public void addSpecialActivationLine(String line)
    {
        this.specialActivation.add(line);
    }
}
