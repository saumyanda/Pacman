package org.jpacman.test.framework.model;
import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.MapParser;
import org.junit.Before;
import org.junit.Test;

public class FactoryExceptionTest {
private MapParser parser;

private final String[] map = new String[] { 
				"#####", 
				"#..#", 
				"#GPG#", 
				"#   #",
				"#####" 
};

/** Create the standard factory and parser.*/
@Before
public void setUp() {
IGameFactory factory = new DefaultGameFactory();
parser = new MapParser(factory);
}

@Test(expected =  FactoryException.class)
public void testFactoryException() throws FactoryException {
parser.parseMap(map);
}
		
@Test(expected =  FactoryException.class)
public void testFactoryException2() throws FactoryException{
throw new FactoryException("test factory exception", null);
}
}

