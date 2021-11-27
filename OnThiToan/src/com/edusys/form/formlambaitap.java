/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import com.edusys.dao.CauHoiDAO;
import com.edusys.utils.XImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author DELL
 */
public class formlambaitap extends javax.swing.JFrame {

    com.edusys.dao.CauHoiDAO CauHoiDao = new CauHoiDAO();
    com.edusys.utils.XImage xImage = new XImage();
    ArrayList<CauHoi> listCH ;
    DefaultTableModel model = new DefaultTableModel();

    public formlambaitap() {
        initComponents();
        innit();

    }

    public void innit() {
        setLocationRelativeTo(null);
        setResizable(false);
        cbbtenbai();
        cbbdokho();
        listCH =  new ArrayList<>();
        filltable();
    }

    public void cbbtenbai() {
        ArrayList<CauHoi> list = CauHoiDao.selecttenbai();
        cbbtenbai.removeAllItems();
        for (CauHoi cauHoi : list) {
            String item = cauHoi.getTenBai();
            cbbtenbai.addItem(item);
        }
    }

    public void cbbdokho() {
        ArrayList<CauHoi> list = CauHoiDao.selectDoKho();
        cbbmucdo.removeAllItems();
        for (CauHoi x : list) {
            String doKho = null;
            if (x.getDoKho() == 1) {
                doKho = "Dễ";
            } else if (x.getDoKho() == 2) {
                doKho = "Trung bình";
            } else if (x.getDoKho() == 3) {
                doKho = "Khó";
            }
            cbbmucdo.addItem(doKho);
        }
    }

    public void filltable() {
        String item = (String) cbbtenbai.getSelectedItem();
        int it = cbbmucdo.getSelectedIndex() + 1;
        listCH = CauHoiDao.selectfill(item, it);
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (CauHoi cauHoi : listCH) {

            model.addRow(new Object[]{
                cauHoi.getTenBai(), cauHoi.getCauHoi()
            });
        }
    }
    public void next(){
        int dem =table.getSelectedRow()+1;
        if(dem>=listCH.size()){
            dem=0;
        }
        table.setRowSelectionInterval(dem, 0);
        ImageIcon img = new ImageIcon(model.getValueAt(dem, 0).toString());
        lbldebai.setIcon(img);
    }
    public void dapandung(){
        listCH = CauHoiDao.selectALL();
        String tenbai=cbbtenbai.getSelectedItem().toString();
        String mucdo = cbbmucdo.getSelectedItem().toString();
        int muc ;
        if(mucdo.equals("Dễ")){
            muc=1;
        }else if (mucdo.equals("Khó")){
            muc =3;
        }else{
            muc = 2;
        }
      int a = CauHoiDao.idcauhoi(tenbai, muc);
        System.out.println(a);
      String dapan = CauHoiDao.dapan(a);
        String chon = null;
        if(rda.isSelected()){
            chon = "A";
        }else if(rdb.isSelected()){
            chon = "B";
        }else if(rdc.isSelected()){
            chon = "C";
        }else if(rdd.isSelected()){
            chon ="D";
        }else{
            MsgBox.alert(this,"bạn chưa chọn đáp án ");
            return;
        }
        if(dapan.equalsIgnoreCase(chon)){
            MsgBox.alert(this,"Bạn đã chọn đáp án đúng ");
        }else{
            MsgBox.alert(this,"Bạn đã chọn sai ");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbtenbai = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbbmucdo = new javax.swing.JComboBox<>();
        lbldebai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdb = new javax.swing.JRadioButton();
        rda = new javax.swing.JRadioButton();
        rdc = new javax.swing.JRadioButton();
        rdd = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btsubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Làm Bài Tập");

        jLabel2.setText("Tên Bài");

        cbbtenbai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbtenbai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbtenbaiMouseClicked(evt);
            }
        });
        cbbtenbai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtenbaiActionPerformed(evt);
            }
        });

        jLabel3.setText("Mức độ");

        cbbmucdo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbmucdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmucdoActionPerformed(evt);
            }
        });

        jLabel4.setText("Đáp án");

        buttonGroup1.add(rdb);
        rdb.setText("B");
        rdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActionPerformed(evt);
            }
        });

        buttonGroup1.add(rda);
        rda.setText("A");

        buttonGroup1.add(rdc);
        rdc.setText("C");

        buttonGroup1.add(rdd);
        rdd.setText("D");
        rdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rddActionPerformed(evt);
            }
        });

        jButton1.setText("|>");

        jButton2.setText("<|");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Câu Hỏi", "Tên Bài "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton3.setText("<<");

        jButton4.setText(">>");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btsubmit.setText("Submit");
        btsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jButton2))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addComponent(jButton3))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(200, 200, 200)
                                                .addComponent(jButton4)))
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(rda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rdb)
                                        .addGap(83, 83, 83)
                                        .addComponent(rdc)
                                        .addGap(44, 44, 44)
                                        .addComponent(rdd)))
                                .addGap(29, 29, 29)
                                .addComponent(btsubmit)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbldebai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(246, 246, 246))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdb)
                    .addComponent(rda)
                    .addComponent(rdc)
                    .addComponent(rdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(btsubmit))
                .addGap(32, 32, 32)
                .addComponent(lbldebai, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rddActionPerformed
      
    }//GEN-LAST:event_rddActionPerformed

    private void rdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbActionPerformed

    private void cbbtenbaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtenbaiActionPerformed
        filltable();
    }//GEN-LAST:event_cbbtenbaiActionPerformed

    private void cbbtenbaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbtenbaiMouseClicked
        filltable();
    }//GEN-LAST:event_cbbtenbaiMouseClicked

    private void cbbmucdoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmucdoActionPerformed
        filltable();
    }//GEN-LAST:event_cbbmucdoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        next();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsubmitActionPerformed
        // TODO add your handling code here:
        dapandung();
    }//GEN-LAST:event_btsubmitActionPerformed

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
            java.util.logging.Logger.getLogger(formlambaitap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formlambaitap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formlambaitap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formlambaitap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formlambaitap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsubmit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbmucdo;
    private javax.swing.JComboBox<String> cbbtenbai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldebai;
    private javax.swing.JRadioButton rda;
    private javax.swing.JRadioButton rdb;
    private javax.swing.JRadioButton rdc;
    private javax.swing.JRadioButton rdd;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
