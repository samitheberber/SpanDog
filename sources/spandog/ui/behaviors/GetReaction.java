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

    /**
     * State if dog is slapped.
     */
    private static boolean slapped = false;
    /**
     * State if dog is afraid from shouting.
     */
    private static boolean shouted = false;
    /**
     * The dog.
     */
    private Dog dog;
    /**
     * Legs of the dog.
     */
    private Pilot legs;

    /**
     * Created reaction behavior.
     * @param dog The dog
     * @param legs Legs of the dog
     */
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

        /**
         * The dog.
         */
        private Dog dog;
        /**
         * Skin of the dog.
         */
        private TouchSensor skin;

        /**
         * Creates slap listener.
         * @param dog The dog
         */
        public SlapListener(Dog dog) {
            this.dog = dog;
            this.skin = new TouchSensor(SensorPort.S3);
        }

        /**
         * Senses if the state of skin has changed.
         * @param port Port of skin sensor
         * @param o Old value of sensor
         * @param n New value of sensor
         */
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

        /**
         * The dog.
         */
        private Dog dog;
        /**
         * Ears of the dog.
         */
        private SoundSensor ears;

        /**
         * Creates shout listener.
         * @param dog The dog
         */
        public ShoutListener(Dog dog) {
            this.dog = dog;
            this.ears = new SoundSensor(SensorPort.S4);
        }

        /**
         * Senses if the state of ears has changed.
         * @param port Port of ears sensor
         * @param o Old value of sensor
         * @param n New value of sensor
         */
        public void stateChanged(SensorPort port, int o, int n) {
            if (ears.readValue() > 80) {
                shouted = true;
                if (this.dog.whenWasLastAction() < 5000) {
                    this.dog.blame();
                }
            }
        }

    }

    /**
     * Checks if the behavior takes control.
     * @return Boolean for taking control
     */
    public boolean takeControl() {
        return this.slapped || this.shouted;
    }

    /**
     * Action of behavior. Does what it needs to do depending of senses.
     */
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

    /**
     * If the action should stop, arbitrator does this.
     */
    public void suppress() {
        this.legs.stop();
    }

}
