/**
 * This class responsible for the runner space ship, and the father of the other enemy space ship.
 * The runner attempt to run away from the fight. It will always accelerate and will constantly turn away
 * from the closet ship.
 * @author Matan Shomer
 */
public class RunnerSpaceShip extends SpaceShip{

    /**
     * This method is the main enemy method. All the enemy ships are using that method.
     * It calculates the distance and the angle from the closet ship.
     * @param game the game object.
     */
    public void specificClassAction(SpaceWars game){
        SpaceShip closetShip = game.getClosestShipTo(this);
        double angleToNum = this.physicsSpaceShip.angleTo(closetShip.physicsSpaceShip);
        double distanceFrom = this.physicsSpaceShip.distanceFrom(closetShip.physicsSpaceShip);
        actionOtherShips(game, closetShip, angleToNum, distanceFrom);
    }

    /**
     * This method is responsible of the runner behavior.
     * Checks if the runner need to teleport if other ship is too close to it and the angle is small;
     * and decide how the runner avoid from other ships.
     * @param game the game object.
     * @param closetShip the closet ship object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    public void actionOtherShips(SpaceWars game, SpaceShip closetShip, double shipAngle,
                                 double distanceFrom) {
        // The max distance that the runner ship teleport. */
        double TELEPRT_DISTANCE = 0.25;
        // The max angle of other ship from it that runner ship teleport.
        double TELEPORT_ANGLE = 0.23;

        if ((distanceFrom < TELEPRT_DISTANCE) &&
                (shipAngle < TELEPORT_ANGLE && shipAngle > -TELEPORT_ANGLE)){
            teleport();
        }
        avoidFromOthers(shipAngle);
    }

    /**
     * Decide in which direction the runner goes for avoid other ships.
     * @param shipAngle the angle from the closet ship.
     */
    private void avoidFromOthers(double shipAngle) {
        if (shipAngle > 0)
            this.physicsSpaceShip.move(true, RIGHT);
        else if (shipAngle == 0)
            this.physicsSpaceShip.move(true, STRAIGHT);
        else
            this.physicsSpaceShip.move(true, LEFT);
    }

}
