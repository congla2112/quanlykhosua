/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class P_home extends javax.swing.JPanel {

    /**
     * Creates new form P_home
     */
    public P_home() {
        initComponents();
        ImageIcon imgIcon = new javax.swing.ImageIcon(getClass().getResource("/Image/nen5.jpg"));
        
        Image image = imgIcon.getImage(); 
        Image newimg = image.getScaledInstance(926, 546,  java.awt.Image.SCALE_SMOOTH);
        
        
        lb_image.setIcon(new ImageIcon(newimg));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_image = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(931, 540));

        lb_image.setMaximumSize(new java.awt.Dimension(915, 540));
        lb_image.setMinimumSize(new java.awt.Dimension(915, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_image, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb_image;
    // End of variables declaration//GEN-END:variables
}
