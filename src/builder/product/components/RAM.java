package builder.product.components;

public class RAM extends Component {
    private int amount;
    private String descriptionText = "RAM: " + model + " (" + amount + " GB)";
    public RAM(String model, int amount, float price) {
        super(model, price);
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return descriptionText;
    }
}
