package test;

import static org.junit.jupiter.api.Assertions.*;

import template.Yay;

import org.junit.jupiter.api.Test;

class YayTest {

	@Test
	void successTest() {
		Yay y = new Yay();
		assertEquals("test", y.test());
	}

	@Test
	void failTest() {
		Yay y = new Yay();
		assertEquals("Test123", y.test());
	}

}
