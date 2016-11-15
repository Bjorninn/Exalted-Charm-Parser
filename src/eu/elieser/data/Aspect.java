package eu.elieser.data;

/**
 * Created by bjorn on 20/04/16.
 */
public class Aspect
{
    private String name;
    private Integer value;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Aspect{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
