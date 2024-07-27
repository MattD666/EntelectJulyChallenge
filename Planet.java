import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Planet
{

   TreeNode northPole;
   int radius = 10;
   ArrayList<TreeNode> nodes;
   ArrayList<TreeNode> northChildren;
   
  
   /**
      Construct a tree with only a root node.
   **/
   public Planet(){
      nodes = new ArrayList<TreeNode>();
      northChildren = new ArrayList<TreeNode>();
      
     
      
      northPole = nodes.get(0);
      
      
      
      for (int i=1; i<nodes.size(); i++){
         
      }
   }
   
   public void constructLevel(int start, int end){
      
      for (int i=start; i<end; i++){
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
      }
      
   }
   
   
   
               
}