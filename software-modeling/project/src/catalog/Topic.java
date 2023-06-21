package catalog;

import java.util.ArrayList;
import java.util.List;

public class Topic extends CatalogComponent {
    private String name;
    private List<CatalogComponent> subcomponents;

    public Topic(String name) {
        this.name = name;
        this.subcomponents = new ArrayList<>();
    }

    @Override
    public void add(CatalogComponent component) {
        subcomponents.add(component);
    }

    @Override
    public void remove(CatalogComponent component) {
        subcomponents.remove(component);
    }

    @Override
    public CatalogComponent getChild(int index) {
        return subcomponents.get(index);
    }

    @Override
    public void display() {
        System.out.println("Topic: " + name);
        for(CatalogComponent subcomponent : subcomponents) {
            subcomponent.display();
        }
    }
} 