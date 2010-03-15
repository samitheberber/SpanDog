package ui.actions;

import java.io.File;
import lejos.nxt.Sound;

public class Eating {
    public static void doAction() {
        System.out.println("Om-nom-nom");
        Sound.playSample(new File("omnomnom_8bit.wav"));
        try{Thread.sleep(2000);} catch (Exception e) {}
    }
}
