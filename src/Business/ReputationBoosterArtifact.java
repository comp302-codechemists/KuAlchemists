package Business;

public class ReputationBoosterArtifact extends Artifact {
    public int boostAmount;
    public String whereToApply;
    private String duration;

    /*public ReputationBoosterArtifact(int boostAmount, String whereToApply, String duration) {
        this.boostAmount = boostAmount;
        this.whereToApply = whereToApply;
        this.duration = duration;
    }*/

    public int getBoostAmount() {
        return boostAmount;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
    }

    public String getWhereToApply() {
        return whereToApply;
    }

    public void setWhereToApply(String whereToApply) {
        this.whereToApply = whereToApply;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

	

	

}