package gna;

public class Tes {
	
	
  public static void main(String [] args){
	  int [][] array = {{1,2,3},{4,5,0},{7,8,6}};
	  
	 Board board = new Board(array);
	 System.out.println(board.toString());
	 for(Board bo: board.neighbors()){
		 System.out.println("hi");
		 System.out.println(bo.manhattan());
	 }
  }

}
