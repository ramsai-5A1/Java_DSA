import java.util.* ;
import java.io.*; 

class Box {
	int destination;
	int weight;

	Box(int destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}
}

class BoxComparator implements Comparator<Box> {

	@Override
	public int compare(Box b1, Box b2) {
		return b1.weight - b2.weight;
	}
}

public class Solution {
	
	public static ArrayList < Integer > dijkstra(ArrayList< ArrayList < Integer > > vec, int vertices, int edges, int source){
		ArrayList<Integer> result = new ArrayList<>();
		ArrayList<ArrayList<Box>> adj = new ArrayList<>();

		for(int i = 0; i < vertices; i++) {
			result.add(Integer.MAX_VALUE);
			adj.add(new ArrayList<Box>());
		}

		
		for(int i = 0; i < edges; i++) {
			int u = vec.get(i).get(0);
			int v = vec.get(i).get(1);
			int weight = vec.get(i).get(2);
			adj.get(u).add(new Box(v, weight));
			adj.get(v).add(new Box(u, weight));
		}

		PriorityQueue<Box> minHeap = new PriorityQueue<>(new BoxComparator());
		minHeap.add(new Box(source, 0));
		while (!minHeap.isEmpty()) {
			Box curr = minHeap.poll();
			if (result.get(curr.destination) < curr.weight)	continue;
			//System.out.println(curr.destination + "#" + curr.weight);

			result.set(curr.destination, curr.weight);

			for(Box neigh: adj.get(curr.destination)) {
				if (result.get(neigh.destination) > curr.weight + neigh.weight) {
					result.set(neigh.destination, curr.weight + neigh.weight);
					minHeap.add(new Box(neigh.destination, result.get(neigh.destination)));
				}
			}
		}
		return result;
	}
}