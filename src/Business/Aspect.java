package Business;

public class Aspect {
	
	/*
	 * Each aspect has a color (green, red, or blue), a sign (positive or negative)
	 * and a size(small or big)
	 * */
	
    private String color;
    private String sign;
    private String size;

    public Aspect(String color, String sign, String magnitude) {
        this.color = color;
        this.sign = sign;
        this.size = magnitude;
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

