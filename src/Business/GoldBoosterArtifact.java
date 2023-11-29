package Business;

public class GoldBoosterArtifact extends Artifact {
    public int boostAmount;
    private String duration;
    
    /*public GoldBoosterArtifact(String duration, int boostAmount) {
        this.duration = duration;
        this.boostAmount = boostAmount;
    }*/

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

	
}
