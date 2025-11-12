package product.components;

public class RAM extends Component {
    private int amount;

    public RAM(String model, int amount, int price) {
        super(model, price);
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return "RAM: " + model + " (" + amount + " GB)";
    }
}
