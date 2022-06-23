/**
 * @author Anthony Campos
 */
package study.sudokusolver;

// current class imports 
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

public class Puzzle {

    // Fixed row of puzzle grid. 
    private final int defaultRowSize_ = 9;
    // Fixed column size of puzzle.
    private final int defaultColSize_ = 9;
    // holds the count of variable entries in the puzzle
    private int size_;

    boolean solvedFlag;
    // Puzzle data structure
    private Square[][] puzzleStructure_;

    // constructor
    public Puzzle() {

        this.size_ = 81;
        this.solvedFlag = false;
        this.puzzleStructure_ = new Square[defaultRowSize_][defaultColSize_];

    } // end of constructor 

    /**
     * @brief fillPuzzlePanel, build out the UI objects that will make up the
     * visual of the puzzle
     * @param JPanel [puzzlePanel] the panel that will make up the puzzle
     * @pre none
     * @post puzzle UI object built out
     * @return true if [newValue] added to indices, false otherwise
     */
    public void fillPuzzlePanel(JPanel puzzlePanel) {

        for (int row = 0; row < defaultRowSize_; row++) {

            for (int col = 0; col < defaultColSize_; col++) {

                Square sq = new Square(-1, false);
                puzzleStructure_[row][col] = sq;
                puzzlePanel.add(puzzleStructure_[row][col].getCell());

            } // end for 

        } // end for

    } // end of fill PuzzlePanel

    /**
     * @brief gets, current value at given row [x] and col [y]
     * @param int [x] row index, and int [y] col index
     * @return the integer value at the given indices
     */
    public int get(int x, int y) {

        return puzzleStructure_[x][y].getValue();

    } // end of get

    /**
     * @brief size, returns the number of variable entries in the puzzle,
     * corresponding to the original size of the puzzle
     * @return true if [newValue] added to indices, false otherwise
     */
    public int size() {

        return this.size_;

    } // end of size

    /**
     * @brief sets, value at given row [x] and col [y] to new integer value
     * [newValue]
     * @param int[x] row index, and int [y] col index, and int [newValue]
     * @post if successful, [newValue] added at provided indices, check by
     * calling contains() method
     * @return true if [newValue] added to indices, false otherwise
     */
    public boolean set(int x, int y, int newValue) {
        // hold if value was set successfully
        boolean valueSet = false;

        if (!contains(x, y, newValue)) {

            puzzleStructure_[x][y].setValue(newValue);
            valueSet = true;

        } // end if

        return valueSet; // return valueSet

    } // end of set

