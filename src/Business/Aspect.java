package Business;

import java.util.HashMap;
import java.util.Map;

public class Aspect {
	
	/*
	 * Each aspect has a color (green, red, or blue), a sign (positive or negative)
	 * and a size(small or big)
	 * */
	
	private static final Map<String, Aspect> aspectsDictionary = new HashMap<String, Aspect>();
	
    private String size;
    private String sign;
    
    

	private String color;
    
    public Aspect(String size, String color, String sign) {
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
    	
    	aspectsDictionary.put("bk-", new Aspect("b","k","-"));
    	aspectsDictionary.put("bk+", new Aspect("b","k","+"));
    	aspectsDictionary.put("kk+", new Aspect("k","k","+"));
    	aspectsDictionary.put("kk-", new Aspect("k","k","-"));
    	aspectsDictionary.put("bm-", new Aspect("b","m","-"));
    	aspectsDictionary.put("bm+", new Aspect("b","m","+"));
    	aspectsDictionary.put("km+", new Aspect("k","m","+"));
    	aspectsDictionary.put("km-", new Aspect("k","m","-"));
    	aspectsDictionary.put("by-", new Aspect("b","y","-"));
    	aspectsDictionary.put("by+", new Aspect("b","y","+"));
    	aspectsDictionary.put("ky+", new Aspect("k","y","+"));
    	aspectsDictionary.put("ky-", new Aspect("k","y","-"));
    	
    	System.out.println("Aspects initialized.");
    	
    }
    
    public static Aspect getAspect(String aspectName)
    {
    	return aspectsDictionary.get(aspectName);
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
    
    @Override
	public String toString() {
		return size+color+sign;
	}

}

