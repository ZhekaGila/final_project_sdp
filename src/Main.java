import product.*;
import product.components.*;

public class Main {
    public static void main(String[] args) {

        IProductBuilder builder = new ComputerBuilder();

        Computer pc = builder.setCPU(new CPU("Intel Core i7-13700K", 170000, 8, 32))
            .setRAM(new RAM("Kingston Fury 32GB DDR5", 45000, 16))
            .setGPU(new GPU("NVIDIA RTX 4070", 350000, 8))
            .setStorage(new Storage("Samsung 990 Pro 1TB SSD", 65000, "SSD", 512))
            .getComputer();

        System.out.println(pc);


    }
}