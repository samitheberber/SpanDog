package models;

import java.util.ArrayList;

public class Memory {

    private ArrayList<Item> items;
    private Item last;

    public Memory() {
        this.items  = new ArrayList<Item>();
        this.last = null;
    }

    public Item getItem(int color) {
        Item mock = new Item(color);
        if (this.items.contains(mock)) {
            return this.items.get(this.items.indexOf(mock));
        } else {
            this.items.add(mock);
            return mock;
        }
    }

    public Item getLastItem() {
        return this.last;
    }

}
