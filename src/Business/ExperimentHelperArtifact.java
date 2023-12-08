package Business;

public class ExperimentHelperArtifact extends Artifact {
    
	private String duration;

    public ExperimentHelperArtifact(String name, String duration) {
		super(name);
		this.duration = duration;
	}

	public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

	@Override
	public void applyArtifact(Player player) {
		// TODO Auto-generated method stub
		
	}

	
}