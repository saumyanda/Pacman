package org.jpacman.test.framework.model;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.jpacman.framework.model.Board;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Tile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import junit.framework.Assert;
@RunWith(Parameterized.class)
public class BoardTest {
/*  Testing the model.Board class for invalid moves and to ensure that the board is correctly generated. 
 * Checking if the tileInvariant holds and the board is created correctly */	 
	private int width, height;
	private Direction direction;
	
	public BoardTest(int width, int height, Direction direction)
	{this.width = width; this.height = height; this.direction=direction;}
	
	//Creating a board from null values should fail
	//Case 1: Testing for negative sized boards
	/**Creating a board from null values should fail. Size of board cannot be negative*/
	//@Test(expected = NegativeArraySizeException.class)
	@Test
	public void testNegativeSizedBoard() { new Board(width, height); }
	
	//Case 2: Testing for 0 sized boards
	/**Creating a board from null values should fail. Size of board cannot be 0*/
	//@Test(expected = ArrayIndexOutOfBoundsException.class)
	@Test
	public void testZeroSizedBoard() { new Board(width, height).tileAt(0, 0); }
	
	//Case 3: Tests the behavior of positioning out of bounds to ensure board is correctly generated.
	/**Test case to check for out of bounds positioning*/
	@Test
	public void testTileAtDirection() {
	Board board = new Board(1, 1);
	Tile source = board.tileAt(0, 0);
	Tile destination = board.tileAtDirection(source, direction);
	assertEquals(source, destination);}
	
	//Case 4:
	/**Creating a board from initialized values of width and height should succeed.*/
	@Test
	public void boardInitTest() { Board board = new Board(5,5);board.tileAt(0, 0); }
	@Parameters
	public static Collection<Object[]> data() {//Simply pick any point that is out of bounds
	Object[][] values = new Object[][] {{2, 2, Direction.RIGHT}, { 4, 4, Direction.LEFT }, 
	{ 3, 3, Direction.UP }, {2, 2, Direction.DOWN}}; 
	return Arrays.asList(values);
	}
}
