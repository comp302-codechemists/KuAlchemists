package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.net.Socket;

import Exceptions.ArtifactStorageIsEmptyException;
import Exceptions.InsufficientBalanceException;
import Exceptions.InsufficientIngredientException;
import Exceptions.InvalidTheoryDebunkException;
import Exceptions.NoPromiseException;
import Exceptions.PlayerDoesNotHaveSuchIngredientException;
import Exceptions.TheoryNotFoundException;
import Factories.ArtifactFactory;

public class Player {
	
	//================================================================================
    // Properties
    //================================================================================

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
	private int gainedReputationPointWhilePublishing;
	private int numberOfIngreientToBeRemovedWhileExperimenting;
	private List<removeArtifactListener> removeArtifactListeners = new ArrayList<removeArtifactListener>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	public Socket socket;

	//================================================================================
    // Constructors
    //================================================================================

	public Player(String userName, String avatarPath) {
		
		this.userName = userName;
		this.avatarPath = avatarPath;
		this.goldtToBePayedToArtifact = -3;
		this.publishTheoryCharge = -1;
		this.gainedReputationPointWhilePublishing = 1;
		this.numberOfIngreientToBeRemovedWhileExperimenting = 2;
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
		this.gainedReputationPointWhilePublishing = 1;
		this.numberOfIngreientToBeRemovedWhileExperimenting = 2;
	}
	
	//================================================================================
    // Accessors
    //================================================================================

	
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
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public void setTheories(List<Theory> theories) {
		this.theories = theories;
	}
	
	public int getGainedReputationPointWhilePublishing() {
		return gainedReputationPointWhilePublishing;
	}

	public void setGainedReputationPointWhilePublishing(int gainedReputationPointWhilePublishing) {
		this.gainedReputationPointWhilePublishing = gainedReputationPointWhilePublishing;
	}
	
	public int getNumberOfIngreientToBeRemovedWhileExperimenting() {
		return numberOfIngreientToBeRemovedWhileExperimenting;
	}

	public void setNumberOfIngreientToBeRemovedWhileExperimenting(int numberOfIngreientToBeRemovedWhileExperimenting) {
		this.numberOfIngreientToBeRemovedWhileExperimenting = numberOfIngreientToBeRemovedWhileExperimenting;
	}
	
	//================================================================================
    // Methods
    //================================================================================

	public void addIngredient(Ingredient ingredient)
	{
		ingredients.add(ingredient);
	}
	
	/**
	 * Makes the experiment, removes the used ingredients from the user's ingredient list
	 * 
	 * Requires:
	 *   * 2 ingredients which exist in user's ingredient list.
	 *   * whereToTest information
	 * Modifies:
	 *   * User's ingredients list.
	 * Effects:
	 *   * The experiment will be conducted, the user's ingredients list
	 *   * will change, the details will be handled in Experiment class.	
	 *   * A poiton will be returned to be displayed.
	 * @throws PlayerDoesNotHaveSuchIngredientException 
	 */

	public Potion makeExperiment(List<String> ingredientList, int whereToTest) throws PlayerDoesNotHaveSuchIngredientException {

		Ingredient ingredientOne = Ingredient.getIngredient(ingredientList.get(0));
		Ingredient ingredientTwo = Ingredient.getIngredient(ingredientList.get(1));

		
		if (!this.ingredients.contains(ingredientOne))
		{
			throw new PlayerDoesNotHaveSuchIngredientException( String.format("Player does not have ingredient %s\n",  ingredientOne.getName()));
		}
		if (!this.ingredients.contains(ingredientTwo))
		{
			throw new PlayerDoesNotHaveSuchIngredientException( String.format("Player does not have ingredient %s\n",  ingredientTwo.getName()));
		}
		
		// remove the ingredients from the user's ingredient list
		removeIngredient(ingredientOne);
		removeIngredient(ingredientTwo);
		
		
		// create an experiment, conduct it, test it
		Experiment experiment = new Experiment(this, ingredientOne, 
				ingredientTwo, whereToTest);
			
		// get the potion created
		Potion potion = experiment.getResultPotion();
		
		populateArtifactListeners("experiment");
		handleRemove();
	
		return potion;

	}
	
