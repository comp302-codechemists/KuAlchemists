package unitTestSimge;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Business.Aspect;
import Business.Experiment;
import Business.Ingredient;
import Business.Player;
import Business.Potion;

class ClassTest {
	
	/*
	 * Overview:
	 * The Experiment class represents a scientific experiment 
	 * conducted by a player in a KuAlchemistGame. It involves 
	 * combining two ingredients to create a potion and testing 
	 * its effects on the player's attributes.
	 * 
	 * Abstract Function:
	 * The Experiment class encapsulates the process of conducting 
	 * an experiment with two ingredients to produce a potion.
	 * It tracks the player conducting the experiment (owner), 
	 * the two ingredients used (ingredientOne and ingredientTwo), 
	 * the type of experiment (whereToTest), and the resulting potion 
	 * (resultPotion). 
	 * 
	 * Representation Invariant: 
	 * owner must be a non-null instance of the Player class.
	 * Both ingredientOne and ingredientTwo must be non-null 
	 * instances of the Ingredient class. 
	 * whereToTest must be either 1 or 2, indicating the location of the test. 
	 * resultPotion must be a non-null instance of the Potion class.
	 */



	@Test
	void testOwner() {
		// Arrange
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		//Act
		Experiment exp = new Experiment(mockPlayer, ingr1, ingr2, 1);
		
		//Assert
		assertEquals(exp.getOwner(), mockPlayer);
	}
	
	@Test
	void testIngredients() {
		
		// Arrange
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		//Act
		Experiment exp = new Experiment(mockPlayer, ingr1, ingr2, 0);
		
		
		//Assert
		assertEquals(ingr1, exp.getIngredientOne());
		
	}
	
	@Test
	void testIngredients2() {
		
		// Arrange
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		//Act
		Experiment exp = new Experiment(mockPlayer, ingr1, ingr2, 0);
		
		
		//Assert
		assertEquals(ingr2, exp.getIngredientTwo());
		
	}
	
	@Test
	void testResult() {
		
		// Arrange
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		//Act
		Experiment exp = new Experiment(mockPlayer, ingr1, ingr2, 1);
		Potion resultPotion = Potion.makePotion(ingr1, ingr2);
		
		
		//Assert
		assertEquals(resultPotion.getName(), exp.getResultPotion().getName());
		
	}
	
	@Test
	void testResult2() {
		
		// Arrange
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(10);
		
		Aspect.initializeAllAspects();
		Ingredient.initializeIngredients();

		Ingredient ingr1 = Ingredient.getIngredient("Verdant Sprig");
		Ingredient ingr2 = Ingredient.getIngredient("Essence of Stride");


		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		//Act
		
		Player owner = mockPlayer;
		
		int initBalance = owner.getBalance();
		int initSick = owner.getSicknessLevel();
		
		Experiment exp = new Experiment(mockPlayer, ingr1, ingr2, 0);
		Potion resultPotion = exp.getResultPotion();
		
		int whereToTest = exp.getWhereToTest();
		int finalBalance = owner.getBalance();
		int finalSick = owner.getSicknessLevel();

		
		
		//Assert
		if(!resultPotion.isPositive() && whereToTest == 1) 
		{
			assertEquals(-1, finalBalance - initBalance);
		}
		else if(!resultPotion.isPositive() && owner.getSicknessLevel() != 2) 
		{
			assertEquals(1, finalSick - initSick);
		}
		else if(!resultPotion.isPositive() && owner.getSicknessLevel() == 2) 
		{
			assertEquals(0, owner.getBalance());
		}
		else {
			assertThrows(IllegalArgumentException.class, () -> exp.testExperiment());
		}
		
	}

}
