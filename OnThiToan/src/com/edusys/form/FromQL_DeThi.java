/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.BaiThi;
import PakagesClass.CauHoi;
import com.edusys.dao.BaiThiDAO;
import com.edusys.dao.CauHoiDAO;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class FromQL_DeThi extends javax.swing.JFrame {

    /**
     * Creates new form FromQL_DeThi
     */
    CauHoiDAO daocauhoi = new CauHoiDAO();
    BaiThiDAO dao = new BaiThiDAO();
    ArrayList<CauHoi> listCH = new ArrayList<>();
    ArrayList<BaiThi> listbt = new ArrayList<>();
    DefaultTableModel model;

    public FromQL_DeThi() {
        initComponents();
        setExit();
        setLocationRelativeTo(null);
        List<Integer> list = new ArrayList<>();
        // add 5 element in ArrayList
        filltable(listCH);
        tongcau();
        
    }

    public void random() {

        List<CauHoi> list = new ArrayList<>();
        List<CauHoi> listde = daocauhoi.dokho(1);
        List<CauHoi> listTB = daocauhoi.dokho(2);
        List<CauHoi> listKho = daocauhoi.dokho(3);
        System.out.println(listde.size());
        System.out.println(listTB.size());
        System.out.println(listKho.size());
        Random rd = new Random();
        for (int i = 0; i < Integer.parseInt(String.valueOf(cbbde.getSelectedItem())); i++) {
            int X = rd.nextInt(listde.size());
            list.add(new CauHoi(listde.get(X)));
            listde.remove(X);
        }
        for (int i = 0; i < Integer.parseInt(String.valueOf(cbbtb.getSelectedItem())); i++) {
            int X = rd.nextInt(listTB.size());
            list.add(new CauHoi(listTB.get(X)));
            listTB.remove(X);
        }
        for (int i = 0; i < Integer.parseInt(String.valueOf(cbbkho.getSelectedItem())); i++) {
            int X = rd.nextInt(listKho.size());
            list.add(new CauHoi(listKho.get(X)));
            listKho.remove(X);
        }
        filltable(list);

    }

    public void filltable(List<CauHoi> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (CauHoi ch : list) {
            model.addRow(new Object[]{
                ch.getID_CauHoi(), getdokho(ch.getDoKho()), ch.getDapAn1(), ch.getDapAn2(), ch.getDapAn3(), ch.getDapAn4(), ch.getDapAnDung()
            });
        }

    }

    public String getdokho(int dokho) {
        switch (dokho) {
            case 1:
                return "Dễ";
            case 2:
                return "Trung bình";
            default:
                return "Khó";
        }
    }

    public void tongcau() {
        lbltongcau.setText(Integer.parseInt(String.valueOf(cbbde.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbtb.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbkho.getSelectedItem())) + "");
    }

    BaiThi getfrom() {

        BaiThi bt = new BaiThi();
        bt.setMaDe(Integer.parseInt(String.valueOf(txtmade.getText())));
        bt.setDoKho(cbbmucdo.getSelectedIndex() + 1);

        return bt;
    }

    void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

    public void them() {
        try {
            if (txtmade.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn không được bỏ trống mã đề");

                return;
            }
            Integer.parseInt(txtmade.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập mã đề là số");
            return;
        }
        ArrayList<BaiThi> listbaithi = dao.selectALL();
        for (BaiThi bt : listbaithi) {
            String made = Integer.toString(bt.getDoKho());
            System.out.println(made);
            if (txtmade.getText().equalsIgnoreCase(made)) {
                JOptionPane.showMessageDialog(this, "Trùng mã đề rồi!");
                return;
            }
        }
        if (table == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa Random câu hỏi");
            return;
        }
        if (Integer.parseInt(String.valueOf(cbbde.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbtb.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbkho.getSelectedItem())) > 50) {
            JOptionPane.showMessageDialog(this, "Bạn chọn câu hỏi lớn hơn 50 rồi!");
            return;
        }
        if (Integer.parseInt(String.valueOf(cbbde.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbtb.getSelectedItem())) + Integer.parseInt(String.valueOf(cbbkho.getSelectedItem())) < 30) {
            JOptionPane.showMessageDialog(this, "Bạn chọn câu hỏi bé hơn 30 rồi!");
            return;
        }

        try {
            model = (DefaultTableModel) table.getModel();
            BaiThi bt = getfrom();
            try {
                listbt = dao.selectALL();
                for (int i = 0; i < listbt.size(); i++) {
                    bt.setID_CauHoi(Integer.parseInt(model.getValueAt(i, 0).toString()));
                    dao.insert(bt);
                    System.out.println(bt);
                }
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbbkho = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbbmucdo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbde = new javax.swing.JComboBox<>();
        cbbtb = new javax.swing.JComboBox<>();
        lbltongcau = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("QUẢN LÍ ĐỀ THI");

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Số câu Khó");

        cbbkho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30" }));
        cbbkho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbkhoMouseClicked(evt);
            }
        });
        cbbkho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbkhoActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Câu hỏi", "Mức độ", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D", "Đáp án đúng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel6.setText("Mức độ đề thi");

        cbbmucdo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dễ", "Trung Bình", "Khó" }));

        jLabel7.setText("Mã đề thi");

        jButton1.setText("Thêm đề thi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Random");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Tổng số câu");

        jLabel3.setText("Số câu dễ");

        cbbde.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30" }));
        cbbde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbdeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbbdeMousePressed(evt);
            }
        });
        cbbde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbdeActionPerformed(evt);
            }
        });

        cbbtb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30" }));
        cbbtb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbtbMouseClicked(evt);
            }
        });
        cbbtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtbActionPerformed(evt);
            }
        });

        lbltongcau.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltongcau.setForeground(new java.awt.Color(153, 0, 0));

        jLabel4.setText("Số câu TB");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton1)
                        .addGap(46, 46, 46)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(49, 49, 49)
                        .addComponent(lbltongcau, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel7)
                        .addGap(29, 29, 29)
                        .addComponent(txtmade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbkho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbtb, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbde, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jButton1)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtmade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbltongcau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbbkho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tạo Đề Thi", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGap(456, 456, 456)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtbActionPerformed
        // TODO add your handling code here:
        tongcau();
    }//GEN-LAST:event_cbbtbActionPerformed

    private void cbbtbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbtbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtbMouseClicked

    private void cbbdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbdeActionPerformed
        // TODO add your handling code here:
        tongcau();
    }//GEN-LAST:event_cbbdeActionPerformed

    private void cbbdeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbdeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbdeMousePressed

    private void cbbdeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbdeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbdeMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        random();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        them();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbkhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbkhoActionPerformed
        // TODO add your handling code here:
        tongcau();
    }//GEN-LAST:event_cbbkhoActionPerformed

    private void cbbkhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbkhoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbkhoMouseClicked

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
            java.util.logging.Logger.getLogger(FromQL_DeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromQL_DeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromQL_DeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromQL_DeThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FromQL_DeThi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbde;
    private javax.swing.JComboBox<String> cbbkho;
    private javax.swing.JComboBox<String> cbbmucdo;
    private javax.swing.JComboBox<String> cbbtb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbltongcau;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtmade;
    // End of variables declaration//GEN-END:variables
}
