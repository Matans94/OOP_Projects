
/**
 * This class responsible for the Basher spaceship. It is extend of Runner.
 * This ship attempt to collide with other ships. It is always accelerate and will constantly turn towards
 * the closet ship.
 *
 * @author Matan Shomer
 */
public class BasherSpaceShip extends RunnerSpaceShip {

    /**
     *This method decide in what direction the closet ship is for the basher to follow and make a collide
     * while his shield is on.
     * @param game the game object.
     * @param closetShip the closet ship object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    public void actionOtherShips(SpaceWars game, SpaceShip closetShip, double shipAngle,
                                 double distanceFrom) {
        if (shipAngle > 0)
            this.physicsSpaceShip.move(true, 1);
        else if (shipAngle==0)
            this.physicsSpaceShip.move(true, 0);
        else
            this.physicsSpaceShip.move(true, -1);

        basherShield(distanceFrom);
    }

    /**
     * This method decide if the basher need to turn on it's shield.
     * It checks specific distance and if it has enough energy for that action.
     */
    private void basherShield(double distanceFrom){
        // The max distance that the basher turn on it shield. */
        double DISTANCE_SHIELD = 0.19;

        if (distanceFrom <= DISTANCE_SHIELD && this.legalShieldOn())
            shieldOn = true;
    }

}
