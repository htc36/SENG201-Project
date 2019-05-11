package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import planet.Planet;


class PlanetTest {
	private Planet p1;
	private Planet p2;
	private Planet p3;


	@BeforeEach
	public void setup() {
		
		p1 = new Planet("Mercury", false);
		p2 = new Planet("Mars", true);
		p3 = new Planet("Venus", true);
	}
	
	@Test
	public void testForShipPieces() {
		p2.extractShipPieces();
		p1.extractShipPieces();
		assertEquals(false, p2.stillHasShipPieces());
		assertEquals(false, p1.stillHasShipPieces());
		assertEquals(true, p3.stillHasShipPieces());
	}
	
	@Test
	public void planetStringTest() {
	    assertEquals("Mercury false", p1.toString());
	}

	@Test
	public void planetGetNameTest() {
	    assertEquals("Mercury", p1.getName());
	}
	
	
}
