package Business;

public class MagicMortarArtifact extends Artifact implements removeArtifactListener{

	public MagicMortarArtifact(String name) {
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