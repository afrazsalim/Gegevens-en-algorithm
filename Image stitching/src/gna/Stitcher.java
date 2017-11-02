package gna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import libpract.Position;
import libpract.Stitch;

/**
 * Implement the methods stitch, seam and floodfill.
 */
public class Stitcher
{
	Position[][] positions;
	Position[] pos;
	ArrayList<Position> seam;
	/**
	 * Return the sequence of positions on the seam. The first position in the
	 * sequence is (0, 0) and the last is (width - 1, height - 1). Each position
	 * on the seam must be adjacent to its predecessor and successor (if any).
	 * Positions that are diagonally adjacent are considered adjacent.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 *
	 * Remark: Here we use the default computer graphics coordinate system,
	 *   illustrated in the following image:
	 * 
	 *        +-------------> X
	 *        |  +---+---+
	 *        |  | A | C |
	 *        |  +---+---+
	 *        |  | B | D |
	 *        |  +---+---+
	 *      Y v 
	 * 
	 *   The historical reasons behind using this layout is explained on the following
	 *   website: http://programarcadegames.com/index.php?chapter=introduction_to_graphics
	 * 
	 *   Position (x, y) corresponds to the pixels image1[x][y] and image2[x][y]. This
	 *   also means that, when an automated test mentioned that it used the array
	 *   {{A,B},{C,D}} as a test image, this corresponds to the image layout as shown in
	 *   the illustration above.
	 */
	public List<Position> seam(int[][] image1, int[][] image2) {
		System.out.println(image1.length + "width " + image1[0].length);
		positions = new Position[image1.length][image1[0].length];
		pos = new Position[image1.length*image1[0].length];
		seam = new ArrayList<Position>();
		for (int i = 0; i < image1.length; i++) {
            for (int j = 0; j < image1[0].length; j++) {
                positions[i][j] = new Position(i, j);
                pos[i*image1[0].length+j] = new Position(i,j);
            }
		}
		Graph graph = new Graph(image1,image2,positions);
		ShortestPath p = new ShortestPath(0,graph,image1);
		for(Edge e: p.pathTo((image1.length)*(image1[0].length)-1)){
            Position position = pos[e.getFrom()];
            seam.add(position);
		}
		seam.add(positions[image1.length-1][image1[0].length-1]);
		Collections.reverse(seam);
		return seam;
	}
	
	
	
	public Position [][] getPositions(){
		return this.positions;
	}
	

	/**
	 * Apply the floodfill algorithm described in the assignment to mask. You can assume the mask
	 * contains a seam from the upper left corner to the bottom right corner. Each position in the
	 * seam is adjacent to only one other seam position. For example, the seam will never go vertical
	 * and then horizontal (it will immediately go diagonal). The seam is represented using Stitch.SEAM
	 * and all other positions contain the default value Stitch.EMPTY. So your algorithm must replace
	 * all Stitch.EMPTY values with either Stitch.IMAGE1 or Stitch.IMAGE2.
	 *
	 * Positions left to the seam should contain Stitch.IMAGE1, and those right to the seam
	 * should contain Stitch.IMAGE2. You can run `ant test` for a basic (but not complete) test
	 * to check whether your implementation does this properly.
	 */
	public void floodfill(Stitch[][] mask) {
		   this.floodFillIterative(mask,2,0);
	   }

	
	
	
	




	

	
	private void floodFillIterative(Stitch[][] mask, int i, int j) {
		boolean [][]mark  = new boolean[mask.length+10][mask[0].length+10];
		Queue<Position>  q = new Queue<Position>(mask.length*mask[0].length+10);
	    boolean [][] OnQueue = new boolean[mask.length+10][mask[0].length+10];
	    Position [][] positions = this.getPositions();
        Position pos = this.positions[i][j];
        q.enqueue(pos);
        while(!(q.isEmpty())){
        	Position p = q.dequeue();
        	int x = p.getX();
        	int y = p.getY();
        	if(mask[x][y] == Stitch.SEAM){
        	mark[x][y] = true;
        	}
        	else
        	{
            if(!(mark[x][y]))
        	 mask[x][y] = Stitch.IMAGE1;
        if(mask[x][y] != Stitch.SEAM){
        	if((x > 0)  && isValid(mask,x-1,y,mark,OnQueue) && (mask[x-1][y] != Stitch.SEAM)){
        		q.enqueue(positions[x-1][y]);
        		OnQueue[x-1][y] = true;
        	}
        	if((y > 0) && isValid(mask,x,y-1,mark,OnQueue)&& (mask[x][y-1] != Stitch.SEAM)){
        		q.enqueue(positions[x][y-1]);
        		OnQueue[x][y-1] = true;
          }
         if((y < mask[0].length-1) && isValid(mask,x,y+1,mark,OnQueue)&& (mask[x][y+1] != Stitch.SEAM)){
    		q.enqueue(positions[x][y+1]);
    		OnQueue[x][y+1] = true;
        }
	    if((x < mask.length-1) && isValid(mask,x+1,y,mark,OnQueue) && (mask[x+1][y] != Stitch.SEAM)){
    		   q.enqueue(positions[x+1][y]);
	       OnQueue[x+1][y] = true;
          }
       }
     }
   }
}
	


	

	private boolean isValid(Stitch[][] mask, int i, int j, boolean[][] marked, boolean[][] onQueue) {
		if(onQueue[i][j] == true)
			return false;
		if(mask[i][j] == Stitch.SEAM){
			return false;
		}
		if(marked[i][j] == true)
			return false;
	return true;
	}







	/**
	 * Return the mask to stitch two images together. The seam runs from the upper
	 * left to the lower right corner, where in general the rightmost part comes from
	 * the first image (but remember that the seam can be complex, see the spiral example
	 * in the assignment). A pixel in the mask is Stitch.IMAGE1 on the places where
	 * image1 should be used, and Stitch.IMAGE2 where image2 should be used. On the seam
	 * record a value of Stitch.SEAM.
	 * 
	 * ImageCompositor will only call this method (not seam and floodfill) to
	 * stitch two images.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 */
	public Stitch[][] stitch(int[][] image1, int[][] image2) {
		List<Position> seam = this.seam(image1, image2);
		Stitch[][] stitch = new Stitch[image1.length][image1[0].length];
       for (Position position : seam){
            stitch[position.getX()][position.getY()] = Stitch.SEAM;
        }
        this.floodfill(stitch);
        return stitch;
	}
}




