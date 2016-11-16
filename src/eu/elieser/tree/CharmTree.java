package eu.elieser.tree;

import eu.elieser.Log;
import eu.elieser.data.Charm;
import eu.elieser.data.Charms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bjorn on 11/15/2016.
 */
public class CharmTree
{
    private final List<Node> roots;
    private final Map<String, Node> nodes;

    private final Node root;

    public CharmTree()
    {
        Charm rootCharm = new Charm();
        rootCharm.setName("None");
        root = new Node(rootCharm);

        roots = new ArrayList<>();
        nodes = new HashMap<>();
    }

    public void construct(Charms charms)
    {
        roots.clear();
        nodes.clear();

        List<Charm> charmList = charms.getCharms();

        for (int i = 0; i < charmList.size(); i++)
        {
            Charm charm = charmList.get(i);

            Node node = new Node(charm);
            nodes.put(charm.getName(), node);

            if (charm.getPrerequisiteCharms().isEmpty())
            {
                roots.add(node);
            }
        }

        for (int i = 0; i < charmList.size(); i++)
        {
            Charm charm = charmList.get(i);

            Node node = nodes.get(charm.getName());

            List<String> prerequisiteCharms = charm.getPrerequisiteCharms();

            if (!prerequisiteCharms.isEmpty())
            {

                for (String preCharm : prerequisiteCharms)
                {
                    Node preNode = nodes.get(preCharm);

                    if (preNode == null)
                    {
                        Log.d("Unprocessable prereq '" + preCharm + "' in charm '" + charm.getName() + "'");
                        continue;
                    }

                    preNode.addUnlock(node);
                    node.addPrerequisite(preNode);
                }
            }
        }

        for (Node n :
                roots)
        {
            root.addUnlock(n);
        }
    }

    public void print()
    {
        root.print();
    }
}
