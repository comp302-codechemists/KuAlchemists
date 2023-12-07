package Business;

import java.util.ArrayList;
import java.util.List;




abstract class Artifact {
	
	private String name;
	
	public Artifact(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Artifact [name=" + name + "]";
	}
	
	public abstract void applyArtifact();


	public static final List<Artifact> artifacts = new ArrayList<Artifact>();
	
	public static void initializeArtifacts()
	{
		// Create all the artifacts
		Artifact discountArtifact = new DiscountArtifact("DiscountArtifact",1,"Artifact","AllGame");
		Artifact experimentHelperArtifact = new ExperimentHelperArtifact("ExperimentHelperArtifact","AllGame");
		Artifact goldBoosterArtifact = new GoldBoosterArtifact(1,"AllGame","GoldBoosterArtifact");
		Artifact potionEffectBoosterArtifact = new PotionEffectBoosterArtifact("PotionEffectBooster",1,"PositivePotion","AllGame");
		Artifact reputationBoosterArtifact = new ReputationBoosterArtifact("ReputationBoosterArtifact",1,"PublishTheory","AllGame");
		Artifact scorePointBoosterArtifact = new ScorePointBoosterArtifact("ScorePointBoosterArtifact",1,"AllGame");
	
		artifacts.add(scorePointBoosterArtifact);
		artifacts.add(reputationBoosterArtifact);
		artifacts.add(potionEffectBoosterArtifact);
		artifacts.add(goldBoosterArtifact);
		artifacts.add(experimentHelperArtifact);
		artifacts.add(experimentHelperArtifact);
		artifacts.add(discountArtifact);
		
		System.out.println("Artifact has been initialized.");
	}
}
