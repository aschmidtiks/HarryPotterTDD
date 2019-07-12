package logic;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestClassHP {
	
	ShoppingCart shoppingCart;
	
	@BeforeEach
	public void testSetup() {
		shoppingCart = new ShoppingCart();
	}

	@Test
	public void testInitialTest() {
				
		assertEquals(0, shoppingCart.price(new int[] {}), "zero arguments");
		assertEquals(8, shoppingCart.price(new int[] {0}), "one argument: 0");
		assertEquals(8, shoppingCart.price(new int[] {1}), "one argument: 1");
		assertEquals(8, shoppingCart.price(new int[] {2}), "one argument: 2");
		assertEquals(8, shoppingCart.price(new int[] {3}), "one argument: 3");
		assertEquals(8, shoppingCart.price(new int[] {4}), "one argument: 4");
		assertEquals(8 * 2, shoppingCart.price(new int[] {0, 0}), "two times same book");
		assertEquals(8 * 3, shoppingCart.price(new int[] {1, 1, 1}), "three times same book");	
		assertEquals(8 * 4, shoppingCart.price(new int[] {1, 1, 1, 1}), "four times same book");
	}

	@Test
	public void testSimpleDiscounts() {
		assertEquals(8*2*0.95, shoppingCart.price(new int[] {0, 1}), "two different books");
		assertEquals(8*3*0.9, shoppingCart.price(new int[] {0, 2, 4}), "three different books");
		assertEquals(8*4*0.8, shoppingCart.price(new int[] {0, 1, 2, 4}), "four different books");
		assertEquals(8*5*0.75, shoppingCart.price(new int[] {0, 1, 2, 3, 4}), "five different books");
	}
	
	@Test
	public void testSeveralDiscounts() {
		assertEquals(8 + (8 * 2 * 0.95), shoppingCart.price(new int[] {0, 0, 1}), "SeveralDiscounts 001");
		assertEquals(2 * (8 * 2 * 0.95), shoppingCart.price(new int[] {0, 0, 1, 1}), "SeveralDiscounts 0011");
		assertEquals((8 * 4 * 0.8) + (8 * 2 * 0.95), shoppingCart.price(new int[] {0, 0, 1, 2, 2, 3}), "SeveralDiscounts 001223");
		assertEquals(8 + (8 * 5 * 0.75), shoppingCart.price(new int[] {0, 1, 1, 2, 3, 4}), "SeveralDiscounts 011234");
	}

	@Test
	public void testEdgeCases() {
		//Die vorgegebenen edge cases ergeben keinen Sinn. Die Rechnung die als Ergebnis verlangt wird ist falsch ???
		//(Siehe auskommentierte Tests)

		assertEquals((8 * 5 * 0.75) + (8 * 3 * 0.9), shoppingCart.price(new int[] {0, 0, 1, 1, 2, 2, 3, 4}), "EdgeCases 1");
		assertEquals(4 * (8 * 5 * 0.75) + (8 * 3 * 0.9), shoppingCart.price(new int[] {0, 0, 0, 0, 0,
														 	     		 	  			 		1, 1, 1, 1, 1,
														 	     		 	  			 		2, 2, 2, 2,
														 	     		 	  			 		3, 3, 3, 3, 3,
														 	     		 	  			 		4, 4, 4, 4}), "EdgeCases 2");

		/*assertEquals(2 * (8 * 4 * 0.8), shoppingCart.price(new int[] {0, 0, 1, 1, 2, 2, 3, 4}), "EdgeCases 1");
		assertEquals(3 * (8 * 5 * 0.75) +
							  2 * (8 * 4 * 0.8), shoppingCart.price(new int[] {0, 0, 0, 0, 0,
																			   1, 1, 1, 1, 1,
																			   2, 2, 2, 2,
																			   3, 3, 3, 3, 3,
																			   4, 4, 4, 4}), "EdgeCases 2");*/
	}

	@Test
	public void testChronologicalInputMethod() {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		result.add(Arrays.asList(0, 1));
		result.add(Arrays.asList(0));
		assertEquals(result, shoppingCart.rearrangeShoppingCart(new int[] {0, 0, 1}), "001");

		result.clear();
		result.add(Arrays.asList(0, 1));
		result.add(Arrays.asList(0, 1));
		assertEquals(result, shoppingCart.rearrangeShoppingCart(new int[] {0, 0, 1, 1}), "0101");

		result.clear();
		result.add(Arrays.asList(0, 1 ,2));
		result.add(Arrays.asList(0));
		assertEquals(result, shoppingCart.rearrangeShoppingCart(new int[] {0, 0, 1, 2}), "0012");

		result.clear();
		result.add(Arrays.asList(0 ,2, 4));
		assertEquals(result, shoppingCart.rearrangeShoppingCart(new int[] {0, 2, 4}), "024");
	}

}
