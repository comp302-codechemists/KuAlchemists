package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientStorage {

	private static IngredientStorage instance;
	public List<Ingredient> ingredientList = new ArrayList<Ingredient>();

	private IngredientStorage() {
		initializeStorage();

		System.out.println("Ingredient storage has been created.");
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

		// initialize all tokens
		Token.initializeTokens();

		// initialize all ingredients
		Ingredient.initializeIngredients();

		// At the beginning of the game
		// The ingredient storage ingredientList
		// will be full of all the ingredients
		ingredientList.addAll(Ingredient.ingredients);

		System.out.println("Ingredient storage ingredient list has been initialized.");
		for (Ingredient i : ingredientList) {
			System.out.println(i.name);
		}
	}

	public Ingredient getRandomIngredient() {

		if(ingredientList.isEmpty()) return null;
					
		int max = ingredientList.size() - 1;
		int min = 0;

		// Generate random int value from min to max
		int randomInt = (int) Math.floor(Math.random() * (-min + 1) + max);

		return ingredientList.remove(randomInt);
	}

	public void shuffleIngredients() {
		Collections.shuffle(ingredientList);
		System.out.println("Ingredients have been shuffled.");
	}

	public void addToBottom(Ingredient ingredient) {
		ingredientList.add(ingredient);
	}


}
