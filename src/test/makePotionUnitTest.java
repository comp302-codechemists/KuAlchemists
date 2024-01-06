package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Business.*;
/**
 * Calculates the resulting potion depending on the given ingredients.
 * 
 * Requires:
 *   * Two ingredients.
 * Modifies:
 *   * None.
 * Effects:
 *   *	  Depending on the size and and the magnitude aspects of the given ingredients
 *   A potion is created and returned
 *   
 */
public class makePotionUnitTest {

    private Ingredient ingredientOne;
    private Ingredient ingredientTwo;
    
    private Ingredient ingredientThree;
    private Ingredient ingredientFour;
    
    private Ingredient ingredientFive;
    private Ingredient ingredientSix;
    
    private Ingredient ingredientSeven;
    private Ingredient ingredientEight;
    
    private Ingredient ingredientNine;
    private Ingredient ingredientTen;
    
    
    @Before
    public void setUp() {
        // Initialize ingredients for testing
        Aspect.initializeAllAspects();
        Token.initializeAllTokens();
        ingredientOne = new Ingredient("Test 1", Token.getTokens().get("by+bk+bm+"));
        ingredientTwo = new Ingredient("Test 2", Token.getTokens().get("by+kk-km+"));
        
        ingredientThree = new Ingredient("Test 3", Token.getTokens().get("by-bk-bm-"));
        ingredientFour = new Ingredient("Test 4", Token.getTokens().get("by-kk+km-"));
        
        ingredientFive = new Ingredient("Test 5", Token.getTokens().get("by+kk-km+"));
        ingredientSix = new Ingredient("Test 6", Token.getTokens().get("ky+kk-bm-"));
        
        ingredientSeven = new Ingredient("Test 7", Token.getTokens().get("ky-bk-km+"));
        ingredientEight = new Ingredient("Test 8", Token.getTokens().get("ky-kk+bm+"));
        
       
    }

 
    @Test
    public void makePotion_Test1() {
        Potion result = Potion.makePotion(ingredientOne, ingredientTwo);

        // Assert that the result is the expected potion
        assertNotNull(result);
        assertEquals("m+", result.getName());
    }

    
    @Test
    public void makePotion_Test2() {
     

        Potion result = Potion.makePotion(ingredientThree, ingredientFour);

        // Assert that the result is the expected potion
        assertNotNull(result);


        assertEquals("m-", result.getName());
    }

    
    @Test
    public void makePotion_Test3() {
      

        Potion result = Potion.makePotion(ingredientFive, ingredientSix);

        // Assert that the result is the expected potion

        assertNotNull(result);
        assertEquals("y+", result.getName());
    }

   
    @Test
    public void makePotion_Test4() throws NullPointerException{
        
    	try {
        Potion result = Potion.makePotion(null, null);

        // Assert that the result is the expected potion
        assertNotNull(result);
        }
    	catch (NullPointerException e) {
    		assertTrue(true);
    	}
    }

    
    @Test
    public void makePotion_Test5() {
   

    	try {
            Potion result = Potion.makePotion(null, ingredientEight);

            // Assert that the result is the expected potion
            assertNotNull(result);
            }
        	catch (NullPointerException e) {
        		assertTrue(true);
        	}
    }

   
}
