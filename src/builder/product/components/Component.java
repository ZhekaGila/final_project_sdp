package builder.product.components;

public abstract class Component{

    protected String model;
    protected float price;

    public Component(String model, float price) {
        this.model = model;
        this.price = price;
    }
    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return getDescription() + " — $" + price;
    }
}
