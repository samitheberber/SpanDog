package spandog.models;

import java.util.ArrayList;

/**
 * This class represents the memory of dog. It contains items, which dog has
 * faced on its journies.
 * @author Sami Saada
 */
public class Memory {

    /**
     * List of items in memory.
     * @see spandog.models.Item
     */
    private ArrayList<Item> items;
    /**
     * Last item in mind.
     * @see spandog.models.Item
     */
    private Item last;

    /**
     * Builds the memory.
     */
    public Memory() {
        this.items  = new ArrayList<Item>();
        this.last = null;
    }

    /**
     * Gives item, which matches on given color. If there isn't any, then it
     * creates that item.
     * @param color Color code
     * @return Item, which matches on given color
     */
    public Item getItem(int color) {
        Item mock = new Item(color);
        if (this.items.contains(mock)) {
            return this.items.get(this.items.indexOf(mock));
        } else {
            this.items.add(mock);
            return mock;
        }
    }

    /**
     * Gives last item on mind.
     * @return Last item on mind
     */
    public Item getLastItem() {
        return this.last;
    }

}
