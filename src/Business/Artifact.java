package Business;

import java.util.ArrayList;
import java.util.List;

import Factories.ArtifactFactory;




public abstract class Artifact {

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

	public abstract void applyArtifact(Player player);

	public static final List<Artifact> artifacts = new ArrayList<Artifact>();

	public static void initializeArtifacts()
	{
		// Create all the artifacts
		Artifact discountArtifact = ArtifactFactory.getInstance().getArtifacts("DiscountArtifact");
		Artifact experimentHelperArtifact = ArtifactFactory.getInstance().getArtifacts("ExperimentHelperArtifact");
		Artifact goldBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("GoldBoosterArtifact");
		Artifact potionEffectBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("PotionEffectBooster");
		Artifact reputationBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("ReputationBoosterArtifact");
		Artifact scorePointBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("ScorePointBoosterArtifact");
		Artifact printingPressArtifact = new printingPressArtifact("PrintingPressArtifact"); //will be changed accordingly to the factory pattern

		artifacts.add(scorePointBoosterArtifact);
		artifacts.add(reputationBoosterArtifact);
		artifacts.add(potionEffectBoosterArtifact);
		artifacts.add(goldBoosterArtifact);
		artifacts.add(experimentHelperArtifact);
		artifacts.add(experimentHelperArtifact);
		artifacts.add(discountArtifact);
		artifacts.add(printingPressArtifact);

		System.out.println("Artifacts initialized.");
	}

}
