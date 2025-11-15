package builder.product.concrete;

import builder.product.Product;
import builder.product.core.IProductBuilder;
import builder.product.components.*;

public class ComputerBuilder implements IProductBuilder {
    private String name;
    private Component cpu;
    private Component ram;
    private Component gpu;
    private Component storage;

    @Override
    public ComputerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ComputerBuilder setCPU(Component cpu) {
        this.cpu = cpu;
        return this;
    }

    @Override
    public ComputerBuilder setRAM(Component ram) {
        this.ram = ram;
        return this;
    }

    @Override
    public ComputerBuilder setGPU(Component gpu) {
        this.gpu = gpu;
        return this;
    }

    @Override
    public ComputerBuilder setStorage(Component storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public Product getComputer() {
        return new Product(this.name, this.cpu, this.ram, this.gpu, this.storage);
    }
}
