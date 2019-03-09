/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

/**
 *  class Grafika is in charge of drawing circuit
 * @author Lukasz Tomaszewski
 */
public class Grafika extends JComponent {

    Node wep, wem, wy, zr;
    Graphics2D g2d;
    int width = 850;
    int height = 600;
/**
 *  class Grafika is in charge of drawing circuit
 */
    public Grafika() {
        setSize(850, 600);
        wep = new Node(630, 280);
        wem = new Node(630, 320);
        wy = new Node(770, 300);
        zr = new Node(60, 250);

    }

 static class ListaP {

        int x1, y1, x2, y2;

        public ListaP(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

static class ListaR {

        int x1, y1, wybor;

        public ListaR(int x1, int y1, int wybor) {
            this.x1 = x1;
            this.y1 = y1;
            this.wybor = wybor;

        }

    }
    LinkedList<ListaR> rezystory = new LinkedList<>();
    LinkedList<ListaP> polaczenia = new LinkedList<>();

 
    void Dodaj(int x1, int x2, int wybor) {
        rezystory.add(new ListaR(x1, x2, wybor));
        repaint();
    }
/*
    adding new connection's graphics
    */
    void Dodaj(Node x, Node y) {
        polaczenia.add(new ListaP(x.p.x, x.p.y, y.p.x, y.p.y));
        repaint();
    }
    /**
     * method which do graphics
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {

        g2d = (Graphics2D) g;

        g2d.setColor(Color.gray);  //rysowanie siatki
        for (int i = 0; i <= width / 20; i++) {
            g2d.drawLine(0, i * 20, width, i * 20);
            g2d.drawLine(i * 20, 0, i * 20, height);

        }
        Color darkRed = new Color(130, 13, 0); //kolor i grubosc dla komponentow
        g2d.setColor(darkRed);
        g2d.setStroke(new BasicStroke(3));

        g2d.drawOval(30, 270, 60, 60); //rysowanie zrodla
        g2d.drawLine(60, 270, 60, 250);
        g2d.drawLine(60, 330, 60, 370);
        g2d.drawLine(45, 370, 75, 370);

        Polygon sinus = new Polygon();
        for (int i = 0; i <= 30; i++) {
            sinus.addPoint(i + 45, (int) (10 * Math.sin(3.14 + i * 2 * 3.14 / 30)) + 300);
        }

        g2d.drawPolyline(sinus.xpoints, sinus.ypoints, sinus.npoints);

        g2d.drawLine(650, 250, 750, 300); //rysowanie WO
        g2d.drawLine(650, 250, 650, 350);
        g2d.drawLine(650, 350, 750, 300);

        g2d.drawLine(660, 320, 670, 320);
        g2d.drawLine(660, 280, 670, 280);
        g2d.drawLine(665, 275, 665, 285);

        g2d.drawLine(750, 300, 770, 300);
        g2d.drawLine(650, 320, 630, 320);
        g2d.drawLine(650, 280, 630, 280);

        for (ListaR listaR : rezystory) { //rysowanie elementow

            if (listaR.wybor == 1) {
                g2d.drawRect(listaR.x1 + 20, listaR.y1 - 10, 60, 20);
                g2d.drawLine(listaR.x1 + 20, listaR.y1, listaR.x1, listaR.y1);
                g2d.drawLine(listaR.x1 + 80, listaR.y1, listaR.x1 + 100, listaR.y1);

            }
            if (listaR.wybor == 2) {
                g2d.drawLine(listaR.x1 + 20, listaR.y1, listaR.x1, listaR.y1);
                g2d.drawLine(listaR.x1 + 20, listaR.y1 - 20, listaR.x1 + 20, listaR.y1 + 20);
                g2d.drawLine(listaR.x1 + 32, listaR.y1 - 20, listaR.x1 + 32, listaR.y1 + 20);
                g2d.drawLine(listaR.x1 + 32, listaR.y1, listaR.x1 + 52, listaR.y1);

            }
            if (listaR.wybor == 3) {
                g2d.drawLine(listaR.x1, listaR.y1 + 20, listaR.x1, listaR.y1);
                g2d.drawLine(listaR.x1 - 15, listaR.y1 + 20, listaR.x1 + 15, listaR.y1 + 20);
            }
        }

        g2d.setColor(Color.green); // rysowanie polaczen
        g2d.setStroke(new BasicStroke(2));
        for (ListaP listaP : polaczenia) {
            g2d.drawLine(listaP.x1, listaP.y1, listaP.x2, listaP.y2);
        }
    }

}
