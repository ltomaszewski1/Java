/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *  class Mechanika handles action listeners, mouse listener and components arrays
 * @author Lukasz Tomaszewski
 */
public class Mechanika {
    Okno o = new Okno();
    int licznik = 0;
    int element1 = 0, element2 = 0; //0-nic 1- rezystor 2- kondensator 3- masa, 4- zrodlo, 5- wejscie+ wo, 6- wejscie- wo, 7- wyjscie wo
    boolean pomocy = false, nozka1, nozka2;
    Dimension dodaj, usun;
    Timer timer;
    int wybor = 0, mouseX, mouseY, mouseX2, mouseY2, p1 = -1, p2 = -1;
    ArrayList<Rezystor> rezystory;
    ArrayList<Kondensator> kondensatory;
    ArrayList<Polaczenie> polaczenia;
    ArrayList<Masa> masy;

    JFrame j;
    JTextField odp;
    float wartosc;
    boolean click = false;
    /**
     * class Mechanika handles action listeners, mouse listener and components arrays
     */
    public Mechanika() {
        rezystory = new ArrayList<>();
        kondensatory = new ArrayList<>();
        polaczenia = new ArrayList<>();
        masy = new ArrayList<>();
        o.wyjscie.addActionListener(new B1());
        o.pomoc.addActionListener(new B2());
        o.reset.addActionListener(new B3());
        o.rezystor.addActionListener(new B5());
        o.kondensator.addActionListener(new B6());
        o.masa.addActionListener(new B7());
        o.przewod.addActionListener(new B8());
        o.setVisible(true);

        o.grafika.addMouseListener(new E1());

        ActionListener Timer12 = (ActionEvent evt) -> {
            o.obraz.setVisible(false);
            o.pomoc.setEnabled(true);
        };
        timer = new Timer(5000, Timer12);
        timer.setRepeats(false);

    }

    class B1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            j = new JFrame("Koniec gry");
            j.setSize(1280, 1024);
            j.setResizable(false);

            o.dispose();
            j.setVisible(true);

            JPanel pole = new JPanel();
            JButton stop = new JButton("Koniec");
            JLabel info = new JLabel("Pomoc zostala uzyta " + licznik + " razy.");

            j.add(pole);
            GridBagLayout layout1 = new GridBagLayout();

