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
	
	public static void initializeTokens()
	{
		/*
		 * this method creates all meaningful tokens,
		 * and then store them in a map.
		 * */
		
		tokens.put("Verdant Sprig", new Token("big-negative-red",
											  "small-negative-green",
											  "small-positive-blue"));
		tokens.put("Essence of Stride", new Token("small-negative-red",
												  "small-positive-green",
												  "big-negative-blue"));
		tokens.put("Fungal Spore", new Token("big-positive-red",
											  "small-positive-green",
											  "small-negative-blue"));
		tokens.put("Azure Blossom", new Token("big-negative-red",
											  "big-negative-green",
											  "big-negative-blue"));
		tokens.put("Terror Root", new Token("small-positive-red",
											 "big-negative-green",
											 "small-negative-blue"));
		tokens.put("Venomous Stinger", new Token("small-negative-red",
												 "big-positive-green",
												 "small-positive-blue"));
		tokens.put("Amphibian Essence", new Token("small-positive-red",
												  "small-negative-green",
												  "big-positive-blue"));
		tokens.put("Avian Quill", new Token("big-positive-red",
								   		    "big-positive-green",
										    "big-positive-blue"));
		
		System.out.println("Tokens has been initialized.");
	}
	
	
	Aspect tokenAspects[] = new Aspect[3];

	public Token(String aspectOne, String aspectTwo, String aspectThree) {
		
		Map<String, Aspect> aspects = Aspect.aspectsDictionary;
		
		tokenAspects[0] = aspects.get(aspectOne);
		tokenAspects[1] = aspects.get(aspectTwo);
		tokenAspects[2] = aspects.get(aspectThree);
		
		
	}

	public Aspect[] getTokenAspects() {
		return tokenAspects;
	}
	
}
