//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.config;

import java.awt.*;
import java.awt.event.*;
import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.UIManager;

/**
 *
 * @author adrianromero
 */
public class JFrmConfig extends javax.swing.JFrame {
    
    private JPanelConfiguration config;
    
    /** Creates new form JFrmConfig */
    public JFrmConfig(AppProperties props) {
        
        initComponents();
        
        try {
            this.setIconImage(ImageIO.read(JRootFrame.class.getResourceAsStream("/com/openbravo/images/favicon.png")));
        } catch (IOException e) {
        }   
        setTitle(AppLocal.APP_NAME + " - " + AppLocal.APP_VERSION + " - " + AppLocal.getIntString("Menu.Configuration"));
        
        addWindowListener(new MyFrameListener()); 
        
        config = new JPanelConfiguration(props);
        
        getContentPane().add(config, BorderLayout.CENTER);
       
        try {
            config.activate();
        } catch (BasicException e) { // never thrown ;-)
        }
    }
    
    private class MyFrameListener extends WindowAdapter{
        
        public void windowClosing(WindowEvent evt) {
            if (config.deactivate()) {
                dispose();
            }
        }
        public void windowClosed(WindowEvent evt) {
            System.exit(0);
        }
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-700)/2, (screenSize.height-500)/2, 700, 500);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                AppConfig config = new AppConfig(args);
                config.load();    
                
                // Set the look and feel.
                try {                    
                    UIManager.setLookAndFeel(config.getProperty("swing.defaultlaf"));
                } catch (Exception e) {
                }
                
                new JFrmConfig(config).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
