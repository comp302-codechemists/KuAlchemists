package Business;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
	
	

	String name;
	
	public static final List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	public static void initializeIngredients()
	{
		/*
    	 * This method will create existing ingredients in the game,
    	 * Then store them in ingredients list.
    	 * This can be done in a different class, later.
    	 * */
		
		// Create all ingredients
	    List<Ingredient> ingredientList = new ArrayList<>();
	    for (String name : Token.tokens.keySet()) {
	        Ingredient ingredient = new Ingredient(name); 
	        ingredientList.add(ingredient);
	    }

	    // Add all ingredients to the existing list
	    ingredients.addAll(ingredientList);
	    
	    System.out.println("All ingredients has been initialized in Ingredient class.");
		
	}
	
	public Ingredient(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Token getToken(String ingredientName) {
		return Token.tokens.get(ingredientName);
	}
	
	@Override
	public String toString() {
		return "Ingredient [name=" + name + "]";
	}
	
	
}
