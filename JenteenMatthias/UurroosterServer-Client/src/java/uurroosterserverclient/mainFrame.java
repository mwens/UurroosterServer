/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uurroosterserverclient;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author witmoca
 */
public class mainFrame extends JFrame{
    private JButton printButton = new JButton("Print");
    
    public mainFrame() {
        this.setLayout(new GridLayout(1,1));
        
        printButton.addActionListener(new printListener(this));
        this.add(printButton);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
