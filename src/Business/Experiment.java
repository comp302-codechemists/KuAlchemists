package Business;

public class Experiment {
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private String whereToTest;
	private Aspect resultToken;
	
	public Experiment(Player owner, Ingredient ingredientOne, Ingredient ingredientTwo, String whereToTest) {
		this.owner = owner;
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
		this.whereToTest = whereToTest;
		makeExperiment(ingredientOne, ingredientTwo, whereToTest);
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

	public Aspect getResultToken() {
		return resultToken;
	}

	public void setResultToken(Aspect resultToken) {
		this.resultToken = resultToken;
	}
	
	public void makeExperiment(Ingredient ing1, Ingredient ing2, String whereToTest) {
		/*Potion potion = new Potion(ing1, ing2);
		this.resultToken = potion.getDominantAspect();
		testExperiment(resultToken);*/
	}
	
	public void testExperiment(Aspect resultToken) {
		
		if(resultToken.getSign().equals("Negative") && whereToTest.equals("Student")) {
			owner.updateBalance(-1);
		}
		else if(resultToken.getSign().equals("Negative") && owner.getSicknessLevel() != 2) {
			owner.setSicknessLevel(owner.getSicknessLevel() + 1);
		}
		else if(resultToken.getSign().equals("Negative") && owner.getSicknessLevel() == 2) {
			owner.updateBalance(- owner.getBalance());
		}
		else {
			throw new IllegalArgumentException();
		}
			
	}
	
	
}
