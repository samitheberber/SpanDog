import models.Dog;
import ui.SpanUi;

public class SpanDog{

    public static void main (String[] args) {
        (new SpanUi(new Dog())).start();
    } 

}
