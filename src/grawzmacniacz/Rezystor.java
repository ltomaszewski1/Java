/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

/**
 * class Rezystor extends class Komponent
 * @author Lukasz Tomaszewski
 */
public class Rezystor extends Komponent {

    float rezystancja;
    Node node1, node2;
/**
 * 
 * @param r resistance of the resistor
 * @param x x coordinate of left end of resistor
 * @param y y coordinate of left end of resistor
 */
    public Rezystor(float r, int x, int y) {
        rezystancja = r;
        node1 = new Node(x, y);
        node2 = new Node(x + 100, y);

    }

}
