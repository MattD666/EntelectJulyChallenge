/**

Node for a general tree
@author Caleb Bessit
@version 16 July 2024

**/

import java.util.ArrayList;

public class TreeNode
{
   
   private float x,y;
   private int biomeIndex;
   private float quality;
   private float value;
   
   private int[] biomeValues = {1,14,28,42,57,71,85,100};
   
   private TreeNode top, bottom, left, right;
   
   public TreeNode(){
      this.top = null;
      this.bottom = null;
      this.left = null;
      this.right = null;

   }
   
   public TreeNode(float x, float y, int biomeIndex, float quality, TreeNode top, TreeNode bottom, TreeNode left, TreeNode right){
      this.x = x;
      this.y = x;
      this.biomeIndex = biomeIndex;
      this.quality = quality;
      this.top = top;
      this.bottom = bottom;
      this.left = left;
      this.right = right;
      
   }
   

  
   public float value(){
      return this.biomeValues[this.biomeIndex]*this.quality;
   }
   
   public void setTop(TreeNode node){
      this.top = node;
   }
      
   public void setBottom(TreeNode node){
      this.bottom = node;
   }
   
   public void setLeft(TreeNode node){
      this.left = node;
   }
   
   public void setRight(TreeNode node){
      this.right = node;
   }
   
   public float getX(){
      return this.x;
      
   }

   public float getY(){
      return this.y;
   }
   
   public TreeNode checkSurroundingNodes(){
      float maxvalue = 0;
      TreeNode nextNode = null;

      //if (!this.left.getVisited()){
          if (this.left.value()>maxvalue){
              nextNode = this.left;
              maxvalue = this.left.value();
          }
      //}

      //if (!this.right.visited){
          if (this.right.value()>maxvalue){
              nextNode = this.right;
              maxvalue = this.right.value();
          }
      //}

      //if (!this.down.visited){
          if (this.bottom.value>maxvalue){
              nextNode = this.bottom;
              maxvalue = this.bottom.value();
          }
      //}

      return nextNode;
  }
}