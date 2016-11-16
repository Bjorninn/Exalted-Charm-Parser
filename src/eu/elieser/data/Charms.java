package eu.elieser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjorn on 20/04/16.
 */
public class Charms
{
    private final List<Charm> charms;

    public Charms(List<Charm> charms)
    {
        this.charms = new ArrayList<>(charms.size());
        this.charms.addAll(charms);
    }

    public Charms()
    {
        charms = new ArrayList<>();
    }

    public List<Charm> getCharms()
    {
        return charms;
    }

    public void setCharms(List<Charm> charms)
    {
        this.charms.clear();
        this.charms.addAll(charms);
    }
}
