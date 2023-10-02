public class Animal {
    protected String name;

    public Animal(){
        this.name = "";
    }
    public Animal (String name) {
        this.name = name;
    }

    public void wakeUp(){
        System.out.println(this.name + " wakes up");
    }

    public void sleep(){
        System.out.println(this.name + " sleeps");
    }

    public void action(){
        System.out.println(this.name + " does an action");
    }
}
