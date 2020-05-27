/* Kristin Roberts
   Chapter 12 Video files
*/

public abstract class Ball
{
	private String type = "";
   private double price = 0;
   private int itemNum = 999;

	public Ball(String type)
	{
		setType(type);  // this.type = type;
	}

   public Ball(int num, String type, double pr)
   {
      this(type);
      setPrice(pr);
      setItemNum(num);
   }
   
   public void setItemNum(int itemNum)
   {
      this.itemNum = itemNum;
   }

   public int getItemNum()
   {
      return itemNum;
   }

	public void setType(String type)  // mutator method
	{
		if( type.equals("") )
        this.type = "unknown";
      else 
        this.type = type;
   }
   public String getType()  // accessor method
	{ 
		return type;
	}
   public double getPrice()  // accessor method
	{ 
		return price;
	}
   public void setPrice(double pr)  // mutator method
	{
		if( pr < 0)
        price = 5;
      else 
        price = pr;
   }


   public String toString()
   {
      return "\nI am a " + getType() + ".";
   }
 //  public abstract String bounce();

}

