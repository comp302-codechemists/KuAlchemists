/**
 * 
 */
package unitTestSimge;
import Business.Artifact;
import Business.Aspect;
import Business.Ingredient;
import Business.Player;
import Business.Token;
import Factories.ArtifactFactory;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Business.Aspect;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;

/**
 * @author simge
 *
 */
class PublishTheoryTests {

	/**
	 * Test method for {@link Business.Player#publishTheory(java.lang.String, java.lang.String)}.
	 */
	
	@Test
	void testZeorBalance() {
		Player mockPlayer = new Player("test", "test");
		mockPlayer.setBalance(0);
		
		assertThrows(IllegalArgumentException.class, ()-> mockPlayer.publishTheory("test", "test"));
	}
	@Test
	void testBalance() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();
		Artifact.initializeArtifacts();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		int initBalance = mockPlayer.getBalance();
		mockPlayer.publishTheory("Verdant Sprig", "Essence of Stride");
		int finalBalance = mockPlayer.getBalance();
		
		assertEquals(-1, finalBalance - initBalance);
		
	}
	@Test
	void testArtifactRemove() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();
		Artifact.initializeArtifacts();
		
		Artifact printingPressArtifact = ArtifactFactory.getInstance().getArtifacts("PrintingPressArtifact");
		
		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");

		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.addArtifact(printingPressArtifact);
		
		int initBalance = mockPlayer.getBalance();
		mockPlayer.publishTheory("Verdant Sprig", "Essence of Stride");
		int finalBalance = mockPlayer.getBalance();
		
		assertEquals(0, finalBalance - initBalance);

	}
	@Test
	void testBalance2() {
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();
		Artifact.initializeArtifacts();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");
		Ingredient ingr3 = Ingredient.getIngredient("Fungal Spore");
		Ingredient ingr4 = Ingredient.getIngredient("Azure Blossom");
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		mockPlayer.addIngredient(ingr3);
		mockPlayer.addIngredient(ingr4);
		
		int initBalance = mockPlayer.getBalance();
		mockPlayer.publishTheory("Verdant Sprig", "Fungal Spore");
		mockPlayer.publishTheory("Essence of Stride", "Azure Blossom");
		int finalBalance = mockPlayer.getBalance();
		
		assertEquals(-2, finalBalance - initBalance);
		
	}
	
	@Test
	void testDoubleTheories() {
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();
		
		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr3 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr4 = Ingredient.getIngredient("Verdant Sprig");

		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		mockPlayer.addIngredient(ingr3);
		mockPlayer.addIngredient(ingr4);
		
		mockPlayer.publishTheory("Verdant Sprig", "Verdant Sprig");
		mockPlayer.publishTheory("Verdant Sprig", "Verdant Sprig");
	
		assertEquals(mockPlayer.getTheories().size(), 1 );
		
	}
	

}


