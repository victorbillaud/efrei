package catalog;

public class Course extends CatalogComponent {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("Course: " + name);
    }
}