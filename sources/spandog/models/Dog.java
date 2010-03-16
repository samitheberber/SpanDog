package spandog.models;

/**
 * This class represents a dog, which contains main intarface, how a dog is
 * modelled.
 * @author Sami Saada
 */
public class Dog {

    /**
     * Constant for eating action number.
     */
    public static final int EATING = 0;
    /**
     * Constant for playing action number.
     */
    public static final int PLAYING = 1;
    /**
     * Constant for ignoring action number.
     */
    public static final int IGNORING = 2;

    /**
     * Memory of dog.
     */
    private Memory memory;
    /**
     * Last action number.
     */
    private int lastAction;
    /**
     * Last action happening time.
     */
    private long lastActionTime;

    /**
     * Creates new dog.
     */
    public Dog() {
        this.memory = new Memory();
    }

    /**
     * Gives an item to dog.
     * @param color Color of item.
     * @return Last action number.
     */
    public int getItem(int color) {
        Item item = this.memory.getItem(color);
        this.lastAction = this.actionCalculus(item);
        this.lastActionTime = System.currentTimeMillis();
        return this.lastAction;
    }

    /**
     * Tells when was the last action.
     * @return Time of the last action.
     */
    public long whenWasLastAction() {
        return this.lastActionTime;
    }

    /**
     * Grants dog from good behavior.
     */
    public void grant() {
        Item item = this.memory.getLastItem();
        if (item != null)
            item.grant(this.lastAction);
    }

    /**
     * Blames dog from bad behavior.
     */
    public void blame() {
        Item item = this.memory.getLastItem();
        if (item != null)
            item.blame(this.lastAction);
    }

    /**
     * Do some action calculus.
     * @param item Item, which needs in calculus.
     * @return Action number.
     */
    private int actionCalculus(Item item) {
        return item.doMostFavourAction();
    }

}
