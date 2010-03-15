package models;

public class Dog {

    public static int EATING = 0;
    public static int PLAYING = 1;
    public static int IGNORING = 2;

    private double feelings;
    private double hunger;
    private double tiredness;
    private Memory memory;
    private int lastAction;
    private long lastActionTime;

    public Dog() {
        this.feelings = 1.0;
        this.hunger = 0.8;
        this.tiredness = 0.9;
        this.memory = new Memory();
    }

    public int getItem(int color) {
        Item item = this.memory.getItem(color);
        this.lastAction = this.actionCalculus(item);
        this.lastActionTime = System.currentTimeMillis();
        return this.lastAction;
    }

    public long whenWasLastAction() {
        return this.lastActionTime;
    }

    public void grant() {
        Item item = this.memory.getLastItem();
        if (item != null)
            item.grant(this.lastAction);
    }

    public void blame() {
        Item item = this.memory.getLastItem();
        if (item != null)
            item.blame(this.lastAction);
    }

    private int actionCalculus(Item item) {
        return item.doMostFavourAction();
    }

}
