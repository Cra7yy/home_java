import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}

class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy getToyById(int id) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    public void playLottery() {
        if (toys.isEmpty()) {
            System.out.println("No toys available for lottery.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of toys to be won: ");
        int numOfToysToWin = scanner.nextInt();

        Random random = new Random();
        for (int i = 0; i < numOfToysToWin; i++) {
            double randomValue = random.nextDouble() * 100;
            Toy selectedToy = selectToyByWeight(randomValue);
            if (selectedToy != null) {
                prizeToys.add(selectedToy);
                selectedToy.decreaseQuantity();
            }
        }

        savePrizeToysToFile();
        scanner.close();
    }

    private Toy selectToyByWeight(double randomValue) {
        double cumulativeWeight = 0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomValue <= cumulativeWeight) {
                return toy;
            }
        }
        return null;
    }

private void savePrizeToysToFile() {
    System.out.println("Prize Toys:");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt"))) {
        for (Toy toy : prizeToys) {
            System.out.println("ID: " + toy.getId() + ", Name: " + toy.getName());
            writer.write("ID: " + toy.getId() + ", Name: " + toy.getName());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
