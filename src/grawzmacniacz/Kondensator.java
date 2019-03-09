/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

/**
 * class Kondensator extends class Komponent
 * @author Lukasz Tomaszewski
 */
public class Kondensator extends Komponent {

    float pojemnosc;
    Node node1, node2;
/**
 * 
 * @param p capacitance of the capacitor
 * @param x x coordinate of left end of capacitor
 * @param y y coordinate of left end of capacitor
 */
    public Kondensator(float p, int x, int y) {
        pojemnosc = p;
        node1 = new Node(x, y);
        node2 = new Node(x + 52, y);

    }
}
