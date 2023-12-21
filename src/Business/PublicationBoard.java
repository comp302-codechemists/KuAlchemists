package Business;

public class PublicationBoard 
{
	private static PublicationBoard instance;
	
	public static PublicationBoard getInstance() {
		if (instance == null) {
			instance = new PublicationBoard();
		}
		return instance;
	}

	public Theory publishTheory(Player owner, Token alchemyMarker, Ingredient ingredient) {

		if( owner.getBalance() >= 1) {
			
			if(Theory.getTheory(ingredient.getName()) != null) {
				System.out.println("This ingredient already have a theory on it.");
			}
			else {
				
				// create theory if not already exist
				Theory theory = new Theory(owner, alchemyMarker, ingredient);
				
				Theory.getAllTheories().add(theory);
				owner.updateBalance(-1);
				owner.setReputationPoints(owner.getReputationPoints() + 1);
				owner.getTheories().add(theory);
				return theory;
			}
			
		}

		return null;
	}

	public Theory chooseTheory(String ingredientName) {
		
		Theory theory = null;
		for(Theory t: Theory.getAllTheories()) {
			if(ingredientName.equals(t.getIngredient().getName())) {
				theory = t;
			}
		}
		return theory;

	}
	
	public boolean debunkTheory(Theory selectedTheory, int selectedAspect) {
		boolean comparaison = false;
		String signOfAspectToDebunk = selectedTheory.getAlchemyMarker().getTokenAspects()[selectedAspect].getSign();
		String actualSignOfAspect = selectedTheory.getIngredient().getToken().getTokenAspects()[selectedAspect].getSign();
		
		if(signOfAspectToDebunk.equals(actualSignOfAspect)) {
			comparaison = true;
		}
		
		return comparaison;
		
	}
}
