/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import com.edusys.dao.CauHoiDAO;
import com.edusys.utils.XImage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    int dem;
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
         table.setRowSelectionInterval(0,0);
        edit();  
    }
    void setExit(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btsubmit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rda = new javax.swing.JRadioButton();
        lbla = new javax.swing.JLabel();
        rdc = new javax.swing.JRadioButton();
        lblc = new javax.swing.JLabel();
        rdb = new javax.swing.JRadioButton();
        rdd = new javax.swing.JRadioButton();
        lbld = new javax.swing.JLabel();
        lblb = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbldebai = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Làm Bài Tập");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("Mời chọn tên bài tập");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 255));
        jLabel3.setText("Mời chọn mức độ bài tập");

        cbbmucdo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbmucdo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbmucdoMouseClicked(evt);
            }
        });
        cbbmucdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmucdoActionPerformed(evt);
            }
        });

        jButton1.setText("|>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
            table.getColumnModel().getColumn(0).setHeaderValue("ID Câu Hỏi");
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setHeaderValue("Tên Bài ");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 255));
        jLabel5.setText("Danh sách câu hỏi");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Hãy ấn nút bên dưới để chuyển câu hỏi");

        buttonGroup1.add(rda);
        rda.setText("A");

        lbla.setText("jLabel5");

        buttonGroup1.add(rdc);
        rdc.setText("C");

        lblc.setText("jLabel7");

        buttonGroup1.add(rdb);
        rdb.setText("B");
        rdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdd);
        rdd.setText("D");
        rdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rddActionPerformed(evt);
            }
        });

        lbld.setText("jLabel8");

        lblb.setText("jLabel6");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Chọn Đáp Án");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rdc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblc))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbla)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdd)
                            .addComponent(rdb))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblb, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbld, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbla)
                    .addComponent(rda)
                    .addComponent(rdb)
                    .addComponent(lblb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdc)
                    .addComponent(lblc)
                    .addComponent(rdd)
                    .addComponent(lbld))
                .addContainerGap())
        );

        jButton2.setText("<|");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbldebai.setColumns(20);
        lbldebai.setRows(5);
        jScrollPane2.setViewportView(lbldebai);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(19, 19, 19)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(46, 46, 46)
                                    .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btsubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbmucdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbbtenbai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jButton2)
                                .addComponent(jButton3)))
                        .addGap(34, 34, 34)
                        .addComponent(btsubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addGap(23, 23, 23))
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

    private void cbbmucdoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbmucdoMouseClicked
        // TODO add your handling code here:
         filltable();
        table.setRowSelectionInterval(0,0);
        edit();  
    }//GEN-LAST:event_cbbmucdoMouseClicked

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbla;
    private javax.swing.JLabel lblb;
    private javax.swing.JLabel lblc;
    private javax.swing.JLabel lbld;
    private javax.swing.JTextArea lbldebai;
    private javax.swing.JRadioButton rda;
    private javax.swing.JRadioButton rdb;
    private javax.swing.JRadioButton rdc;
    private javax.swing.JRadioButton rdd;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
