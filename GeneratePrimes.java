package dijkstrasShortestPathForNegativeWeights;

public class GeneratePrimes {
	private int n = 1;
	private boolean subtract = true;
	public int getAPrime() {
		int generatedPrime;
		if(subtract) {
			generatedPrime = (6 * n) - 1;
			subtract = false;
		}
		else {
			generatedPrime = (6 * n) + 1;
			n++;
			subtract = true;
		}
		return generatedPrime;
	}
}