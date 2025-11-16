package builder.product.components;

public class GPU extends Component {

    private int vram;
    private static String descriptionTemplate = "GPU: %s (%d GB VRAM)";

    public GPU(String model, int vram, float price) {
        super(model, price);
        this.vram = vram;
    }

    @Override
    public String getDescription() {
        return String.format(descriptionTemplate, model, vram);
    }

    public String getModel() {
        return model;
    }

    public int getVram() {
        return vram;
    }
}
