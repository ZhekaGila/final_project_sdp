package product.components;

public class GPU extends Component {
    private int vram;

    public GPU(String model, int vram, int price) {
        super(model, price);
        this.vram = vram;
    }

    @Override
    public String getDescription() {
        return "GPU: " + model + " (" + vram + " GB VRAM)";
    }
}
