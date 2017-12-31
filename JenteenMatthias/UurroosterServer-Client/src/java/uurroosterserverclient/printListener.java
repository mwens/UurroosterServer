/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uurroosterserverclient;

import beans.printBeanRemote;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author witmoca
 */
public class printListener implements ActionListener{
    private final Component PARENT;
    
    private printBeanRemote printBean;
    
    public printListener( Component parent){
        PARENT = parent;
        
        
        try {
            InitialContext ic = new InitialContext();
            printBean = (printBeanRemote) ic.lookup(printBeanRemote.class.getName());
            
        } catch (NamingException ex) {
            Logger.getLogger(printListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!printBean.isGefinaliseerd()){
            int go = JOptionPane.showConfirmDialog(PARENT,"De groepslijsten zijn nog niet gefinaliseerd. Toch Printen?","Waarschuwing",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(go != JOptionPane.OK_OPTION)
                return;
        }
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(printListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFileChooser fc = new JFileChooser();
        int ret = fc.showSaveDialog(PARENT);
        if(ret != JFileChooser.APPROVE_OPTION)
            return;
        
        PrintWriter writer;
        File saveLoc = fc.getSelectedFile();
        try {
            writer = new PrintWriter(saveLoc);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(PARENT, "File niet gevonden!","File not found error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<String> klassen = printBean.getKlassen();
        for(String klas : klassen){
            writer.println(klas);
            List<String> studenten = printBean.getStudentenInKlas(klas);
            for(String student : studenten){
                writer.println("- " + student);
            }
            writer.write("\n");
        }
        writer.close();
    }    
}
