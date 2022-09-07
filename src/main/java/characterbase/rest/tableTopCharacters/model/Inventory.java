package characterbase.rest.tableTopCharacters.model;

import java.util.List;

public class Inventory {

    private List<String> items; // пока реализация через список всех предметов.

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
