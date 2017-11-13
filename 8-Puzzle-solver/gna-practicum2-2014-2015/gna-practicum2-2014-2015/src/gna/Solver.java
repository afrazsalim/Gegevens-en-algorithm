package gna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import libpract.PriorityFunc;

public class Solver
{
	
	private boolean isSolved;
	private Collection<Board> b;
	HashSet<Node> openList;


	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority)
	{
		
		 HashSet<Node> closedList = new HashSet<Node>();
		 openList = new HashSet<Node>();
		 Collection<Board> b = new Stack<Board>();
		 this.setCollecion(b);
		 MinPQ<Node> pq = new MinPQ<Node>();
		 Node node = new Node(initial,null,0);
		 pq.insert(node);
		
		if (priority == PriorityFunc.HAMMING) {
			Node.setPriority("hamming");
		
		} 
		else if (priority == PriorityFunc.MANHATTAN) {
			 Node.setPriority("manhattan");
			}
	 else
		throw new IllegalArgumentException("Priority function not supported");
		this.solve(pq,closedList);
	}
	

	private void solve(MinPQ<Node> pq, HashSet<Node> closedList) {
		while(!(pq.min().getBoad().isGoal(pq.min().getBoad()))){
			Node e = pq.delMin();
			closedList.add(e);
			for(Board boards: e.getBoad().neighbors()){
				Node nextNode = new Node(boards,e,e.getMoves()+1);
				if(!equalToPreviousNode(nextNode,e.getPreviousNode()))
					  pq.insert(nextNode);
			   }
			}
		Node collection = pq.delMin();
			while(!(collection.getPreviousNode() == null)){
				this.getB().add(collection.getBoad());
				collection =collection.getPreviousNode();
		}
			this.getB().add(collection.getBoad());
			System.out.println(pq.size());
	}

	
	
	

	private boolean equalToPreviousNode(Node nextNode, Node previousNode) {
           if(nextNode.equals(previousNode))
        	   return true;
		return false;
	}


	private boolean inClosedList(Node nextNode, HashSet<Node> closedList) {
		for(Node close: closedList){
			if((close.equals(nextNode))){
				return true;
			}
		}
		return false;
	}

	

	private void setCollecion(Collection<Board> b) {
		this.b = b;
		
	}
	
	public Collection<Board> getB(){
		return this.b;
	}


	/**
	 * Returns a Collection of board positions as the solution. It should contain the initial
	 * Board as well as the solution (if these are equal only one Board is returned).
	 */
	public Collection<Board> solution()
	{
		return this.getB(); 
	}
}


