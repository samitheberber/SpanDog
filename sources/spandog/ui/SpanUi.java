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

    private Dog dog;

    private Pilot legs;

    public SpanUi(Dog dog) {
        this.dog = dog;
        this.legs = new TachoPilot(5.6f, 9.5f, Motor.A, Motor.B, true);
    }

    public void start() {
        System.out.println("Give a life");
        while (!Button.ENTER.isPressed()) {} //Wait for enter
        LCD.clear();
        Behavior[] behaviors = {new MoveAround(this.legs), new SenseItem(this.dog, this.legs), new GetReaction(this.dog, this.legs)};
        Arbitrator arby = new Arbitrator(behaviors);
        arby.start();
    }

}
