package Business;

public class printingPressArtifact extends Artifact implements removeArtifactListener{

	public printingPressArtifact(String name) {
		super(name);
	}

	@Override
	public void applyArtifact(Player player) {
		// TODO Auto-generated method stub
		player.setPublishTheoryCharge(0);
	}

	@Override
	public void handleRemove(Player player) {
		// TODO Auto-generated method stub
		player.setPublishTheoryCharge(-1);
	}
	
}