	public Potion makeExperiment(List<String> ingredientList, int whereToTest, String keptIngredient) {

		Ingredient ingredientOne = Ingredient.getIngredient(ingredientList.get(0));
		Ingredient ingredientTwo = Ingredient.getIngredient(ingredientList.get(1));
		
		// remove the ingredients from the user's ingredient list
		removeIngredient((ingredientOne.getName().equals(keptIngredient) ? ingredientTwo : ingredientOne));
		
		// create an experiment, conduct it, test it
		Experiment experiment = new Experiment(this, ingredientOne, 
				ingredientTwo, whereToTest);
			
		// get the potion created
		Potion potion = experiment.getResultPotion();
		
		populateArtifactListeners("experiment");
		handleRemove();
		

		return potion;

	}
	
	public void updateBalance(int amount) {
		setBalance(getBalance() + amount);
	}
	
	/**
	 * Calculates the player's score based on reputation points and artifacts.
	 * 
	 * Requires:
	 *   * None.
	 * Modifies:
	 *   * None.
	 * Effects:
	 *   *	  Player's score will be calculated based on:
	 *   	  1 artifact -> 2 gold, 3 gold -> 1 score point => 1 artifact -> 2/3 score point.
	 *        score = (reputationPoints * 10) + (totalArtifacts player has * 2 / 3)
	 *   A float will be returned as the calculated score.
	 */
	
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
	
	public String transmuteIngredient(Ingredient ingredient) throws PlayerDoesNotHaveSuchIngredientException {
		
		System.out.println("");
		System.out.println("Previous ingredients");
		getIngredients().forEach(System.out::println);
	    System.out.printf("Old Balance: %d%n",getBalance());
	    
		if(removeIngredient(ingredient)) {									
			updateBalance(1);
			IngredientStorage.getInstance().addToBottom(ingredient);
						
		}
		else {
			throw new PlayerDoesNotHaveSuchIngredientException(String.format("Player does not have ingredient %s\n", ingredient.getName()));
		}
		
		System.out.println("New ingredients");
		getIngredients().forEach(System.out::println);
		System.out.printf("New Balance: %d%n",getBalance());
		System.out.printf("Ingredient %s is removed from the player's storage%n",ingredient.getName());
		GameEvent events = new GameEvent(null, this, GameEvent.EventID.TRANSMUTE_INGREDIENT);
		
		return ingredient.getName();
	}
	
	public String buyArtifact() throws ArtifactStorageIsEmptyException, InsufficientBalanceException {
		
		if(getBalance() >= -getGoldtToBePayedToArtifact()) {
			
			Artifact artifact = ArtifactStorage.getArtifact();
			if(artifact != null) {
							
				System.out.println();
				System.out.println("Previous artifacts");
				getArtifacts().forEach(System.out::println);
				int oldBalance = getBalance();
			    System.out.printf("Old Balance: %d%n",oldBalance);
			    
			    if(!artifact.getName().equals("ElixirOfInsightArtifact")) {
			    	addArtifact(artifact);
			    }
				
				updateBalance(getGoldtToBePayedToArtifact());
				applyArtifact(artifact);
				
				
				int newBalance = getBalance();
				if(oldBalance - newBalance == 2) {
					populateArtifactListeners("buy artifact");
					handleRemove();
				}
				
				System.out.println("New artifacts");
				getArtifacts().forEach(System.out::println);				
				System.out.printf("New Balance: %d%n",newBalance);
				System.out.printf("Artifact %s is added to the player's storage%n",artifact.getName());
				
				GameEvent events = new GameEvent(null, this, GameEvent.EventID.BUY_ARTIFACT);

				
				return artifact.getName();
			}
			else {
				throw new ArtifactStorageIsEmptyException();
			}
		}
		else {
			throw new InsufficientBalanceException();
		}
	}
	

	public void applyArtifact(Artifact artifact) {
		artifact.applyArtifact(this);
	}
	
