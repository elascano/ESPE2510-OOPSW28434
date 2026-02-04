package ec.edu.espe.inventory.model;

public final class Product {
    private final Object id;    
    private final String name;
    private final int stock;

    public Product(Object id, String name, int stock) {
        if (id == null) throw new IllegalArgumentException("Id no puede ser null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name es requerido");
        if (stock < 0) throw new IllegalArgumentException("Stock no puede ser negativo");

        this.id = id;
        this.name = name.trim();
        this.stock = stock;
    }

    public Object getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }

    public Product withStock(int newStock) {
        return new Product(this.id, this.name, newStock);
    }
}

