public class Bird extends Animal {

    public Bird (String name) {
        super(name); //uses the animal classes constructor
    }
    @Override
    public void action(){
        System.out.println(this.name + " flies");
    }
}
