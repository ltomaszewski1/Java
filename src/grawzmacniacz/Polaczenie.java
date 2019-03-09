/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

/**
 * class Polaczenie implement a conection beetewen 2 components from the component array list
 * @author Lukasz Tomaszewski
 */
public class Polaczenie {

    Boolean n1, n2;
    int p1, p2, k1, k2;
/**
 * 
 * @param p1 position of the first component in the list
 * @param p2 position of the second component in the list
 * @param n1 end of first component in connection - false stands for left
 * @param n2 end of second component in connection - false stands for left
 * @param k1 type of first component 1-resistor 2-ground 3-ground 4-voltage source 5-opamp's non-inverting input 6-opamp's inverting input 7-opamp's output
 * @param k2 type of first component 1-resistor 2-ground 3-ground 4-voltage source 5-opamp's non-inverting input 6-opamp's inverting input 7-opamp's output
 */
    public Polaczenie(int p1, int p2, boolean n1, boolean n2, int k1, int k2) {

        this.n1 = n1;
        this.n2 = n2;
        this.p1 = p1;
        this.p2 = p2;
        this.k1 = k1;
        this.k2 = k2;

    }
}
