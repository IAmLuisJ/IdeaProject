/* Kristin Roberts
   Chapter 12 Video files
*/

public class Baseball extends Ball
{
	private int quantity = 0;
   
   public Baseball()
  	{
  	  super(1, "Baseball", 15);         // Call the base constructor
  	}
   
   public Baseball(int quantity)
   { 
      this();
      setQuantity(quantity);
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

