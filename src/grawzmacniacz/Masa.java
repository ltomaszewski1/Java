/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

/**
 * class Masa extends class Komponent
 * @author Lukasz Tomaszewski
 */
public class Masa extends Komponent {

    Node node1;
/**
 * 
 * @param x x coordinate of ground
 * @param y y coordinate of ground
 */
    public Masa(int x, int y) {

        node1 = new Node(x, y);

    }
}
