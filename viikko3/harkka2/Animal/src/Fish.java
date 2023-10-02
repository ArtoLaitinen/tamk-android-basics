public class Fish extends Animal {
    public Fish (String name) {
        super(name); //uses the animal classes constructor
    }
    @Override
    public void action(){
        System.out.println(this.name + " swims");
    }
}