	public int sellPotion(Ingredient ingredientOne, Ingredient ingredientTwo, String promise) throws InsufficientIngredientException, NoPromiseException 
	{
		//@requires: not null ingredientOne and ingredientTwo; not null and not empty promise
		
		//@effects: if ingredientOne, ingredientTwo are not null and promise is not null or empty
		//makePotion method of Potion class is called which deducts the outcome of the mixation of
		//these two ingredients based on an algorithm written inside this method. The ingredients are 
		//removed from the storage of the player and the payment is calculated according to
		//value of promise and outcome of potion. If promise is equal to outcome 3 gold will be payed,
		//if outcome is + but promise is - then 1 gold will be payed. Vice versa, 1 gold will be payed.
		//And for any other circumstances 2 gold will be payed.
		
		if(ingredientOne == null || ingredientTwo == null) {
			throw new InsufficientIngredientException();
		}
		if(promise == null || promise.equals("")) {
			throw new NoPromiseException();
		}
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
		updateBalance(payment);
		
		System.out.println(potion.getSign());
		System.out.println(promise);
		return payment;
		
	}
	
	
	/**
	* Updates the balance, reputation points, and the publication board.
 	* 
 	* Requires: Initializes player, ingredients and aspects. 
 	* 
 	* Modifies: None
 	*   
 	* Effects:
 	*   Updates the common publication board. Player's reputation point and balance.
	 * @throws InsufficientBalanceException 
    */
	
	public void publishTheory(String selectedMarker, String selectedTheory) throws InsufficientBalanceException {
		
		if (this.getBalance() < 1) {
			throw new InsufficientBalanceException("Insufficient balance to publish a theory");
		}
		else {
			PublicationBoard.getInstance().publishTheory(this, Token.getTokens().get(selectedMarker), Ingredient.getIngredient(selectedTheory));
			populateArtifactListeners("publish");
			handleRemove();
			GameEvent event = new GameEvent(null, this, GameEvent.EventID.PUBLISH_THEORY);
			System.out.println("Theories");
			getTheories().forEach(System.out::println);
		}
	}
	
	public boolean debunkTheory(String selectedTheory, String selectedAspect)  {
		Theory theory = PublicationBoard.getInstance().chooseTheory(selectedTheory);
		boolean result = false;
		//Aspect aspect = Aspect.getAspect(selectedAspect);
		if(theory == null) {
			System.out.println("Invalid theory");
			return false;
		}
		else {
			result = PublicationBoard.getInstance().debunkTheory(theory, selectedAspect);
			GameEvent event = new GameEvent(null, this, GameEvent.EventID.DEBUNK_THEORY);
		}
		
		return result;
	}
	
	public boolean isSuccesfullDebunk(String selectedTheory) {
				
		Theory theory = PublicationBoard.getInstance().chooseTheory(selectedTheory);				
		
		return !theory.getOwner().getUserName().equals(this.getUserName());
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
	
	
	
	/*public void removeArtifact(Artifact artifact) throws NotFoundInStorageException {
		if(getArtifacts().contains(artifact)) {
			getArtifacts().remove(artifact);
		}
		else {
			throw new NotFoundInStorageException("Artifact is not found in the storage");
		}
		
	}*/
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
	
	private void populateArtifactListeners(String source) {
		
		this.removeArtifactListeners = new ArrayList<removeArtifactListener>();
		
		Artifact discountArtifact = ArtifactFactory.getInstance().getArtifacts("DiscountArtifact");
		Artifact reputationBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("ReputationBoosterArtifact");
		Artifact printingPressArtifact = ArtifactFactory.getInstance().getArtifacts("PrintingPressArtifact");
		Artifact wisdomIdolArtifact = ArtifactFactory.getInstance().getArtifacts("WisdomIdolArtifact");
		Artifact magicMortarArtifact = ArtifactFactory.getInstance().getArtifacts("MagicMortarArtifact");
		
		switch(source) {			
			case "experiment":
				this.removeArtifactListeners.add((removeArtifactListener) magicMortarArtifact);
				break;
			case "publish":
				this.removeArtifactListeners.add((removeArtifactListener) printingPressArtifact);
				this.removeArtifactListeners.add((removeArtifactListener) reputationBoosterArtifact);
			case "buy artifact":
				this.removeArtifactListeners.add((removeArtifactListener) discountArtifact);
			default:
				break;
		}
	}
	
	private void handleRemove() {
		
		for(removeArtifactListener ral : removeArtifactListeners) {
			Optional<Artifact> wanted_artifact = getArtifacts().stream().filter(artifact -> artifact.getName().equals(((Artifact)ral).getName())).findFirst();
			if(wanted_artifact.isPresent()) {
				getArtifacts().remove(wanted_artifact.get());
				ral.handleRemove(this);
			}

		}
	}


}