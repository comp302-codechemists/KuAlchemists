package Business;

/*
 * This class stores the possible potions,
 * each potion has a name.
 * There exists an image under potionImages folder
 * with the same name of the potion. 
 * */

public class Potion {
	
	public static final String[] potions = {"m-", "m+", "y-",
			"y+", "k-", "k+", "0"};
	
	private String name;

	public Potion(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Potion makePotion(Ingredient ingredientOne, Ingredient ingredientTwo)
	{
		Potion potion = new Potion("0");
		
		for (Aspect ingredientOneAspect: ingredientOne.getToken().getTokenAspects())
		{
			for (Aspect ingredientTwoAspect: ingredientTwo.getToken().getTokenAspects())
			{
				// the same color
				if (ingredientOneAspect.getColor().equals(ingredientTwoAspect.getColor()))
				{
					// the same sign
					if (ingredientOneAspect.getSign().equals(ingredientTwoAspect.getSign()))
					{
						// the opposite magnitude
						if (!ingredientOneAspect.getMagnitude().equals(ingredientTwoAspect.getMagnitude()))
						{
							System.out.println("Color: " + ingredientOneAspect.getColor() + " - " + ingredientTwoAspect.getColor());
							System.out.println("Sign: " + ingredientOneAspect.getSign() + " - " + ingredientTwoAspect.getSign());
							System.out.println("Size: " + ingredientOneAspect.getMagnitude() + " - " + ingredientTwoAspect.getMagnitude());

							String potionName = ingredientOneAspect.getColor() + ingredientOneAspect.getSign();
							potion = new Potion(potionName);
							break;
						}
					}
				}
			}
		}
		
			return potion;
	}
}

/*private void findDominantAspect() {
	
	Token tokenOne = Ingredient.getToken(ingredientOne.getName());
	Token tokenTwo = Ingredient.getToken(ingredientTwo.getName());
	
	Aspect[] aspectsOne = tokenOne.getTokenAspects();
	Aspect[] aspectsTwo = tokenTwo.getTokenAspects();
	
	for(int i = 0; i < 3; i++) {
		Aspect currentAspect = aspectsOne[i];
		Aspect correspondingAspect = Arrays.stream(aspectsTwo)
				.filter(p -> p.getColor().equals(currentAspect.getColor()) &&
						p.getSign().equals(currentAspect.getSign()) &&
						(currentAspect.getMagnitude().equals("big") 
								? p.getMagnitude().equals("small") 
								: p.getMagnitude().equals("big"))
						)
				.findFirst().orElse(null);
		if(correspondingAspect != null) {
			dominantAspect = correspondingAspect;
			break;
		}
	}
	if(dominantAspect == null) {
		dominantAspect = new Aspect("neutral","neutral","neutral");
	}
}*/



