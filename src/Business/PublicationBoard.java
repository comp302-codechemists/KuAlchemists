package Business;

public class PublicationBoard {

	public static Player owner;
	private static PublicationBoard instance;

	public static Player getOwner() {
		return owner;
	}

	public static PublicationBoard getInstance() {
		if (instance == null) {
			instance = new PublicationBoard();
		}
		return instance;
	}

	public static Theory publishTheory(Token alchemyMarker, Ingredient ingredient) {

		if( getOwner().getBalance() >= 1) {
			Theory theory = new Theory(owner, alchemyMarker, ingredient);
			Theory.getAllTheories().add(theory);
			owner.updateBalance(-1);
			owner.setReputationPoints( owner.getReputationPoints() + 1);
			return theory;
		}

		return null;
	}

	public static Theory chooseTheory(Theory t) {

		if (!(t.getOwner().equals(owner)) && Theory.getAllTheories().contains(t)) {
			return t;
		}
		
		return null;

	}
}
