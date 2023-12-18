package Business;
public class Token {
	
	/*
	 * Token has 3 aspects.
	 * Each aspect has a color, a sign 
	 * and a magnitude(small or big)
	 * Token is the combination of these 3 aspects.
	 * Some tokens symbolize an ingredient,
	 * while some others do not.
	 * */
	
	private static final String[] markers = {"by+bk+bm+", "by+kk-km+","by-bk-bm-", "by-kk+km-", 
											"ky+bk+km-", "ky+kk-bm-", "ky-bk-km+", "ky-kk+bm+"};

	private final Aspect tokenAspects[] = new Aspect[3];
	
	private final String name;

	public Token(Aspect aspectOne, Aspect aspectTwo, Aspect aspectThree) {
				
		tokenAspects[0] = aspectOne;
		tokenAspects[1] = aspectTwo;
		tokenAspects[2] = aspectThree;
		
		this.name = aspectOne.getMagnitude() + aspectOne.getColor() + aspectOne.getSign();
	}

	public static String[] getTokenNames()
	{
		return markers;
	}
	public Aspect[] getTokenAspects() {
		return tokenAspects;
	}

	public String getName() {
		return name;
	}
	
}
