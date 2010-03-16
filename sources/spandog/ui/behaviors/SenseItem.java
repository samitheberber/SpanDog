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

    private Dog dog;
    private Pilot legs;
    private boolean inWork;
    private LightSensor eyes;
    private UltrasonicSensor nose;

    public SenseItem(Dog dog, Pilot legs) {
        this.dog = dog;
        this.legs = legs;
        this.inWork = false;
        this.eyes = new LightSensor(SensorPort.S2);
        this.nose = new UltrasonicSensor(SensorPort.S1);
    }

    public boolean takeControl() {
        return !this.inWork && this.nose.getDistance() < 10;
    }

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

    public void suppress() {
        this.legs.stop();
    }

    private void eat() {
        System.out.println("Eating ...");
        Eating.doAction();
    }

    private void play() {
        System.out.println("Playing ...");
        this.legs.travel(20);
        this.legs.travel(-20);
    }

    private void ignore() {
        System.out.println("Ignoring ...");
        this.legs.travel(-50);
    }

}

