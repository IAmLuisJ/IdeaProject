/* Kristin Roberts
   Chapter 12 Video files
*/

import javax.swing.*;

public class DemoBall
{
   public static void main(String[] args)throws BallException
   {
      BallTest order = new BallTest();
   }
}

class BallTest implements BallConstants
{
   Ball[] ball = {new Baseball(),new Tennisball(), new Bowlingball(),  new Basketball()};                 
   Ball[] ballsOrdered = new Ball[MAX];         
   String  inQuan = "";
   int item = 0, quantity = 0, itemCount = 0;
      
   public BallTest() throws BallException
   {
      item = getBallOrdered();
      while(item != QUIT)
      {
         try
         {
            boolean found = false;
            for(int x = 0; x < MAX; x++)
            {
              if(item == ball[x].getItemNum())
              {   
                        found = true;
              }
            }
            if(!found)
            {
               throw(new BallException(item + " is an invalid item number!"));
            }
      
            inQuan = JOptionPane.showInputDialog(null, "Enter quantity for item " + item);
            if(inQuan == null) 
            {
               System.out.println("You selected cancel");   // these two lines are the actions 
               System.exit(0);              // you want your program to take if cancel is selected
            }

            quantity = Integer.parseInt(inQuan);
            if(quantity > MAXQUAN)
               throw(new BallException("No more than " + MAXQUAN + " of an item!"));
            if (item == 1) 
                ballsOrdered[itemCount] = new Baseball(quantity);
            if (item == 2) 
                ballsOrdered[itemCount] = new Tennisball(quantity); 
            if (item == 3) 
                ballsOrdered[itemCount] = new Bowlingball(quantity); 
            if (item == 4) 
                ballsOrdered[itemCount] = new Basketball(quantity);  
            ++itemCount;
            if(itemCount == MAX)
            {
              String message = "";
              for(int i = 0; i< MAX; i++)
                 message += (i+1) + ".  " + ballsOrdered[i] + "\n";
              message += "\n\nThank you. That is " + MAX + " items.";
              JOptionPane.showMessageDialog(null, message);
              item = QUIT;
            }
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage() + "\nEnter new item");
         }
         if(item != QUIT)
             item = getBallOrdered();
      }  //end while    
      System.out.println("\nEnd of program.");

    } //end constructor
    
    public static int getBallOrdered()
    {
      Ball[] ballList = {new Baseball(),new Tennisball(), new Bowlingball(), new Basketball()};

      String message="";
      String inStr="";
      int item = 0;
      for(int i =0; i < ballList.length; i++)
      {
         message += ballList[i].getItemNum() + ".  " + ballList[i].getType() + "\n";        
      }
      boolean itemOK = false;
      while(!itemOK)
      {
         itemOK = true;
         try
         {
             inStr = JOptionPane.showInputDialog(null,
               "\nEnter item to order, or " + QUIT + " to quit" +
               "\nItems are:\n" + message);
             if(inStr == null) 
             {
                System.out.println("You selected cancel");   // these two lines are the actions 
                System.exit(0);              // you want your program to take if cancel is selected
             }
             item = Integer.parseInt(inStr);             
         }
         catch(Exception e)
         {
             itemOK = false;
         }
      }
      return item;      
    } // getBallOrdered

}// end class



