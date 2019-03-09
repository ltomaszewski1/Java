/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

/**
 * class for making component
 * @author Lukasz Tomaszewski
 */
public class Komponent {

    Node node;
/**
 * 
 * @param node1 instance of class Node, coordinates of component's end
 */
    public Komponent(Node node1) {
        this.node = node1;
    }
/**
 * 
 * @param x x coordinate of end of component
 * @param y y coordinate of end of component
 */
    public Komponent(int x, int y) {
    }
/**
 *  empty constructor for extensions
 */
    public Komponent() {

    }
}
