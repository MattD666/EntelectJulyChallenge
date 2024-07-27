import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Planet
{

   TreeNode northPole, southPole;
   int radius = 210;
   int days = 14770;
   int steps  = 3*this.days;
   int stepCounter = 3*this.days; //ADDED
   ArrayList<TreeNode> nodes;
   ArrayList<TreeNode> northChildren;
   String path = "";
   ArrayList<TreeNode> pathList = new ArrayList<TreeNode>(); //ADDED
   
  
   /**
      Construct a tree with only a root node.
   **/
   public Planet(){


      nodes = new ArrayList<TreeNode>();
      
      //READ IN FILE
      
      try {
            File myObj = new File("4.txt");
            Scanner myReader = new Scanner(myObj);  

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                // print line


                // process line
                data = data.substring(1, data.length()-1);
                String[] myArray = data.split(";");

                String coords = myArray[0];
                coords = coords.substring(1, coords.length()-1);
                String[] myCoords = coords.split(",");

                int xCoord = Integer.parseInt(myCoords[0]);
                int yCoord = Integer.parseInt(myCoords[1]);



                // check if not first or last lines
                if(myArray.length > 1){
                    int biome = Integer.parseInt(myArray[1]);
                    // System.out.println("Biome:\t\t" + (biome+1-1));
                    float quality = Float.parseFloat(myArray[2]);
                    // System.out.println("Quality:\t" + (quality+1-1));
                    

                    nodes.add( new TreeNode(xCoord, yCoord, biome, quality)  );
                }
                else{
                  nodes.add( new TreeNode(xCoord, yCoord, -1, -1)  );
                }
                
                


            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
      
      
      //END READ IN FILE
      
      System.out.println(nodes);
      northChildren = new ArrayList<TreeNode>();
      
     northPole = nodes.get(0);
     southPole = nodes.get( nodes.size()-1 );
      
      
      
        int start = -1;
      int end = -1;
      
      for (int i=0; i<(radius*2+1); i++){
         start = i*(radius*2+1) + 1;
         end = (i+1)*(radius*2+1);
         this.constructLevel(start, end);
      }
      
    

    //     int testIndex = 1;
//         System.out.println("Node (-10,10) left and right");
//         System.out.println(nodes.get(testIndex).toString());
//         System.out.println(nodes.get(testIndex).getLeft());   
//         System.out.println(nodes.get(testIndex).getRight()); 
//         System.out.println(nodes.get(testIndex).getTop());   
//         System.out.println(nodes.get(testIndex).getBottom()); 
      }
      
//    public void displayChildren(){
//       System.out.println("Node: left, right, top, bottom");
//       for (TreeNode node: nodes){
//          System.out.println(String.format("%s: %s, %s, %s, %s", this.toString(), this.getLeft.toSTri));
//          
//       }
//    
//    }
   
   
   public void constructLevel(int start, int end){
      
      for (int i=start; i<=end; i++){
         TreeNode current = nodes.get(i);
         if (current.getX()==this.radius ){
            current.setLeft(nodes.get(i-1));
            current.setRight(nodes.get(start));
         }
         else if (current.getX()==-1*this.radius){
            current.setRight(nodes.get(i+1));
            current.setLeft(nodes.get(end) );
         }  
         else{
            current.setRight(nodes.get(i+1));
            current.setLeft(nodes.get(i-1));
         }
         
         if (i<=(2*this.radius+1)){
            current.setTop(this.northPole);
            this.northChildren.add(current);
         }
         else if (i>=((nodes.size()-1) - (radius*2+1))){
            current.setBottom(this.southPole);
            TreeNode topcurrent = nodes.get(i-(2*this.radius+1));
            current.setTop(topcurrent);
            topcurrent.setBottom(current);
            
            }
         else{
            TreeNode topcurrent = nodes.get(i-(2*this.radius+1));
            current.setTop(topcurrent);
            topcurrent.setBottom(current);
         }

      }
      
   }

   
   public void traverse(){
      northPole.visit();
      this.path = this.path + northPole.toString();
      
      TreeNode bestNode = northChildren.get(0);
      
      float maxvalue = bestNode.value();
      for (int i=1; i<northChildren.size(); i++){
         if (northChildren.get(i).value()>maxvalue){
            bestNode = northChildren.get(i);
         }
      }
      
      this.pathList.add(bestNode); //ADDED

      System.out.println(bestNode);
      System.out.println(path);
      this.traverse(bestNode);
      
   
   }
   
   public void traverse(TreeNode node){
      if ( (node.getX()==this.southPole.getX()) && (node.getY()==this.southPole.getY())){
         return;
      }
      // if ( (node.getY()-this.southPole.getY()) == this.steps  ){
      //    this.goToSouthPole(node);
      // }
      else{
      //Not going directly to south pole
      
         // System.out.println(bestNode.getTop());
         TreeNode bestNode = node.checkSurroundingNodes(); //Replace with method

         //ADDED
         if (bestNode==null || ((node.getY()+(radius+1))>this.stepCounter)){
            TreeNode prevNode = this.pathList.get(this.pathList.size()-2);
            TreeNode removedNode = this.pathList.remove(days);

            prevNode.blockNode(removedNode);

            System.out.println(this.pathList);
            this.stepCounter++;
            this.traverse(bestNode);
         }
         else{
            this.pathList.add(bestNode);
            node.visit();
            this.path = this.path +  ","+node.toString();
            System.out.println(path);
            this.stepCounter--;  //ADDED
            this.traverse(bestNode);
         }
         
      
      }//END Not going directly to south pole
   
   }
   
   public void goToSouthPole(TreeNode node){
      if ( (node.getX()==this.southPole.getX()) && (node.getY()==this.southPole.getY())){
         return;
      }  
      else{
         this.path = this.path +  ","+node.toString();
         node.visit();
         this.goToSouthPole(node.getBottom() );
      } 
   }
   
   public String getPath(){
   
      return "[" + this.path +","+ this.southPole.toString()+ "]";
   }
   
               
}