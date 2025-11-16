package builder.product.components;

public class Storage extends Component {

    private String type;
    private int size;
    private static final String descriptionTemplate = "Storage: %s (%d GB)";

    public Storage(String model, String type, int size, float price) {
        super(model, price);
        this.type = type;
        this.size = size;
    }

    @Override
    public String getDescription() {
        return String.format(descriptionTemplate, type, size);
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
