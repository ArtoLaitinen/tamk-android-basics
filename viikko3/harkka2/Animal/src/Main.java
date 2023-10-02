import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Bird bird = new Bird("Haukka");
        Fish fish = new Fish("Hauki");
        bird.wakeUp();
        bird.action();
        bird.sleep();
        fish.wakeUp();
        fish.action();
        fish.sleep();

        System.out.println();
        System.out.println("Using an array of animals: ");
        System.out.println();

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Bird("Haukka"));
        animals.add(new Fish("Hauki"));
        animals.add(new Animal("Susi"));

        for( Animal a : animals){
            a.wakeUp();
        }

        for( Animal a : animals){
            a.action();
        }

        for( Animal a : animals){
            a.sleep();
        }

    }
}