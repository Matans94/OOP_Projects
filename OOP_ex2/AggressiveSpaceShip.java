/**
 * This class responsible for the aggressive spaceship. It is extend of Runner.
 * This ship pursues other ships. It will always accelerate and turn towards the nearest ship.
 * @author Matan Shomer
 */
public class AggressiveSpaceShip extends RunnerSpaceShip {

    /**
     * This method admiring the aggressive ship. It decide in which direction it should go in order to
     * attack the closet ship and shoot it.
     * @param game the game object.
     * @param closetShip the closet ship object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    public void actionOtherShips(SpaceWars game, SpaceShip closetShip, double shipAngle, double distanceFrom){

        if (shipAngle > 0) {
            this.physicsSpaceShip.move(true, LEFT);
        } else if (shipAngle < 0)
            {this.physicsSpaceShip.move(true, RIGHT);
        }
        aggressiveSpaceShip(game, shipAngle);
        }

    /**
     * Checks if the closet ship in specific sight.
     * @param game the game object.
     * @param shipAngle the angle from the closet ship.
     */
    private void aggressiveSpaceShip(SpaceWars game, double shipAngle){
        // The angle from other ship that the aggressive ship shoot the closet ship. */
        double ANGLE_SHOOT = 0.21;

        if (shipAngle < ANGLE_SHOOT && shipAngle >- ANGLE_SHOOT) {fire(game);
        }
    }
 }

