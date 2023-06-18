package dijkstrasShortestPathForNegativeWeights;

public interface Vertex{
	/*I would use Collection as the return type instead of Object,
	 * but that would exclude Map subclasses.*/
	public abstract Object getNeighbors();
	
	public abstract double getEdgeTo(Vertex neighbor);
}