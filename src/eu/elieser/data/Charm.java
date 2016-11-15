package eu.elieser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjorn on 20/04/16.
 */
public class Charm
{
    private String ability;
    private String name;
    private String cost;

    private Aspect minAbility;
    private Aspect minEssence;

    private String type;
    private String duration;
    private List<String> keywords;
    private List<String> prerequisiteCharms;
    private String description;

    public Charm()
    {
        keywords = new ArrayList<>();
        prerequisiteCharms = new ArrayList<>();
    }

    public String getAbility()
    {
        return ability;
    }

    public void setAbility(String ability)
    {
        this.ability = ability;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public List<String> getKeywords()
    {
        return keywords;
    }

    public void setKeywords(List<String> keywords)
    {
        this.keywords.addAll(keywords);
    }

    public List<String> getPrerequisiteCharms()
    {
        return prerequisiteCharms;
    }

    public void setPrerequisiteCharms(List<String> prerequisiteCharms)
    {
        this.prerequisiteCharms.addAll(prerequisiteCharms);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Aspect getMinAbility()
    {
        return minAbility;
    }

    public void setMinAbility(Aspect minAbility)
    {
        this.minAbility = minAbility;
    }

    public Aspect getMinEssence()
    {
        return minEssence;
    }

    public void setMinEssence(Aspect minEssence)
    {
        this.minEssence = minEssence;
    }

    @Override
    public String toString()
    {
        return "Charm{" +
                "ability='" + ability + '\'' +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", minAbility=" + minAbility +
                ", minEssence=" + minEssence +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", keywords=" + keywords +
                ", prerequisiteCharms=" + prerequisiteCharms +
                ", description='" + description + '\'' +
                '}';
    }
}