package spandog.ui.actions;

import java.io.File;
import lejos.nxt.Sound;

/**
 * This class contains all actions, which belongs to barking.
 * @author Sami Saada
 */
public class Barking {

    /**
     * Does the main barking action.
     */
    public static void doAction() {
        System.out.println("Wuh");
        Sound.playSample(new File("barking_8bit.wav"));
        try{Thread.sleep(5000);} catch (Exception e) {}
    }

}
