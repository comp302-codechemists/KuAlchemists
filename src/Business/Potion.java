package Business;

public class Potion {
	
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private AlchemyMarker token;
	private String promise;
	
	public Potion(Ingredient ingredientOne, Ingredient ingredientTwo) {
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
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

	public AlchemyMarker getToken() {
		return token;
	}

	public void setToken(AlchemyMarker token) {
		this.token = token;
	}

	public String getPromise() {
		return promise;
	}

	public void setPromise(String promise) {
		this.promise = promise;
	}
	
	
	
	
}
