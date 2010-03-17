package spandog.models;

/**
 * This class represents an item, which contains the main interface, how items
 * are modelled.
 * @author Sami Saada
 */
public class Item {

    /**
     * Color of item.
     */
    private int color;
    /**
     * Should eat this?
     * @see #getEatable()
     */
    private double eatable;
    /**
     * Should play with this?
     * @see #getPlayable()
     */
    private double playable;
    /**
     * Should ignore this?
     * @see #getIgnorable()
     */
    private double ignorable;

    /**
     * Creates new item.
     * @param color Color of item.
     */
    public Item(int color) {
        this.color = color;
        this.eatable = 1.0;
        this.playable = 1.0;
        this.ignorable = 1.0;
    }

    /**
     * Gives eatable value.
     * @return Eatable value.
     */
    public double getEatable() {
        return this.eatable;
    }

    /**
     * Gives playable value.
     * @return Playable value.
     */
    public double getPlayable() {
        return this.playable;
    }

    /**
     * Gives ignorable value.
     * @return Ignorable value.
     */
    public double getIgnorable() {
        return this.ignorable;
    }

    /**
     * Does the most favour action.
     * @return Action number.
     */
    public int doMostFavourAction() {
        if (this.eatable > this.playable && this.eatable > this.ignorable) {
            this.raiseEatable(1.1, 0.9);
            return 0;
        } else if (this.playable > this.eatable && this.playable > this.ignorable) {
            this.raisePlayable(1.1, 0.9);
            return 1;
        } else if (this.ignorable > this.eatable && this.ignorable > this.playable) {
            this.raiseIgnorable(1.1, 0.9);
            return 2;
        } else {
            int random = (int) (Math.random()*3);
            if (random == 0) {
                this.raiseEatable(1.1, 0.9);
            } else if (random == 1) {
                this.raisePlayable(1.1, 0.9);
            } else {
                this.raiseIgnorable(1.1, 0.9);
            }
            return random;
        }
    }

    /**
     * Gives color of item.
     * @return Color code.
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Equals method, which is used in ArrayList's contains method.
     * @see java.util.ArrayList
     * @param other Compared object.
     * @return Boolean value for matching.
     */
    public boolean equals(Object other) {
        int otherColor = ((Item) other).getColor();
        int distance = this.getColor() - otherColor;
        if (distance < 0)
            distance *= -1;
        return distance < 20;
    }

    /**
     * Grants item by certain action.
     * @param action Action number
     */
    public void grant(int action) {
        if (action == 0) {
            this.raiseEatable(1.25, 0.75);
        } else if (action == 1) {
            this.raisePlayable(1.25, 0.75);
        } else {
            this.raiseIgnorable(1.25, 0.75);
        }
    }

    /**
     * Blames item by certain action.
     * @param action Action number
     */
    public void blame(int action) {
        if (action == 0) {
            this.eatable *= 0.75;
            this.playable *= 1.25;
            this.ignorable *= 1.25;
        } else if (action == 1) {
            this.eatable *= 1.25;
            this.playable *= 0.75;
            this.ignorable *= 1.25;
        } else {
            this.eatable *= 1.25;
            this.playable *= 1.25;
            this.ignorable *= 0.75;
        }
    }

    /**
     * Raises eatable value and lowers others.
     * @param higher Eatable value
     * @param lower Others value
     */
    private void raiseEatable(double higher, double lower) {
        this.eatable *= higher;
        this.playable *= lower;
        this.ignorable *= lower;
    }

    /**
     * Raises playable value and lowers others.
     * @param higher Playable value
     * @param lower Others value
     */
    private void raisePlayable(double higher, double lower) {
        this.eatable *= lower;
        this.playable *= higher;
        this.ignorable *= lower;
    }

    /**
     * Raises ignorable value and lowers others.
     * @param higher Ignorable value
     * @param lower Others value
     */
    private void raiseIgnorable(double higher, double lower) {
        this.eatable *= lower;
        this.playable *= lower;
        this.ignorable *= higher;
    }

}
