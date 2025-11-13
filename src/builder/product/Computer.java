package builder.product;

import builder.product.components.*;

public class Computer {
    private Component cpu;
    private Component ram;
    private Component gpu;
    private Component storage;
    private float price;

    public Computer(Component cpu, Component ram, Component gpu, Component storage) {
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.storage = storage;
        this.price=cpu.getPrice()+ram.getPrice()+gpu.getPrice()+ storage.getPrice();
    }

//    public void setPrice(float price) {this.price = price;}
//    public void setCPU(Component cpu) {this.cpu = cpu;}
//    public void setRAM(Component ram) {this.ram = ram;}
//    public void setGPU(Component gpu) {this.gpu = gpu;}
//    public void setStorage(Component storage) {this.storage = storage;}

    public Component getCPU(){
        return cpu;
    }
    public Component getRAM(){return ram;}
    public Component getGPU() {return gpu;}
    public Component getStorage() {return storage;}
    public float getPrice() {return price;}

    @Override
    public String toString(){
        return "DETAILS: \n" +
                cpu.getDescription()+ "\n" +
                ram.getDescription()+ "\n" +
                gpu.getDescription()+ "\n" +
                storage.getDescription() + "\n" +
                "TOTAL PRICE: " + getPrice();
    }
}

