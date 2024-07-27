
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToNode {  

    public static void main(String[] args) {  

        try {
            File myObj = new File("1.txt");
            Scanner myReader = new Scanner(myObj);  

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                // print line
                System.out.println("Original:\t" + data);

                // process line
                data = data.substring(1, data.length()-1);
                String[] myArray = data.split(";");

                String coords = myArray[0];
                coords = coords.substring(1, coords.length()-1);
                String[] myCoords = coords.split(",");

                int xCoord = Integer.parseInt(myCoords[0]);
                int yCoord = Integer.parseInt(myCoords[1]);

                System.out.println("x coord:\t" + (xCoord+1-1));
                System.out.println("y coord:\t" + (yCoord+1-1));

                // check if not first or last lines
                if(myArray.length > 1){
                    int biome = Integer.parseInt(myArray[1]);
                    System.out.println("Biome:\t\t" + (biome+1-1));
                    float quality = Float.parseFloat(myArray[2]);
                    System.out.println("Quality:\t" + (quality+1-1));
                }

                System.out.println();

            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }
}
