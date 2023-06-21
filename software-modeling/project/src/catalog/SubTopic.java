package catalog;

import java.util.ArrayList;
import java.util.List;

public class SubTopic extends CatalogComponent {
    private String name;
    private List<Course> courses;

    public SubTopic(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    @Override
    public void add(CatalogComponent component) {
        if(component instanceof Course) {
            courses.add((Course) component);
        }
    }

    @Override
    public void remove(CatalogComponent component) {
        courses.remove(component);
    }

    @Override
    public CatalogComponent getChild(int index) {
        return courses.get(index);
    }

    @Override
    public void display() {
        System.out.println("Sub-Topic: " + name);
        for(CatalogComponent course : courses) {
            course.display();
        }
    }
}