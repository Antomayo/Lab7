//Reading and Writing imports
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Data Mangagment Imports
import java.util.ArrayList;
import java.text.DecimalFormat;


public class Lab7 {

    //Sets decimals to 2 decimal points.
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] Args) {

        //Arraylist for each column of the table
        ArrayList<String> orderID = new ArrayList<String>();
        ArrayList<String> productID = new ArrayList<String>();
        ArrayList<Double> unitPrice = new ArrayList<Double>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        ArrayList<Double> discount = new ArrayList<Double>();

        //for tracking the while loop's repetitions
        int counter = 0;

        try {

            //Points to the OrderDetails.txt file and creates the OrderDetails.txt Reader
            File orderDetails = new File("OrderDetails.txt");
            Scanner reader = new Scanner(orderDetails);

            //Creates the TotalPrices.txt File if it does not already exist
            File totalPrices = new File("TotalPrices.txt");
            if(totalPrices.createNewFile()){
                System.out.println("File created: " + totalPrices.getName());
            } else {
                System.out.println("File already exists.");
            }

            //Creates a writer for the TotalPrices.txt file
            FileWriter totalWriter = new FileWriter("TotalPrices.txt");

            //While there are still lines to read on OrderDetails.txt
            while (reader.hasNextLine()) {

                //Reads the file line my line and splits the different numbers by their commas
                String data = reader.nextLine();
                String[] sp = data.split(",");

                //Add each value in the line to its array list
                orderID.add(sp[2]);
                productID.add(sp[3]);
                unitPrice.add(Double.parseDouble(sp[1]));
                quantity.add(Integer.parseInt(sp[0]));
                discount.add(Double.parseDouble(sp[4]));

                //Calculates the total price for each line of the Order Details file
                double total = unitPrice.get(counter) * quantity.get(counter) - (discount.get(counter) * quantity.get(counter) * unitPrice.get(counter));

                //Writes prices in order of ID Number
                totalWriter.write(orderID.get(counter) + ": " + df.format(total) + "\n");
                
                counter++;

            }

        //Close the reader and writer, inform the user the program has finished running
        reader.close();
        totalWriter.close();
        System.out.println("Succesfully wrote file.");

        //Error catchers
        }
        catch (FileNotFoundException exc) {
            System.out.println("An Error Occured.");
            exc.printStackTrace();
        }
        catch (IOException exc) {
            System.out.println("An Error Occured.");
            exc.printStackTrace();
        }

    }
}
