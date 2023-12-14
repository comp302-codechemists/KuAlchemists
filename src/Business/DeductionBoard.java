package Business;
import java.util.HashMap;
import java.util.Map;


public class DeductionBoard {
	
	
	// Index int the triangle 1, 2, ..., 27 and the value of the deduction
	//blue -, blue + , ... 
	private Map<Integer, String> triangle;
	private Map<Integer,Integer> existingItems;
	
	public DeductionBoard() {
		triangle = new HashMap<>();
		existingItems = new HashMap<>();
	}

	public Map<Integer, String> getTriangle() {
		return triangle;
	}
	
	public Map<Integer, Integer> getExistingItems() {
		return existingItems;
	}

	public void addDeduction(int index, String value) {
		triangle.put(index, value);
	}
	
	public void addExistingItem(int selectedTriangle, int selectedLeft) {
		existingItems.put(selectedTriangle, selectedLeft);
	}
	
	public static String getName(int i) {
		String ret = "";
		switch (i) {
		
		case 0:
			ret = "blue-";
			break;
		case 1:
			ret = "blue+";
			break;
		case 2:
			ret = "green-";
			break;
		case 3:
			ret = "green+";
			break;
		case 4:
			ret = "red-";
			break;
		case 5:
			ret = "red+";
			break;
		case 6:
			ret = "neutral";
			break;
		}
		
		return ret;
	}

}
