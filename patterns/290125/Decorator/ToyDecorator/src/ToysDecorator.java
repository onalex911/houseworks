interface Toy {
    int getPrice();
}

abstract class ToyDecorator implements Toy {
    Toy wrappee;
    ToyDecorator(Toy wrappee) {
        this.wrappee = wrappee;
    }
    @Override
    public int getPrice() {
        return 0;
    }
}
