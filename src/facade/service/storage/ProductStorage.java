package facade.service.storage;

import builder.product.Product;
import builder.product.components.CPU;
import builder.product.components.GPU;
import builder.product.components.RAM;
import builder.product.components.Storage;
import builder.product.core.IProductBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductStorage {

    public static List<Product> createProducts(IProductBuilder builder){

        List<Product> products = new ArrayList<>();

        // 1
        Product officePC = builder.setName("Office PC")
                .setCPU(new CPU("Intel Core i3-12100F", 4, 8, 55000))
                .setRAM(new RAM("Kingston Fury 8GB DDR4", 8, 15000))
                .setGPU(new GPU("Integrated UHD 730", 0, 0))
                .setStorage(new Storage("Kingston A400 240GB SSD", "SSD", 240, 12000))
                .getComputer();
        products.add(officePC);

        // 2
        Product midGaming = builder.setName("Mid Gaming PC")
                .setCPU(new CPU("AMD Ryzen 5 5600", 6, 12, 60000))
                .setRAM(new RAM("Corsair Vengeance 16GB DDR4", 16, 28000))
                .setGPU(new GPU("NVIDIA GTX 1660 Super", 6, 160000))
                .setStorage(new Storage("Samsung 870 EVO 500GB", "SSD", 500, 25000))
                .getComputer();
        products.add(midGaming);

        // 3
        Product highEnd = builder.setName("High-End Gaming PC")
                .setCPU(new CPU("Intel Core i9-13900K", 24, 64, 220000))
                .setRAM(new RAM("G.Skill TridentZ 32GB DDR5", 32, 75000))
                .setGPU(new GPU("NVIDIA RTX 4090", 16, 950000))
                .setStorage(new Storage("Samsung 990 Pro 2TB", "SSD", 2000, 90000))
                .getComputer();
        products.add(highEnd);

        // 4
        Product workstation = builder.setName("Rendering Workstation")
                .setCPU(new CPU("AMD Threadripper 3960X", 24, 64, 550000))
                .setRAM(new RAM("Corsair 64GB DDR4", 64, 120000))
                .setGPU(new GPU("NVIDIA RTX 4080 Super", 16, 750000))
                .setStorage(new Storage("WD Black SN850 2TB NVMe", "SSD", 2000, 110000))
                .getComputer();
        products.add(workstation);

        // 5
        Product miniPC = builder.setName("Mini PC")
                .setCPU(new CPU("AMD Ryzen 3 5300G", 4, 8, 35000))
                .setRAM(new RAM("Patriot Viper 8GB DDR4", 8, 14000))
                .setGPU(new GPU("Integrated Vega 6", 0, 0))
                .setStorage(new Storage("Crucial MX500 500GB", "SSD", 500, 22000))
                .getComputer();
        products.add(miniPC);

        return products;

    }
}
