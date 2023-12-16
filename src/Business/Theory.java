package Business;

import java.util.ArrayList;
import java.util.List;

public class Theory {
	private static List<Theory> allTheories = new ArrayList<>();
	private Player owner;
	private Token alchemyMarker;
	private Ingredient ingredient;
	//private List <Player> sealers; 
	
	public Theory(Player owner, Token alchemyMarker, Ingredient ingredient) {
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

	public Token getAlchemyMarker() {
		return alchemyMarker;
	}

	public void setAlchemyMarker(Token alchemyMarker) {
		this.alchemyMarker = alchemyMarker;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public static List<Theory> getAllTheories() {
		return allTheories;
	}

	public static void setAllTheories(List<Theory> allTheories) {
		Theory.allTheories = allTheories;
	}

	public void showResult() {
		
		
	}
	
	public void chooseAspectToDebunk(Aspect asp) {
		
	}
	
	
}
