/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import PakagesClass.DapAn;
import com.edusys.dao.CauHoiDAO;
import com.edusys.dao.DapAnDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author taola
 */
public class JForm_QLCauHoi extends javax.swing.JDialog {

    com.edusys.dao.CauHoiDAO CauHoiDao;
    com.edusys.dao.DapAnDAO DapAnDao;
    ArrayList<CauHoi> listCH = new ArrayList<>();
    public int id_cauhoi = 0;

    public JForm_QLCauHoi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setInit();
    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Quản lý câu hỏi");
        runs();
        this.CauHoiDao = new CauHoiDAO();
        this.DapAnDao = new DapAnDAO();
        this.listCH = CauHoiDao.selectALL();
        
        setCBB();
    }

    public CauHoi getFormCauHoi() {
        CauHoi ch = new CauHoi();
        int id = Integer.valueOf(tf_id.getText());
        ch.setID_BT(id);
        ch.setCauHoi(tf_true.getText());
        ch.setDoKho(cbb_mucDo.getSelectedIndex()+1);
        return ch;
    }

    public DapAn getFormDapAn() {
        DapAn da = new DapAn();
        CauHoi ch = CauHoiDao.selectIDNew();
        id_cauhoi = ch.getID_CauHoi();
        System.out.println("ID_CauHoi: "+ id_cauhoi);
        da.setID_CauHoi(id_cauhoi);
        da.setDapAnDung(tf_true.getText());
        da.setDapAnSai1(tf_false1.getText());
        da.setDapAnSai2(tf_false2.getText());
        da.setDapAnSai3(tf_false3.getText());
        return da;
    }
    public void insert(){
        try{
            CauHoi ch = getFormCauHoi();
            CauHoiDao.insert(ch);
            DapAn da = getFormDapAn();
            DapAnDao.insert(da);
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setCBB() {
        cbb_mucDo.removeAllItems();
        for (CauHoi x : listCH) {
            String doKho = null;
            if (x.getDoKho() == 1) {
                doKho = "Dễ";
            } else if (x.getDoKho() == 2) {
                doKho = "Trung bình";
            } else if (x.getDoKho() == 3) {
                doKho = "Khó";
            }
            cbb_mucDo.addItem(doKho);
        }
    }

    public void runs() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    String lbText = lb_ql.getText();
                    lbText = lbText.charAt(lbText.length() - 1) + lbText.substring(0, lbText.length() - 1);
                    lb_ql.setText(lbText);
                    try {
                        sleep(700);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    public void refresh() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_total = new javax.swing.JPanel();
        lb_ql = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbb_mucDo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tp_cauHoi = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        tf_true = new javax.swing.JTextField();
        tf_false1 = new javax.swing.JTextField();
        tf_false3 = new javax.swing.JTextField();
        tf_false2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_cauHoi = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        tf_id = new javax.swing.JTextField();
        True = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lb_ql.setFont(new java.awt.Font("Sitka Text", 0, 20)); // NOI18N
        lb_ql.setForeground(new java.awt.Color(51, 51, 255));
        lb_ql.setText("Quản lý câu hỏi");

        jLabel2.setText("Mức độ");

        cbb_mucDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_mucDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_mucDoActionPerformed(evt);
            }
        });

        jLabel1.setText("Câu hỏi");

        tp_cauHoi.setMaximumSize(new java.awt.Dimension(6, 20));
        jScrollPane1.setViewportView(tp_cauHoi);

        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Đáp án");

        jButton1.setText("Sửa");

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tb_cauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Độ khó", "Đáp án"
            }
        ));
        jScrollPane2.setViewportView(tb_cauHoi);

        jLabel5.setText("ID:");

        True.setText("True");

        jLabel7.setText("False");

        jLabel8.setText("False");

        jLabel9.setText("False");

        javax.swing.GroupLayout pn_totalLayout = new javax.swing.GroupLayout(pn_total);
        pn_total.setLayout(pn_totalLayout);
        pn_totalLayout.setHorizontalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(135, 135, 135))
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_totalLayout.createSequentialGroup()
                            .addComponent(True, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_true, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_totalLayout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_false1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_totalLayout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_false2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_totalLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tf_false3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(pn_totalLayout.createSequentialGroup()
                            .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addGap(19, 19, 19)
                            .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbb_mucDo, 0, 94, Short.MAX_VALUE)
                                .addComponent(tf_id)))
                        .addComponent(jScrollPane1))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(lb_ql)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_totalLayout.setVerticalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_ql)
                .addGap(11, 11, 11)
                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_totalLayout.createSequentialGroup()
                        .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_true, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(True))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_totalLayout.createSequentialGroup()
                                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_false1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tf_false2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_false3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insert();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbb_mucDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_mucDoActionPerformed
        int i = cbb_mucDo.getSelectedIndex() + 1;
        System.out.println(i);
    }//GEN-LAST:event_cbb_mucDoActionPerformed

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
            java.util.logging.Logger.getLogger(JForm_QLCauHoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JForm_QLCauHoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JForm_QLCauHoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JForm_QLCauHoi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JForm_QLCauHoi dialog = new JForm_QLCauHoi(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel True;
    private javax.swing.JComboBox<String> cbb_mucDo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_ql;
    private javax.swing.JPanel pn_total;
    private javax.swing.JTable tb_cauHoi;
    private javax.swing.JTextField tf_false1;
    private javax.swing.JTextField tf_false2;
    private javax.swing.JTextField tf_false3;
    private javax.swing.JTextField tf_id;
    private javax.swing.JTextField tf_true;
    private javax.swing.JTextPane tp_cauHoi;
    // End of variables declaration//GEN-END:variables
}
