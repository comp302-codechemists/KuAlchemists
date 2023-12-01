package Business;

import java.util.Arrays;

public class Potion {
	
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private Token token;
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
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
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
							(currentAspect.getMagnitude().equals("Big") 
									? p.getMagnitude().equals("Small") 
									: p.getMagnitude().equals("Big"))
							)
					.findFirst().orElse(null);
			if(correspondingAspect != null) {
				dominantAspect = correspondingAspect;
				break;
			}
		}
	}
	
	
	
	
}
