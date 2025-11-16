package builder.product.components;

public class CPU extends Component {

    private int core;
    private int bitSystem;

    private static final String descriptionTemplate = "CPU: %s (%d cores, %d-bit)";

    public CPU(String model, int core, int bitSystem, float price) {
        super(model, price);
        this.core = core;
        this.bitSystem = bitSystem;
    }

    @Override
    public String getDescription() {
        return String.format(descriptionTemplate, model, core, bitSystem);
    }

    public int getCore() {
        return core;
    }

    public int getBitSystem() {
        return bitSystem;
    }
}
