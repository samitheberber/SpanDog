package ui.actions;

import java.io.File;
import lejos.nxt.Sound;

public class Barking {
    public static void doAction() {
        System.out.println("Wuh");
        Sound.playSample(new File("barking_8bit.wav"));
        try{Thread.sleep(5000);} catch (Exception e) {}
    }
}
