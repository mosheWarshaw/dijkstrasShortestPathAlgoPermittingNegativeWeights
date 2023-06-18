package dijkstrasShortestPathForNegativeWeights;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstrasShortestPathWithNegativeWeights {
	private boolean graphHasNegativeWeights;
	private PriorityQueue<VertexWrapper> pq;
	private HashMap<Vertex, VertexWrapper> distances = new HashMap<>();
	private GeneratePrimes primeGenerator = new GeneratePrimes();	
	
	public DijkstrasShortestPathWithNegativeWeights(boolean graphHasNegativeWeightsPar, Comparator comparator) {
		graphHasNegativeWeights = graphHasNegativeWeightsPar;
		pq = new PriorityQueue<>(comparator);
	}
	

	
	
	public HashMap<Vertex, VertexWrapper> findShortestPathsFrom(Vertex root) {
		VertexWrapper rootVw = new VertexWrapper(root, 0.0, primeGenerator.getAPrime());
		//The root's path starts with itself.
		rootVw.path = rootVw.getPrime();
		distances.put(root, rootVw);
		pq.add(rootVw);
		while(!(pq.isEmpty())) {
			VertexWrapper head = pq.poll();
			addNewVertexesToPq(head.vertex.getNeighbors());
			offerToEachNeighbor(head);
		}
		return distances;
	}
	
	
	
	/*You know a vertex hasn't been added to the pq if it isn't in
	 * the distances collection.*/
	public void addNewVertexesToPq(Object neighbors) {
		if(neighbors instanceof Collection) {
			VertexWrapper neighborVw;
			Iterator<Vertex> iterator = ((Collection)neighbors).iterator();
			while(iterator.hasNext()) {
				Vertex neighbor = iterator.next();
				if(!(distances.containsKey(neighbor))) {
					neighborVw = new VertexWrapper(neighbor, Double.MAX_VALUE, primeGenerator.getAPrime());
					distances.put(neighbor, neighborVw);
					pq.add(neighborVw);
				}
			}
		}
		else if(neighbors instanceof Map) {
			((Map)neighbors).forEach((k, v) -> {
				VertexWrapper neighborVw = null;
				//I don't know if the user had the key or value be the neighbor.
				if(k instanceof Vertex) {
					if(!(distances.containsKey(k))) {
						neighborVw = new VertexWrapper((Vertex)k, Double.MAX_VALUE, primeGenerator.getAPrime());
						distances.put((Vertex)k, neighborVw);
						pq.add(neighborVw);
					}
				}
				else { //v instanceof Vertex
					if(!(distances.containsKey(k))) {
						neighborVw = new VertexWrapper((Vertex)v, Double.MAX_VALUE, primeGenerator.getAPrime());
						distances.put((Vertex)v, neighborVw);
						pq.add(neighborVw);
					}
				}
			});
		}
	}

	
	
	
	public void offerToEachNeighbor(VertexWrapper head) {
		Object neighbors = head.vertex.getNeighbors();
		if(neighbors instanceof Collection) {
			Iterator<Vertex> iterator = ((Collection)neighbors).iterator();
			VertexWrapper wv;
			while(iterator.hasNext()) {
				offerToANeighbor(head, distances.get(iterator.next()));
			}
		}
		else if(neighbors instanceof Map) {
			((Map)neighbors).forEach((k, v) -> {
				VertexWrapper vw;
				if(k instanceof Vertex) {
					offerToANeighbor(head, distances.get(k));
				}
				else { // v instanceof Vertex
					offerToANeighbor(head, distances.get(v));			
				}
			});
		}
	}
	
	
	
	
	public void offerToANeighbor(VertexWrapper head, VertexWrapper neighbor) {
		boolean neighborAlreadyPartOfThePath = false;
		if(graphHasNegativeWeights) {
			neighborAlreadyPartOfThePath = isNeighborAlreadyPartOfThePath(head, neighbor);
		}
		if(!neighborAlreadyPartOfThePath) {
			double offer = head.distance + head.vertex.getEdgeTo(neighbor.vertex);
			if(neighbor.distance > offer) {
				neighbor.acceptOffer(offer, head);
				
				/*Changing the vertex's distance means u have to
				 * reevaluate where the vertex belongs in the pq,
				 * which is accomplished by removing and inserting it.*/
				pq.remove(neighbor);
				pq.add(neighbor);
			}
		}
	}	
	
	
	
	
	public boolean isNeighborAlreadyPartOfThePath(VertexWrapper head, VertexWrapper neighbor) {
		return head.path % neighbor.getPrime() == 0;
	}
}