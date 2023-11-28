package Business;

import java.util.HashMap;
import java.util.Map;

public class Aspect {
	
	/*
	 * Each aspect has a color (green, red, or blue), a sign (positive or negative)
	 * and a size(small or big)
	 * */
	
	public static Map<String, Aspect> aspectsDictionary = new HashMap<String, Aspect>();
	
    private String size;
    private String sign;
    private String color;
    
    public Aspect(String size, String sign, String color) {
        this.color = color;
        this.sign = sign;
        this.size = size;
    }
    
    public static void initializeAllAspects()
    {
    	/*
    	 * This method will create all the possible aspects,
    	 * Then store them in aspectsDictionary
    	 * */
    	
    	aspectsDictionary.put("big-negative-red", new Aspect("big", "negative", "red"));
    	aspectsDictionary.put("big-positive-red", new Aspect("big", "positive", "red"));
    	aspectsDictionary.put("small-positive-red", new Aspect("small", "positive", "red"));
    	aspectsDictionary.put("small-negative-red", new Aspect("small", "negative", "red"));
    	aspectsDictionary.put("big-negative-blue", new Aspect("big", "negative", "blue"));
    	aspectsDictionary.put("big-positive-blue", new Aspect("big", "positive", "blue"));
    	aspectsDictionary.put("small-positive-blue", new Aspect("small", "positive", "blue"));
    	aspectsDictionary.put("small-negative-blue", new Aspect("small", "negative", "blue"));
    	aspectsDictionary.put("big-negative-green", new Aspect("big", "negative", "green"));
    	aspectsDictionary.put("big-positive-green", new Aspect("big", "positive", "green"));
    	aspectsDictionary.put("small-positive-green", new Aspect("small", "positive", "green"));
    	aspectsDictionary.put("small-negative-green", new Aspect("small", "negative", "green"));
    	
    	System.out.println("Aspects has been initialized.");
    	
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMagnitude() {
        return size;
    }

    public void setMagnitude(String magnitude) {
        this.size = magnitude;
    }

}

