package eu.elieser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjorn on 24/04/16.
 */
public class MartialArtsCharms
{
    private List<MartialArtsCharm> charms;

    public MartialArtsCharms(List<MartialArtsCharm> charms)
    {
        this.charms = new ArrayList<>(charms.size());
        this.charms = charms;
    }

    public MartialArtsCharms()
    {
        charms = new ArrayList<>();
    }

    public List<MartialArtsCharm> getCharms()
    {
        return charms;
    }

    public void setCharms(List<MartialArtsCharm> charms)
    {
        this.charms.clear();
        this.charms.addAll(charms);
    }
}
