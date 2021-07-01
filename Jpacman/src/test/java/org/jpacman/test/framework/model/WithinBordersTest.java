package org.jpacman.test.framework.model;
import static org.junit.Assert.*;
import java.util.*;
import org.jpacman.framework.model.Board;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class WithinBordersTest {
	private boolean expected;
	private int x;
	private int y;
	private static final int width = 10;
	private static final int height = 20;
	
	/* Taking one on point and one off point case for:
	x >= 0, x < width, y >= 0, y < height
	Taking the following in points:
	For x: 4,5,6,7 and For y: 3,8,11,15 */
	
	@Parameters
	public static Collection data() {
	//Parameters used to exercise different instances of the class
	return Arrays.asList( new Object[ ][ ] { { true, 0, 3 }, { false, -1,  8 },{ false, 10, 11 }, { true, 9, 15 },
		{ true, 4, 0 }, { false, 5, -1 }, { false, 6, 20 }, { true, 7, 19 } });
	}
	
	public WithinBordersTest(boolean expected, int x, int y) //Constructor
	{
		this.expected=expected;
		this.x=x;
		this.y=y;
	}
	
	@Test
	public void testWithinBorders()
	{
		assertEquals(expected,new Board(width,height).withinBorders(x, y));
	}
}