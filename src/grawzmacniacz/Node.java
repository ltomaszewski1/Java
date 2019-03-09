/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;
import java.awt.Point;

/**
 *  class Node saves placement of component
 * @author Lukasz Tomaszewski
 */
public class Node {
    Point p;
    /**
     * 
 * @param x x coordinate
 * @param y y coordinate
     */
    public Node(int x, int y){
        
      
      this.p = new Point(x,y);
    }
    
    
}
