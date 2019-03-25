package test;

import static org.junit.jupiter.api.Assertions.*;

import template.Yay;

import org.junit.jupiter.api.Test;

class YayMoreTest {

	@Test
	void successTestAgain() {
		Yay y = new Yay();
		assertEquals("test", y.test());
	}

	@Test
	void failTestAgain() {
		Yay y = new Yay();
		assertEquals("Test123", y.test());
	}

}