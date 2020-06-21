import java.util.LinkedList;

public class ClosedHashSet extends SimpleHashSet {

    /**A manual hash set object */
    private java.lang.Object[] arraySet;

    private CollectionFacadeSet fifo;

    /**Number of elements in the array*/
    private int elementNumber = 0;

    /**The load factor is in the legal range */
    private static final int LEGAL_RANGE = 0;

    /**The load factor is above the legal load factor.*/
    private static final int ABOVE_LOAD_FACTOR = 1;

    /**The load factor is below the legal load factor.*/
    private static final int BELOW_LOAD_FACTOR = -1;

    /** The index of value that it should delete. */
    private int deleteTargetIndex;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor  - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.arraySet = new Object[INITIAL_CAPACITY];
        this.upperLoadCapacity = upperLoadFactor;
        this.lowerLoadCapacity = lowerLoadFactor;
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        this.arraySet = new Object[INITIAL_CAPACITY];
        this.upperLoadCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadCapacity = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(String[] data){
        this.arraySet = new Object[INITIAL_CAPACITY];
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

    //Methods*/


    @Override
    public boolean add(String newValue) {
        if (this.contains(newValue)) return false;

        this.resizeArray(this.loadFactorRange(1));
        this.insert(newValue);
        return true;
    }

    /**
     * Method that add a string to the array after it search for a place to it.
     * @param value - The value to add.
     */
    private void insert(String value){
        int i = 0;
        int counter = 0;
        int index = this.clamp(value.hashCode() + ((i+ i* i)/2));
        while ((this.arraySet[index] instanceof String) && (counter<this.arraySet.length)) {
            i++;
            counter++;
            index = this.clamp(value.hashCode() + ((i+ i* i)/2));
        }
        this.arraySet[index] = value;
        this.elementNumber += 1;
    }


    /**
     * Method which calculate if resize is needed to the array. It is checks if the load factor is in
     * legal range.
     * Edge Case- while there are no elements in array so it will return automatic 0;
     * @return - 0 if its in legal range. -1 if it is below and 1 if it is above.
     */
    private int loadFactorRange(int action){
        float loadFactor = (this.size() + action)/(float) this.capacity();
        if ((loadFactor > this.upperLoadCapacity) && (action != -1))
            return ABOVE_LOAD_FACTOR;
        if ((loadFactor < this.lowerLoadCapacity) && (action != 1) && (this.capacity()/2>=this.elementNumber))
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
        java.lang.Object[] copyOldArray = this.arraySet;
        java.lang.Object[] newArray = new java.lang.Object[length];
        this.arraySet = newArray;
        this.elementNumber = 0;

        for (Object data : copyOldArray){
            if (data instanceof String){
                this.insert((String) data);
            }
        }
    }


    @Override
    public boolean delete(String toDelete) {
        if (this.contains(toDelete)){
            this.arraySet[this.deleteTargetIndex] = new Object();
            this.elementNumber -= 1;
            this.resizeArray(this.loadFactorRange(-1));

            return true;
        }
        return false;
    }


    @Override
    public boolean contains(String searchVal) {
        int index = this.clamp(searchVal.hashCode());
        int i = 1;
        int counter = 0;

        while ((this.arraySet[index] != null) && (counter != this.capacity())){

            if (this.arraySet[index].equals(searchVal)){
                this.deleteTargetIndex = index;
                return true;
            }
            index = this.clamp(searchVal.hashCode()+((i+ i* i)/2));
            i++;
            counter++;
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
