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
    int index;
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

            model.addRow(new Object[]{cauHoi.getID_CauHoi(),cauHoi.getCauHoi(),cauHoi.getTenBai()});
        }
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
      void edit() {

        int dem =table.getSelectedRow();
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        CauHoi ch = CauHoiDao.selectById(id);
        lbla.setText(ch.getDapAn1());     
        lblb.setText(ch.getDapAn2());
        lblc.setText(ch.getDapAn3());
        lbld.setText(ch.getDapAn4());
         
       lbldebai.setText(model.getValueAt(dem, 1).toString());
    }
      void first() {
        table.setRowSelectionInterval(0,0);
        edit();
    }

    void prev() {
       int dem =table.getSelectedRow()-1;
        if(dem<0){
            dem=listCH.size()-1;
        }
        table.setRowSelectionInterval(dem, dem);
        edit();
    }

    void next() {
        int dem =table.getSelectedRow()+1;
        if(dem>=listCH.size()){
            dem=0;
        }
        table.setRowSelectionInterval(dem, dem);
        edit();
    }

    void last() {
        table.setRowSelectionInterval(listCH.size()-1, listCH.size()-1);
        edit();
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
        lbla = new javax.swing.JLabel();
        lblb = new javax.swing.JLabel();
        lblc = new javax.swing.JLabel();
        lbld = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbldebai = new javax.swing.JLabel();

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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<|");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Câu Hỏi", "Câu Hỏi", "Tên Bài "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        lbla.setText("jLabel5");

        lblb.setText("jLabel6");

        lblc.setText("jLabel7");

        lbld.setText("jLabel8");

        lbldebai.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbldebai, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbldebai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jButton1)
                                .addGap(29, 29, 29)
                                .addComponent(btsubmit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rda)
                                    .addComponent(rdb)
                                    .addComponent(rdc)
                                    .addComponent(rdd))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbld)
                                    .addComponent(lblc)
                                    .addComponent(lblb)
                                    .addComponent(lbla)))
                            .addComponent(jLabel4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(btsubmit)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rda)
                    .addComponent(lbla))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdb)
                    .addComponent(lblb))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdc)
                    .addComponent(lblc))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdd)
                    .addComponent(lbld))
                .addGap(60, 60, 60))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         prev();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
//         int index =table.getSelectedRow();
//         edit();
         int dem =table.getSelectedRow();
         edit();
         // đổi ảnh
        
    
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        first();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        last();    
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbla;
    private javax.swing.JLabel lblb;
    private javax.swing.JLabel lblc;
    private javax.swing.JLabel lbld;
    private javax.swing.JLabel lbldebai;
    private javax.swing.JRadioButton rda;
    private javax.swing.JRadioButton rdb;
    private javax.swing.JRadioButton rdc;
    private javax.swing.JRadioButton rdd;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
