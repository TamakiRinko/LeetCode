package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataStructure.UndirectedGraphNode;

public class CloneGraph {
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if(node == null){
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // 利用nodes+start来模拟queue，因为queue中弹出后还需要保存着，后续克隆边时使用
        List<UndirectedGraphNode> nodes = new ArrayList<>();

        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));

        cloneNodes(nodes, map);
        cloneEdges(nodes, map);

        return map.get(node);
    }

    public void cloneNodes(List<UndirectedGraphNode> nodes, Map<UndirectedGraphNode, UndirectedGraphNode> map){
        int start = 0;
        while(start < nodes.size()){
            UndirectedGraphNode cur = nodes.get(start++);
            for(UndirectedGraphNode neighbor: cur.neighbors){
                if(!map.containsKey(neighbor)){
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    nodes.add(neighbor);
                }
            }
        }
    }

    public void cloneEdges(List<UndirectedGraphNode> nodes, Map<UndirectedGraphNode, UndirectedGraphNode> map){
        for(UndirectedGraphNode node: nodes){
            UndirectedGraphNode newNode = map.get(node);
            for(UndirectedGraphNode neighbor: node.neighbors){
                UndirectedGraphNode newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
    }

}
