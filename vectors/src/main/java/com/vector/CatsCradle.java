package com.vector;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Container;

public class CatsCradle extends JFrame{

    private static final int FRAME_WIDTH = 600;
    private static final int  FRAME_HEIGHT = 600;
    private static final String FRAME_TITLE = "Cat's Cradle";
    private static final int NUMBER_OF_SIDES = 8;

    public CatsCradle() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        CatsCradlePanel panel = new CatsCradlePanel(NUMBER_OF_SIDES);
        pane.add(panel);

        Timer timer = new Timer(20, panel);
        timer.start();

        this.setVisible(true);

    }

    public static void main(String[] args) {
        CatsCradle catsCradle = new CatsCradle();
    }
    
}
