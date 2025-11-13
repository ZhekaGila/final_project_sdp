package builder.product.components;

public class CPU extends Component {
    private int core;
    private int bit_system;

    public CPU(String model, float price, int core, int bit_system) {
        super(model, price);
        this.core = core;
        this.bit_system = bit_system;
    }

    @Override
    public String getDescription() {
        return "CPU Model: " + model + "(" + core + " cores)";
    }
}
