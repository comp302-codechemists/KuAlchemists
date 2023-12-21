package Factories;

import Business.Artifact;
import Business.DiscountArtifact;
import Business.GoldBoosterArtifact;
import Business.PotionEffectBoosterArtifact;
import Business.ReputationBoosterArtifact;
import Business.ScorePointBoosterArtifact;
import Business.magicMortarArtifact;
import Business.printingPressArtifact;
import Business.wisdomIdolArtifact;

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
			
		case "GoldBoosterArtifact":
			artifact = new GoldBoosterArtifact(1,"AllGame","GoldBoosterArtifact");
			break;

		case "PotionEffectBooster":
			artifact =  new PotionEffectBoosterArtifact("PotionEffectBooster",1,"PositivePotion","AllGame");
			break;

		case "ReputationBoosterArtifact":
			artifact =   new ReputationBoosterArtifact("ReputationBoosterArtifact",1,"PublishTheory","AllGame");
			break;

		case "ScorePointBoosterArtifact":
			artifact =  new ScorePointBoosterArtifact("ScorePointBoosterArtifact",1,"AllGame");
			break;
			
		case "wisdomIdolArtifact":
			artifact = new wisdomIdolArtifact("wisdomIdolArtifact");
			break;
			
		case "printingPressArtifact":
			artifact = new printingPressArtifact("printingPressArtifact");
			break;
			
		case "magicMortarArtifact":
			artifact = new magicMortarArtifact("magicMortarArtifact");
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


