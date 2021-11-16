/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.NguoiDung;
import PakagesClass.TaiKhoan;
import com.edusys.dao.NguoiDungDAO;
import com.edusys.dao.TaiKhoanDAO;
import com.edusys.utils.XDate;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class JFormDangKy extends javax.swing.JDialog {

    com.edusys.dao.TaiKhoanDAO taiKhoanDAO;
    com.edusys.dao.NguoiDungDAO nguoiDungDAO;
    public static int ID_MaND = 0;
    public JFormDangKy(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Đăng ký tài khoản");
        loandForm();
        setExit();
        taiKhoanDAO = new TaiKhoanDAO();
        nguoiDungDAO = new NguoiDungDAO();
    }
    
    void loandForm() {
       
    }

    public void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

    public NguoiDung setNguoiDung() {
        
        NguoiDung nd = new NguoiDung();
        nd.setHoTen(FormSignInNguoiDung.getHoTen);
        nd.setGioiTinh(FormSignInNguoiDung.getGioiTinh);
        nd.setSDT(FormSignInNguoiDung.getSDT);
        nd.setNgaySinh(XDate.toDate(FormSignInNguoiDung.getNgaySinh, "dd-MM-yyyy"));
        nd.setEmail(FormSignInNguoiDung.getEmail);
        return nd;
    }
    
    public TaiKhoan getForm() {
        NguoiDung nd = nguoiDungDAO.getMaxID();
        ID_MaND = nd.getID_MaND();
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(tf_name.getText());
        tk.setMatKhau(tf_pass.getText());
        tk.setMKCap2(tf_pass2.getText());
        tk.setVaiTro(false);
        tk.setID_MaND(ID_MaND);
        System.out.println(ID_MaND);
        return tk;
    }
    
    public void insert() {
        try {
            
            if (tf_name.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống tài khoản!");
                return;
            } else if (tf_pass.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu!");
                return;
            } else if (tf_pass2.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu c2!");
                return;
            }
            String checkTK = "[,'/;+-_.]{1,}";
            if (tf_name.getText().matches(checkTK)) {
                JOptionPane.showMessageDialog(rootPane, "Tài khoản không chứa ký tự đặc biệt!");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "Xác nhận đăng ký?", "Đăng ký tài khoản", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                NguoiDung nd = setNguoiDung();
                nguoiDungDAO.insert(nd);
                TaiKhoan tk = getForm();
                taiKhoanDAO.insert(tk);
                JOptionPane.showMessageDialog(rootPane, "Đăng ký thành công!");
                refresh();
            }
        } catch (Exception e) {
            
        }
    }
    
    public void refresh() {
        tf_name.setText("");
        tf_pass.setText("");
        tf_pass2.setText("");
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        bt_dangky = new javax.swing.JButton();
        bt_Cancel = new javax.swing.JButton();
        tf_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_pass = new javax.swing.JTextField();
        tf_pass2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bt_dangky1 = new javax.swing.JButton();
        bt_Cancel1 = new javax.swing.JButton();
        tf_name1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_pass1 = new javax.swing.JTextField();
        tf_pass3 = new javax.swing.JTextField();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 255));
        jLabel4.setText("Đăng ký tài khoản");

        bt_dangky.setText("Đăng ký");
        bt_dangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dangkyActionPerformed(evt);
            }
        });

        bt_Cancel.setText("Cancel");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên tài khoản:");

        jLabel2.setText("Mật khẩu:");

        jLabel3.setText("Mật khẩu cấp 2:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 255));
        jLabel5.setText("Đăng ký tài khoản");

        bt_dangky1.setText("Đăng ký");
        bt_dangky1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dangky1ActionPerformed(evt);
            }
        });

        bt_Cancel1.setText("Cancel");
        bt_Cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_Cancel1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Tên tài khoản:");

        jLabel7.setText("Mật khẩu:");

        jLabel8.setText("Mật khẩu cấp 2:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(bt_dangky1)
                .addGap(37, 37, 37)
                .addComponent(bt_Cancel1)
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(tf_pass1)
                    .addComponent(tf_pass3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pass3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_dangky1)
                    .addComponent(bt_Cancel1))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_dangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dangkyActionPerformed
        insert();

    }//GEN-LAST:event_bt_dangkyActionPerformed

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát không?", "Thoát", JOptionPane.YES_NO_OPTION);

        if (i == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_bt_CancelActionPerformed

    private void bt_dangky1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dangky1ActionPerformed
        insert();

    }//GEN-LAST:event_bt_dangky1ActionPerformed

    private void bt_Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Cancel1ActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát không?", "Thoát", JOptionPane.YES_NO_OPTION);

        if (i == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_bt_Cancel1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFormDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFormDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFormDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFormDangKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFormDangKy dialog = new JFormDangKy(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Cancel1;
    private javax.swing.JButton bt_dangky;
    private javax.swing.JButton bt_dangky1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_name1;
    private javax.swing.JTextField tf_pass;
    private javax.swing.JTextField tf_pass1;
    private javax.swing.JTextField tf_pass2;
    private javax.swing.JTextField tf_pass3;
    // End of variables declaration//GEN-END:variables
}
