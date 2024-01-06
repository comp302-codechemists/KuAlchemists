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
		/*
		 * The next artifact is 1 gold instead of 3
		 * implemented
		 * will remain
		 * */

		Artifact reputationBoosterArtifact = ArtifactFactory.getInstance().getArtifacts("ReputationBoosterArtifact");
		/*
		 * when a theory published
		 * the player wins 2 reputation points instead of 1
		 * implemented
		 * will remain
		 * */
		
				
		Artifact printingPressArtifact = ArtifactFactory.getInstance().getArtifacts("PrintingPressArtifact");
		/*
		 * it will publish the theory free
		 * it will remain
		 * */
		
		Artifact wisdomIdolArtifact = ArtifactFactory.getInstance().getArtifacts("WisdomIdolArtifact");
		/*
		 * when a theory is debunked
		 * the owner will be informed 
		 * in case she wants to use and 
		 * does not want to lose reputation,
		 * or she can save till the end of the game
		 * to gain 1 reputation point
		 * */
		
		Artifact magicMortarArtifact = ArtifactFactory.getInstance().getArtifacts("MagicMortarArtifact");
		/*
		 * when an experiment is conducted
		 * 1 ingredient will be removed
		 * instead of two
		 * implemented
		 * */
		
		Artifact elixirOfInsight = ArtifactFactory.getInstance().getArtifacts("ElixirOfInsightArtifact");
		// elixir of insigt
		/*
		 * top3 ingredient card can be seen and
		 * change the place
		 * as soon as it is picked
		 * */
		
		artifacts.add(reputationBoosterArtifact);
		artifacts.add(discountArtifact);
		artifacts.add(printingPressArtifact);
		artifacts.add(magicMortarArtifact);
		artifacts.add(wisdomIdolArtifact);
		artifacts.add(elixirOfInsight);

		System.out.println("Artifacts initialized.");
	}

}
