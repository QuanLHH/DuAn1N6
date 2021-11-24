/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import javax.swing.ImageIcon;
import utils.MsgBox;

/**
 *
 * @author taola
 */
public class FormChinh extends javax.swing.JFrame {

    /**
     * Creates new form FormChinh
     */
    public FormChinh() {
        initComponents();
        setInit();
        ImageIcon icon = new ImageIcon("Hinh/Exit.png");
        this.btnDangXuat.setIcon(icon);
        ImageIcon icon1 = new ImageIcon("Hinh/Stop.png");
        this.btnKetThuc.setIcon(icon1);
        ImageIcon icon2 = new ImageIcon("Hinh/To do list.png");
        this.btnLBT.setIcon(icon2);
        ImageIcon icon3 = new ImageIcon("Hinh/Properties.png");
        this.btnLBTap.setIcon(icon3);
        ImageIcon icon4 = new ImageIcon("Hinh/List.png");
        this.btnTaiLieu.setIcon(icon4);
        ImageIcon icon5 = new ImageIcon("Hinh/Comments.png");
        this.btnPhanHoi.setIcon(icon5);
        ImageIcon icon6 = new ImageIcon("Hinh/logoweb.png");
        this.lblAnh.setIcon(icon6);
        ImageIcon icon7 = new ImageIcon("Hinh/Best.png");
        this.btnXemTop.setIcon(icon7);
        
    }

    public void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ôn thi đại học môn toán");

    }
    void openLogin(){
        FormLogin_Start login = new FormLogin_Start();
        login.setVisible(true);
        this.dispose();
    }
    void openDMK(){ 
        new JFormDMK(this,true).setVisible(true);
    }
    void dangxuat(){
        
    }
    void ketThuc(){
        if(MsgBox.confirm(this, "Bạn muốn kết thúc?"));
        System.exit(0);
    }
    void QLCH(){
        new JForm_QLCauHoi(this, true).setVisible(true);
    }
    void QLTK(){
        new JFormNguoiDung(this, true).setVisible(true);
    }
    void LamBaiTap(){
        formlambaitap lbt = new formlambaitap();
        lbt.setVisible(true);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jToolBar1 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnLBT = new javax.swing.JButton();
        btnLBTap = new javax.swing.JButton();
        btnXemTop = new javax.swing.JButton();
        btnTaiLieu = new javax.swing.JButton();
        btnPhanHoi = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        mnuHethong = new javax.swing.JMenu();
        mniDMK = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniDangxuat = new javax.swing.JMenuItem();
        mniKetthuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniQLTK = new javax.swing.JMenuItem();
        mniQLCH = new javax.swing.JMenuItem();
        mniQLDT = new javax.swing.JMenuItem();
        mnuThongTin = new javax.swing.JMenu();
        mnuKiemTra = new javax.swing.JMenu();

        jMenuItem7.setText("jMenuItem7");

        jMenu8.setText("jMenu8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        btnKetThuc.setText("Kết Thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKetThuc);
        jToolBar1.add(jSeparator1);

        btnLBT.setText("Làm Bài Thi");
        btnLBT.setFocusable(false);
        btnLBT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLBT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLBTActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLBT);

        btnLBTap.setText("Làm Bài Tập");
        btnLBTap.setFocusable(false);
        btnLBTap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLBTap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLBTap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLBTapActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLBTap);

        btnXemTop.setText("Xem Top");
        btnXemTop.setFocusable(false);
        btnXemTop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXemTop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnXemTop);

        btnTaiLieu.setText("Tài Liệu");
        btnTaiLieu.setFocusable(false);
        btnTaiLieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTaiLieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnTaiLieu);

        btnPhanHoi.setText("Phản Hồi");
        btnPhanHoi.setFocusable(false);
        btnPhanHoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhanHoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPhanHoi);

        mnuHethong.setText("Hệ Thống");

        mniDMK.setText("Đổi Mật Khẩu");
        mniDMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDMKActionPerformed(evt);
            }
        });
        mnuHethong.add(mniDMK);
        mnuHethong.add(jSeparator2);

        mniDangxuat.setText("Đăng Xuất");
        mniDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangxuatActionPerformed(evt);
            }
        });
        mnuHethong.add(mniDangxuat);

        mniKetthuc.setText("Kết Thúc");
        mniKetthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetthucActionPerformed(evt);
            }
        });
        mnuHethong.add(mniKetthuc);

        jMenuBar3.add(mnuHethong);

        mnuQuanLy.setText("Quản Lý");

        mniQLTK.setText("Quản Lý Tài Khoản");
        mniQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLTKActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLTK);

        mniQLCH.setText("Quản Lý Câu Hỏi");
        mniQLCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQLCHActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniQLCH);

        mniQLDT.setText("Quản Lý Đề Thi");
        mnuQuanLy.add(mniQLDT);

        jMenuBar3.add(mnuQuanLy);

        mnuThongTin.setText("Thông Tin");
        jMenuBar3.add(mnuThongTin);

        mnuKiemTra.setText("Kiểm Tra");
        jMenuBar3.add(mnuKiemTra);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniDMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDMKActionPerformed
        // TODO add your handling code here:
        openDMK();
    }//GEN-LAST:event_mniDMKActionPerformed

    private void mniDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangxuatActionPerformed
        // TODO add your handling code here:
        openLogin();
    }//GEN-LAST:event_mniDangxuatActionPerformed

    private void btnLBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLBTActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        openLogin();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        // TODO add your handling code here:
        ketThuc();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void mniKetthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetthucActionPerformed
        // TODO add your handling code here:
        ketThuc();
    }//GEN-LAST:event_mniKetthucActionPerformed

    private void mniQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLTKActionPerformed
        // TODO add your handling code here:
        QLTK();
    }//GEN-LAST:event_mniQLTKActionPerformed

    private void mniQLCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQLCHActionPerformed
        // TODO add your handling code here:
        QLCH();
    }//GEN-LAST:event_mniQLCHActionPerformed

    private void btnLBTapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLBTapActionPerformed
        // TODO add your handling code here:
        LamBaiTap();
    }//GEN-LAST:event_btnLBTapActionPerformed

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
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnLBT;
    private javax.swing.JButton btnLBTap;
    private javax.swing.JButton btnPhanHoi;
    private javax.swing.JButton btnTaiLieu;
    private javax.swing.JButton btnXemTop;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JMenuItem mniDMK;
    private javax.swing.JMenuItem mniDangxuat;
    private javax.swing.JMenuItem mniKetthuc;
    private javax.swing.JMenuItem mniQLCH;
    private javax.swing.JMenuItem mniQLDT;
    private javax.swing.JMenuItem mniQLTK;
    private javax.swing.JMenu mnuHethong;
    private javax.swing.JMenu mnuKiemTra;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongTin;
    // End of variables declaration//GEN-END:variables
}
