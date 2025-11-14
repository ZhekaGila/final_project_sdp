package factory.core;

import model.User;

public interface IPayment {
    boolean processPayment(User user, float amount);
}
