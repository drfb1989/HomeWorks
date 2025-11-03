package coffee.order;

public class Demo {
    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();

        board.add("Alen");
        board.add("Yoda");
        board.add("Obi-van");
        board.add("John Snow");

        board.draw();
        // Example out-of-turn: John Snow is ready first
        board.deliver(4);
        // Next in FIFO
        board.deliver();
        board.draw();
    }
}
