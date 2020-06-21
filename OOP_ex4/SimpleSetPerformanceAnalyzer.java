
import java.util.*;

/**
 * compare the performances of the following data structures:
 * 1. OpenHashSet
 * 2. ClosedHashSet
 * 3. Java’s TreeSet
 * 4. Java’s LinkedList
 * 5. Java’s HashSe
 */
public class SimpleSetPerformanceAnalyzer {

    /**
     * Name of source 1
     */
    private static String DATA1 = "data1.txt";
    /**
     * Name of source 2
     */
    private static String DATA2 = "data2.txt";
    /**
     * String that we check if contain, it has different hashcode in data 1. for test 3.
     */
    private static String HI = "hi";
    /**
     * String that we check if contain with the same hashcode for test 4.
     */
    private static String BIG_STRING = "-13170890158";
    /**
     * String that contain in data 2 and we want to measure the time to check contain.
     */
    private static String SMALL_STRING = "23";


    /**
     * Array for each data that the data should contain all the data in the specific file.
     */
    private static SimpleSet[] dataStructuresData1;
    private static SimpleSet[] dataStructuresData2;

    /**
     * The default array if none of the above is in use.
     */
    private static SimpleSet[] dataStructuresNew;

    /**
     * Boolean field that check if the data is already in the simpleSet array.
     */
    private static boolean flagData1 = false;
    private static boolean flagData2 = false;

    /**
     * The symbol of tests.
     */
    private static String TEST1 = "1";
    private static String TEST2 = "2";
    private static String TEST3 = "3";
    private static String TEST4 = "4";
    private static String TEST5 = "5";
    private static String TEST6 = "6";

    private static SimpleSet[] arrayConstructor() {
        SimpleSet[] array = new SimpleSet[5];
        array[0] = new OpenHashSet();
        array[1] = new ClosedHashSet();
        array[2] = new CollectionFacadeSet(new TreeSet<String>());
        array[3] = new CollectionFacadeSet(new LinkedList<String>());
        array[4] = new CollectionFacadeSet(new HashSet<String>());

        return array;
    }


    /**
     * Method which measure the time of each data structure in the array, how much ms it takes to add from
     * the data.
     *
     * @param fileName - The data source it add to each data structure.
     */
    private static void add(String fileName) {
        int MILLION = 1000000;

        SimpleSet[] array;
        if (fileName.equals(DATA1) && !flagData1) {
            dataStructuresData1 = arrayConstructor();
            array = dataStructuresData1;
            flagData1 = true;
        } else if (fileName.equals(DATA2) && !flagData2) {
            dataStructuresData2 = arrayConstructor();
            array = dataStructuresData2;
            flagData2 = true;
        } else {
            dataStructuresNew = arrayConstructor();
            array = dataStructuresNew;
        }

        System.out.println("Add all the strings in " + fileName+"\n");
        String[] stringsToAdd = Ex4Utils.file2array(fileName);
        for (SimpleSet dataStructure : array) {
            long timeBefore = System.nanoTime();
            for (String data : stringsToAdd) {
                dataStructure.add(data);
            }
            long different = (System.nanoTime() - timeBefore) / MILLION;
            System.out.println("The running time of " + dataStructure.toString() + " is " + different + " " +
                    "ms.\n");
        }
    }


    /**
     * An insert method that add all the inputs from the data. This method called when we want to build an
     * array with data structures that full with the data parameter.
     *
     * @param array    - The array it add all the data.
     * @param fileName - The file source that the data come from.
     */
    private static void insert(SimpleSet[] array, String fileName) {
        String[] stringsToAdd = Ex4Utils.file2array(fileName);
        for (SimpleSet dataStructure : array) {
            for (String data : stringsToAdd) {
                dataStructure.add(data);
            }
        }
    }


    /**
     * This method can allows us to use the array of data structure it already build the array of the test
     * so it won't need to build it once again.
     *
     * @param test The number of the test to analyze.
     * @return - An array that we should use (new one or exist- according to the past test).
     */
    private static SimpleSet[] chooseArray(String test) {
        if (test.equals(TEST3) || test.equals(TEST4)) {
            if (!flagData1) {
                dataStructuresData1 = arrayConstructor();
                insert(dataStructuresData1, DATA1);
            }
            return dataStructuresData1;
        } else {// (test == TEST5 || test == TEST6)
            if (!flagData2) {
                dataStructuresData2 = arrayConstructor();
                insert(dataStructuresData2, DATA2);
            }
            return dataStructuresData2;
        }
    }


    /**
     * Method that check the running time of conatain operator in different data structure.
     *
     * @param searchValue - The value that we search if it contain in the data structure.
     * @param test        - The number of the test to analyze.
     */
    private static void contain(String searchValue, String test) {
        int LINKED_LIST = 3;
        int NUMBER_OF_ITERATION = 70000;
        long difference;

        SimpleSet[] array = chooseArray(test);
        System.out.println("Contain the string " + searchValue + ". Test " + test);
        for (int i = 0; i < 5; i++) {

            if (i != LINKED_LIST) {
                iteration(array[i], searchValue, NUMBER_OF_ITERATION); // WarmUp
                long timeBefore = System.nanoTime();
                iteration(array[i], searchValue, NUMBER_OF_ITERATION);
                difference = (System.nanoTime() - timeBefore) / NUMBER_OF_ITERATION;
            } else {
                NUMBER_OF_ITERATION = 7000;
                long timeBefore = System.nanoTime();
                iteration(array[i], searchValue, NUMBER_OF_ITERATION);
                difference = (System.nanoTime() - timeBefore) / NUMBER_OF_ITERATION;
            }
            System.out.println("The running time of " + array[i].toString() + " is " +
                    difference + " " + "ns.\n");
        }
    }


    /**
     * Simple iterator from 0 to 70,000.
     * This method called twice: First When we want to warm up the data structure before measuring.
     * The second when we want to measure the running time of contain operation.
     *
     * @param dataStructure - The data structure we want to check.
     * @param searchValue   - the value that we search if it contain in the data structure.
     */
    private static void iteration(SimpleSet dataStructure, String searchValue, int numberOfiteration) {
        for (int i = 0; i < numberOfiteration; i++) {
            dataStructure.contains(searchValue);
        }
    }


    /**
     * Main method that measures the run-times requested in the "Performance Analysis" section.
     *
     * @param args - What analyze the user want to do from 1 to 6.
     */
    public static void main(String[] args) {
        Set<String> targetSet = new HashSet<String>();
        Arrays.sort(args);
        Collections.addAll(targetSet, args);

        for (String test : targetSet) {
            if (test.equals(TEST1)) add(DATA1);
            else if (test.equals(TEST2)) add(DATA2);
            else if (test.equals(TEST3)) contain(HI, TEST3);
            else if (test.equals(TEST4)) contain(BIG_STRING, TEST4);
            else if (test.equals(TEST5)) contain(SMALL_STRING, TEST5);
            else if (test.equals(TEST6)) contain(HI, TEST6);
        }
    }
}
