package factory.core;

import model.User;

public interface IPayment {
    void processPayment(User user, float amount);
}
