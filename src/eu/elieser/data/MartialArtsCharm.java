package eu.elieser.data;

/**
 * Created by bjorn on 24/04/16.
 */
public class MartialArtsCharm extends Charm
{
    private String martialArtStyle;
    private String mastery;
    private String terrestrial;
    private String specialActivationRules;

    public String getMartialArtStyle()
    {
        return martialArtStyle;
    }

    public void setMartialArtStyle(String martialArtStyle)
    {
        this.martialArtStyle = martialArtStyle;
    }

    public String getMastery()
    {
        return mastery;
    }

    public void setMastery(String mastery)
    {
        this.mastery = mastery;
    }

    public String getTerrestrial()
    {
        return terrestrial;
    }

    public void setTerrestrial(String terrestrial)
    {
        this.terrestrial = terrestrial;
    }

    public String getSpecialActivationRules()
    {
        return specialActivationRules;
    }

    public void setSpecialActivationRules(String specialActivationRules)
    {
        this.specialActivationRules = specialActivationRules;
    }
}
