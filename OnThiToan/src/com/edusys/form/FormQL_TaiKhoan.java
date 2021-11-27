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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FormQL_TaiKhoan extends javax.swing.JFrame {

    /**
     * Creates new form FormQL_TaiKhoan
     */
    NguoiDungDAO daond = new NguoiDungDAO();
    TaiKhoanDAO daotk = new TaiKhoanDAO();
    int row = 0;

    public FormQL_TaiKhoan() {
        initComponents();
        setLocationRelativeTo(null);
        JPasswordField pass = new JPasswordField();
         pass.setEchoChar('*');
        
        setExit();
        filltable();
        setExit();
    }

    public void setform(TaiKhoan tk) {
        txtma.setText(tk.getID_MaND() + "");
        txthoten.setText(tk.getTenTaiKhoan());
        txtmk.setText(tk.getMatKhau());
        txtmk2.setText(tk.getMKCap2());
        if (tk.getVaiTro() == true) {
            rdadd.setSelected(true);
        } else {
            rdngd.setSelected(true);
        }

    }

    public void moustClick() {
        int row = table.getSelectedRow();
        String idnd = (String) table.getValueAt(row,1);
        TaiKhoan tk = daotk.selectById(idnd);
        int index = table.getSelectedRow();
        if (index >= 0) {
            setform(tk);
            tas.setSelectedIndex(0);
        }
    }

    public void filltable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<TaiKhoan> list = daotk.selectALL();

            for (TaiKhoan tk : list) {
                Object[] row = {
                    tk.getID_MaND(), tk.getTenTaiKhoan(), tk.getVaiTro()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    TaiKhoan getfrom() {
        TaiKhoan tk = new TaiKhoan();
        tk.setID_MaND(Integer.parseInt(txtma.getText()));
        tk.setTenTaiKhoan(txthoten.getText());
        tk.setMatKhau((String) txtmk.getText());
        tk.setMKCap2((String) txtmk2.getText());
        boolean vaitro;
        if (rdadd.isSelected() == true) {
            vaitro = true;
        } else {
            vaitro = false;
        }
        tk.setVaiTro(vaitro);
        return tk;
    }

    public void sua() {

        int index = table.getSelectedRow();
        String mk = new String(txtmk2.getText());
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để update");
            return;
        }
        TaiKhoan tk = getfrom();
        try {
            if (txthoten.getText().equals("") || txtmk.getText().equals("") || txtmk2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập đầy đủ thông tin!");
                return;
            }
            if (!mk.equalsIgnoreCase(tk.getMatKhau())) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không đúng! ");
                return;
            }
            daotk.update(tk);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            this.filltable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng để update");
        }

    }

    public void delete() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để Delete");
            return;
        }
        TaiKhoan tk = getfrom();
        String ten = txthoten.getText();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa " + txthoten.getText(), "", JOptionPane.YES_NO_OPTION);
        if (chon == 0) {
            daotk.delete(ten);

            this.filltable();
            this.clear();
            JOptionPane.showMessageDialog(this, "Xoa thành công ");
        }
    }

    public void clear() {
        txthoten.setText("");
        txtma.setText("");
        txtmk.setText("");
        txtmk.setText("");
        txtmk2.setText("");
        rdadd.setSelected(false);
        rdngd.setSelected(false);
    }

    public void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

    public void them() {
        new JFormNguoiDung(this, true).setVisible(true);
        new JFormSignUp(this, true).setVisible(true);
        filltable();
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdadd = new javax.swing.JRadioButton();
        rdngd = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtmk = new javax.swing.JPasswordField();
        txtmk2 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("QUẢN LÍ TÀI KHOẢN");

        jLabel2.setText("MãND");

        txtma.setEditable(false);

        jLabel3.setText("Tên tài khoản");

        jLabel5.setText("Vai trò");

        buttonGroup1.add(rdadd);
        rdadd.setText("Admin");
        rdadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdaddActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdngd);
        rdngd.setText("Người dùng");
        rdngd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdngdActionPerformed(evt);
            }
        });

        jLabel6.setText("Mật khẩu");

        jLabel7.setText("Xác nhận mật khẩu");

        btnthem.setText("Add");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setText("Delete");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnthem)
                        .addGap(41, 41, 41)
                        .addComponent(btnupdate)
                        .addGap(48, 48, 48)
                        .addComponent(btnxoa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(txthoten, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(txtma)
                            .addComponent(jLabel6)
                            .addComponent(txtmk)
                            .addComponent(txtmk2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(rdadd)
                        .addGap(18, 18, 18)
                        .addComponent(rdngd))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel5)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(13, 13, 13)
                .addComponent(txtmk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdadd)
                    .addComponent(rdngd))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnupdate)
                    .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        tas.addTab("Cập Nhật", jPanel1);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaND", "Họ tên", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setColumnSelectionAllowed(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        tas.addTab("Danh Sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tas)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(tas))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdaddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdaddActionPerformed

    private void rdngdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdngdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdngdActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_btnthemActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        moustClick();
    }//GEN-LAST:event_tableMouseClicked

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btnupdateActionPerformed

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
            java.util.logging.Logger.getLogger(FormQL_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQL_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQL_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQL_TaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQL_TaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdadd;
    private javax.swing.JRadioButton rdngd;
    private javax.swing.JTable table;
    private javax.swing.JTabbedPane tas;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtma;
    private javax.swing.JPasswordField txtmk;
    private javax.swing.JPasswordField txtmk2;
    // End of variables declaration//GEN-END:variables
}
