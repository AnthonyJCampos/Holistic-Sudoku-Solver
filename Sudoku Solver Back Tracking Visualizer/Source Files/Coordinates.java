/**
 * @author Anthony Campos
 */
package study.sudokusolver;

public class Coordinates implements Comparable<Coordinates> {

    // attributes of Coordinates
    private int x_; // hold row/x coordinate
    private int y_; // hold col/y coordinate
    private int options_;      // holds amount of options

    public Coordinates(int x, int y, int options) {

        this.x_ = x;
        this.y_ = y;
        this.options_ = options;

    } // end of constructor 

    /**
     * @brief getX, get row/x coordinate
     * @return integer value held in x_
     */
    public int getX() {

        return x_;

    } // end of getX

    /**
     * @brief getX, col/y coordinate
     * @return integer value held in y_
     */
    public int getY() {

        return y_;

    } // end of getY

    /**
     * @brief compareTo, comparable for coordinate object
     * @parm Coordinates [other]
     * @return the Coordinate object with the least amount of options
     */
    @Override
    public int compareTo(Coordinates other) {

        return Integer.compare(options_, other.options_);

    } // end of compareTo

} // end of Coordinates
