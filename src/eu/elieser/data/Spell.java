package eu.elieser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bjorn on 11/15/2016.
 */
public class Spell
{
    private String name;
    private String circle;
    private String cost;
    private String duration;
    private List<String> keywords;
    private String description;
    private String distortion;

    public Spell()
    {
        keywords = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCircle()
    {
        return circle;
    }

    public void setCircle(String circle)
    {
        this.circle = circle;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDistortion()
    {
        return distortion;
    }

    public void setDistortion(String distortion)
    {
        this.distortion = distortion;
    }
}
