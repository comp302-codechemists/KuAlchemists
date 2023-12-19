package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientStorage {

	private static IngredientStorage instance;
	public List<Ingredient> ingredientList = new ArrayList<Ingredient>();

	private IngredientStorage() {
		initializeStorage();
	}

	public static IngredientStorage getInstance() {
		if (instance == null) {
			instance = new IngredientStorage();
		}
		return instance;
	}

	private void initializeStorage() {

		/*
		 * This method will initialize all ingredient objects, and put them to the
		 * storage (the ingredients list) This can be done in a separate class later.
		 */

		// initialize all aspects
		Aspect.initializeAllAspects();

		// initialize all ingredients
		Ingredient.initializeIngredients();
		
		// initialize all tokens
		Token.initializeAllTokens();

		// At the beginning of the game
		// The ingredient storage ingredientList
		// will be full of all the ingredients
		// In the original game there is 4 of each ingredient card.
		ingredientList.addAll(Ingredient.ingredients);
		ingredientList.addAll(Ingredient.ingredients);
		ingredientList.addAll(Ingredient.ingredients);
		ingredientList.addAll(Ingredient.ingredients);
		

		System.out.println("Ingredient storage initialized.");

	}

	public Ingredient getIngredient() {

		if(ingredientList.isEmpty()) return null;
		return ingredientList.remove(0);
	}

	public void shuffleIngredients() {
		Collections.shuffle(ingredientList);
		System.out.println("Ingredients  shuffled.");
	}

	public void addToBottom(Ingredient ingredient) {
		ingredientList.add(ingredient);
	}

	// Added getters and setter for elixir of insight.
	public List<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	


}
