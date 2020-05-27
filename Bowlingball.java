/* Kristin Roberts
   Chapter 12 Video files
*/

public class Bowlingball extends Ball
{
	private int size = 8;
   private String color = "black";
   private int quantity = 0;
   
   public Bowlingball()
  	{
  	  super(3, "Bowling ball", 25);         // Call the base constructor
  	}
   public Bowlingball(int quantity)
   { 
      this();
      setQuantity(quantity);
   } 

 //  public Bowlingball(int size)
//   {
//     this();                  // Call the Bowlingball constructor
 //    setSize(size);
              
//    }
   public Bowlingball(int size, String color)
   {
     this(size);                // Call the Bowlingball 2nd constructor
     setColor(color);           // this.color = color;
   }


   public void setSize(int size)  // mutator method
   {
     if( size != 8 && size != 15 )
        this.size = 8;
     else 
        this.size = size;
   }

   public int getSize()  // accessor method
   {
      return size;
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

   public boolean equals(Bowlingball bowlingB)
   {
 //     if (size == bowlingB.size && color == bowlingB.getColor())
 //     if (this.size == bowlingB.size && this.color == bowlingB.getColor())
  //    if (getSize() == bowlingB.size && getColor() == bowlingB.getColor())
      if (this.getSize() == bowlingB.getSize() && this.getColor() == bowlingB.getColor())
          return true;
      else
          return false;
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
 /* public String toString()
   {
      return super.toString() + "  I am " + getColor() + " and " + getSize() + " pound.  ";
   }*/

}


