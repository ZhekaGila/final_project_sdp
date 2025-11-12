package product;

import product.components.*;

public interface IProductBuilder {
    IProductBuilder setCPU(Component cpu);
    IProductBuilder setRAM(Component ram);
    IProductBuilder setStorage(Component storage);
    IProductBuilder setGPU(Component gpu);
    Computer getComputer();
}
