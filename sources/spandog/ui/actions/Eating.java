package spandog.ui.actions;

import java.io.File;
import lejos.nxt.Sound;

/**
 * This class contains all actions, which belongs to eating.
 * @author Sami Saada
 */
public class Eating {
    public static void doAction() {
        System.out.println("Om-nom-nom");
        Sound.playSample(new File("omnomnom_8bit.wav"));
        try{Thread.sleep(2000);} catch (Exception e) {}
    }
}
