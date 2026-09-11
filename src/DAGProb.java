import java.util.*;

public class DAGProb {
    private int[] arrNums;
    private int[] arrPath;
    private int source;
    private int sink;
    private ArrayList<int[]> graph;
    private ArrayList<Integer> order;
    public DAGProb(ArrayList<int[]> tempGraph, int start, int end){
        order = topologicalOrdering(tempGraph);
        graph = tempGraph;
        arrNums = new int[tempGraph.size()];
        arrPath = new int[tempGraph.size()];
        sink = end;
        source = start;
    }
    public int LongestPath(){
        ArrayList<Integer> check = new ArrayList<>();
        for(int i = 0; i < graph.size(); i++){
            arrNums[i] = -100000; //min
            arrPath[i] = -1;
        }

        arrNums[source] = 0;
        for (int i: order) {
            for(int[] edge: graph){
                if(edge[0] == i){
                    int node = edge[1];
                    int weight = edge[2];
                    if (arrNums[i] + weight > arrNums[node]){
                        arrPath[node] = i;
                        arrNums[node] = arrNums[i] + weight;
                    }
                }
            }
        }
        return arrNums[sink];
    }

    public String getPath() {
        ArrayList<Integer> path = new ArrayList<>();
        int curr = sink;
        while (curr != -1) {
            path.add(curr);
            curr = arrPath[curr];
        }

        Collections.reverse(path);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            ans.append(path.get(i));
            if (i < path.size() - 1) {
                ans.append("->");
            }
        }
        return ans.toString();
    }

    /*public ArrayList<Integer> topologicalOrdering(ArrayList<int[]> tempGraph){
        ArrayList<Integer> l =  new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<>(); // nodes with no incomming edges
        ArrayList<Integer> nonS = new ArrayList<>();
        for(int[] ints: tempGraph){
            nonS.add(ints[1]);
        }
        for(int[] ints: tempGraph){
            if(!nonS.contains(ints[0])){
                s.add(ints[0]);
            }
        }
        while(!s.isEmpty()){
            int n = s.removeFirst();
            l.add(n);
            ArrayList<int[]> removables = new ArrayList<>();
            for(int[] ints: tempGraph){
                if(ints[0] == n) {
                    removables.add(ints);
                }
            }
            int count = 1;
            for(int[] ints: removables){
                tempGraph.remove(ints);
                if(count == removables.size()){
                    s.add(ints[1]);
                }

                else{
                    count++;
                }
            }
        }
        return l;
    }*/
    public ArrayList<Integer> topologicalOrdering(ArrayList<int[]> edges){
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, n -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, n -> new ArrayList<>());
            inDegree.put(u, inDegree.getOrDefault(u, 0));
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry: inDegree.entrySet()) {// Add nodes with in degree 0
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            result.add(u);
            if (adj.containsKey(u)) {
                for (int v : adj.get(u)) {
                    inDegree.put(v, inDegree.get(v) - 1);
                    if (inDegree.get(v) == 0) {
                        q.add(v);
                    }
                }
            }
        }
        return result;
    }
}