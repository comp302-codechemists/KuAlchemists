package Business;

public class magicMortarArtifact extends Artifact implements removeArtifactListener{

	public magicMortarArtifact(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	} 

	@Override
	public void applyArtifact(Player player) {
		// TODO Auto-generated method stub
		player.setNumberOfIngreientToBeRemovedWhileExperimenting(1);
	}

	@Override
	public void handleRemove(Player player) {
		// TODO Auto-generated method stub
		player.setNumberOfIngreientToBeRemovedWhileExperimenting(2);
	}

}