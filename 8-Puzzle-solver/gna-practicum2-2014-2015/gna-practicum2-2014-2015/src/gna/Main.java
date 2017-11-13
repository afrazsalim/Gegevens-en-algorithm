package gna;

import libpract.PriorityFunc;
import libpract.StdIn;


class Main
{
	public static void main( String[] args )
	{
		int N = StdIn.readInt();
		int[][] tiles = new int[N][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tiles[i][j] = StdIn.readInt();
		
		System.out.println("started ");
		Board initial = new Board(tiles);
		if (!initial.isSolvable())
		{
			System.out.println("No solution possible");
		}
		else
		{
			StopWatch timer = new StopWatch();
			Solver solver = new Solver(initial, PriorityFunc.HAMMING);
	        double time = timer.elapsedTime();
			for (Board board : solver.solution())
				System.out.println(board.toString() + "   Boards");
            
			System.out.println("elapsed timer " + time);
			System.out.println("Minimum number of moves = " + solver.solution().size());
		}
	}
}


