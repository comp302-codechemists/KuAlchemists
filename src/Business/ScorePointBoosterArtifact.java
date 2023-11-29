package Business;

public class ScorePointBoosterArtifact extends Artifact {
    public int boostAmount;
    private String duration;

   /* public ScorePointBoosterArtifact(int boostAmount, String duration) {
        this.boostAmount = boostAmount;
        this.duration = duration;
    }*/

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

	

	
}
