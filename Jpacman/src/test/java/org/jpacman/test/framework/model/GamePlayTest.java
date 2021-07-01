package org.jpacman.test.framework.model;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.IBoardInspector.SpriteType;
import org.jpacman.framework.model.Tile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class GamePlayTest {
	
	public IGameFactory makeFactory() {
		return new DefaultGameFactory();
	}
	
	/**
	 * Simply create a single row game.
	 * The resulting game is returned, and can also
	 * be obtained via getGame().
	 * 
	 * @param singleRow String representation of one row
	 * @throws FactoryException If singleRow contains illegal chars.
	 * @return The game created while parsing the row.
	 */
	protected Game makePlay(String singleRow) throws FactoryException {
		MapParser p = new MapParser(makeFactory());
		Game theGame = p.parseMap(new String[]{singleRow});
		return theGame;
	}
	
	/**
	 * This method sets up the Game that we will be testing.
	 * @throws FactoryException 
	 */
	@Test
	public void testInitialSetting() throws FactoryException {
		Game g = makePlay("P");
		assertEquals(g.getPlayer(), g.getBoard().spriteAt(0, 0));
		assertThat(tileAt(g, 0, 0), equalTo(g.getPlayer().getTile()));
		assertEquals(SpriteType.PLAYER, g.getBoard().spriteTypeAt(0, 0));
		assertEquals(0, g.getPlayer().getPoints());
		assertTrue(g.getPlayer().isAlive());
		assertEquals(Direction.LEFT, g.getPlayer().getDirection());
	}
	
	/* Test winning a game*/
	@Test 
	public void testPlayWin() throws FactoryException {
		Game g = makePlay("P# ");
		g.movePlayer(Direction.RIGHT);
		assertThat("Game won", g.won(), equalTo(g.getPlayer().isAlive()));
		
	}
	
	/* Test losing a game*/
	@Test 
	public void testPlayLose() throws FactoryException {
		Game g = makePlay("P# ");
		g.movePlayer(Direction.LEFT);
		assertThat("Game won", !g.won(), equalTo(!g.getPlayer().isAlive()));
		
	}
	
	/**
	 * Convenience method to make assertion checking more natural.
	 * 
	 * @param g Game containing the board
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return The tile at the given location in the board under test.
	 */
	protected Tile tileAt(Game g, int x, int y) {
		return g.getBoard().tileAt(x, y);
	}

}
