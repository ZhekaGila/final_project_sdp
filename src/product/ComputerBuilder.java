package product;

import product.components.*;

public class ComputerBuilder implements IProductBuilder {
    private Component cpu;
    private Component ram;
    private Component gpu;
    private Component storage;

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
    public Computer getComputer() {
        return new Computer(this.cpu, this.ram, this.gpu, this.storage);
    }
}
