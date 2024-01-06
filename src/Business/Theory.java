package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theory {
	private static List<Theory> allTheories = new ArrayList<>();
	private static List<String> unavailableIngredients = new ArrayList<>();
	private static Map<Theory,Boolean> debunkedTheories = new HashMap<>();
	private Player owner;
	private Token alchemyMarker;
	private Ingredient ingredient;
	//private List <Player> sealers; 
	
	public Theory(Player owner, Token alchemyMarker, Ingredient ingredient) {
		this.owner = owner;
		this.alchemyMarker = alchemyMarker;
		this.ingredient = ingredient;
		allTheories.add(this);
		unavailableIngredients.add(ingredient.getName());
	}

	public static List<String> getUnavailableIngredients()
	{
		return unavailableIngredients;
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

	@Override
	public String toString() {
		return "Theory [owner=" + owner + ", alchemyMarker=" + alchemyMarker + ", ingredient=" + ingredient + "]";
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
	
	public static Theory getTheory(String ingredientName) {
		Theory theory = null;
		for(Theory t: allTheories) {
			if(ingredientName.equals(t.getIngredient().getName())) {
				theory = t;
			}
		}
		return theory;
	}

	public static Map<Theory,Boolean> getDebunkedTheories() {
		return debunkedTheories;
	}

	public static void setDebunkedTheories(Map<Theory,Boolean> debunkedTheories) {
		Theory.debunkedTheories = debunkedTheories;
	}
	
	
}