            pole.setLayout(layout1);
            GridBagConstraints gbc1 = new GridBagConstraints();

            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.gridx = 0;
            gbc1.gridy = 0;
            pole.add(info, gbc1);

            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.gridx = 0;
            gbc1.gridy = 1;
            pole.add(stop, gbc1);
            stop.addActionListener(new B4());

        }
    }

    class B2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //wstawic pokazywanie sie okna pomocy

            o.obraz.setVisible(true);
            o.pomoc.setEnabled(false);
            
            licznik++;
            timer.start();

        }
    }

    class B3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Reset();
            
        }
    }

    class B4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

    class B5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wybor = 1;
        }
    }

    class B6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wybor = 2;
        }
    }

    class B7 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wybor = 3;
        }
    }

    class B8 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            click = false;
            wybor = 4;
        }
    }

    class B9 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wartosc = Float.parseFloat(odp.getText());
            
            j.dispose();

            rezystory.add(new Rezystor(wartosc, mouseX, mouseY));

        }
    }

    class B10 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wartosc = Float.parseFloat(odp.getText());
           
            j.dispose();

            kondensatory.add(new Kondensator(wartosc, mouseX, mouseY));

        }
    }

    class E1 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (wybor == 1) {
                mouseX = e.getX();
                mouseY = e.getY();
                o.grafika.Dodaj(mouseX, mouseY, wybor);
                wybor = 0;
                j = new JFrame("Wybor");
                j.setSize(200, 200);
                j.setResizable(false);
                j.setLocation(540, 412);

                j.setVisible(true);

                JPanel pole = new JPanel();
                JButton stop = new JButton("OK");
                JLabel rez = new JLabel("Podaj rezystancje (kOhm)");

                odp = new JTextField();

                j.add(pole);
                GridBagLayout layout1 = new GridBagLayout();

                pole.setLayout(layout1);
                GridBagConstraints gbc1 = new GridBagConstraints();

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 0;
                pole.add(rez, gbc1);

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 1;
                pole.add(odp, gbc1);

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 2;
                pole.add(stop, gbc1);
                stop.addActionListener(new B9());
            }
            if (wybor == 2) {
                mouseX = e.getX();
                mouseY = e.getY();
                o.grafika.Dodaj(mouseX, mouseY, wybor);
                wybor = 0;
                j = new JFrame("Wybor");
                j.setSize(200, 200);
                j.setResizable(false);
                j.setLocation(540, 412);

                j.setVisible(true);

                JPanel pole = new JPanel();
                JButton stop = new JButton("OK");
                JLabel poj = new JLabel("Podaj pojemnosc (uF)");

                odp = new JTextField();

                j.add(pole);
                GridBagLayout layout1 = new GridBagLayout();

                pole.setLayout(layout1);
                GridBagConstraints gbc1 = new GridBagConstraints();

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 0;
                pole.add(poj, gbc1);

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 1;
                pole.add(odp, gbc1);

                gbc1.fill = GridBagConstraints.HORIZONTAL;
                gbc1.gridx = 0;
                gbc1.gridy = 2;
                pole.add(stop, gbc1);
                stop.addActionListener(new B10());
            }

            if (wybor == 3) {
                mouseX = e.getX();
                mouseY = e.getY();
                o.grafika.Dodaj(mouseX, mouseY, wybor);
                masy.add(new Masa(mouseX, mouseY));
                wybor = 0;
            }
            if (wybor == 4) {

                int pole = 15;
                if (click == false) {
                    mouseX = e.getX();
                    mouseY = e.getY();

                    for (int j = 0; j < rezystory.size(); j++) {
                        if ((rezystory.get(j).node1.p.x > mouseX - pole) && (rezystory.get(j).node1.p.x < mouseX + pole) && (rezystory.get(j).node1.p.y > mouseY - pole) && (rezystory.get(j).node1.p.y < mouseY + pole)) {

                            //dodać do listy polaczen
                            p1 = j;
                            element1 = 1;
                            nozka1 = false;
                            click = true;
                            break;

                        }
                        if ((rezystory.get(j).node2.p.x > mouseX - pole) && (rezystory.get(j).node2.p.x < mouseX + pole) && (rezystory.get(j).node2.p.y > mouseY - pole) && (rezystory.get(j).node2.p.y < mouseY + pole)) {
                            element1 = 1;
                            click = true;
                            nozka1 = true;
                            p1 = j;
                            break;
                        }
                    }
                    for (int j = 0; j < kondensatory.size(); j++) {
                        if ((kondensatory.get(j).node1.p.x > mouseX - pole) && (kondensatory.get(j).node1.p.x < mouseX + pole) && (kondensatory.get(j).node1.p.y > mouseY - pole) && (kondensatory.get(j).node1.p.y < mouseY + pole)) {

                            p1 = j;
                            element1 = 2;
                            nozka1 = false;

                            click = true;

                            break;

                        }
                        if ((kondensatory.get(j).node2.p.x > mouseX - pole) && (kondensatory.get(j).node2.p.x < mouseX + pole) && (kondensatory.get(j).node2.p.y > mouseY - pole) && (kondensatory.get(j).node2.p.y < mouseY + pole)) {
                            element1 = 2;
                            click = true;
                            nozka1 = true;
                            p1 = j;
                            break;
                        }
                    }
                    for (int i = 0; i < masy.size(); i++) {

                        if ((masy.get(i).node1.p.x > mouseX - pole) && (masy.get(i).node1.p.x < mouseX + pole) && (masy.get(i).node1.p.y > mouseY - pole) && (masy.get(i).node1.p.y < mouseY + pole)) {
                            element1 = 3;
                            p1 = i;
                            click = true;
                            break;
                        }
                    }
                    if ((o.grafika.zr.p.x > mouseX - pole) && (o.grafika.zr.p.x < mouseX + pole) && (o.grafika.zr.p.y > mouseY - pole) && (o.grafika.zr.p.y < mouseY + pole)) {
                        element1 = 4;
                        click = true;

                    }
                    if ((o.grafika.wep.p.x > mouseX - pole) && (o.grafika.wep.p.x < mouseX + pole) && (o.grafika.wep.p.y > mouseY - pole) && (o.grafika.wep.p.y < mouseY + pole)) {
                        element1 = 5;
                        click = true;

                    }
                    if ((o.grafika.wem.p.x > mouseX - pole) && (o.grafika.wem.p.x < mouseX + pole) && (o.grafika.wem.p.y > mouseY - pole) && (o.grafika.wem.p.y < mouseY + pole)) {
                        element1 = 6;
                        click = true;

                    }
                    if ((o.grafika.wy.p.x > mouseX - pole) && (o.grafika.wy.p.x < mouseX + pole) && (o.grafika.wy.p.y > mouseY - pole) && (o.grafika.wy.p.y < mouseY + pole)) {
                        element1 = 7;
                        click = true;

                    }

                } else {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    for (int i = 0; i < rezystory.size(); i++) {
                        if ((rezystory.get(i).node1.p.x > mouseX - pole) && (rezystory.get(i).node1.p.x < mouseX + pole) && (rezystory.get(i).node1.p.y > mouseY - pole) && (rezystory.get(i).node1.p.y < mouseY + pole)) {
                            element2 = 1;
                            click = true;
                            nozka2 = false;
                            p2 = i;
                            break;
                        }
                        if ((rezystory.get(i).node2.p.x > mouseX - pole) && (rezystory.get(i).node2.p.x < mouseX + pole) && (rezystory.get(i).node2.p.y > mouseY - pole) && (rezystory.get(i).node2.p.y < mouseY + pole)) {
                            //dodać do listy polaczen
                            element2 = 1;
                            click = true;
                            nozka2 = true;
                            p2 = i;
                            break;
                        }

                    }
                    for (int i = 0; i < kondensatory.size(); i++) {
                        if ((kondensatory.get(i).node1.p.x > mouseX - pole) && (kondensatory.get(i).node1.p.x < mouseX + pole) && (kondensatory.get(i).node1.p.y > mouseY - pole) && (kondensatory.get(i).node1.p.y < mouseY + pole)) {
                            element2 = 2;

                            nozka2 = false;
                            p2 = i;
                            break;
                        }
                        if ((kondensatory.get(i).node2.p.x > mouseX - pole) && (kondensatory.get(i).node2.p.x < mouseX + pole) && (kondensatory.get(i).node2.p.y > mouseY - pole) && (kondensatory.get(i).node2.p.y < mouseY + pole)) {
                            element2 = 2;

                            nozka2 = true;
                            p2 = i;
                            break;
                        }
                    }
                    for (int i = 0; i < masy.size(); i++) {

                        if ((masy.get(i).node1.p.x > mouseX - pole) && (masy.get(i).node1.p.x < mouseX + pole) && (masy.get(i).node1.p.y > mouseY - pole) && (masy.get(i).node1.p.y < mouseY + pole)) {
                            element2 = 3;
                            p2 = i;

                            break;
                        }
                    }
                    if ((o.grafika.zr.p.x > mouseX - pole) && (o.grafika.zr.p.x < mouseX + pole) && (o.grafika.zr.p.y > mouseY - pole) && (o.grafika.zr.p.y < mouseY + pole)) {
                        element2 = 4;
                        click = true;

                    }
                    if ((o.grafika.wep.p.x > mouseX - pole) && (o.grafika.wep.p.x < mouseX + pole) && (o.grafika.wep.p.y > mouseY - pole) && (o.grafika.wep.p.y < mouseY + pole)) {
                        element2 = 5;
                        click = true;

                    }
                    if ((o.grafika.wem.p.x > mouseX - pole) && (o.grafika.wem.p.x < mouseX + pole) && (o.grafika.wem.p.y > mouseY - pole) && (o.grafika.wem.p.y < mouseY + pole)) {
                        element2 = 6;
                        click = true;

                    }
                    if ((o.grafika.wy.p.x > mouseX - pole) && (o.grafika.wy.p.x < mouseX + pole) && (o.grafika.wy.p.y > mouseY - pole) && (o.grafika.wy.p.y < mouseY + pole)) {
                        element2 = 7;
                        click = true;

                    }

                    Node robocza1 = null;
                    Node robocza2 = null;

                    if (element1 == 1) {

                        if (nozka1 == false) {
                            robocza1 = rezystory.get(p1).node1;

                        } else {
                            robocza1 = rezystory.get(p1).node2;
                        }

                    }
                    if (element1 == 2) {

                        if (nozka1 == false) {
                            robocza1 = kondensatory.get(p1).node1;
                        } else {
                            robocza1 = kondensatory.get(p1).node2;
                        }

                    }
                    if (element1 == 3) {

                        robocza1 = masy.get(p1).node1;

                    }
                    if (element1 == 4) {

                        robocza1 = o.grafika.zr;

                    }
                    if (element1 == 5) {

                        robocza1 = o.grafika.wep;

                    }
                    if (element1 == 6) {

                        robocza1 = o.grafika.wem;

                    }
                    if (element1 == 7) {

                        robocza1 = o.grafika.wy;
                    }

                    if (element2 == 1) {

                        if (nozka2 == false) {
                            robocza2 = rezystory.get(p2).node1;
                        } else {
                            robocza2 = rezystory.get(p2).node2;
                        }
                    }
                    if (element2 == 2) {

                        if (nozka2 == false) {
                            robocza2 = kondensatory.get(p2).node1;
                        } else {
                            robocza2 = kondensatory.get(p2).node2;
                        }
                    }
                    if (element2 == 3) {

                        robocza2 = masy.get(p2).node1;
                    }
                    if (element2 == 4) {

                        robocza2 = o.grafika.zr;

                    }
                    if (element2 == 5) {

                        robocza2 = o.grafika.wep;

                    }
                    if (element2 == 6) {

                        robocza2 = o.grafika.wem;

                    }
                    if (element2 == 7) {

                        robocza2 = o.grafika.wy;
                    }
                    if (robocza1 != null & robocza2 != null) {
                        o.grafika.Dodaj(robocza1, robocza2);

                        polaczenia.add(new Polaczenie(p1, p2, nozka1, nozka2, element1, element2));

                    }
                    p1 = -1;
                    p2 = -1;
                    element1 = 0;
                    element2 = 0;
                    click = false;

                }

            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
    /**
     * Reset is clearing ArrayLists of components, and repainting the Graphics
     */
    public void Reset() {
        rezystory.clear();
        kondensatory.clear();
        polaczenia.clear();
        masy.clear();
        o.grafika.rezystory.clear();
        o.grafika.polaczenia.clear();
        o.grafika.repaint();
    }
}
