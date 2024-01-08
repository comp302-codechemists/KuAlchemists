package Factories;

import Business.Artifact;
import Business.DiscountArtifact;
import Business.ElixirOfInsightArtifact;
import Business.MagicMortarArtifact;
import Business.ReputationBoosterArtifact;
import Business.PrintingPressArtifact;
import Business.WisdomIdolArtifact;

public class ArtifactFactory {
	private Artifact artifact;
	private static ArtifactFactory artiFactory;

	private ArtifactFactory() {
		
	}
	
	//Example of Factory Design Pattern from GoF
	public Artifact getArtifacts(String name) {

		switch(name){
		case "DiscountArtifact":
			artifact = new DiscountArtifact("DiscountArtifact",1,"Artifact","AllGame");
			break;
			
		case "ReputationBoosterArtifact":
			artifact =   new ReputationBoosterArtifact("ReputationBoosterArtifact",1,"PublishTheory","AllGame");
			break;
			
		case "WisdomIdolArtifact":
			artifact = new WisdomIdolArtifact("WisdomIdolArtifact");
			break;
			
		case "PrintingPressArtifact":
			artifact = new PrintingPressArtifact("PrintingPressArtifact");
			break;
			
		case "MagicMortarArtifact":
			artifact = new MagicMortarArtifact("MagicMortarArtifact");
			break;
			
		case "ElixirOfInsightArtifact":
			artifact = new ElixirOfInsightArtifact("ElixirOfInsightArtifact");
			break;

		default:
			System.out.println("Something went wrong");
			break;


		}
		return artifact;	
	}
	
	
	public static ArtifactFactory getInstance() {
		if(artiFactory == null) {
			artiFactory = new ArtifactFactory();
		}
		return artiFactory;		
	}

}


