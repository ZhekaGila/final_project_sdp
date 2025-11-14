package builder.product.components;

public class Storage extends Component {
    private String type;
    private int size;

    private String descriptionText = "Storage Type: " + type + " (" + size + " GB)";

    public Storage(String model, float price, String type, int size) {
        super(model, price);
        this.type = type;
        this.size = size;
    }

    @Override
    public String getDescription() {
        return descriptionText;
    }
}
