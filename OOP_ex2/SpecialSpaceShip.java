import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This class responsible for the Special spaceship. It is extend of Runner.
 * This is an angry Patrick. It is rarely move, and turn for closet ship and shoot at it.
 *
 * @author Matan Shomer
 */
public class SpecialSpaceShip extends RunnerSpaceShip{

    /** Create a new gif for special ship. */
    private static final Image SPECIAL_SHIP_IMAGE =
            createImageIcon("special.gif","");

    /*----=  Constructors  =-----*/

    SpecialSpaceShip(){
        this.image = SPECIAL_SHIP_IMAGE;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * This method admiring the special spaceship. It is check if the closet ship close enough and fix it
     * sight in order to shoot that ship.
     * @param game the game object.
     * @param closetShip the closet ship object.
     * @param shipAngle the angle from the closet ship.
     * @param distanceFrom the distance from the closet ship.
     */
    public void actionOtherShips(SpaceWars game, SpaceShip closetShip, double shipAngle, double distanceFrom){
        // The max distance that the runner ship shoot. */
        double DISTANCE_SHOOT = 0.3;
        // The max angle of other ship from it that special ship shot.
        double SHOOT_ANGLE = 0.05;

        if (distanceFrom < DISTANCE_SHOOT) {
            if (shipAngle >= 0)
                this.physicsSpaceShip.move(false, LEFT);
            else if (shipAngle < 0)
                this.physicsSpaceShip.move(false, RIGHT);
        }
        if (shipAngle > -SHOOT_ANGLE && shipAngle<SHOOT_ANGLE)
            fire(game);
    }

    /**
     * Get the Image Icon from the given path (relative to the source code)
     * @param path the relative path to the image file.
     * @param description A description of the file.
     * @return the icon with the image.
     */
    private static Image createImageIcon(String path, String description) {
        java.net.URL imgURL = SpaceShip.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * special spaceship. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */    public Image getImage() {
        return (SPECIAL_SHIP_IMAGE);
    }

}
