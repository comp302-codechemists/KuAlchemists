package Business;

import java.util.ArrayList;
import java.util.List;

import Exceptions.NotFoundInStorageException;

public class Player {
	private String userName;
	private String avatarPath;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private List<Artifact> artifacts = new ArrayList<Artifact>();
	private List<Theory> theories = new ArrayList<Theory>();
	private int balance;
	private int reputationPoints;
	private DeductionBoard deductionBoard = new DeductionBoard();
	private int sicknessLevel;
	private int goldtToBePayedToArtifact;
	private int publishTheoryCharge;
	private List<removeArtifactListener> removeArtifactListeners = new ArrayList<removeArtifactListener>();
	
	public Player(String userName, String avatarPath) {
		
		this.userName = userName;
		this.avatarPath = avatarPath;
		this.goldtToBePayedToArtifact = -3;
		this.publishTheoryCharge = -1;
		populateArtifactListeners();
	}
	
	public Player(String userName, String avatarPath, List<Ingredient> ingredients, List<Artifact> artifacts,
		int balance, int reputationPoints, DeductionBoard deductionBoard) {
		this.userName = userName;
		this.avatarPath = avatarPath;
		this.ingredients = ingredients;
		this.artifacts = artifacts;
		this.balance = balance;
		this.reputationPoints = reputationPoints;
		this.deductionBoard = deductionBoard;
		this.goldtToBePayedToArtifact = -3;
		this.publishTheoryCharge = -1;
		populateArtifactListeners();
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		ingredients.add(ingredient);
	}
	


	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getAvatarPath() {
		return avatarPath;
	}




	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}




	public List<Ingredient> getIngredients() {
		return ingredients;
	}




	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}




	public List<Artifact> getArtifacts() {
		return artifacts;
	}




	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}




	public int getBalance() {
		return balance;
	}




	public void setBalance(int balance) {
		this.balance = balance;
	}




	public int getReputationPoints() {
		return reputationPoints;
	}




	public void setReputationPoints(int reputationPoints) {
		this.reputationPoints = reputationPoints;
	}




	public DeductionBoard getDeductionBoard() {
		return deductionBoard;
	}




	public void setDeductionBoard(DeductionBoard deductionBoard) {
		this.deductionBoard = deductionBoard;
	}
	
	
	
	public int getSicknessLevel() {
		return sicknessLevel;
	}

	
	
	public void setSicknessLevel(int sicknessLevel) {
		this.sicknessLevel = sicknessLevel;
	}
	
	public int getGoldtToBePayedToArtifact() {
		return goldtToBePayedToArtifact;
	}

	public void setGoldtToBePayedToArtifact(int goldtToBePayedToArtifact) {
		this.goldtToBePayedToArtifact = goldtToBePayedToArtifact;
	}
	
	public List<Theory> getTheories() {
		return theories;
	}


	public void setTheories(List<Theory> theories) {
		this.theories = theories;
	}
	
	public List<removeArtifactListener> getRemoveArtifactListeners() {
		return removeArtifactListeners;
	}

	public void setRemoveArtifactListeners(List<removeArtifactListener> removeArtifactListeners) {
		this.removeArtifactListeners = removeArtifactListeners;
	}
	

	public Potion makeExperiment(List<String> ingredientList, int whereToTest) {

		Ingredient ingredientOne = Ingredient.getIngredient(ingredientList.get(0));
		Ingredient ingredientTwo = Ingredient.getIngredient(ingredientList.get(1));

		// remove the ingredients from the user's ingredient list
		removeIngredient(ingredientOne);
		removeIngredient(ingredientTwo);
		
		// create an experiment, conduct it, test it
		Experiment experiment = new Experiment(this, ingredientOne, 
				ingredientTwo, whereToTest);
			
		// get the potion created
		Potion potion = experiment.getResultPotion();
		
		return potion;

	}
	
	
	
	public void updateBalance(int amount) {
		setBalance(getBalance() + amount);
	}
	
	
	// I made it public so that I can use it in KUAlchGame to get the winner. And made it return float.
	public float calculateScore() {
		int score = 0;
		score += getReputationPoints() * 10;
		// 1 artifact -> 2 gold, 3 gold -> 1 score point => 1 artifact -> 2/3 score point.
		score += getArtifacts().size() * 2 / 3; 
		return score;
	}
	
	public String forageForIngredient() {
		
		Ingredient foragedIngredient = IngredientStorage.getInstance().getIngredient();
		if(foragedIngredient != null) {
			System.out.println("Previous ingredients");
			getIngredients().forEach(System.out::println);
			
			addIngredientCard(foragedIngredient);
			
			System.out.println("New ingredients");
			getIngredients().forEach(System.out::println);
			
			System.out.printf("Ingredient %s is added to the player's storage%n",foragedIngredient.getName());
			GameEvent events = new GameEvent(null, this, GameEvent.EventID.FORAGE_INGREDIENT);
			
			return foragedIngredient.getName();
			
		}
		else {
			System.out.println("Ingredient Storage is empty!");
			return null;
		}
	}
	
	public String transmuteIngredient(Ingredient ingredient) {
		
		System.out.println("");
		System.out.println("Previous ingredients");
		getIngredients().forEach(System.out::println);
	    System.out.printf("Old Balance: %d%n",getBalance());
	    
		if(removeIngredient(ingredient)) {									
			updateBalance(1);
			IngredientStorage.getInstance().addToBottom(ingredient);
						
		}
		else {
			throw new IllegalArgumentException();
		}
		
		System.out.println("New ingredients");
		getIngredients().forEach(System.out::println);
		System.out.printf("New Balance: %d%n",getBalance());
		System.out.printf("Ingredient %s is removed from the player's storage%n",ingredient.getName());
		GameEvent events = new GameEvent(null, this, GameEvent.EventID.TRANSMUTE_INGREDIENT);
		
		return ingredient.getName();
	}
	
	public String buyArtifact() {
		
		if(getBalance() >= -getGoldtToBePayedToArtifact()) {
			
			Artifact artifact = ArtifactStorage.getArtifact();
			if(artifact != null) {
				
				System.out.println();
				System.out.println("Previous artifacts");
				getArtifacts().forEach(System.out::println);
			    System.out.printf("Old Balance: %d%n",getBalance());
			    
				addArtifact(artifact);
				updateBalance(getGoldtToBePayedToArtifact());
				applyArtifact(artifact);
				
				System.out.println("New artifacts");
				getArtifacts().forEach(System.out::println);
				System.out.printf("New Balance: %d%n",getBalance());
				System.out.printf("Artifact %s is added to the player's storage%n",artifact.getName());
				GameEvent events = new GameEvent(null, this, GameEvent.EventID.BUY_ARTIFACT);
				
				return artifact.getName();
			}
			else {
				System.out.println("Artifact Storage is empty!");
			}
		}
		else {
			System.out.println("Balance is unsufficient, come back when you have more gold :D");
		}
		
		return null;
	}
	

	public void applyArtifact(Artifact artifact) {
		artifact.applyArtifact(this);
	}
	
	public int sellPotion(Ingredient ingredientOne, Ingredient ingredientTwo, String promise) 
	{
		Potion potion = Potion.makePotion(ingredientOne, ingredientTwo);

		// remove the ingredients from the user's ingredient list
		removeIngredient(ingredientOne);
		removeIngredient(ingredientTwo);
		
		int payment;
		
		if (potion.getSign().equals(promise))
		{
			payment = 3;
		}
		else if (potion.getSign().equals("+") &&
				promise.equals("-"))
		{
			payment = 1;
		}
		else if (potion.getSign().equals("-") &&
				promise.equals("+"))
		{
			payment = 1;
		}
		else // (promise.equals("0")), but the potion is not neutral
		{
			payment = 2;
		}
		setBalance(payment);
		
		System.out.println(potion.getSign());
		System.out.println(promise);
		return payment;
		
	}
	
	public void publishTheory(String selectedMarker, String selectedTheory) {
		
		if (this.getBalance() < 1) {
			
			System.out.println("Insufficient balance to publish a theory");
		}
		else {
			PublicationBoard.getInstance().publishTheory(this, Token.getTokens().get(selectedMarker), Ingredient.getIngredient(selectedTheory));
			GameEvent event = new GameEvent(null, this, GameEvent.EventID.PUBLISH_THEORY);
			System.out.println("Theories");
			getTheories().forEach(System.out::println);
		}
	}
	
	public void debunkTheory(String selectedTheory,int selectedAspect) {
		Theory theory = PublicationBoard.getInstance().chooseTheory(selectedTheory);
		//Aspect aspect = Aspect.getAspect(selectedAspect);
		if(theory == null) {
			System.out.println("Theory not found");
		}
		else if(theory.getOwner().getUserName().equals(this.getUserName())) {
			System.out.println("You cannot debunk your own theory");
		}
		else {
			boolean result = PublicationBoard.getInstance().debunkTheory(theory, selectedAspect);
			GameEvent event = new GameEvent(null, this, GameEvent.EventID.DEBUNK_THEORY);
		}
	}
	
	public void putTokenToResultsTriangle(int selectedTriangle,String name, int selectedLeft) {
		deductionBoard.addDeduction(selectedTriangle, name);
		deductionBoard.addExistingItem(selectedTriangle, selectedLeft);
	}

	public int getPublishTheoryCharge() {
		return publishTheoryCharge;
	}

	public void setPublishTheoryCharge(int publishTheoryCharge) {
		this.publishTheoryCharge = publishTheoryCharge;
	}
	
	public void removeArtifact(Artifact artifact) throws NotFoundInStorageException {
		if(getArtifacts().contains(artifact)) {
			getArtifacts().remove(artifact);
			handleRemove();
		}
		else {
			throw new NotFoundInStorageException("Artifact is not found in the storage");
		}
		
	}
	@Override
	public String toString() {
		return "Player [userName=" + userName + ", balance=" + balance + ", reputationPoints=" + reputationPoints
				+ ", sicknessLevel=" + sicknessLevel + "]";
	}

	private void updateReputation(int amount) {
		setReputationPoints(getReputationPoints() + amount);
	}	
	
	private void addIngredientCard(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
		
	
	private boolean removeIngredient(Ingredient ingredient) {
		return getIngredients().remove(ingredient);
	}
		
		
	private void addArtifact(Artifact artifact) {
		getArtifacts().add(artifact);		
	}
	
	private void populateArtifactListeners() {
		getRemoveArtifactListeners().add(new DiscountArtifact("DiscountArtifact",1,"Artifact","AllGame")) ;
		getRemoveArtifactListeners().add(new GoldBoosterArtifact(1,"AllGame","GoldBoosterArtifact")) ;
		getRemoveArtifactListeners().add(new PotionEffectBoosterArtifact("PotionEffectBooster",1,"PositivePotion","AllGame")) ;
		getRemoveArtifactListeners().add(new ReputationBoosterArtifact("ReputationBoosterArtifact",1,"PublishTheory","AllGame")) ;
		getRemoveArtifactListeners().add(new ScorePointBoosterArtifact("ScorePointBoosterArtifact",1,"AllGame")) ;
		getRemoveArtifactListeners().add(new printingPressArtifact("PrintingPressArtifact")) ;
	}
	
	private void handleRemove() {
		for(removeArtifactListener ral: getRemoveArtifactListeners()) {
			ral.handleRemove(this);
		}
	}

	
	
	/*private int enumeratePromises(String promise) {
		int return_val = 0;
		
		switch(promise) {
		case "+":
			return_val = 3;
			break;
		case "+ or 0":
			return_val = 2;
			break;
		case "Nothing":
			return_val = 1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		return return_val;
	}
	
	private int enumeratePotionResult(Potion potion) {
		int return_val = 0;
		
		switch(potion.getDominantAspect().getSign()) {
		case "positive":
			return_val = 3;
			break;
		case "neutral":
			return_val = 2;
			break;
		case "negative":
			return_val = 1;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		return return_val;
	}
	*/
	
		
	
		
	
	
	
	
	

	
	
	
	
	

	
	
	


}
