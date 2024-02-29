package base;

public class Product {

    public String id;
    public categorias categoria;

    public enum categorias {HANDBAGS, BRACELETS, WEEKEND, PANTS}

    public Product(String _id, categorias _categoria) {
        this.id = _id;
        this.categoria = _categoria;
    }

    public String getId() {
        return this.id;
    }
}