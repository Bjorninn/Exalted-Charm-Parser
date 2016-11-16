package eu.elieser.tree;

import eu.elieser.data.Charm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bjorn on 11/15/2016.
 */
public class Node
{
    private final Charm charm;
    private final List<Node> prerequisites;
    private final List<Node> unlocks;

    public Node(Charm charm)
    {
        this.charm = charm;
        prerequisites = new ArrayList<>();
        unlocks = new ArrayList<>();
    }

    public void addPrerequisite(Node node)
    {
        prerequisites.add(node);
    }

    public void addUnlock(Node node)
    {
        unlocks.add(node);
    }

    public Charm getCharm()
    {
        return charm;
    }

    public List<Node> getPrerequisites()
    {
        return prerequisites;
    }

    public List<Node> getUnlocks()
    {
        return unlocks;
    }

    public void print()
    {
        print("", true);
    }

    private void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + charm.getName());
        for (int i = 0; i < unlocks.size() - 1; i++)
        {
            unlocks.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (unlocks.size() > 0)
        {
            unlocks.get(unlocks.size() - 1)
                    .print(prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
