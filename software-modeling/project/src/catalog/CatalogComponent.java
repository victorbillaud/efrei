package catalog;

// Abstract class for the composite design pattern
public abstract class CatalogComponent {
    public void add(CatalogComponent component) {
        throw new UnsupportedOperationException("Cannot add to a single object.");
    }

    public void remove(CatalogComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a single object.");
    }

    public CatalogComponent getChild(int index) {
        throw new UnsupportedOperationException("Cannot get child from a single object.");
    }

    public abstract void display();
}