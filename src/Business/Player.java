package Business;

import java.util.List;

public class Player {
	private String userName;
	private String avatarPath;
	private List<Ingredient> ingredients;
	private List<Artifact> artifacts;
	private int balance;
	private int reputationPoints;
	private DeductionBoard deductionBoard;
	
	public Player(String userName, String avatarPath) {
		this.userName = userName;
		this.avatarPath = avatarPath;
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




	private void updateBalance(int amount) {
		setBalance(getBalance() + amount);
	}
	
	private void updateReputation(int amount) {
		setReputationPoints(getReputationPoints() + amount);
	}
	
	private void addIngredientCard(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	
	private boolean removeIngredientCard(Ingredient ingredient) {
		return getIngredients().remove(ingredient);
	}
	
	private void transmuteIngredient(Ingredient ingredient) {
		if(removeIngredientCard(ingredient)) {
			updateBalance(1);
			IngredientStorage.addToBottom(ingredient);
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	private void forageForIngredient() {		
		Ingredient foragedIngredient = IngredientStorage.removeFromTop();
		if(foragedIngredient != null) {
			addIngredientCard(foragedIngredient);
		}
	}
	
	private void addArtifact(Artifact artifact) {
		getArtifacts().add(artifact);
	}
	
	private void applyArtifact(Artifact artifact) {
		
	}
	
	private void buyArtifact() {
		if(getBalance() >= 3) {
			Artifact artifact = ArtifactStorage.removeRandomArtifact();
			if(artifact != null) {
				addArtifact(artifact);
				updateBalance(-2);
				applyArtifact(artifact);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	private void makeExperiment() {
		
	}
	
	private void sellPotion() {
		
	}
	
	private void publishTheory() {
		
	}
	
	private void debunkTheory() {
		
	}
	
	private void calculateScore() {
		
	}
	
	private void putTokenToResultsTriangle() {
		
	}
}
