import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Planet
{

   TreeNode northPole;
   TreeNode southPole;
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
      southPole = nodes.get(nodes.size()-1);
      
      
      for (int i=0; i<(radius*2+1); i++){
         constructLevel(i*(radius*2+1) + 1, (i+1)*(radius*2+1));
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
         
         if (i<=(2*this.radius+1)){
            current.setTop(this.northPole);
            this.northChildren.add(current);
         }
         else if (i>=((nodes.size()-1) - (radius*2+1)))
            current.setBottom(this.southPole);
         else{
            TreeNode topcurrent = nodes.get(i-(2*this.radius+1));
            current.setTop(topcurrent);
            topcurrent.setBottom(current);
         }

      }
      
   }
   
   
   
               
}