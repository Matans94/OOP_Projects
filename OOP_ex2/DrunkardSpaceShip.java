import java.util.Random;

/**
 * This class responsible for the drunk spaceship. It is extend of Runner.
 * Its pilot had too much drink. His shield broken and turn on and off, and his direction is random.
 * Sometimes it executing teleport and it shoot other spaceship if it close enough and not in his sight,
 * well.. noob pilot.
 *
 * @author Matan Shomer
 */
public class DrunkardSpaceShip extends RunnerSpaceShip {

    private int counterDrunkShield;

    /*----=  Constructors  =-----*/

    DrunkardSpaceShip() {
        this.counterDrunkShield = 0;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * This method admiring the drunk spaceship. It is responsible for the shield when it is need to turn
     * on and off; It is plans it direction; decide when to teleport and when to shoot other ships.
     * @param game the game object.
     * @param closetShip the closet ship object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    public void actionOtherShips(SpaceWars game, SpaceShip closetShip, double shipAngle, double distanceFrom){
        counterShieldTime();
        if (legalShieldOn()) shieldOn();
        counterDrunkShield--;
        randomDirection();
        randomTeleport();
        shootingShip(game, shipAngle, distanceFrom);
    }

    /**
     * The shield has a counter that is on for 20 rounds and off for 40 rounds.
     */
    private void counterShieldTime(){
        //The number of rounds the shield of the drunk spaceship is on.
        int SHIELD_ON_TIMER = 20;
        //The number of rounds the shield of the drunk spaceship is off.
        int SHIELD_OFF_TIMER = 40;

        if (counterDrunkShield == 0){
            if (shieldOn){
                shieldOn = false;
                counterDrunkShield = SHIELD_OFF_TIMER;
            }else {
                shieldOn = true;
                counterDrunkShield = SHIELD_ON_TIMER;
            }
        }
    }

    /**
     * This method draw a number from 0 to 2 and decide in which way the drunk goes in every round.
     */
    private void randomDirection(){
        Random treeDirection = new Random();
        Random twoExcelerator = new Random();
        int randomDirection = treeDirection.nextInt(3);
        boolean exceleratorIndicator = twoExcelerator.nextBoolean();

        switch (randomDirection){
            case 0:
                this.physicsSpaceShip.move(exceleratorIndicator, STRAIGHT);
            case 1:
                this.physicsSpaceShip.move(exceleratorIndicator, LEFT);
            case 2:
                this.physicsSpaceShip.move(exceleratorIndicator, RIGHT);
        }
    }

    /**
     * This method draw a number between 1 to 100 and if it 99 it will teleport.
     */
    private void randomTeleport(){
        Random teleportOption = new Random();
        int randomTeleport = teleportOption.nextInt(100);

        if (randomTeleport/99 == 1) {
            teleport();
        }
    }

    /**
     * This method responsible when the drunk spaceship is shooting.
     * @param game the game object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    private void shootingShip(SpaceWars game, double shipAngle, double distanceFrom){
        // The max distance that the runner ship shoot. */
        double DISTANCE_SHOOT = 0.25;
        // The angle from the closet ship that it shoot when the other ship not in his sight. */
        double ANGLE_SHOOT = 0.1;

        if ((distanceFrom < DISTANCE_SHOOT) && (shipAngle > ANGLE_SHOOT && shipAngle < -ANGLE_SHOOT)){
           fire(game);
        }
    }




}
