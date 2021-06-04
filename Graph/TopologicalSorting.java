package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import DataStructure.DirectedGraphNode;

public class TopologicalSorting {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        // 计算每个点的入度
        for(DirectedGraphNode node: graph){
            for(DirectedGraphNode neighbor: node.neighbors){
                map.merge(neighbor, 1, Integer::sum);
            }
        }
        // 添加入度为0的点
        for(DirectedGraphNode node: graph){
            if(!map.containsKey(node)){
                queue.offer(node);
                results.add(node);
            }
            // 如果node不在map中，(node, function(node))放入map中，类型不匹配
            // map.computeIfAbsent(node, queue::offer);
        }

        // 依次拿出入度为0的点，去除对于他们的依赖
        while(!queue.isEmpty()){
            DirectedGraphNode node = queue.poll();
            for(DirectedGraphNode neighbor: node.neighbors){
                map.put(neighbor, map.get(neighbor) - 1);
                if(map.get(neighbor) == 0){
                    results.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return results;
    }
}
