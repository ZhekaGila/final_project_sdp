package product.components;

public abstract class Component{
    protected String model; // модель компонента
    protected float price;    // цена компонента в условных единицах

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
