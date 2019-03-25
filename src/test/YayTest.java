package test;

import static org.junit.jupiter.api.Assertions.*;

import template.Yay;

import org.junit.jupiter.api.Test;

class YayTest {

	@Test
	void test() {
		Yay y = new Yay();
		assertEquals("test", y.test());
	}

}
