package builder.product.components;

public class RAM extends Component {

    private int amount;
    private static final String descriptionTemplate = "RAM: %s (%d GB)";

    public RAM(String model, int amount, int price) {
        super(model, price);
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return String.format(descriptionTemplate, model, amount);
    }

    public int getAmount() {
        return amount;
    }
}
