package Business;

import java.util.ArrayList;
import java.util.List;

public class IngredientStorage {
	
	List<Ingredient> ingredientList;
	
	public IngredientStorage() 
	{
		ingredientList = new ArrayList<Ingredient>();
		initializeStorage();
		
		System.out.println("Ingredient storage has been created.");
	}
	
	private void initializeStorage() {
		
		/*
		 * This method will initialize all ingredient objects,
		 * and put them to the storage (the ingredients list)
		 * This can be done in a separate class later.
		 * */
		
		// initialize all aspects
		Aspect.initializeAllAspects();
		
		// initialize all tokens
		Token.initializeTokens();
		
		// initialize all ingredients
		Ingredient.initializeIngredient();
		
		System.out.println("Ingredient storage has been initialized.");
		
	}

	public static void addToBottom(Ingredient ingredient) {
		
	}

	public static Ingredient removeFromTop() {
		return null;
	}
}
