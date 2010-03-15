package ui.behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.Pilot;
import java.util.Random;
import ui.actions.Barking;

public class MoveAround implements Behavior {

    private Pilot legs;
    private Random gen;

    public MoveAround(Pilot legs) {
        this.legs = legs;
        this.gen = new Random();
    }

    public boolean takeControl() {
        return true;
    }

    public void action() {
        this.move();
        Barking.doAction();
    }

    public void suppress() {
        this.legs.stop();
    }

    private void move() {
        int style = gen.nextInt(4);

        System.out.println(style);

        if (style == 0) {
            this.legs.steer(-45);
        } else if (style == 1) {
            this.legs.steer(45);
        } else if (style == 2) {
            this.legs.travel(-30);
            this.legs.rotate(90);
        } else {
            this.legs.forward();
        }
    }

}
