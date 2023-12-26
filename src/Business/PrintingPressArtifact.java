package Business;

public class PrintingPressArtifact extends Artifact implements removeArtifactListener{

	public PrintingPressArtifact(String name) {
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
