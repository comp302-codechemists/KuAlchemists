package Business;

public class DiscountArtifact extends Artifact {
    public int discountAmount;
    public String whereToApply;
    private String duration;

    /*public DiscountArtifact(int discountAmount, String whereToApply, String duration) {
        this.discountAmount = discountAmount;
        this.whereToApply = whereToApply;
        this.duration = duration;
    }
    these need to be determined*/ 

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
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