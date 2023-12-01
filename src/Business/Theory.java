package Business;

import java.util.List;

public class Theory {
	private Player owner;
	private AlchemyMarker alchemyMarker;
	private Ingredient ingredient;
	//private List <Player> sealers; 
	
	public Theory(Player owner, AlchemyMarker alchemyMarker, Ingredient ingredient) {
		this.owner = owner;
		this.alchemyMarker = alchemyMarker;
		this.ingredient = ingredient;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public AlchemyMarker getAlchemyMarker() {
		return alchemyMarker;
	}

	public void setAlchemyMarker(AlchemyMarker alchemyMarker) {
		this.alchemyMarker = alchemyMarker;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public void showResult() {
		
		
	}
	
	public void chooseAspectToDebunk(Aspect asp) {
		
	}
	
	
}
