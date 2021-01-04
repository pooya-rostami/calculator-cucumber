package calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


public class TestTimes implements TestInterface {

	int value1, value2;
	MyNumber number1, number2;
	Times op;
	List<Expression> params;

	@Before
	public void setUp() {
		  value1 = 8;
		  value2 = 6;
		  number1 = new MyNumber(value1);
		  number2 = new MyNumber(value2);
		  params = new ArrayList<>();
		  Collections.addAll(params, number1, number2);
		  try { op = new Times(params); }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test(expected=IllegalConstruction.class)
	public void testNullParamList() throws IllegalConstruction {
		params = null;
		op = new Times(params);
	}

	@Test
	public void test_compute() {
		assertEquals(value1*value2, op.compute().intValue());
		assertEquals(48, op.compute().intValue());
	}

	@Test
	public void test_countDepth() {
		assertEquals(Integer.valueOf(1), op.countDepth());
	}

	@Test
	public void test_countOps() {
		assertEquals(Integer.valueOf(1), op.countOps());
	}

	@Test
	public void test_countNbs() {
		assertEquals(Integer.valueOf(2), op.countNbs());
	}

	@Test
	public void test_toString() {
		// default printing notation is infix
		assertEquals("( 8 * 6 )", op.toString());
	}

	@Test
	public void test_prefix() {
		op.notation = Notation.PREFIX;
		assertEquals("* (8, 6)", op.toString());
	}

	@Test
	public void test_infix() {
		op.notation = Notation.INFIX;
		assertEquals("( 8 * 6 )", op.toString());
	}

	@Test
	public void test_postfix() {
		op.notation = Notation.POSTFIX;
		assertEquals("(8, 6) *", op.toString());
	}

}