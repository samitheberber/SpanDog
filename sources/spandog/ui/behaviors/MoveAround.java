package spandog.ui.behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.Pilot;
import java.util.Random;
import spandog.ui.actions.Barking;

/**
 * This class implements the moving behavior.
 * @author Sami Saada
 */
public class MoveAround implements Behavior {

    /**
     * Legs of the dog.
     */
    private Pilot legs;
    /**
     * Random number generator.
     */
    private Random gen;

    /**
     * Creates moving behavior.
     * @param legs Legs of the dog
     */
    public MoveAround(Pilot legs) {
        this.legs = legs;
        this.gen = new Random();
    }

    /**
     * Checks if the behavior takes control.
     * @return Boolean for taking control
     */
    public boolean takeControl() {
        return true;
    }

    /**
     * Action of behavior.
     */
    public void action() {
        this.move();
        Barking.doAction();
    }

    /**
     * If the action should stop, arbitrator does this.
     */
    public void suppress() {
        this.legs.stop();
    }

    /**
     * The movement action, which selects the style of movement.
     */
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
