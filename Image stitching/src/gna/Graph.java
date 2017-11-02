package gna;

import libpract.Position;

public class Graph {

	private int [][] image1;
	private int [][] image2;
	private Position[][] positions;
	private Bag<Edge> [] adj;
	
	public Graph(int[][] image1, int[][] image2, Position[][] positions) {
		this.image1 = image1;
		this.image2 = image2;
		this.positions = positions;
		 adj = (Bag<Edge>[]) new Bag[image1.length*image1[0].length];
	        for (int i = 0; i < image1.length; i++) {
	        	for(int j = 0; j< image1[0].length;j++){
		            adj[i*image1[0].length+j] = new Bag<Edge>();
	        	}
	        }
		this.createGraph();
	}
	
	
	private void createGraph() {
		for(int i = 0; i < image1.length;i++){
			for(int j = 0; j< image1[0].length;j++){
				this.addEdges(i*image1[0].length+j,i,j);
			}
		}
		
	}
	 public void addEdges(int v, int i, int j) {
		   int width = image1[0].length;
	       if(i < image1.length-1 && positions[i][j].isAdjacentTo(positions[i+1][j]))
	    	   adj[i*width+j].add(new Edge(i*width+j,((i+1)*width+j),this.getWeight(i+1,j,i,j)));
	       if(j < image1[0].length-1 && positions[i][j].isAdjacentTo(positions[i][j+1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i*width+(j+1)),this.getWeight(i,j+1,i,j)));
	       if(i < image1.length-1 &&j < image1[0].length-1 && positions[i][j].isAdjacentTo(positions[i+1][j+1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,((i+1)*width+(j+1)),this.getWeight(i+1,j+1,i,j)));
	       if(j > 0 && positions[i][j].isAdjacentTo(positions[i][j-1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i*width+(j-1)),this.getWeight(i,j-1,i,j)));
	       if(i < image1.length-1 &&j > 0 && positions[i][j].isAdjacentTo(positions[i+1][j-1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i+1)*width+(j-1),this.getWeight(i+1,j-1,i,j)));     
	       if(i > 0 && positions[i][j].isAdjacentTo(positions[i-1][j]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i-1)*width+j,this.getWeight(i-1,j,i,j)));
	       if(i > 0 && j < width-1 && positions[i][j].isAdjacentTo(positions[i-1][j+1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i-1)*width+(j+1),this.getWeight(i-1,j+1,i,j)));
	       if(i > 0 && j > 0 && positions[i][j].isAdjacentTo(positions[i-1][j-1]))
	    	   adj[i*width+j].add(new Edge(i*width+j,(i-1)*width+(j-1),this.getWeight(i-1,j-1,i,j)));

	    }

	   public Iterable<Edge> adj(int v) {
	        return adj[v];
	    }

	   
	private double getWeight(int i, int j,int x, int y) {
		return ImageCompositor.pixelSqDistance(image1[i][j], image2[i][j]);
	 }


	  public Iterable<Edge> edges() {
	        Bag<Edge> list = new Bag<Edge>();
	        for (int v = 0; v < adj.length; v++) {
	            for (Edge e : adj(v)) {
	                list.add(e);
	            }
	        }
	        return list;
	    } 
	
	

    }
