package Business;

public class DiscountArtifact extends Artifact {
    private int discountAmount;
    private String whereToApply;
    private String duration;

    public DiscountArtifact(int discountAmount, String whereToApply, String duration) {
        this.discountAmount = discountAmount;
        this.whereToApply = whereToApply;
        this.duration = duration;
    }

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