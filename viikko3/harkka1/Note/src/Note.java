public class Note {
    public enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        ORANGE,
        PURPLE
    }
    private String content;
    private Color backgroundColor;

    public Note(){
        this.content = "";
        this.backgroundColor = Color.RED;
    }

    public Note(String content, Color backgroundColor){
        this.content = content;
        this.backgroundColor = backgroundColor;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String toString() {
        return this.content + " - " + this.backgroundColor;
    }


    public static void main(String[] args) {
        Note note1 = new Note();
        Note note2 = new Note("Muista tunnit", Color.YELLOW);

        System.out.println(note1);
        System.out.println(note2);

        note1.setContent("Testataan setteriä ja getteriä");
        System.out.println(note1.getContent());
    }
}