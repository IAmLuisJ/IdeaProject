/* Kristin Roberts
   Chapter 12 Video files
*/
import javax.swing.JOptionPane;

public class BallException extends Exception
{
   String ballType;
   public BallException(String type)
   {
       ballType = type;
       JOptionPane.showMessageDialog(null, ballType + " is not a valid ball.\nPlease enter valid ball type:\n Baseball, Tennisball, Bowlingball or Basketball",
				"Invalid Ball Type", JOptionPane.ERROR_MESSAGE);

   }
   public String getMessage()
   {
      //  return  super.getMessage() + ballType + " is not a valid ball.\nPlease enter valid ball type:\n Baseball, Tennisball, Bowlingball or Basketball";
        return  super.getMessage() + "Super class message\n"; 
      //+ ballType + " is not a valid ball.\nPlease enter valid ball type:\n Baseball, Tennisball, Bowlingball or Basketball";

   }
}