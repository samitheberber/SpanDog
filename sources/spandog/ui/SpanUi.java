package spandog.ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.*;
import lejos.nxt.Motor;
import spandog.models.Dog;
import spandog.ui.behaviors.*;

/**
 * This class is the main user interface.
 * @author Sami Saada
 */
public class SpanUi {

    /**
     * Dog, which is bound on UI.
     * @see spandog.models.Dog
     */
    private Dog dog;

    /**
     * Legs of dog.
     */
    private Pilot legs;

    private Arbitrator arby;

    /**
     * Creates new UI.
     * @param dog Dog instance
     */
    public SpanUi(Dog dog) {
        this.dog = dog;
        this.legs = new TachoPilot(5.6f, 9.5f, Motor.A, Motor.B, true);
        Behavior[] behaviors = {new MoveAround(this.legs), new SenseItem(this.dog, this.legs), new GetReaction(this.dog, this.legs)};
        this.arby = new Arbitrator(behaviors);
    }

    /**
     * Start the UI by starting arbitrator.
     */
    public void start() {
        System.out.println("Give a life");
        while (!Button.ENTER.isPressed()) {} //Wait for enter
        LCD.clear();
        this.arby.start();
    }

}
