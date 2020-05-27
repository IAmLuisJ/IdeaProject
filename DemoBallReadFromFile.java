/* Kristin Roberts
   Chapter 13 Video files
*/

//import javax.swing.*;  // for JOptionPane
import javax.swing.JOptionPane;
import java.nio.file.*;  // for File and Path
import java.io.*;      // for OutputStream and BufferedWriter
import static java.nio.file.StandardOpenOption.*;  //for CREATE, APPEND and TRUNCATE_EXISTING


public class DemoBallReadFromFile
{
   public static void main(String[] args) throws BallException
   {
      int balltypes = 0;
      boolean valid = false;
      while(true)
      {
        try
         {
            String option = JOptionPane.showInputDialog(null, "How many ball types are in your order?", 
                                                          "Ball Count",JOptionPane.QUESTION_MESSAGE);
         // balltypes = Integer.parseInt(option);  // NullPointerException not called for Integer.parseInt
                                                   // parseInt throws NumberFormatException if the value can't be parsed
       /*   if(option == null)
               System.out.println("You selected cancel"); // these two lines are the actions
               System.exit(0); // you want your program to take if cancel is selected
            }   */       

            double count = Double.parseDouble(option); // NullPointerException called for parseDouble but not parseInt
            balltypes = (int)count;
            break; 
         }
         catch(NullPointerException ex){
         	JOptionPane.showMessageDialog(null, "Null Pointer Exception!", 
                                          "NullPointerException", JOptionPane.ERROR_MESSAGE);
				System.exit(0); 
			}
         catch (NumberFormatException ex)
   		{
   			JOptionPane.showMessageDialog(null, "You must enter a numeric value for COUNT of ball types!",
   						                      "Invalid Quanitity", JOptionPane.ERROR_MESSAGE);
   		}
      } // end while
      
      BallOrder order = new BallOrder(balltypes);
      for(int i = 0; i < balltypes; i++)
          order.addBall();
          
      order.close();
      
      order.getBalls();
      order.displayOrder();
   }
} //end DemoBallReadFromFile

class BallOrder // implements BallConstants
{
  // Baseball, Tennisball, Bowlingball,  Basketball                
   Ball[] ballsOrdered;        
     
   Path file; // = Paths.get("Order.txt");
   OutputStream output;
   BufferedWriter writer;
   int ballQuantity = 0;
      
   public BallOrder(int balltypes) 
   {
      getFile();
      ballsOrdered = new Ball[balltypes]; 
      try
      {
         output = new BufferedOutputStream(Files.newOutputStream(file, TRUNCATE_EXISTING) ); // CREATE));
         writer = new BufferedWriter(new OutputStreamWriter(output));
      }
      catch(Exception e)
      {
          System.out.println("Error message: " + e);
      }
   }
   
   public void getFile()
   {
      String filename = JOptionPane.showInputDialog(null, "Please enter filename:");
      if(filename == null)  // NullPointerException not called for return value of String, so you need to use if test
      {
         System.out.println("You selected cancel"); // these two lines are the actions
         System.exit(0); // you want your program to take if cancel is selected
      }
      File f = new File(filename);
      if(f.exists()){
         System.out.println("File already exists");
      }
      else
      {
         try 
         {
            f.createNewFile();
            System.out.println("File " + f.getPath() + " was created");
         } 
         catch (IOException e) 
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      file = Paths.get(filename);
   }
   
   public void addBall() throws BallException
   {
      String delimiter = ",";
      boolean valid = false;
      while(!valid)
      {
        try
        {
           String  ballName = JOptionPane.showInputDialog(null, "Enter Ball name?");      
           if( ballName.equalsIgnoreCase("Baseball") || ballName.equalsIgnoreCase("Tennisball")||
                  ballName.equalsIgnoreCase("Bowlingball") || ballName.equalsIgnoreCase("Basketball") )
           {
            ballQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quanitity of balls?") );
            valid = true;
               String s = ballName + delimiter + ballQuantity + System.getProperty("line.separator");
               writer.write(s, 0, s.length());
            }
            else
            {       
                throw new BallException(ballName);
            }
         }
         catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "You must enter a numeric value for Quantity of balls!",
						"Invalid Quanitity", JOptionPane.ERROR_MESSAGE);
			}
			catch (BallException ex)
			{
				JOptionPane.showMessageDialog(null, "\nThrowable message: " + ex.getMessage(), "Ball Exception Thrown!",
						JOptionPane.ERROR_MESSAGE);
			}
         catch(Exception e)
         {
             System.out.println("Error message: " + e);
         }
      } // end while
   } // end add ball
    
   public void close()
   {
      try
      {
          writer.close();
      }
      catch(Exception e)
      {
          System.out.println("Error message: " + e);
      }   
   }
   
   public void getBalls()
   {
       String delimiter = ",";
       try
       {
         InputStream input = new BufferedInputStream(Files.newInputStream(file));
         BufferedReader reader = new BufferedReader(new InputStreamReader(input));
         String[] array = new String[3];
         System.out.println();        
         String s = reader.readLine();
         String name;
         int quantity = 0;
         int itemCount = 0;
         while(s != null)
         {
            array = s.split(delimiter);
            name = array[0];
            quantity = Integer.parseInt(array[1]);
            if( name.equalsIgnoreCase("Baseball") )
                ballsOrdered[itemCount] = new Baseball(quantity);
            if( name.equalsIgnoreCase("Tennisball") ) 
                ballsOrdered[itemCount] = new Tennisball(quantity); 
            if( name.equalsIgnoreCase("Bowlingball") ) 
                ballsOrdered[itemCount] = new Bowlingball(quantity); 
            if( name.equalsIgnoreCase("Basketball") ) 
                ballsOrdered[itemCount] = new Basketball(quantity);  
            itemCount++;
            s = reader.readLine();           
         }
         reader.close();
      }
      catch(Exception e)
      {
        System.out.println("Message: " + e);
      }
   }    
  
   public void displayOrder()
   {
      String message="";
      for(int i = 0; i <  ballsOrdered.length; i++)
      {
         message += (i+1) + ".  " +  ballsOrdered[i] + "\n";       
      }
      JOptionPane.showMessageDialog(null, message);
   }
} // end class
 