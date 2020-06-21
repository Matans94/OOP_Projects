import java.util.*;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    protected Collection<java.lang.String> collection;

/** Boolean field that change if the collection is kind of a set.*/
    private boolean ifSet;


    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        this.collection = collection;
        this.checkIfSet();
    }



    /**
     * Method that check if the collection is a set. It is change the field isSet to true or false
     * according to it checks.
     */
    private void checkIfSet() {
        String s = "s";
        if (this.collection.contains(s)) {
            this.ifSet = !this.collection.add(s);
        } else {
            this.collection.add(s);
            this.ifSet = !this.collection.add(s);
            this.collection.remove(s);
            this.collection.remove(s);
        }
    }

    @Override
    public boolean add(java.lang.String newValue) {
        if (!this.ifSet){
            if (!this.contains(newValue)) {
                this.collection.add(newValue);
                return true;
            }
        }//when it is a set, try to add.
        else if (this.collection.add(newValue)) {
            return true;
        }
        return false;
    }


    @Override
    public boolean contains(java.lang.String searchVal) {
        return this.collection.contains(searchVal);
    }


    @Override
    public boolean delete(java.lang.String toDelete) {
        if (this.collection.remove(toDelete)) {
            return true;
        }
        return false;
    }


    @Override
    public int size() {
        return this.collection.size();
    }

    /**
     * Method that give the option to run in the cell which has linked list.
     * @return - iterator of cell.
     */
    public Iterator iterator(){
        return this.collection.iterator();
    }

    /**
     * @return - name the type of the class.
     */
    @Override
    public String toString() {
        return this.collection.getClass().getSimpleName();
    }
}
