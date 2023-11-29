package Business;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
	
	String name;
	
	public static List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public static void initializeIngredient()
	{
		/*
    	 * This method will create existing ingredients in the game,
    	 * Then store them in ingredients list.
    	 * This can be done in a different class, later.
    	 * */
		
		// Convert Set<String> to List<Ingredient>
	    List<Ingredient> ingredientList = new ArrayList<>();
	    for (String name : Token.tokens.keySet()) {
	        Ingredient ingredient = new Ingredient(name); 
	        ingredientList.add(ingredient);
	    }

	    // Add all ingredients to the existing list
	    ingredients.addAll(ingredientList);
	    
	    System.out.println("Ingredient has been initialized.");
		
	}
	
	public Ingredient(String name) {
		super();
		this.name = name;
	}
	
	public static Token getToken(String ingredientName) {
		return Token.tokens.get(ingredientName);
	}
	
	
}
