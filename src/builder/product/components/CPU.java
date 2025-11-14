package builder.product.components;

public class CPU extends Component {
    private int core;
    private int bit_system;

    private String descriptionText = "CPU Model: " + model + "(" + core + " cores)";

    public CPU(String model, int core, int bit_system, float price) {
        super(model, price);
        this.core = core;
        this.bit_system = bit_system;
    }

    @Override
    public String getDescription() {
        return descriptionText;
    }
}
