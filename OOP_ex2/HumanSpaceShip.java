import oop.ex2.GameGUI;

/**
 * This class responsible of the human spaceship that the user play.
 * It is inheritor from SpaceShip class.
 * @author Matan Shomer
 */
public class HumanSpaceShip extends SpaceShip {

    /*----=  Constructors  =-----*/

    HumanSpaceShip() {
        this.image = GameGUI.SPACESHIP_IMAGE;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * In addition for doActive method in SpaceShip, this method adds the Human ship attributes, such as
     * the human can play with the keys. This method checks if the user press any game key and act the ship
     * according to his play.
     * @param game the game object.
     */
    public void specificClassAction(SpaceWars game) {
        if (game.getGUI().isTeleportPressed())
            teleport();

        keyDirector(game);

        if (game.getGUI().isShieldsPressed()) this.shieldOn = true;

        if (game.getGUI().isShotPressed()) fire(game);

    }

    /**
     * This method is responsible for the direction of the human spaceship.
     * @param game - the game object to which this ship belongs.
     */
    private void keyDirector(SpaceWars game) {
        if (game.getGUI().isUpPressed()) {
            if (game.getGUI().isLeftPressed() ^ (game.getGUI().isRightPressed())) {
                if (game.getGUI().isLeftPressed())
                    this.physicsSpaceShip.move(true, LEFT);
                if (game.getGUI().isRightPressed())
                    this.physicsSpaceShip.move(true, RIGHT);
            } else this.physicsSpaceShip.move(true, STRAIGHT);
        }
        else{
            if (game.getGUI().isLeftPressed() ^ (game.getGUI().isRightPressed())) {
                if (game.getGUI().isLeftPressed())
                    this.physicsSpaceShip.move(false, LEFT);
                if (game.getGUI().isRightPressed())
                    this.physicsSpaceShip.move(false, RIGHT);
                } else this.physicsSpaceShip.move(false, STRAIGHT);
            }
    }

    /**
     * Attempts to turn on the shield. Gui for Human.
     */
    public void shieldOn() {
        if (this.shieldOn && legalShieldOn()) {
            this.currentEnergyLevel = this.currentEnergyLevel - SHIELD_COST;
            this.image = GameGUI.SPACESHIP_IMAGE_SHIELD;
        } else this.image = GameGUI.SPACESHIP_IMAGE;
    }
}





