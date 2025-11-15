package builder.product.core;

import builder.product.Product;
import builder.product.components.*;

public interface  IProductBuilder {
    IProductBuilder setName(String name);
    IProductBuilder setCPU(Component cpu);
    IProductBuilder setRAM(Component ram);
    IProductBuilder setStorage(Component storage);
    IProductBuilder setGPU(Component gpu);
    Product getComputer();
}
