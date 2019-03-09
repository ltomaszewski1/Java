/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class Logika is responsible for checking corectness of the circuit
 *
 * @author Lukasz Tomaszewski
 */
public class Logika {

    Mechanika mech;
    int poziom = 0;
    JFrame jF;
/**
 * class Logika is responsible for checking corectness of the circuit
 */
    public Logika() {

        mech = new Mechanika();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() { // funkcja wywołuje sprawdzanie aktualnego poziomu
            @Override
            public void run() {

                Sprawdzenie();
                

            }

        }, 100, 100);
    }

    void Sprawdzenie() { //funkcja wywołuje odpowiednią funkcje zależnie od poziomu, losuje wzmocnienie oraz generuje nazwę poziomu
        if (poziom == 0) {
            poziom++;
            mech.o.nazwa = "Wtórnik napięciowy";
            mech.o.poziom.setText(mech.o.nazwa);
            mech.o.wzmocnienie.setVisible(false);

            try {
                Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/1.png"));
                mech.o.obraz.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                System.err.println("Blad odczytu obrazka");

            }
        }
        if (poziom == 1) {

            if (Sprawdz1()) {
                poziom++;

                mech.o.nazwa = "Wzmacniacz nieodwracający";
                mech.o.poziom.setText(mech.o.nazwa);
                mech.o.wzm = LosowaK(2, 10);
                
                mech.o.wzmocnienie.setText("Zadane wzmocnienie: " + mech.o.wzm);
                mech.o.wzmocnienie.setVisible(true);
                mech.Reset();
                try {
                    Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/2.png"));
                    mech.o.obraz.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    System.err.println("Blad odczytu obrazka");

                }
            }
        }

        if (poziom == 2) {
            if (Sprawdz2()) {
                poziom++;
                mech.o.nazwa = "Wzmacniacz odwracający";
                mech.o.poziom.setText(mech.o.nazwa);
                mech.o.wzm = LosowaK(2, 10);
                
                mech.o.wzmocnienie.setText("Zadane wzmocnienie: -" + mech.o.wzm);
                mech.Reset();
                try {
                    Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/3.png"));
                    mech.o.obraz.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    System.err.println("Blad odczytu obrazka");

                }
            }
        }
        if (poziom == 3) {
            if (Sprawdz3()) {
                poziom++;
                mech.o.nazwa = "Wzmacniacz różniczkujący";
                mech.o.poziom.setText(mech.o.nazwa);
                mech.o.wzm = LosowaK(2, 10);
                
                mech.o.wzmocnienie.setText("Zadane wzmocnienie: -s *" + mech.o.wzm);
                mech.Reset();

                try {
                    Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/4.png"));
                    mech.o.obraz.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    System.err.println("Blad odczytu obrazka");

                }
            }
        }
        if (poziom == 4) {
            if (Sprawdz4()) {
                poziom++;
                mech.o.nazwa = "Wzmacniacz całkujący";
                mech.o.poziom.setText(mech.o.nazwa);
                mech.o.wzm = LosowaK(2, 10);
                
                mech.o.wzmocnienie.setText("Zadane wzmocnienie: -" + mech.o.wzm+"/s");
                mech.Reset();
                try {
                    Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/5.png"));
                    mech.o.obraz.setIcon(new ImageIcon(img));
                } catch (IOException e) {
                    System.err.println("Blad odczytu obrazka");

                }
            }
        }
        if (poziom == 5) {
            if (Sprawdz5()) {
                poziom++;
            }
        }
        if (poziom == 6) {

            jF = new JFrame("Gratulacje!");
            jF.setSize(400, 400);
            jF.setResizable(false);
            jF.setLocation(540, 412);
            jF.setVisible(true);

            JPanel pole = new JPanel();
            JButton nowa = new JButton("Nowa gra");
            JButton stop = new JButton("Koniec");
            JLabel info = new JLabel("Gratulacje! Pomoc zostala uzyta " + mech.licznik + " razy.");

            jF.add(pole);
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

            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.gridx = 0;
            gbc1.gridy = 2;
            pole.add(nowa, gbc1);
            nowa.addActionListener(new B5());
            poziom++;

        }
    }

    boolean Sprawdz1() {
        for (int j = 0; j < mech.polaczenia.size(); j++) {
            if ((mech.polaczenia.size() == 3) && (((mech.polaczenia.get(j).k1 == 4) && (mech.polaczenia.get(j).k2 == 5)) || ((mech.polaczenia.get(j).k1 == 5) && (mech.polaczenia.get(j).k2 == 4)))) {
                
                for (int i = 0; i < mech.polaczenia.size(); i++) {
                    if ((mech.polaczenia.get(i).k1 == 6) && (mech.polaczenia.get(i).k2 == 1)) {
                        if (mech.polaczenia.get(i).n2 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                    return true; //
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                    return true; // 
                                }
                            }
                        }

                    } else if ((mech.polaczenia.get(i).k1 == 1) && (mech.polaczenia.get(i).k2 == 6)) {
                        if (mech.polaczenia.get(i).n1 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                    return true; //
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                    return true; //
                                }
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

    boolean Sprawdz2() {
        boolean r1 = false, r2 = false;
        float rez1 = 1, rez2 = 1;
        for (int j = 0; j < mech.polaczenia.size(); j++) {
            if ((mech.polaczenia.size() == 5) && (((mech.polaczenia.get(j).k1 == 4) && (mech.polaczenia.get(j).k2 == 5)) || ((mech.polaczenia.get(j).k1 == 5) && (mech.polaczenia.get(j).k2 == 4)))) {
                
                for (int i = 0; i < mech.polaczenia.size(); i++) {
                    if ((mech.polaczenia.get(i).k1 == 1 && mech.polaczenia.get(i).k2 == 3)) {
                        if (mech.polaczenia.get(i).n1 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;

                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                                }
                            }
                        }

                    } else if ((mech.polaczenia.get(i).k1 == 3 && mech.polaczenia.get(i).k2 == 1)) {
                        if (mech.polaczenia.get(i).n2 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        }
                    }
                }
            } /// kod na dole będzie odpowiedzialny za sprawdzanie drugiego rezystora
            for (int i = 0; i < mech.polaczenia.size(); i++) {
                if ((mech.polaczenia.get(i).k1 == 6) && (mech.polaczenia.get(i).k2 == 1)) {
                    if (mech.polaczenia.get(i).n2 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(k).p2).rezystancja; // 
                            }
                        }
                    }

                } else if ((mech.polaczenia.get(i).k1 == 1) && (mech.polaczenia.get(i).k2 == 6)) {
                    if (mech.polaczenia.get(i).n1 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    }

                }
            }
        }
        
        if (r1 == true && r2 == true) {
            if (rez2 / rez1 == mech.o.wzm - 1) {
                return true;
            } else {
                ZleWzmocnienie();
            }
        }
        return false;
    }

    boolean Sprawdz3() {
        boolean r1 = false, r2 = false;
        float rez1 = 1, rez2 = 1;
        for (int j = 0; j < mech.polaczenia.size(); j++) {
            if ((mech.polaczenia.size() == 5) && (((mech.polaczenia.get(j).k1 == 3) && (mech.polaczenia.get(j).k2 == 5)) || ((mech.polaczenia.get(j).k1 == 5) && (mech.polaczenia.get(j).k2 == 3)))) {
               
                for (int i = 0; i < mech.polaczenia.size(); i++) {
                    if ((mech.polaczenia.get(i).k1 == 1 && mech.polaczenia.get(i).k2 == 4)) {
                        if (mech.polaczenia.get(i).n1 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;

                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                                }
                            }
                        }

                    } else if ((mech.polaczenia.get(i).k1 == 4 && mech.polaczenia.get(i).k2 == 1)) {
                        if (mech.polaczenia.get(i).n2 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        }
                    }
                }
            } /// kod na dole będzie odpowiedzialny za sprawdzanie drugiego rezystora
            for (int i = 0; i < mech.polaczenia.size(); i++) {
                if ((mech.polaczenia.get(i).k1 == 6) && (mech.polaczenia.get(i).k2 == 1)) {
                    if (mech.polaczenia.get(i).n2 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(k).p2).rezystancja; // 
                            }
                        }
                    }

                } else if ((mech.polaczenia.get(i).k1 == 1) && (mech.polaczenia.get(i).k2 == 6)) {
                    if (mech.polaczenia.get(i).n1 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    }

                }
            }
        }
        
        if (r1 == true && r2 == true) { //dodać sprawdzanie k
            if (rez2 / rez1 == mech.o.wzm) {
                return true;
            } else {
                ZleWzmocnienie();
            }
        }
        return false;
    }

    boolean Sprawdz4() {
        boolean c1 = false, r2 = false;
        float poj1 = 1, rez2 = 1;
        for (int j = 0; j < mech.polaczenia.size(); j++) {
            if ((mech.polaczenia.size() == 5) && (((mech.polaczenia.get(j).k1 == 3) && (mech.polaczenia.get(j).k2 == 5)) || ((mech.polaczenia.get(j).k1 == 5) && (mech.polaczenia.get(j).k2 == 3)))) {
                
                for (int i = 0; i < mech.polaczenia.size(); i++) {
                    if ((mech.polaczenia.get(i).k1 == 2 && mech.polaczenia.get(i).k2 == 4)) {
                        if (mech.polaczenia.get(i).n1 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    c1 = true;
                                    poj1 = mech.kondensatory.get(mech.polaczenia.get(i).p1).pojemnosc;

                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    c1 = true;
                                    poj1 = mech.kondensatory.get(mech.polaczenia.get(i).p1).pojemnosc;
                                }
                            }
                        }

                    } else if ((mech.polaczenia.get(i).k1 == 4 && mech.polaczenia.get(i).k2 == 2)) {
                        if (mech.polaczenia.get(i).n2 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    c1 = true;
                                    poj1 = mech.kondensatory.get(mech.polaczenia.get(i).p2).pojemnosc;
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    c1 = true;
                                    poj1 = mech.kondensatory.get(mech.polaczenia.get(i).p2).pojemnosc;
                                }
                            }
                        }
                    }
                }
            } /// kod na dole będzie odpowiedzialny za sprawdzanie drugiego rezystora
            for (int i = 0; i < mech.polaczenia.size(); i++) {
                if ((mech.polaczenia.get(i).k1 == 6) && (mech.polaczenia.get(i).k2 == 1)) {
                    if (mech.polaczenia.get(i).n2 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(k).p2).rezystancja; // 
                            }
                        }
                    }

                } else if ((mech.polaczenia.get(i).k1 == 1) && (mech.polaczenia.get(i).k2 == 6)) {
                    if (mech.polaczenia.get(i).n1 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                r2 = true;
                                rez2 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                            }
                        }
                    }

                }
            }
        }

        if (c1 == true && r2 == true) { //dodać sprawdzanie k
            if (rez2 * 1000 * poj1 * 0.000001 == mech.o.wzm) {
                return true;
            } else {
                ZleWzmocnienie();
            }
        }
        return false;
    }

    boolean Sprawdz5() {
        boolean r1 = false, c2 = false;
        float rez1 = 1, poj2 = 1;
        for (int j = 0; j < mech.polaczenia.size(); j++) {
            if ((mech.polaczenia.size() == 5) && (((mech.polaczenia.get(j).k1 == 3) && (mech.polaczenia.get(j).k2 == 5)) || ((mech.polaczenia.get(j).k1 == 5) && (mech.polaczenia.get(j).k2 == 3)))) {
                
                for (int i = 0; i < mech.polaczenia.size(); i++) {
                    if ((mech.polaczenia.get(i).k1 == 1 && mech.polaczenia.get(i).k2 == 4)) {
                        if (mech.polaczenia.get(i).n1 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;

                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p1).rezystancja;
                                }
                            }
                        }

                    } else if ((mech.polaczenia.get(i).k1 == 4 && mech.polaczenia.get(i).k2 == 1)) {
                        if (mech.polaczenia.get(i).n2 == false) {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == true)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        } else {
                            for (int k = 0; k < mech.polaczenia.size(); k++) {
                                if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 6) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 6) && (mech.polaczenia.get(k).n2 == false)) {
                                    r1 = true;
                                    rez1 = mech.rezystory.get(mech.polaczenia.get(i).p2).rezystancja;
                                }
                            }
                        }
                    }
                }
            } /// kod na dole będzie odpowiedzialny za sprawdzanie drugiego rezystora
            for (int i = 0; i < mech.polaczenia.size(); i++) {
                if ((mech.polaczenia.get(i).k1 == 6) && (mech.polaczenia.get(i).k2 == 2)) {
                    if (mech.polaczenia.get(i).n2 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                c2 = true;
                                poj2 = mech.kondensatory.get(mech.polaczenia.get(i).p2).pojemnosc;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p2) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                c2 = true;
                                poj2 = mech.kondensatory.get(mech.polaczenia.get(k).p2).pojemnosc; // 
                            }
                        }
                    }

                } else if ((mech.polaczenia.get(i).k1 == 2) && (mech.polaczenia.get(i).k2 == 6)) {
                    if (mech.polaczenia.get(i).n1 == false) {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == true)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == true)) {
                                c2 = true;
                                poj2 = mech.kondensatory.get(mech.polaczenia.get(i).p1).pojemnosc;
                            }
                        }
                    } else {
                        for (int k = 0; k < mech.polaczenia.size(); k++) {
                            if (((mech.polaczenia.get(k).p1 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k2 == 7) && (mech.polaczenia.get(k).n1 == false)) || (mech.polaczenia.get(k).p2 == mech.polaczenia.get(i).p1) && (mech.polaczenia.get(k).k1 == 7) && (mech.polaczenia.get(k).n2 == false)) {
                                c2 = true;
                                poj2 = mech.kondensatory.get(mech.polaczenia.get(i).p1).pojemnosc;
                            }
                        }
                    }

                }
            }
        }
      
        if (r1 == true && c2 == true) { //dodać sprawdzanie k
            float margines = (float) 0.125;
            if ((1 / (rez1 * 1000 * poj2 * 0.000001) > mech.o.wzm - margines) && (1 / (rez1 * 1000 * poj2 * 0.000001) < mech.o.wzm + margines)) {
                return true;
            } else {
                ZleWzmocnienie();
            }
        }
        return false;
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
            jF.dispose();
            poziom = 0;
            mech.Reset();
            mech.licznik = 0;

        }
    }

    class B6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jF.dispose();

            mech.Reset();

        }
    }

    int LosowaK(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    void ZleWzmocnienie() {
        mech.Reset();
        jF = new JFrame();
        jF.setSize(400, 400);
        jF.setResizable(false);
        jF.setLocation(540, 412);
        jF.setVisible(true);

        JPanel pole = new JPanel();
        JButton reset = new JButton("RESET");

        JLabel info = new JLabel("Niepoprawna wartość wzmocnienia!");

        jF.add(pole);
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
        pole.add(reset, gbc1);
        reset.addActionListener(new B6());

    }
}
