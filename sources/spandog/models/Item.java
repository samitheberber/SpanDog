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

    public int doMostFavourAction() {
        if (this.eatable > this.playable && this.eatable > this.ignorable) {
            this.raiseEatable();
            return 0;
        } else if (this.playable > this.eatable && this.playable > this.ignorable) {
            this.raisePlayable();
            return 1;
        } else if (this.ignorable > this.eatable && this.ignorable > this.playable) {
            this.raiseIgnorable();
            return 2;
        } else {
            int random = (int) (Math.random()*3);
            if (random == 0) {
                this.raiseEatable();
            } else if (random == 1) {
                this.raisePlayable();
            } else {
                this.raiseIgnorable();
            }
            return random;
        }
    }

    public int getColor() {
        return this.color;
    }

    public boolean equals(Object other) {
        int otherColor = ((Item) other).getColor();
        int distance = this.getColor() - otherColor;
        if (distance < 0)
            distance *= -1;
        return distance < 20;
    }

    public void grant(int action) {
        if (action == 0) {
            this.eatable *= 1.25;
            this.playable *= 0.75;
            this.ignorable *= 0.75;
        } else if (action == 1) {
            this.eatable *= 0.75;
            this.playable *= 1.25;
            this.ignorable *= 0.75;
        } else {
            this.eatable *= 0.75;
            this.playable *= 0.75;
            this.ignorable *= 1.25;
        }
    }

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

    private void raiseEatable() {
        this.eatable *= 1.1;
        this.playable *= 0.9;
        this.ignorable *= 0.9;
    }

    private void raisePlayable() {
        this.eatable *= 0.9;
        this.playable *= 1.1;
        this.ignorable *= 0.9;
    }

    private void raiseIgnorable() {
        this.eatable *= 0.9;
        this.playable *= 0.9;
        this.ignorable *= 1.1;
    }

}
