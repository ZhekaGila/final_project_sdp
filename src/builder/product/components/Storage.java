package builder.product.components;

public class Storage extends Component {
    private String type;
    private int size;

    private String descriptionText = "Storage Type: " + type + " (" + size + " GB)";

    public Storage(String model,String type, int size, float price) {
        super(model, price);
        this.type = type;
        this.size = size;
    }

    @Override
    public String getDescription() {
        return descriptionText;
    }
}
