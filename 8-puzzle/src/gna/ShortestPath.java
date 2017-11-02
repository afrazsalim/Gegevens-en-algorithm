package gna;

import java.util.ArrayList;
import java.util.Collection;

import libpract.Position;
import libpract.Stitch;

public class ShortestPath {

	private Graph graph;
	private int source;
	private double[] distTo;  
    private Edge[] edgeTo;   
    private IndexMinPQ<Double> pq;

	public ShortestPath(int i, Graph graph, int[][] image1) {
     this.graph = graph;
     this.source = i;
     distTo = new double[image1.length*image1[0].length];
     edgeTo = new Edge[image1.length*image1[0].length];
     for (Edge e : graph.edges()) {
         if (e.getWeight() < 0)
             throw new IllegalArgumentException("edge " + e + " has negative weight");
     }
     for (int v = 0; v < image1.length*image1[0].length; v++)
         distTo[v] = Double.POSITIVE_INFINITY;
     distTo[0] = 0.0;
     pq = new IndexMinPQ<Double>(image1.length*image1[0].length);
     pq.insert(0, distTo[0]);
     while(!(pq.isEmpty())){
    	 int v = pq.delMin();
    	 for(Edge e: graph.adj(v)){
    		 this.relax(e);
    	 }
      }
	}

	private void relax(Edge e) {
		int v = e.getFrom();
		int w = e.getTo();
        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            if (pq.contains(w))
            	pq.decreaseKey(w, distTo[w]);
            else              
            	pq.insert(w, distTo[w]);
        }		
	}
	
	
	 public Iterable<Edge> pathTo(int v) {
	        if (!hasPathTo(v)) return null;
	        Collection<Edge> path = new ArrayList<Edge>();
	        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.getFrom()]) {
	            path.add(e);
	        }
	        return path;
	    }
	 
	
	 public boolean hasPathTo(int v) {
		 System.out.println(distTo[v]);
	        return distTo[v] < Double.POSITIVE_INFINITY;
	    }
	
	 
	 
	 
	 
	 
		
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
