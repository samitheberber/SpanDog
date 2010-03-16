package spandog.ui.behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.Pilot;
import lejos.nxt.*;
import spandog.models.Dog;

/**
 * This class handles all reaction behaviors.
 * @author Sami Saada
 */
public class GetReaction implements Behavior {

    private static boolean slapped = false;
    private static boolean shouted = false;
    private Dog dog;
    private Pilot legs;

    public GetReaction(Dog dog, Pilot legs) {
        this.dog = dog;
        this.legs = legs;
        SensorPort.S3.addSensorPortListener(new SlapListener(this.dog));
        SensorPort.S4.addSensorPortListener(new ShoutListener(this.dog));
    }

    /**
     * This class listens of slap.
     */
    class SlapListener implements SensorPortListener {

        private Dog dog;
        private TouchSensor skin;

        public SlapListener(Dog dog) {
            this.dog = dog;
            this.skin = new TouchSensor(SensorPort.S3);
        }

        public void stateChanged(SensorPort port, int o, int n) {
            if (skin.isPressed()) {
                slapped = true;
                if (this.dog.whenWasLastAction() < 5000) {
                    this.dog.grant();
                }
            }
        }

    }

    /**
     * This class listens of shout.
     */
    class ShoutListener implements SensorPortListener {

        private Dog dog;
        private SoundSensor ears;

        public ShoutListener(Dog dog) {
            this.dog = dog;
            this.ears = new SoundSensor(SensorPort.S4);
        }

        public void stateChanged(SensorPort port, int o, int n) {
            if (ears.readValue() > 80) {
                shouted = true;
                if (this.dog.whenWasLastAction() < 5000) {
                    this.dog.blame();
                }
            }
        }

    }

    public boolean takeControl() {
        return this.slapped || this.shouted;
    }

    public void action() {
        if (this.slapped) {
            this.legs.travel(-20);
            this.legs.travel(20);
            this.slapped = false;
        } else if (this.shouted) {
            this.legs.travel(-30);
            this.shouted = false;
        }
    }

    public void suppress() {
        this.legs.stop();
    }

}
