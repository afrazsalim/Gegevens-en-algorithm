package gna;

import libpract.Position;

public class TestClass {
	public static void main(String [] args){
		int [][] array = new int[10][10];
		int [][] array2 = new int[10][10];
		Position [][] positions = new Position[10][10];
		for(int i = 0; i < 10;i++){
			for(int j = 0; j< 10;j++){
				positions[i][j] = new Position(i,j);
			}
		}
		Graph g = new Graph(array,array2,positions);
		for(Edge e: g.adj(20))
			System.out.println(e);
	}

}
