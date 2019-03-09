/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grawzmacniacz;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

import java.io.IOException;
/**
 * class Okno initialise swing GUI
 *
 * @author Lukasz Tomaszewski
 */
public class Okno extends JFrame {

    JPanel menu, poleGraf, komponenty, naglowek, wzory;
    JButton rezystor, masa, przewod, kondensator, pomoc, reset, wyjscie;
    JLabel poziom, obraz, wzmocnienie;
    String nazwa = "Wtórnik napięciowy";
    int wzm = 0;
    Grafika grafika;
    /**
     * class Okno initialise swing GUI
     */
    public Okno() {
        super("Gra Wzmacniacz");
        setSize(1280, 1024);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new JPanel();
        poleGraf = new JPanel();
        komponenty = new JPanel();
        naglowek = new JPanel();
        wzory = new JPanel();

        GridBagConstraints gbc1 = new GridBagConstraints();

        komponenty.setPreferredSize(new Dimension(1152, 170));
        komponenty.setBackground(new Color(157, 206, 255));

        naglowek.setPreferredSize(new Dimension(850, 78));
        naglowek.setBackground(new Color(81, 168, 255));

   
        menu.setBackground(new Color(81, 168, 255));

        wzory.setBackground(new Color(157, 206, 255));
        wzory.setPreferredSize(new Dimension(282, 600));

        poleGraf.setPreferredSize(new Dimension(850, 600));
        poleGraf.setBackground(new Color(221, 238, 255));
        GridBagLayout layout1 = new GridBagLayout();

        setLayout(layout1);

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        add(naglowek, gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 0;
        add(menu, gbc1);

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.ipady = 20;
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        add(poleGraf, gbc1);

        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.ipady = 20;
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        add(wzory, gbc1);

        gbc1.ipady = 20;
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridwidth = 2;
        add(komponenty, gbc1);

        rezystor = new JButton();
        komponenty.add(rezystor);

        try {
            Image img = ImageIO.read(getClass().getResource("obrazy/rezystor.png"));
            rezystor.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        kondensator = new JButton();
        komponenty.add(kondensator);

        try {
            Image img = ImageIO.read(getClass().getResource("obrazy/kondensator.png"));
            kondensator.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        przewod = new JButton();
        komponenty.add(przewod);

        try {
            Image img = ImageIO.read(getClass().getResource("obrazy/przewod.png"));
            przewod.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        masa = new JButton();
        komponenty.add(masa);

        try {
            Image img = ImageIO.read(getClass().getResource("obrazy/masa.png"));
            masa.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        poziom = new JLabel(nazwa);
        wzmocnienie = new JLabel("Zadane wzmocnienie: " + wzm);
        GridBagLayout layout3 = new GridBagLayout();

        naglowek.setLayout(layout3);
        GridBagConstraints gbc3 = new GridBagConstraints();

        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        naglowek.add(poziom, gbc3);

        gbc3.gridx = 0;
        gbc3.gridy = 1;
        naglowek.add(wzmocnienie, gbc3);
        wzmocnienie.setVisible(false);

        pomoc = new JButton("Pomoc");
        reset = new JButton("Reset");
        wyjscie = new JButton("Wyjscie");

        GridBagLayout layout2 = new GridBagLayout();

        menu.setLayout(layout2);
        GridBagConstraints gbc2 = new GridBagConstraints();

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        menu.add(pomoc, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        menu.add(reset, gbc2);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        menu.add(wyjscie, gbc2);

        obraz = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("obrazy/pomoc/1.png"));
            obraz.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");

        }

        wzory.add(obraz);
        obraz.setVisible(false);
        grafika = new Grafika();
        poleGraf.add(grafika);
        grafika.setPreferredSize(new Dimension(850, 600));
    }

}
