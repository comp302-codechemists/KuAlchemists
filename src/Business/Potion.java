package Business;

import java.util.ArrayList;
import java.util.Arrays;

public class Potion {
	
	/*
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private Token resultToken;
	private String promise;
	private Aspect dominantAspect = null;	

	public Potion(Ingredient ingredientOne, Ingredient ingredientTwo) {
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
		this.findDominantAspect();
	}

	public Potion(Ingredient ingredientOne, Ingredient ingredientTwo, String promise) {
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
		this.promise = promise;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Ingredient getIngredientOne() {
		return ingredientOne;
	}

	public void setIngredientOne(Ingredient ingredientOne) {
		this.ingredientOne = ingredientOne;
	}

	public Ingredient getIngredientTwo() {
		return ingredientTwo;
	}

	public void setIngredientTwo(Ingredient ingredientTwo) {
		this.ingredientTwo = ingredientTwo;
	}

	public Token getToken() {
		return resultToken;
	}

	public void setToken(Token token) {
		this.resultToken = token;
	}

	public String getPromise() {
		return promise;
	}

	public void setPromise(String promise) {
		this.promise = promise;
	}
	
	public Aspect getDominantAspect() {
		return dominantAspect;
	}
	
	private void findDominantAspect() {
		
		Token tokenOne = Ingredient.getToken(ingredientOne.getName());
		Token tokenTwo = Ingredient.getToken(ingredientTwo.getName());
		
		Aspect[] aspectsOne = tokenOne.getTokenAspects();
		Aspect[] aspectsTwo = tokenTwo.getTokenAspects();
		
		for(int i = 0; i < 3; i++) {
			Aspect currentAspect = aspectsOne[i];
			Aspect correspondingAspect = Arrays.stream(aspectsTwo)
					.filter(p -> p.getColor().equals(currentAspect.getColor()) &&
							p.getSign().equals(currentAspect.getSign()) &&
							(currentAspect.getMagnitude().equals("big") 
									? p.getMagnitude().equals("small") 
									: p.getMagnitude().equals("big"))
							)
					.findFirst().orElse(null);
			if(correspondingAspect != null) {
				dominantAspect = correspondingAspect;
				break;
			}
		}
		if(dominantAspect == null) {
			dominantAspect = new Aspect("neutral","neutral","neutral");
		}
	}
	*/
	
	public static final String[] potions = {"potion_blue_minus", "potion_blue_plus", "potion_green_minus",
			"potion_green_plus", "potion_red_minus", "potion_red_plus", "potion_null"};
	
	private String name;

	public Potion(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
