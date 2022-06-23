/**
 * @author Anthony Campos
 */
package study.sudokusolver;

// current class imports 
import java.awt.Color;
import javax.swing.JLabel;

public class Square {

    // private attributes
    private int value_ = -1; // holds the squares numerical value. default is -1

    // A flag indicating if the value is fixed
    // is (one of the values given at the start of the puzzle)
    // or variable (the values written in as parts of a possible solution)
    private boolean fixed_ = false;

    // UI element that makes up the puzzle visual
    private javax.swing.JLabel cell_;

    // constructor 
    public Square(int value, boolean fixed) {

        this.value_ = value;
        this.fixed_ = fixed;
        initLabel(); // init label that represents each puzzle Square

    } // end of constructor 

    /**
     * @brief initLabel, build out the UI objects that represents a Square
     * object
     * @pre none
     * @post square UI object built out and styling set
     */
    private void initLabel() {

        this.cell_ = new javax.swing.JLabel();
        cell_.setText("");
        cell_.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cell_.setOpaque(true);
        cell_.setBackground(Color.white);
        cell_.setFont(new java.awt.Font("Segoe UI", 1, 18));
        cell_.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cell_.setMaximumSize(new java.awt.Dimension(50, 50));
        cell_.setMinimumSize(new java.awt.Dimension(50, 50));
        cell_.setPreferredSize(new java.awt.Dimension(50, 50));

    } // end of init label

    /**
     * @brief getValue, get value stored in value_
     * @return current value int, if 0 or less will return -1
     */
    public int getValue() {

        return ((value_ > 0) ? value_ : (-1));

    } // end getValue

    /**
     * @brief getFixed, get fixed value stored in square object
     * @return boolean value stored in fixed_
     */
    public boolean getFixed() {

        return fixed_;

    } // end getFixed

    /**
     * @brief setValue, update the current value stored in the square object
     * @parm int [newValue]
     * @post value_ updated and cell text updated
     */
    public void setValue(int newValue) {

        value_ = newValue;
        updateCellText();

    } // end setValue

    /**
     * @brief setFixed, update the current fixed_ value stored in the square
     * object
     * @parm boolean [fixed]
     * @post fixed_ updated to fixed
     */
    public void setFixed(boolean fixed) {

        fixed_ = fixed;

    } // end setFixed

    /**
     * @brief updateCellText, update cell text string to value_
     * @post text stored in cell updated to value unless -1, otherwise empty
     */
    public void updateCellText() {

        String text = (getValue() != -1) ? String.valueOf(value_) : "";
        cell_.setText(text);

    } // end updateCellText

    /**
     * @brief getCell, return square objects JLabel element
     * @return return cell_
     */
    public JLabel getCell() {

        return cell_;

    } // end get cell

    /**
     * @brief resetSquare, reset square object back to default values
     * @post resets Square object to default values
     */
    public void resetSquare() {

        value_ = -1;
        fixed_ = false;
        cell_.setText("");

    } // end resetSquare

} // end Square 
