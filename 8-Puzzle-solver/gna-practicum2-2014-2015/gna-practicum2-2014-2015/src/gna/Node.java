package gna;


public class Node implements Comparable {
	
	
	
	 private Board board;
     private Node previousNode;       
     public int moves;
	 private static String priority;
     
     public Node (Board bd) {
         this.board = bd;
         this.previousNode = null;
         this.moves = 0;
     }
     
     public Node (Board bd, Node pre, int moves) {
         this.board = bd;
         this.previousNode = pre;
         this.moves = moves;
     }
   
     
     
     public int priorityDistance () {
    	 if(this.getPriority().equals("manhattan"))
         return this.getMoves() + this.getBoad().manhattan();
    	 else 
             return this.getMoves() + this.getBoad().hamming();
     }
      
     
     public static void setPriority(String priority){
    	 Node.priority = priority;
     }
     
     protected String getPriority() {
    	 return Node.priority;
	}

	public int compareTo (Object other) {
         Node second = (Node) other;
         if (this ==  other) return 0;
         int distanceFirst = this.priorityDistance();
         int distanceSecond= second.priorityDistance();
         if (distanceFirst > distanceSecond) return 1;
         if ( distanceFirst == distanceSecond) return 0;
         return -1;
     }
     
     
     public Node getPreviousNode(){
    	 return this.previousNode;
     }
     
     
     
     public Board getBoad(){
 		return this.board;
 	}
     
    public int getMoves(){
    	return this.moves;
    }
     
     public boolean equals(Object other){
    	 Node o = (Node)other;
    	 if(o == null)
    		 return false;
    	 for(int i = 0; i< getBoad().getSize();i++){
 			for(int j = 0; j < getBoad().getSize();j++){
 				if(this.getBoad().board[i][j] != o.getBoad().board[i][j])
 					return false;
 			}
    	 }
    	 if(this.getPriority().equals("hamming")){
    		 if(o.getBoad().hamming() != this.getBoad().hamming())
    			 return false;
    	 }
    	 else 
    		 if(o.getBoad().manhattan() != this.getBoad().manhattan())
    			 return false;
		return true;
     }
   }


