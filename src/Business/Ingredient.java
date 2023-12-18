package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ingredient {

	private String name;
	private Token token;
	
	public static final List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public static void initializeIngredients()
	{
		/*
    	 * This method will create existing ingredients in the game,
    	 * Then store them in ingredients list.
    	 * This can be done in a different class, later.
    	 * */
		
		// Create all ingredients
		ingredients.add(new Ingredient("Verdant Sprig", new Token(Aspect.getAspect("bk-"),
				Aspect.getAspect("ky-"),Aspect.getAspect("km+"))));
		ingredients.add(new Ingredient("Essence of Stride", new Token(Aspect.getAspect("kk-"),
				Aspect.getAspect("ky+"),Aspect.getAspect("bm-"))));
		ingredients.add(new Ingredient("Fungal Spore", new Token(Aspect.getAspect("ky+"),
				Aspect.getAspect("bk+"),Aspect.getAspect("km-"))));
		ingredients.add(new Ingredient("Azure Blossom", new Token(Aspect.getAspect("by-"), 
				Aspect.getAspect("bk-"), Aspect.getAspect("bm-"))));
		ingredients.add(new Ingredient("Terror Root", new Token(Aspect.getAspect("by-"),
				Aspect.getAspect("kk+"), Aspect.getAspect("km-"))));
		ingredients.add(new Ingredient("Venomous Stinger", new Token(Aspect.getAspect("by+"),
				Aspect.getAspect("kk-"), Aspect.getAspect("km+"))));
		ingredients.add(new Ingredient("Amphibian Essence", new Token(Aspect.getAspect("ky-"),
				Aspect.getAspect("kk+"), Aspect.getAspect("bm+"))));
		ingredients.add(new Ingredient("Avian Quill", new Token(Aspect.getAspect("by+"),
				Aspect.getAspect("bk+"), Aspect.getAspect("bm+"))));
			    
	    System.out.println("Ingredients initialized.");
		
	}
	public static int getIngredientIndex(String name)
	{
		Map<String, Integer> map = new HashMap<>();
		map.put("Verdant Sprig", 1);
		map.put("Essence of Stride", 2);
		map.put("Fungal Spore", 3);
		map.put("Azure Blossom", 4);
		map.put("Terror Root", 5);
		map.put("Venomous Stinger", 6);
		map.put("Amphibian Essence", 7);
		map.put("Avian Quill", 8);
		
		return map.get(name);
	}
	
	public Ingredient(String name, Token token) {
		super();
		this.name = name;
		this.token = token;
	}
	
	public static Ingredient getIngredient(String name)
	{
		for (Ingredient ingredient: ingredients)
		{
			if (ingredient.name.equals(name))
			{
				return ingredient;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "Ingredient [name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public static List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	
	
}
