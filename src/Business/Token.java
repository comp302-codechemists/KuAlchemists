package Business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<String,Token> tokenDictionary = new HashMap<String,Token>();
	
	public static void initializeAllTokens() {
		tokenDictionary.put("by+bk+bm+", new Token(Aspect.getAspect("by+"),
				Aspect.getAspect("bk+"),Aspect.getAspect("bm+")));
		tokenDictionary.put("by+kk-km+", new Token(Aspect.getAspect("by+"),
				Aspect.getAspect("kk-"),Aspect.getAspect("km+")));
		tokenDictionary.put("by-bk-bm-", new Token(Aspect.getAspect("by-"),
				Aspect.getAspect("bk-"),Aspect.getAspect("bm-")));
		tokenDictionary.put("by-kk+km-", new Token(Aspect.getAspect("by-"),
				Aspect.getAspect("kk+"),Aspect.getAspect("km-")));
		tokenDictionary.put("ky+bk+km-", new Token(Aspect.getAspect("ky+"),
				Aspect.getAspect("bk+"),Aspect.getAspect("km-")));
		tokenDictionary.put("ky+kk-bm-", new Token(Aspect.getAspect("ky+"),
				Aspect.getAspect("kk-"),Aspect.getAspect("bm-")));
		tokenDictionary.put("ky-bk-km+", new Token(Aspect.getAspect("ky-"),
				Aspect.getAspect("bk-"),Aspect.getAspect("km+")));
		tokenDictionary.put("ky-kk+bm+", new Token(Aspect.getAspect("ky-"),
				Aspect.getAspect("kk+"),Aspect.getAspect("bm+")));
	}
	private final Aspect tokenAspects[] = new Aspect[3];
	
	private final String name;

	public Token(Aspect aspectOne, Aspect aspectTwo, Aspect aspectThree) {
				
		tokenAspects[0] = aspectOne;
		tokenAspects[1] = aspectTwo;
		tokenAspects[2] = aspectThree;
		
		this.name = aspectOne.toString() + aspectTwo.toString() + aspectThree.toString();
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
	
	@Override
	public String toString() {
		return name;
	}

	public static Map<String,Token> getTokens(){
		return tokenDictionary;
	}
}
