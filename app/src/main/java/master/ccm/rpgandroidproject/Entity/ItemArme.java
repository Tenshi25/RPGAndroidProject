package master.ccm.rpgandroidproject.Entity;

public class ItemArme extends Item {
    private int minDegat;
    private int maxDegat;
    private boolean equiper;

    public boolean isEquiper() {
        return equiper;
    }

    public void setEquiper(boolean equiper) {
        this.equiper = equiper;
    }

    public int getMinDegat() {
        return minDegat;
    }

    public void setMinDegat(int minDegat) {
        this.minDegat = minDegat;
    }

    public int getMaxDegat() {
        return maxDegat;
    }

    public void setMaxDegat(int maxDegat) {
        this.maxDegat = maxDegat;
    }
}
