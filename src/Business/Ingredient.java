package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
