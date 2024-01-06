package unitTestSimge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Business.Aspect;
import Business.Ingredient;
import Business.Player;
import Business.PublicationBoard;
import Business.Theory;
import Business.Token;

class DebunkTheoryUnitTest {

	
	@Test
	public void testValidAspectMatch() {

		/*
		 * This test method test a valid existing ingredient
		 * Test is made with a true aspect
		 * */
		
		Aspect aspectOne = new Aspect("b", "y", "+");
	    Aspect aspectTwo = new Aspect("b", "k", "+");
	    Aspect aspectThree = new Aspect("b", "m", "+");
	    Token token = new Token(aspectOne, aspectTwo, aspectThree);
	    Ingredient ingredient = new Ingredient("IngredientName", token);
	    Player player = new Player("PlayerName", "AvatarPath");
	    Theory theory = new Theory(player, token, ingredient);

	    PublicationBoard board = new PublicationBoard();
	    assertTrue(board.debunkTheory(theory, "y+"));
	}


	@Test
	public void testInvalidAspectMatch() {
		
		/*
		 * This test method test a valid existing ingredient
		 * Test is made with a false aspect
		 * */

		Aspect aspectOne = new Aspect("b", "k", "+");
	    Aspect aspectTwo = new Aspect("b", "y", "+");
	    Aspect aspectThree = new Aspect("b", "m", "+");
	    Token token = new Token(aspectOne, aspectTwo, aspectThree);
	    Ingredient ingredient = new Ingredient("IngredientName", token);
	    Player player = new Player("PlayerName", "AvatarPath");
	    Theory theory = new Theory(player, token, ingredient);

	    PublicationBoard board = new PublicationBoard();
	    assertFalse(board.debunkTheory(theory, "y-"));
	}
	
	@Test
	public void testBoundaryCase() {
		
		/*
		 * This test method test a valid existing ingredient
		 * Test is made with a non-existing aspect
		 * */

		Aspect aspectOne = new Aspect("b", "k", "+");
	    Aspect aspectTwo = new Aspect("b", "y", "+");
	    Aspect aspectThree = new Aspect("b", "m", "+");
	    Token token = new Token(aspectOne, aspectTwo, aspectThree);
	    Ingredient ingredient = new Ingredient("IngredientName", token);
	    Player player = new Player("PlayerName", "AvatarPath");
	    Theory theory = new Theory(player, token, ingredient);

	    PublicationBoard board = new PublicationBoard();
	    assertFalse(board.debunkTheory(theory, "noSuchAspect"));
	}
	
	@Test
	public void testNullTheory() {
		
		/*
		 * This test method test a null value as theory
		 * Test is made with a true aspect
		 * */
		
	    PublicationBoard board = new PublicationBoard();
	    assertFalse(board.debunkTheory(null, "y+"));
	}

	@Test
    public void testDebunkEmptyTheories() {
		
		/*
		 * This test method tests the game
		 * when there is no theory
		 * since player cannot click on a non-existing theory 
		 * this test method will work correctly, actually
		 * */
        
        Theory.getAllTheories().clear(); // Clear existing theories
        PublicationBoard board = new PublicationBoard();

        boolean result = board.debunkTheory(null, "y+");

        assertFalse(result);
    }
}