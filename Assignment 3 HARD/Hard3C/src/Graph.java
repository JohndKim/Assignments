import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a graph and all its counterparts (starting city, end city, number of cities, the adjacency matrix)
 *
 * @author kayak >:D
 * @version 1.0
 */

public class Graph {
    private final String path;
    private final int startCity;
    private final int endCity;
    private final int steps;
    private final int numOfCities;
    private final int[][] matrix;
    private final String[] cities;


    /**
     * Initializes the path (file we want to read), start city, end city, number of steps, the array of cities, the adjacency matrix of cities, and number of cities
     *
     * @param path the location of the file we want to read from
     * @param startCity the starting city
     * @param endCity the destination city
     * @param steps number of steps to go from starting city to destination city
     */
    public Graph(String path, int startCity, int endCity, int steps){
        this.path = path;
        this.startCity = startCity;
        this.endCity = endCity;
        this.steps = steps;
        this.cities = readFile(path);
        this.numOfCities = findNumOfCities(cities);
        this.matrix = createMatrix(cities); // am i doing this right T-T or is this too many like constructor things
    }

    /**
     * multiplies the adjacency matrix by the number of steps to find how many ways there are to go from city a to b
     *
     * @return the number of ways to go from starting to destination city
     */
    public int adjacencyMatrixMultiplication(){
        int[][] tmp = createEmptyMatrix();
        // steps = exponential factor (raised thing)
        for (int h = 0; h < steps; h++) {
            // rows
            for (int i = 0; i < numOfCities; i++) {
                // columns
                for (int j = 0; j < numOfCities; j++) {
                    for (int k = 0; k < numOfCities; k++) {
                        // row * columns
                        tmp[i][j] += matrix[i][k] * matrix[j][k];
                    }
                }
            }
        }
        return tmp[startCity][endCity];
    }

    /**
     * finds the number of cities
     *
     * @param cities the string array containing all cities
     * @return the number of cities
     */
    public int findNumOfCities(String[] cities){
        int temp1;
        int temp2;
        int biggestNum = 0;
        // for each line
        for (int i = 0; i < cities.length; i++) {
            String[] nodes = cities[i].split("\\s"); // separates the string by white space
            temp1 = Integer.parseInt(nodes[0]); // start city, 0
            temp2 = Integer.parseInt(nodes[1]); // end city, 1

            if (i == 0) biggestNum = temp1; // sets biggestNum
            if (biggestNum < temp1) biggestNum = temp1;
            else if (biggestNum < temp2) biggestNum = temp2;
        }     
        return biggestNum+1;
    }

    /**
     * Creates an initialized, empty, matrix (with 0s instead of null)
     *
     * @return
     */
    public int[][] createEmptyMatrix(){
        // this will size the matrix (e.g. 5x5)
        int[][] tmp = new int[numOfCities][numOfCities];
        // this will fill the matrix with all 0s
        for (int i = 0; i < numOfCities; i++){
            for (int j = 0; j < numOfCities; j++){
                tmp[i][j] = 0;
            }
        }
        return tmp;
    }

    /**
     * Creates the matrix with all the cities
     *
     * @param cities the string with all the cities
     * @return the city matrix
     */
    public int[][] createMatrix(String[] cities){
        int[][] tmp = createEmptyMatrix();
        int temp1;
        int temp2;
        // for each line
        for (int i = 0; i < numOfCities; i++){
            String[] nodes = cities[i].split("\\s"); // separates the string by white space
            temp1 = Integer.parseInt(nodes[0]); // start city
            temp2 = Integer.parseInt(nodes[1]); // end city
            tmp[temp1][temp2] = 1; // one path from temp1 to temp2
        }
        return tmp;
    }

    /**
     * this method prints the matrix as a string
     *
     * @param matrix we want to be printed
     * @return the string matrix
     */
    public String tooString(int[][] matrix){
        String stringMatrix = "";
        for (int i = 0; i < numOfCities; i++){
            for (int j = 0; j < numOfCities; j++){
                stringMatrix += matrix[i][j] + " ";
            }
            stringMatrix += "\n";
        }
        return stringMatrix;
    }

    /**
     * Reads the file with the specified path and returns its content stored in a {@code String} array, whereas the
     * first array field contains the file's first line, the second field contains the second line, and so on.
     *
     * @param  path the path of the file to be read
     * @return the content of the file stored in a {@code String} array
     */
    public static String[] readFile(final String path) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().toArray(String[]::new);
        } catch (final IOException e) {
            /*
             * You can expect that the praktomat exclusively provides valid file-paths. Therefore
             * there will no IOException occur while reading in files during the tests, the
             * following RuntimeException does not have to get handled.
             */
            throw new RuntimeException(e);
        }
    }

    public String getPath() {
        return path;
    }

    public int getStartCity() {
        return startCity;
    }

    public int getEndCity() {
        return endCity;
    }

    public int getSteps() {
        return steps;
    }

    public int getNumOfCities() {
        return numOfCities;
    }

    public String[] getCities() {
        return cities;
    }

    /**
     * returns the matrix
     *
     * @return the matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }
}
