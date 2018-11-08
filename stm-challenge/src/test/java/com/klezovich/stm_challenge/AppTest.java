package com.klezovich.stm_challenge;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void test3by3gridCreation() {

		if (!CanvasTester.test(new File(Property.testDir + "canvas 3x3")))
			fail();
	}

	public void testWrong3by3GridCreation() {
		if (CanvasTester.test(new File(Property.testDir + "canvas 3x3 wrong")))
			fail();
	}

	public void test5by5gridCreation() {

		if (!CanvasTester.test(new File(Property.testDir + "canvas 5x5")))
			fail();
	}
	
	public void test5by5gridWithEasyLine() {

		if (!CanvasTester.test(new File(Property.testDir + "canvas 5x5 easy line")))
			fail();
	}
	
	public void test5by5gridWithEasyLineFail() {

		if (CanvasTester.test(new File(Property.testDir + "canvas 5x5 easy line fail")))
			fail();
	}
	
	public void test5by5gridFill() {
		if (!CanvasTester.test(new File(Property.testDir + "canvas 5x5 easy fill")))
			fail();
	}
	
	public void test5by5wrongColorFill() {
		if (CanvasTester.test(new File(Property.testDir + "canvas 5x5 wrong fill")))
			fail();
	}
	
	public void test5by5easyRectangle() {
		if (!CanvasTester.test(new File(Property.testDir + "canvas 5x5 easy rectangle")))
			fail();
	}

}
