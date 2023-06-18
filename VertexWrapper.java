package dijkstrasShortestPathForNegativeWeights;

public class VertexWrapper {
	public Vertex vertex;
	private int prime;
	public double distance;
	/*Each wrapper stores the previous vertex of its path (for eg,
	 * if the path is A -> B then B's wrapper will store A as its
	 * prevVertex), and the shortest path can be retraced from the
	 * target vertex's wrapper.*/
	public Vertex prevVertex;
	public double path;
	
	public VertexWrapper(Vertex vertexPar, double distancePar, int primePar) {
		vertex = vertexPar;
		distance = distancePar;
		prime = primePar;
		path = prime;
	}
	
	public void acceptOffer(double offer, VertexWrapper head) {
		distance = offer;
		prevVertex = head.vertex;
		path = head.path * prime;
	}
	
	public int getPrime() {
		return prime;
	}
}