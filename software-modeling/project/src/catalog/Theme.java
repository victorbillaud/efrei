package catalog;

import java.util.ArrayList;
import java.util.List;

public class Theme extends CatalogComponent {
    private String name;
    private List<Topic> topics;

    public Theme(String name) {
        this.name = name;
        this.topics = new ArrayList<>();
    }

    @Override
    public void add(CatalogComponent component) {
        if(component instanceof Topic) {
            topics.add((Topic) component);
        }
    }

    @Override
    public void remove(CatalogComponent component) {
        topics.remove(component);
    }

    @Override
    public CatalogComponent getChild(int index) {
        return topics.get(index);
    }

    @Override
    public void display() {
        System.out.println("Theme: " + name);
        for(CatalogComponent topic : topics) {
            topic.display();
        }
    }
}