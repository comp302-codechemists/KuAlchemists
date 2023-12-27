package UnitTests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Business.Aspect;
import Business.Ingredient;
import Business.Player;
import Business.Token;

class SellPotionTests {

	@Test
	void example1() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(0);
		
		Aspect.initializeAllAspects();
		
		Ingredient ingr1 = new Ingredient("test", new Token(Aspect.getAspect("bk-"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+")));
		
		Ingredient ingr2 = new Ingredient("test", new Token(Aspect.getAspect("kk-"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-")));
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.sellPotion(ingr1, ingr2, "-");
		
		assertEquals(mockPlayer.getBalance(),3);
		
	}
	
	@Test
	void example2() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(0);
		
		Aspect.initializeAllAspects();
		
		Ingredient ingr1 = new Ingredient("test", new Token(Aspect.getAspect("bk+"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+")));
		
		Ingredient ingr2 = new Ingredient("test", new Token(Aspect.getAspect("kk+"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-")));
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.sellPotion(ingr1, ingr2, "-");
		
		assertEquals(mockPlayer.getBalance(),1);
		
	}
	
	@Test
	void example3() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(0);
		
		Aspect.initializeAllAspects();
		
		Ingredient ingr1 = new Ingredient("test", new Token(Aspect.getAspect("bk-"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+")));
		
		Ingredient ingr2 = new Ingredient("test", new Token(Aspect.getAspect("kk-"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-")));
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.sellPotion(ingr1, ingr2, "+");
		
		assertEquals(mockPlayer.getBalance(),1);
		
	}
	
	@Test
	void example4() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(0);
		
		Aspect.initializeAllAspects();
		
		Ingredient ingr1 = new Ingredient("test", new Token(Aspect.getAspect("bk-"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+")));
		
		Ingredient ingr2 = new Ingredient("test", new Token(Aspect.getAspect("kk-"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-")));
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.sellPotion(ingr1, ingr2, "0");
		
		assertEquals(mockPlayer.getBalance(),2);
		
	}
	
	@Test
	void example5() {
		
		Player mockPlayer = new Player("test","test");
		mockPlayer.setBalance(0);
		
		Aspect.initializeAllAspects();
		
		Ingredient ingr1 = new Ingredient("test", new Token(Aspect.getAspect("bk-"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+")));
		
		Ingredient ingr2 = new Ingredient("test", new Token(Aspect.getAspect("kk-"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-")));
		
		mockPlayer.addIngredient(ingr1);
		mockPlayer.addIngredient(ingr2);
		
		mockPlayer.sellPotion(ingr1, ingr2, "0");
		
		assertTrue(mockPlayer.getIngredients().isEmpty());
	}

}
