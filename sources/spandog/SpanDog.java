package spandog;

import spandog.models.Dog;
import spandog.ui.SpanUi;

/**
 * This class is the executable class and main class of the package.
 * @author Sami Saada
 */
public class SpanDog {

    /**
     * The main method of SpanDog. This builds a dog and ui for it, and starts
     * the ui.
     * @param args Arguments, that are passed to program.
     */
    public static void main (String[] args) {
        (new SpanUi(new Dog())).start();
    } 

}
