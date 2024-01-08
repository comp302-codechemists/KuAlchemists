package Business;

public class Experiment 
{
	private enum Exp_ID {
		NEGATIVE_STUDENT,
		NEGATIVE_SELF,
		MADE_SICK,
		NO_EFFECT
	}
	
	private Player owner;
	private Ingredient ingredientOne;
	private Ingredient ingredientTwo;
	private int whereToTest;
	private Potion resultPotion;
	private Exp_ID exp_id;
	
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
	
	
	public Exp_ID getExp_id() {
		return exp_id;
	}


	public void makeExperiment() {

		// make potion
		this.resultPotion = Potion.makePotion(ingredientOne, ingredientTwo);

		// test it
		testExperiment();
	}
	
	public void testExperiment() {
		
		if (resultPotion.isPositive()) { // Player tested positive, nothing changes.
			exp_id = Exp_ID.NO_EFFECT;
			return;
		}
		
		else {
			if (whereToTest == 1) { // Player tested negative on student.
				owner.updateBalance(-1);
				exp_id = Exp_ID.NEGATIVE_STUDENT;
			}
			
			else if (whereToTest == 2) { // Player tested negative on self.
				owner.setSicknessLevel(owner.getSicknessLevel() + 1);
				exp_id = Exp_ID.NEGATIVE_SELF;
			}
			else { // WhereToTest other than 1 or 2 was entered.
				throw new IllegalArgumentException();
			}
		}
		
		if (owner.getSicknessLevel() == 3) { // Sickness level reached 3 after the experiment.
			owner.setBalance(0);
			exp_id = Exp_ID.MADE_SICK;
		}
		
		/*
		
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
		*/
			
	}
	
}
