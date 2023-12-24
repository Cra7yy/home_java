public class ToyLotteryProgram {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Doll", 10, 20));
        toyStore.addToy(new Toy(2, "Car", 15, 30));
        toyStore.addToy(new Toy(3, "Robot", 5, 10));

        Toy doll = toyStore.getToyById(1);
        if (doll != null) {
            doll.setWeight(25);
        }

        toyStore.playLottery();
    }
}
