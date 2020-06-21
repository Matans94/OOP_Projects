
/**
 * This class has a single static method which is used by the supplied driver to create all the spaceship
 * objects according to the command line arguments.
 */
public class SpaceShipFactory {

    /**
     * This method is calling in the opening, after the user input the spaceships that he wants in the game.
     * @param args - The input in the command line that choose the types of the spaceships in the game.
     * @return an array containing the spaceships that were input in the command line argument.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] SpaceShipArgs = new SpaceShip[args.length];
        int index = 0;
        for (String ship: args){
            switch (ship) {
                case "h":
                    SpaceShipArgs[index] = new HumanSpaceShip();
                    break;
                case "r":
                    SpaceShipArgs[index] = new RunnerSpaceShip();
                    break;
                case "a":
                    SpaceShipArgs[index] = new AggressiveSpaceShip();
                    break;
                case "b":
                    SpaceShipArgs[index] = new BasherSpaceShip();
                    break;
                case "d":
                    SpaceShipArgs[index] = new DrunkardSpaceShip();
                    break;
                case "s":
                    SpaceShipArgs[index] = new SpecialSpaceShip();
                    break;
                default:
                    SpaceShipArgs[index] = null;
                    break;
            }
            index++;
        }
        return SpaceShipArgs;
    }
}
