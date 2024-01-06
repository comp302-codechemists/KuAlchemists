package Business;

public class ElixirOfInsightArtifact extends Artifact implements removeArtifactListener {

	public ElixirOfInsightArtifact(String name) {
		super(name);
	}

	@Override
	public void handleRemove(Player player) {
		return; //intentionally left blank
	}

	@Override
	public void applyArtifact(Player player) {
		return; //intentionally left blank
	}

}