package Business;

import java.util.ArrayList;
import java.util.List;

abstract class Artifact {

	public static final List<Artifact> artifacts = new ArrayList<Artifact>();
	
	public static void initializeArtifacts()
	{
		// Create all the artifacts
		Artifact discountArtifact = new DiscountArtifact();
		Artifact experimentHelperArtifact = new ExperimentHelperArtifact();
		Artifact goldBoosterArtifact = new GoldBoosterArtifact();
		Artifact potionEffectBoosterArtifact = new PotionEffectBoosterArtifact();
		Artifact reputationBoosterArtifact = new ReputationBoosterArtifact();
		Artifact scorePointBoosterArtifact = new ScorePointBoosterArtifact();
	
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
