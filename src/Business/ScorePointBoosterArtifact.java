package Business;

public class ScorePointBoosterArtifact extends Artifact implements removeArtifactListener {
    public int boostAmount;
    private String duration;

    public ScorePointBoosterArtifact(String name, int boostAmount, String duration) {
		super(name);
		this.boostAmount = boostAmount;
		this.duration = duration;
	}

	public int getBoostAmount() {
        return boostAmount;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
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

	@Override
	public void handleRemove(Player player) {
		// TODO Auto-generated method stub
		
	}

	

	
}
