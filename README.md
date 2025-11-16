# Computer Shop System

A Java-based computer shop simulation demonstrating object-oriented design patterns and software architecture principles.

## Features

- **Product Catalog**: Browse pre-configured computer builds (Office PC, Gaming PC, Workstation, etc.)
- **Shopping Cart**: Add/remove products from your cart
- **Multiple Payment Methods**: Support for Card, PayPal, and Kaspi payments
- **Promotion System**: Apply discount promocodes during checkout
- **User Wallet**: Manage balance with deposit functionality
- **Notification System**: Get notified when new products are added/removed (Observer pattern)
- **Flexible Checkout**: Facade pattern simplifies the checkout process

## Design Patterns Used

- **Adapter**: KaspiPaymentAdapter for third-party payment integration
- **Builder**: ComputerBuilder for flexible product creation
- **Factory**: PaymentFactory for creating different payment processors
- **Strategy**: IDiscountStrategy for various discount calculation methods
- **Facade**: CheckoutFacade simplifying complex checkout operations
- **Observer**: Product notifications for users

## Project Structure

Key packages:
- `adapter/` - Payment adapter implementations
- `builder/` - Product construction using Builder pattern
- `factory/` - Payment processor factories
- `model/` - Domain objects (User, Wallet, Promocode)
- `observer/` - Notification system implementation
- `strategy/` - Discount calculation strategies
- `facade/` - Simplified checkout interface

```
    src/
    ├── adapter/
    │   ├── KaspiBankService.java
    │   ├── KaspiPaymentAdapter.java
    │   └── KaspiPaymentCreator.java
    ├── builder/
    │   └── product/
    │       ├── components/
    │       ├── concrete/
    │       ├── core/
    │       └── Product.java
    ├── cart/
    │   └── Cart.java
    ├── facade/
    │   └── CheckoutFacade.java
    ├── factory/
    │   ├── client/
    │   ├── concrete/
    │   ├── core/
    │   └── creators/
    ├── model/
    │   ├── promocode/
    │   ├── wallet/
    │   └── User.java
    ├── observer/
    │   ├── core/
    │   ├── events/
    │   ├── observers/
    │   └── subjects/
    ├── strategy/
    │   ├── concrete/
    │   └── core/
    └── Main.java
```
    
## How to Run

1. Compile all Java files
2. Run the `Main` class
3. Follow the interactive menu to:
    - Set up your user profile
    - Choose payment method
    - Browse and add products to cart
    - Apply promocodes
    - Complete checkout

The system demonstrates clean architecture with separation of concerns and extensible design for adding new payment methods, discount strategies, and product types.