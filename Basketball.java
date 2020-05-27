/* Kristin Roberts
   Chapter 12 Video files
*/

public class Basketball extends Ball implements Bounceable
{
	private int quantity = 0;
   
   public Basketball()
  	{
  	  super(4, "Basketball", 10);         // Call the base constructor
  	}
   public Basketball(int quantity)
   { 
      this();
      setQuantity(quantity);
   } 

   public String bounce()
   {
     return " I bounce so fast, they call it dribbling.";
   }
   void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }
   int getQuantity()
   { 
      return quantity;
   } 
   public String toString()
   {
      return "Type: " + getType() + ",  Quantity: " + getQuantity();
   }

 
}
