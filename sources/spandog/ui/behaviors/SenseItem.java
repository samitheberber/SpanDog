package spandog.ui.behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.*;
import lejos.nxt.*;
import spandog.ui.actions.Eating;
import spandog.models.Dog;

/**
 * This class implements the sensing behavior.
 * @author Sami Saada
 */
public class SenseItem implements Behavior {

    /**
     * The dog.
     */
    private Dog dog;
    /**
     * Legs of the dog.
     */
    private Pilot legs;
    /**
     * Value of already in work.
     */
    private boolean inWork;
    /**
     * Eyes of the dog.
     */
    private LightSensor eyes;
    /**
     * Nose of the dog.
     */
    private UltrasonicSensor nose;

    /**
     * Creates basic item recognize senses for dog.
     * @param dog The dog
     * @param legs Legs of the dog
     */
    public SenseItem(Dog dog, Pilot legs) {
        this.dog = dog;
        this.legs = legs;
        this.inWork = false;
        this.eyes = new LightSensor(SensorPort.S2);
        this.nose = new UltrasonicSensor(SensorPort.S1);
    }

    /**
     * Checks if the behavior takes control.
     * @return Boolean for taking control
     */
    public boolean takeControl() {
        return !this.inWork && this.nose.getDistance() < 10;
    }

    /**
     * Action of behavior. Does what it needs to do depending of item.
     */
    public void action() {
        this.inWork = true;
        int action = this.dog.getItem(this.eyes.readNormalizedValue());
        if (action == 0)
            this.eat();
        else if (action == 1)
            this.play();
        else
            this.ignore();
        this.inWork = false;
    }

    /**
     * If the action should stop, arbitrator does this.
     */
    public void suppress() {
        this.legs.stop();
    }

    /**
     * Determines eating action.
     */
    private void eat() {
        System.out.println("Eating ...");
        Eating.doAction();
    }

    /**
     * Determines playing action.
     */
    private void play() {
        System.out.println("Playing ...");
        this.legs.travel(20);
        this.legs.travel(-20);
    }

    /**
     * Determines ignoring action.
     */
    private void ignore() {
        System.out.println("Ignoring ...");
        this.legs.travel(-50);
    }

}

