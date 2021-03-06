/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.TaiKhoan;
import com.edusys.dao.TaiKhoanDAO;

import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;


import utils.Auth2;
import javax.swing.JOptionPane;

/**
 *
 * @author taola
 */
public class FormLogin_Start extends javax.swing.JFrame {

    com.edusys.dao.TaiKhoanDAO daotk = new TaiKhoanDAO();
    public static boolean vaiTro=false;
    public FormLogin_Start() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login");
        fChao();
    }
    void fChao(){
        new JFormLoad(this, true).setVisible(true);
    }
    public void vao() {
        String name = txtname.getText();
        String pass = new String(txtpas.getText());
        try {
            TaiKhoan tk = daotk.selectById(name);

            if (txtname.getText().equals("") || txtpas.getText().equals("")) {
                lablecheck.setText("Vui lòng điền đầy đủ thông tin!");
                return;
            } else if (txtname.getText().equals("")) {

                lablecheck.setText("Vui lòng điền tên tài khoản! ");
                return;
            } else if (txtpas.getText().equals("")) {

                lablecheck.setText("Vui lòng điền mật khẩu!");
                return;
            } else if (tk == null) {
                lablecheck.setText("Tài khoản không đúng!");
                return;
            } else if (!tk.getMatKhau().equalsIgnoreCase(pass)) {
                lablecheck.setText("Mật khẩu không đúng!");
                return;
            } else {
                Auth2.use = tk;
                JOptionPane.showMessageDialog(this, "Login thành công");
                fLogin();
                vaiTro=tk.getVaiTro();
                System.out.println(vaiTro);
                this.dispose();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fLogin() {
        FormChinh fdn = new FormChinh();
        fdn.show();

    }

    public void can() {
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn hủy đăng nhập?", "", JOptionPane.YES_NO_OPTION);
        if (chon == 0) {
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnlogin = new javax.swing.JButton();
        btncan = new javax.swing.JButton();
        lablecheck = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpas = new javax.swing.JPasswordField();
        lbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        checkpasss = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnlogin.setText("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        btncan.setText("Cancel");
        btncan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncanActionPerformed(evt);
            }
        });

        lablecheck.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lablecheck.setForeground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("ĐĂNG NHẬP");

        jLabel3.setText("Username");

        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnameKeyPressed(evt);
            }
        });

        jLabel4.setText("PassWord");

        txtpas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasKeyPressed(evt);
            }
        });

        lbl.setForeground(new java.awt.Color(51, 51, 255));
        lbl.setText(" Forgot password ?");
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMouseClicked(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Đăng ký");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        checkpasss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkpasss.setForeground(new java.awt.Color(0, 0, 255));
        checkpasss.setText("Hiện thị Pass");
        checkpasss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkpasssActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(txtpas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(61, 61, 61)
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lablecheck, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkpasss)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnlogin)
                        .addGap(38, 38, 38)
                        .addComponent(btncan)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtpas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(lablecheck, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkpasss))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlogin)
                    .addComponent(btncan))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        vao();
    }//GEN-LAST:event_btnloginActionPerformed

    private void btncanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncanActionPerformed
        can();
    }//GEN-LAST:event_btncanActionPerformed

    private void lblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMouseClicked
        FormQuenMK f = new FormQuenMK();
        f.setVisible(true);
        
    }//GEN-LAST:event_lblMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new JFormNguoiDung(this, true).setVisible(true);
        new JFormSignUp(this, true).setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked


    private void checkpasssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkpasssActionPerformed
        // TODO add your handling code here:
        if(checkpasss.isSelected()){
        txtpas.setEchoChar((char)0);
        
        }else{
        txtpas.setEchoChar('*');
        }
    }//GEN-LAST:event_checkpasssActionPerformed

    private void txtnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            vao();
        }
    }//GEN-LAST:event_txtnameKeyPressed

    private void txtpasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            vao();
        }
    }//GEN-LAST:event_txtpasKeyPressed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLogin_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin_Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncan;
    private javax.swing.JButton btnlogin;
    private javax.swing.JCheckBox checkpasss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lablecheck;
    private javax.swing.JLabel lbl;
    private javax.swing.JTextField txtname;
    private javax.swing.JPasswordField txtpas;
    // End of variables declaration//GEN-END:variables
}
