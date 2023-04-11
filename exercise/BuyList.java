package exercise;

import java.util.ArrayList;

public class BuyList {

    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public int size() {
        return this.items.size();
    }

    public String getItemName(int index) {
        return this.items.get(index).getName();
    }

    public int getItemCount(int index) {
        return this.items.get(index).getCount();
    }

    public static class Item {

        private final String name;
        private final int amount;

        public Item(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return this.name;
        }

        public int getCount() {
            return this.amount;
        }
    }
}
