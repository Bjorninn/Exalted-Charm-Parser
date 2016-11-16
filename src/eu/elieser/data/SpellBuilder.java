package eu.elieser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bjorn on 11/15/2016.
 */
public class SpellBuilder
{
    private String name;
    private String circle;
    private String cost;
    private String duration;
    private List<String> keywords;
    private List<String> description;
    private List<String> distortion;

    public SpellBuilder()
    {
        keywords = new ArrayList<>();
        description = new ArrayList<>();
        distortion = new ArrayList<>();
    }

    public Spell build()
    {
        Spell spell = new Spell();
        spell.setName(name);
        spell.setCircle(circle);
        spell.setCost(cost);
        spell.setDuration(duration);
        spell.setKeywords(keywords);

        String description = "";
        for (String string : this.description)
        {
            description += string + " ";
        }

        spell.setDescription(description);

        String distortion = "";
        for (String string : this.distortion)
        {
            distortion += string + " ";
        }

        spell.setDistortion(distortion);

        clear();

        return spell;
    }

    private void clear()
    {
        name = null;
        circle = null;
        cost = null;
        duration = null;

        keywords.clear();
        description.clear();
        distortion.clear();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCircle(String circle)
    {
        this.circle = circle.replace("Spells", "").trim();
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public void addDescriptionLine(String description)
    {
        this.description.add(description);
    }

    public void removeLastDescriptionLine()
    {
        this.description.remove(description.size() - 1);
    }

    public void addDistortionLine(String distortion)
    {
        this.distortion.add(distortion);
    }

    public void removeLastDistortionLine()
    {
        this.distortion.remove(distortion.size() - 1);
    }

    public void addKeyword(String keyword)
    {
        this.keywords.add(keyword.trim());
    }

    public void setKeywords(List<String> keywords)
    {
        for (String string : keywords)
        {
            this.keywords.add(string.trim());
        }
    }
}
