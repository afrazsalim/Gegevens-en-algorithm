package gna;

import java.util.ArrayList;
import java.util.Collection;

public class Board
{

	protected int [][] board;
	private int N;
	private int hammingDistance;
	private int manhattanDistance;
	public Board( int[][] tiles )
	{
        this.board = tiles;
        this.N = tiles.length;
        this.hammingDistance = 0;
        this.manhattanDistance = 0;
	}
	
	
	
	
	
	public void setHammingDistance(int distance){
		this.hammingDistance = distance;
	}
	
	
	
	
	
	
	public int getHammingDistance(){
		return this.hammingDistance;
	}
	
	
	
	
	
	public int hamming()
	{
		int index = 1;
		this.setHammingDistance(0);
		for(int i = 0; i< this.getSize();i++){
			for(int j = 0; j < this.getSize();j++){
				if(this.board[i][j]  != index)
					this.setHammingDistance(this.getHammingDistance()+1);
				index++;
			}
		}
		this.setHammingDistance(this.getHammingDistance()-1);
		return this.getHammingDistance();
	}
	
	
	
	
	
	
	protected int getSize() {
		return this.N;
	}


	
	
	
	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		this.setManhattenDistance(0);
		for(int i = 0; i < this.getSize();i++){
			for(int j = 0; j< this.getSize();j++){
				if(this.board[i][j] > 0){
					int xCo = ((this.board[i][j]-1)/this.getSize());
					int yCo = ((this.board[i][j]-1)%this.getSize());
					this.setManhattenDistance(this.getManhattenDistance()+ Math.abs(xCo-i)+Math.abs(yCo-j));
				}
			}
		}
		return this.getManhattenDistance();
	}
	
	
	
	
	
	
	
	private void setManhattenDistance(int i) {
        this.manhattanDistance = i;		
	}


	public int getManhattenDistance() {
		return this.manhattanDistance;
	}


	
	
	// does this board position equal y
	public boolean equals(Object y)
	{
		int index = 0;
		if ((y == null) || (getClass() != y.getClass()))
			return false;
		Board otherBoard = (Board) y;
		for(int i = 0; i< this.getSize();i++){
			for(int j = 0; j < this.getSize();j++){
				if(this.board[i][j] != otherBoard.board[i][j]){
					index = j;
					return false;
				}
			}
			if(this.board[i][index] != otherBoard.board[i][index])
				break;
		}
		if(this.getManhattenDistance() != otherBoard.getManhattenDistance())
			return false;
		if(this.getHammingDistance() !=otherBoard.getHammingDistance())
			return false;
		return true;
	}
	
	
	
	
	
	// return a Collection of all neighboring board positions
	public Collection<Board> neighbors()
	{
	   int emptyTile = 0;
	   int [][] succesor;
	   Board newBoard;
	   Collection<Board> collection = new ArrayList<Board>();
       for(int i = 0; i< this.getSize();i++){
    	   for(int j = 0;j <this.getSize();j++){
    		   if(this.board[i][j] == emptyTile){
    			   if(i == 0 && j ==0){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if (j  > 0 && j < this.getSize()-1 && i == 0){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if(i == 0 && j == this.getSize()-1){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if(i  > 0  && j ==0 && i < this.getSize()-1){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if(i > 0 && j > 0 && i < this.getSize()-1 && j < this.getSize()-1){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if(i > 0 && j > 0 && j == this.getSize()-1 && i < this.getSize()-1){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i+1][j];
    				   succesor[i+1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if( i == this.getSize()-1 && j == 0){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if(i == this.getSize()-1 && j < this.getSize()-1 && j > 0){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j+1];
    				   succesor[i][j+1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
    			   else if (i == this.getSize()-1 && j == this.getSize()-1){
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i][j-1];
    				   succesor[i][j-1] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    				   succesor = new int[this.getSize()][this.getSize()];
    				   this.makeCloneToTheNewObject(succesor);
    				   succesor[i][j] = succesor[i-1][j];
    				   succesor[i-1][j] = 0;
    				   newBoard = new Board(succesor);
    				   collection.add(newBoard);
    			   }
 
    		   }
    	   }
       }
	return collection;
	}
	
	
	
	
	
	private void makeCloneToTheNewObject(int[][] succesor) {
        for(int i = 0; i< this.getSize(); i ++){
        	for(int j =0 ; j< this.getSize();j++){
        		succesor[i][j] = this.board[i][j];
        	}
        }
	}
   
	private static Node node;
	public boolean isGoal(Board board){
		if (board.manhattan() == 0)
			return true;
		else
		return false;
	}




	// return a string representation of the board
	public String toString()
	{
		 StringBuilder s = new StringBuilder();
	        s.append(N + "\n");
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                s.append(String.format("%2d ", this.board[i][j]));
	            }
	            s.append("\n");
	        }
	        return s.toString();
	}

	// is the initial board solvable?
	public boolean isSolvable()
	{
          return true;
	}
}