    /**
     * @brief contains, check to see if provided value is legal to insert at
     * provided indices
     * @param [targetRow] row to be inserted at, [targetCol] col to be inserted
     * at, and [value] the integer to insert]
     * @post no changes
     * @return True if [value] is legal to insert at specific indices, false
     * otherwise.
     */
    public boolean contains(int targetRow, int targetCol, int value) {
        boolean found = false;
        // searches columns for occurrence of value
        for (int row = 0; row < defaultRowSize_; ++row) {
            if (get(row, targetCol) == value) {
                found = true; // set found to true
                row = defaultRowSize_; // break out of loop
            } // end if
        } // end for

        // searches row for occurrence of value
        for (int col = 0; col < defaultColSize_; ++col) {

            if (get(targetRow, col) == value) {
                found = true; // set found to true
                col = defaultColSize_; // break out of loop
            } // end if

        } // end for

        // must search particular 3*3 matrix,
        //starting row position
        int startRowPos = targetRow - targetRow % 3;
        //starting column position
        int startColPos = targetCol - targetCol % 3;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (get((i + startRowPos), (j + startColPos)) == value) {
                    found = true; // set found to true
                } // end if

            } // end for

        } // end for

        return found; // return found

    } // end of contains 

    /**
     * @brief numEmpty, returns amount of open spaces in the puzzle object
     * @pre none
     * @post no changes
     * @return integer number that represents the number of current open spaces
     */
    public int numEmpty() {

        // holds open space count
        int openSpaceCount = 0;

        for (int row = 0; row < defaultRowSize_; ++row) {

            for (int col = 0; col < defaultColSize_; ++col) {

                if (get(row, col) < 0) {
                    ++openSpaceCount;
                } // end if

            } // end for

        } // end for

        return openSpaceCount; // return open space count   

    } // end of numEmpty

    /**
     * @brief clear, resets all square objects to default values and size_
     * @pre none
     * @post all Square objects in the puzzle contain a value of -1, false for
     * fixed_, and size_ reset to 81
     */
    public void clear() {

        //loop through each row
        for (int row = 0; row < defaultRowSize_; ++row) {

            //loop through each column
            for (int col = 0; col < defaultColSize_; ++col) {

                puzzleStructure_[row][col].resetSquare();

            } // end for

        } // end for

        // reset size of Puzzle object
        solvedFlag = false;
        size_ = 81;

    } // end clear

    /**
     * @brief fill, the puzzle data structure with the provided input
     * @parms int[] [inputData] puzzle data to fill the puzzle data structure
     * with
     * @pre none
     * @post If successful 81 integer values are inserted into Puzzle Squares,
     * Otherwise, returns false and resets puzzle object data.
     * @return true if fill is successful, false otherwise
     */
    public boolean fill(int[] inputData) {
        boolean success = true;
        int inputIndex = 0; // index variable for inputData

        // loop through each row
        for (int row = 0; row < defaultRowSize_ && success; ++row) {

            // loop through column
            for (int col = 0; col < defaultColSize_ && success; ++col) {

                // check if value is a blank space, represented 
                if (inputData[inputIndex] != 0) {

                    if (!contains(row, col, inputData[inputIndex])) {

                        puzzleStructure_[row][col].setValue(inputData[inputIndex]);
                        puzzleStructure_[row][col].setFixed(true);
                        ++inputIndex;

                    } else {

                        success = false;
                        // call clear method to reset puzzle object. 
                        clear();

                    } // end if

                } else { // Blank Space

                    puzzleStructure_[row][col].setValue(inputData[inputIndex]);
                    ++inputIndex;

                } // end if

            } // end for

        } // end for

        // set size_ to current open space count
        size_ = numEmpty();
        return success;

    }

    /**
     * @brief isSolved, return if the puzzle provide has been solved or not yet
     * @pre none
     * @post no changes
     * @return true if the puzzle has been solved, false otherwise
     */
    public boolean isSolved() {

        return solvedFlag;

    } // end isSolved

    /**
     * @brief solve, solves the provided puzzle stating at indices [0,0].
     * @pre none
     * @post if successful, the provided Sudoku puzzle has been solved, calls
     * solve(int row, int col), solvedFlag updated to true
     */
    public void solve() {

        solvedFlag = solve(0, 0);

    } // end solve

    /**
     * @brief solve, solves the provided puzzle stating at the provided indices
     * recursively using backtracking.
     * @param int [row] and int [col] starting indices
     * @pre none
     * @post if successful, the provided Sudoku puzzle has been solved, calls
     * set(), numEmpty(), moveToEmptySquare(), getFixed(), setValue
     * @return true if puzzle successfully solved, otherwise false
     */
    private boolean solve(int row, int col) {

        pause(500);
        // base case
        if (numEmpty() == 0) {

            return true; // return true/success

        } // end if

        //Move to next square without a value
        int[] rowCol = {row, col};
        moveToHardestSquare(rowCol);
        row = rowCol[0];
        col = rowCol[1];

        for (int i = 1; i < 10; ++i) {

            //check if legal move
            //if legel value is set
            if (set(row, col, i)) {

                if (solve(row, col)) {

                    return true; // return true/success

                } // end if

            } // end if

            // remove incorrect value;
            // don't remove fixed values
            if (!puzzleStructure_[row][col].getFixed()) {
                puzzleStructure_[row][col].setValue(0);
            } // end if	

        } // end for

        // no values valid in current square, previous square is invalid
        return false;

    } // end of solve

    /**
     * @brief moveToHardestSquare Searches for next open space with the least
     * amount of choices.
     * @param int[] [rowCol] passed as an object to updated internal values that
     * represent row and col
     * @pre none
     * @post updates row and col values stored in rowCol array to open space
     * with the least options
     */
    private void moveToHardestSquare(int[] rowCol) {

        // ArrayList to hold open spaces in puzzle 
        ArrayList<Coordinates> options = new ArrayList<>();
        // loop through each row
        for (int i = 0; i < defaultRowSize_; ++i) {
            // loop through each col
            for (int j = 0; j < defaultColSize_; ++j) {

                // if sqaure at i(row), j(col) eqauls -1
                if (get(i, j) == -1) {
                    // blank space found

                    //create temp object to hold this space
                    //get the amount of options at the given indices
                    Coordinates temp = new Coordinates(i, j, getOptions(i, j));

                    //push back this sqaure for later comparison
                    options.add(temp);

                } // end if

            } // end for

        } // end for

        // find an return the space with the least amount of options
        // sorts our options from least to greatest
        Collections.sort(options);

        if (!options.isEmpty()) {

            //set the space to use in solve
            rowCol[0] = options.get(0).getX();
            rowCol[1] = options.get(0).getY();

        } // end if 

        options.clear();

    } // end of moveToHardestSquare

    /**
     * @brief getOptions get the amount of options for the given square
     * @param int [targetRow] and int [targetCol], indices of the current square
     * @pre none
     * @post no changes
     * @return the count of options for the given square
     */
    private int getOptions(int targetRow, int targetCol) {

        int optionCount = 0;
        // count open columns for target sqaure
        for (int row = 0; row < defaultRowSize_; ++row) {

            if (get(row, targetCol) == -1) {
                optionCount++;
            } // end if	

        } // end for

        // count open row for target sqaure
        for (int col = 0; col < defaultColSize_; ++col) {

            if (get(targetRow, col) == -1) {
                optionCount++;
            } // end if

        } // end for

        // must search particular 3*3 matrix,
        //starting row position
        int startRowPos = targetRow - targetRow % 3;
        //starting column position
        int startColPos = targetCol - targetCol % 3;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (get((i + startRowPos), (j + startColPos)) == -1) {
                    optionCount++;
                } // end if

            } // end for

        } // end for

        return optionCount; // return optionCount        

    } // end of getOptions 

    /**
     * @brief pause, put the current thread to sleep for the given amount of
     * time
     * @param int [ms] amount of time to put the thread to sleep.
     * @pre none
     * @post no changes
     */
    private void pause(int ms) {

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        } // end catch 

    } // end pause 

} // end of Puzzle
