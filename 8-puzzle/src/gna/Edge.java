package gna;

public class Edge {

	private int from;
	private int to;
	private double weight;

	public Edge(int i, int j, double weight) {
     this.setFrom(i);
     this.setTo(j);
     this.setWeight(weight);
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	 public String toString() {
	        return this.getFrom() + "->" + this.getTo() + " " + String.format("%5.2f", weight);
	    }

}
