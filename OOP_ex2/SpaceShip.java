import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships implement for the SpaceWars game.
 *  
 * @author oop
 */
public class SpaceShip{

    /**Count the energy that have to a single ship. */
    public int currentEnergyLevel;

    /**The maximum capacity of energy that the ship has. */
    public int maxEnergyLevel;

    /**Counter of the health level of a single ship. */
    public int health;

    /**Location of the ship in the board. */
    public SpaceShipPhysics physicsSpaceShip;

    /**Counts how many rounds have been pass between a single shot. */
    private int firingCoolDown;

    /** The picture user interface used to display the ship, and get input.*/
    public Image image;

    /**Boolean value, check if the shield is on or off. */
    public boolean shieldOn;

    /**The start value of current energy level. */
    private final int START_ENERGY_CURRENT = 190;

    /**The start value of the capacity of energy for ship. */
    private final int START_ENERGY_CAPACITY = 220;

    /**The start value of the health in single ship. */
    private final int FULL_HEALTH = 22;

    /**The energy cost of teleport for ship. */
    private final int TELEPORT_COST = 140;

    /**Hit point when the ship got shot or collide with other ship. */
    private final int HIT_POINT = 1;

    /**The number of subtracting from the current energy level when ship commit collide or had been shot. */
    private final int ENERGY_HIT = 10;

    /**The energy cost of fire a shot. */
    private final int FIRE_COST = 19;

    /**Counter of rounds. Split rounds between every shot. */
    private final int SHOOT_SPACE = 7;

    /**The energy cost to turn on the shield. */
    public final int SHIELD_COST = 3;

    /**The energy bonus to the current and the max capacity when the shield is on while get collide. */
    private final int BONUS_SCORE = 18;

    /**Turn left magic number. */
    protected final int LEFT = 1;

    /**Turn right magic number. */
    protected final int RIGHT = -1;

    /**Not right or left magic number. */
    protected final int STRAIGHT = 0;

    /*----=  Constructors  =-----*/

    SpaceShip(){
        this.image = GameGUI.ENEMY_SPACESHIP_IMAGE;
        this.currentEnergyLevel = START_ENERGY_CURRENT;
        this.maxEnergyLevel = START_ENERGY_CAPACITY;
        this.health = FULL_HEALTH;
        this.physicsSpaceShip = new SpaceShipPhysics();
        this.firingCoolDown = 0;
        this.shieldOn = false;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.shieldOn = false;
        specificClassAction(game);
        shieldOn(); // Check every loop if the shield is true of false.

        if (this.firingCoolDown > 0)
            firingCoolDown--;

        if (this.currentEnergyLevel<=this.maxEnergyLevel)
            this.currentEnergyLevel++;

        if (this.currentEnergyLevel>this.maxEnergyLevel)
            this.currentEnergyLevel = this.maxEnergyLevel;
    }

    /**
     * This method is called every time a collision with this ship occurs.
     * It first check if the shield of the ship is on and then do the changes.
     * When shield is on- the energy capacity and current energy get bonus.
     * else - health's score and energy capacity get down.
     */
    public void collidedWithAnotherShip(){
        if (!this.shieldOn){
            this.health = this.health - HIT_POINT;
            this.maxEnergyLevel = this.maxEnergyLevel- ENERGY_HIT;
        } else { //shield is on
            this.maxEnergyLevel = this.maxEnergyLevel+BONUS_SCORE;
            this.currentEnergyLevel = this.currentEnergyLevel+BONUS_SCORE;
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.health = FULL_HEALTH;
        this.physicsSpaceShip = new SpaceShipPhysics();
        this.maxEnergyLevel = START_ENERGY_CAPACITY;
        this.currentEnergyLevel = START_ENERGY_CURRENT;
        this.firingCoolDown = 0;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.health<=0){
            return true;}
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.physicsSpaceShip;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!this.shieldOn){
            this.health = this.health -HIT_POINT ;
            this.maxEnergyLevel = this.maxEnergyLevel - ENERGY_HIT;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {return this.image;}

    /**
     * Attempts to fire a shot.d
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if ((this.firingCoolDown == 0) && (this.currentEnergyLevel >= FIRE_COST)) {
           game.addShot(this.physicsSpaceShip);
           this.currentEnergyLevel = this.currentEnergyLevel - FIRE_COST;
           this.firingCoolDown = SHOOT_SPACE;
       }
    }

    /**
     * Attempts to turn on the shield. Gui for enemy.
     */
    public void shieldOn() {
        if (this.shieldOn && legalShieldOn()) {
            this.currentEnergyLevel = this.currentEnergyLevel - SHIELD_COST;
            this.image = GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } else {
            this.image = GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Checks if the shield can turn on, if the ship has enough energy.
     * @return boolean value. true if it has enough energy, false otherwise.
     */
    public boolean legalShieldOn(){
        if (this.currentEnergyLevel >= SHIELD_COST)
            return true;

        return false;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.currentEnergyLevel >= TELEPORT_COST) {
            this.currentEnergyLevel = this.currentEnergyLevel - TELEPORT_COST;
            this.physicsSpaceShip = new SpaceShipPhysics();
        }
    }

    /**
     * Method that changes in every inheritance (Human & Runner)
     * @param game the game object.
     */
    public void specificClassAction(SpaceWars game){    }


}
