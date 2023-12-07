package Business;

public class GoldBoosterArtifact extends Artifact {
    private int boostAmount;
    private String duration;   
    
    public GoldBoosterArtifact(int boostAmount, String duration, String name) {
		super(name);
		this.boostAmount = boostAmount;
		this.duration = duration;
	}

	public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getBoostAmount() {
        return boostAmount;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
    }

	@Override
	public void applyArtifact() {
		// TODO Auto-generated method stub
		
	}

	
}
