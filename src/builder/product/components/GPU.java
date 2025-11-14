package builder.product.components;

public class GPU extends Component {
    private int vram;

    private String descriptionText = "GPU: " + model + " (" + vram + " GB VRAM)";

    public GPU(String model, int vram, int price) {
        super(model, price);
        this.vram = vram;
    }

    @Override
    public String getDescription() {
        return descriptionText;
    }
}
