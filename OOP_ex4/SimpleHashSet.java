/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet{

    /**Describes the lower load factor of a newly created hash set*/
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**Describes the higher load factor of a newly created hash set*/
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /**Describes the capacity of a newly created hash set*/
    protected static final int INITIAL_CAPACITY = 16;

    /**The higher load factor of a newly created hash set- not default. */
    protected float upperLoadCapacity;

    /**The lower load factor of a newly created hash set- not default. */
    protected float lowerLoadCapacity;


    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY
     */
    protected SimpleHashSet(){
        this.upperLoadCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadCapacity = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor - the lower load factor before rehashing
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadCapacity = upperLoadFactor;
        this.lowerLoadCapacity = lowerLoadFactor;
    }

    /**
     *
     * @return - The current capacity (number of cells) of the table.
     */
    public abstract int capacity();


    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index - the index before clamping
     * @return an index properly clamped
     */
    protected int clamp(int index){
        return index & (this.capacity()-1);
    }

    /**
     *
     * @return - The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return this.lowerLoadCapacity;
    }

    /**
     * @return - The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return this.upperLoadCapacity;
    }

    /**
     * @return - name the type of the class.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
