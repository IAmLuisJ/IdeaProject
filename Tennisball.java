/* Kristin Roberts
   Chapter 12 Video files
*/

public class Tennisball extends Ball implements Bounceable
{
   private String color = "white";
   private int quantity = 0;
   
   public Tennisball()
  	{
  	  super(2, "Tennis ball", 5);         // Call the base constructor
  	}
   
   public Tennisball(int quantity)
   { 
      this();
      setQuantity(quantity);
   } 

   public Tennisball(String color)
   {
     //super("Tenis ball");         // Call the base constructor
     this(); 
     setColor(color);               // this.color = color;
   }
   
   public void setColor(String color)  // mutator method
   {
     if( color.equals("") )
        this.color = "unknown";
     else 
        this.color = color;
   }

   public String getColor()  // accessor method
   {
      return color;
   }

   void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }
   int getQuantity()
   { 
      return quantity;
   } 
 
   public String bounce()
   {
     return " I bounce high and fast!";
   }
   public String toString()
   {
      return "Type: " + getType() + ",  Quantity: " + getQuantity();
   }
/*   public String toString()
   {
     // return "\nI am a " + getType() + "." + "  I am " + getColor() + ".  ";
     return super.toString() + "  I am " + getColor() + ".  ";
   }*/
   

}

