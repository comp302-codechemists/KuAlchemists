package Business;

public class Experiment 
{
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private int whereToTest;
	private Potion resultPotion;
	
	public Potion getResultPotion() {
		return resultPotion;
	}

	public Experiment(Player owner, Ingredient ingredientOne, Ingredient ingredientTwo, int whereToTest) {
		
		this.owner = owner;
		this.ingredientOne = ingredientOne;
		this.ingredientTwo = ingredientTwo;
		this.whereToTest = whereToTest;
		
		makeExperiment();
	}
	
	private boolean repOk() {
	    return owner != null &&
	           ingredientOne != null &&
	           ingredientTwo != null &&
	           (whereToTest == 0 || whereToTest == 1) &&
	           resultPotion != null;
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

	public int getWhereToTest() {
		return whereToTest;
	}

	public void setWhereToTest(int whereToTest) {
		this.whereToTest = whereToTest;
	}

	public void makeExperiment() {

		// make potion
		this.resultPotion = Potion.makePotion(ingredientOne, ingredientTwo);

		// test it
		testExperiment();
	}
	
	public void testExperiment() {
		
		if(!resultPotion.isPositive() && whereToTest == 1) 
		{
			owner.updateBalance(-1);
		}
		else if(!resultPotion.isPositive() && owner.getSicknessLevel() != 2) 
		{
			owner.setSicknessLevel(owner.getSicknessLevel() + 1);
		}
		else if(!resultPotion.isPositive() && owner.getSicknessLevel() == 2) 
		{
			owner.updateBalance(- owner.getBalance());
		}
		else {
			throw new IllegalArgumentException();
		}
			
	}
	
}
