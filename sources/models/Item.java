package models;

public class Item {

    private int color;
    private double eatable;
    private double playable;
    private double ignorable;

    public Item(int color) {
        this.color = color;
        this.eatable = 1.0;
        this.playable = 1.0;
        this.ignorable = 1.0;
    }

    public double getEatable() {
        return this.eatable;
    }

    public double getPlayable() {
        return this.playable;
    }

    public double getIgnorable() {
        return this.ignorable;
    }

}
