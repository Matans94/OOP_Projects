import java.util.*;
import java.util.Iterator;

public class OpenHashSet extends SimpleHashSet {

    /**A manual hash set object */
    private CollectionFacadeSet[] arraySet;

    /**Number of elements in the array*/
    private int elementNumber = 0;

    /**The load factor is in the legal range */
    private static final int LEGAL_RANGE = 0;

    /**The load factor is above the legal load factor.*/
    private static final int ABOVE_LOAD_FACTOR = 1;

    /**The load factor is below the legal load factor.*/
    private static final int BELOW_LOAD_FACTOR = -1;


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.arraySet = new CollectionFacadeSet[INITIAL_CAPACITY];
        this.upperLoadCapacity = upperLoadFactor;
        this.lowerLoadCapacity = lowerLoadFactor;
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        this.arraySet = new CollectionFacadeSet[INITIAL_CAPACITY];
        this.upperLoadCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadCapacity = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(String[] data){
        this.arraySet = new CollectionFacadeSet[INITIAL_CAPACITY];
        this.upperLoadCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadCapacity = DEFAULT_LOWER_CAPACITY;
        this.dataHelper(data);
    }

    /**
     * Helper method to build an array for the constructor with data.
     * @param data - Values to add to the set.
     */
    private void dataHelper(String[] data){
        for (String str : data) {
            this.add(str);
        }
    }


    // Methods*/


    @Override
    public boolean add(String newValue) {
        if ((this.contains(newValue)))  return false;

        this.resizeArray(this.loadFactorRange(1));
        this.insert(newValue);
        return true;
    }


    /**
     * Method which calculate if resize is needed to the array. It is checks if the load factor is in
     * legal range.
     * Edge Case- while there are no elements in array so it will return automatic 0;
     * @return - 0 if its in legal range. -1 if it is below and 1 if it is above.
     */
    private int loadFactorRange(int action){
        //Edge case- empty array
        if (this.size()  + action < 0) return LEGAL_RANGE;

        float loadFactor = (this.size() + action)/(float) this.capacity();

        if ((loadFactor > this.getUpperLoadFactor()) && (action != 0))
            return ABOVE_LOAD_FACTOR;
        if ((loadFactor < this.getLowerLoadFactor()) && (action != 1))
            return BELOW_LOAD_FACTOR;
        else return LEGAL_RANGE;
    }


    /**
     * Method that decides if the size of the array is enough according to the param, and send it to the
     * rehash method respectively.
     * @param range - number, can be 0 - the load factor is ok, -1 when the array should be decrease and 1
     *              when the array should be increase.
     */
    private void resizeArray(int range) {
        if ((range == LEGAL_RANGE) || (this.capacity()==1)) return;

        if (range == ABOVE_LOAD_FACTOR) {
            reHash(this.capacity() * 2);
        }
        else { //BELOW_LOAD_FACTOR
            reHash(this.capacity() / 2);
        }
    }


    /**
     *Method which doing the rehash to a new bigger or smaller array.
     * @param length - the capacity of the old array.
     */
    private void reHash(int length){
        CollectionFacadeSet[] copyOldArray = this.arraySet;
        this.arraySet = new CollectionFacadeSet[length];
        this.elementNumber = 0;

        for (CollectionFacadeSet data : copyOldArray) {
            if (data != null) {
                Iterator dataIterator = data.iterator();
                while (dataIterator.hasNext()) {
                    String element = (String) dataIterator.next();
                    this.insert(element);
                }
            }
        }
    }

    /**
     * Insert method, add helper. Add a string to the open hash set.
     * @param value
     */
    private void insert(String value){
        int index = this.clamp(value.hashCode());
        if(this.arraySet[index] == null){
            this.arraySet[index] = new CollectionFacadeSet (new LinkedList<String>());
        }
        this.arraySet[index].add(value);
        this.elementNumber += 1;
    }


    @Override
    public boolean contains(String searchVal) {

        int index = this.clamp(searchVal.hashCode());

        if (this.arraySet[index] == null)
            return false;
        return this.arraySet[index].contains(searchVal);
    }


    @Override
    public boolean delete(String toDelete) {
        if (this.contains(toDelete)) {
            int index = this.clamp(toDelete.hashCode());
            this.arraySet[index].delete(toDelete);
            this.elementNumber -= 1;
            this.resizeArray(this.loadFactorRange(0));

            return true;
        }
        return false;
    }


    @Override
    public int size() {
        return this.elementNumber;
    }


    @Override
    public int capacity() {
        return this.arraySet.length;
    }
}
