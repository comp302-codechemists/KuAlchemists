package Business;

public class Experiment {
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private String whereToTest;
	private AlchemyMarker resultToken;
	
	public Experiment(Player owner, Ingredient ingredientOne, Ingredient ingredientTwo, String whereToTest,
			AlchemyMarker resultToken) {
		this.owner = owner;
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
		this.whereToTest = whereToTest;
		this.resultToken = resultToken;
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

	public String getWhereToTest() {
		return whereToTest;
	}

	public void setWhereToTest(String whereToTest) {
		this.whereToTest = whereToTest;
	}

	public AlchemyMarker getResultToken() {
		return resultToken;
	}

	public void setResultToken(AlchemyMarker resultToken) {
		this.resultToken = resultToken;
	}
	
	public void makeExperiment(Ingredient ing1, Ingredient ing2, String whereToTest) {
		Potion potion = new Potion(ing1, ing2);
	}
	
	public void testExperiment() {
		
	}
	
	
}
