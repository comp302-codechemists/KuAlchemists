package Business;

import java.util.HashMap;
import java.util.List;
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
	
	public static Map<String, Token> tokens = new HashMap<String, Token>();
	public static final String[] markers = {"by+bk+bm+", "by+kk-km+","by-bk-bm-", "by-kk+km-", 
											"ky+bk+km-", "ky+kk-bm-", "ky-bk-km+", "ky-kk+bm+"};
	
	public static void initializeTokens()
	{
		/*
		 * this method creates all meaningful tokens,
		 * and then store them in a map.
		 * */
		
		tokens.put("Verdant Sprig", new Token("big-negative-red",
											  "small-negative-green",
											  "small-positive-blue", "ky-bk-km+"));
		tokens.put("Essence of Stride", new Token("small-negative-red",
												  "small-positive-green",
												  "big-negative-blue", "ky+kk-bm-"));
		tokens.put("Fungal Spore", new Token("big-positive-red",
											  "small-positive-green",
											  "small-negative-blue", "ky+bk+km-"));
		tokens.put("Azure Blossom", new Token("big-negative-red",
											  "big-negative-green",
											  "big-negative-blue", "by-bk-bm-"));
		tokens.put("Terror Root", new Token("small-positive-red",
											 "big-negative-green",
											 "small-negative-blue", "by-kk+km-"));
		tokens.put("Venomous Stinger", new Token("small-negative-red",
												 "big-positive-green",
												 "small-positive-blue", "by+kk-km+"));
		tokens.put("Amphibian Essence", new Token("small-positive-red",
												  "small-negative-green",
												  "big-positive-blue", "ky-kk+bm+"));
		tokens.put("Avian Quill", new Token("big-positive-red",
								   		    "big-positive-green",
										    "big-positive-blue", "by+bk+bm+"));
		
		System.out.println("Tokens has been initialized.");
	}
	
	
	Aspect tokenAspects[] = new Aspect[3];
	
	private String name;

	public Token(String aspectOne, String aspectTwo, String aspectThree, String name) {
		
		Map<String, Aspect> aspects = Aspect.aspectsDictionary;
		
		tokenAspects[0] = aspects.get(aspectOne);
		tokenAspects[1] = aspects.get(aspectTwo);
		tokenAspects[2] = aspects.get(aspectThree);
		
		this.name = name;
	}

	public Aspect[] getTokenAspects() {
		return tokenAspects;
	}
	
}
