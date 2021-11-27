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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class JForm_QLBaiThi extends javax.swing.JDialog {

    com.edusys.dao.BaiThiDAO BaiThiDao = new BaiThiDAO();
    ArrayList<BaiThi> listBT = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    public static boolean check = true;

    /**
     * Creates new form JForm_QLDeThi
     */
    public JForm_QLBaiThi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setInit();
    }

    public void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Quản lý bài thi");
        this.listBT = BaiThiDao.selectALL();
        model = (DefaultTableModel) tb_BaiThi.getModel();
        setCbbMucDo();
        setCbbID();
        fillTable();
    }

    public void fillTable() {
        this.listBT = BaiThiDao.selectALL();
        model.setRowCount(0);
        for (BaiThi x : listBT) {
            model.addRow(new Object[]{
                x.getID_BaiThi(), x.getDoKho(), x.getMaDe(), x.getID_CauHoi()
            });
        }
    }

    public void setCbbMucDo() {
        ArrayList<BaiThi> list = BaiThiDao.selectDoKho();
        cbb_Mucdo.removeAllItems();
        for (BaiThi x : list) {
            String mucdo = null;
            if (x.getDoKho() == 1) {
                mucdo = "Dễ";
            } else if (x.getDoKho() == 2) {
                mucdo = "Trung Bình";
            } else if (x.getDoKho() == 3) {
                mucdo = "Khó";
            }
            cbb_Mucdo.addItem(mucdo);
        }
    }

    public void setCbbID() {
        CauHoi ch = new CauHoi();
        CauHoiDAO cauHoiDao = new CauHoiDAO();
        cbb_IDCauHoi.removeAllItems();
        ArrayList<CauHoi> list = cauHoiDao.selectID();
        for (CauHoi x : list) {
            cbb_IDCauHoi.addItem(Integer.toString(x.getID_CauHoi()));
        }

    }

    public void shows() {
        int dem = tb_BaiThi.getSelectedRow();
        txt_Made.setText(model.getValueAt(dem, 1).toString());
        int item = Integer.valueOf(model.getValueAt(dem, 2).toString());
        if (item == 1) {
            cbb_Mucdo.setSelectedIndex(0);
        } else if (item == 2) {
            cbb_Mucdo.setSelectedIndex(1);
        } else if (item == 3) {
            cbb_Mucdo.setSelectedIndex(2);
        }
        cbb_IDCauHoi.setSelectedItem(model.getValueAt(dem, 3).toString());
    }

    public void lamNew() {
        txt_Made.setText("");
        cbb_Mucdo.setSelectedIndex(0);
        cbb_IDCauHoi.setSelectedIndex(0);
    }

    void checkMaBaiThi() {
        if (txt_Made.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập mã của bài thi");
            check = false;
            return;
        }
        String checkMaBai = "\\d*";
        if (!txt_Made.getText().matches(checkMaBai)) {
            JOptionPane.showMessageDialog(this, "Mã bài phải là số");
            check = false;
            return;
        }
    }

    public BaiThi getInsert() {
        BaiThi bt = new BaiThi();
        int dem = tb_BaiThi.getSelectedRow();
        int mucdo = cbb_Mucdo.getSelectedIndex() + 1;
        int idCauHoi = cbb_IDCauHoi.getSelectedIndex() + 1;
        bt.setMaDe(Integer.parseInt(txt_Made.getText()));
        bt.setDoKho(mucdo);
        bt.setID_CauHoi(idCauHoi);
        return bt;
    }

    public void them() {
        try {
            checkMaBaiThi();
            if (check == true) {
                BaiThi bt = getInsert();
                BaiThiDao.insert(bt);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                fillTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaiThi getFormUpdate() {
        int dem = tb_BaiThi.getSelectedRow();
        BaiThi bt = new BaiThi();
        int idBaiThi = Integer.valueOf(model.getValueAt(dem, 0).toString());
        bt.setID_BaiThi(idBaiThi);
        bt.setMaDe(Integer.parseInt(txt_Made.getText()));
        int doKhoBai = cbb_Mucdo.getSelectedIndex() + 1;
        bt.setDoKho(doKhoBai);
        int idCauHoi = cbb_IDCauHoi.getSelectedIndex() + 1;
        bt.setID_CauHoi(idCauHoi);
        return bt;
    }

    public void sua() {
        int dem = tb_BaiThi.getSelectedRow();
        try {
            if (dem == -1) {
                JOptionPane.showMessageDialog(this, "Chọn để sửa");
                return;
            }
            BaiThi bt = getFormUpdate();
            checkMaBaiThi();
            if (check == true) {

                BaiThiDao.update(bt);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                fillTable();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        int dem = tb_BaiThi.getSelectedRow();
        try {

            if (dem == -1) {
                JOptionPane.showMessageDialog(this, "Chọn để xóa");
                return;
            }
            BaiThi bt = getFormUpdate();
            int idBaiThi = Integer.valueOf(model.getValueAt(dem, 0).toString());
            BaiThiDao.delete(idBaiThi);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            fillTable();
            return;

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

        jMenu1 = new javax.swing.JMenu();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_Made = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbb_Mucdo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbb_IDCauHoi = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_BaiThi = new javax.swing.JTable();
        btn_First = new javax.swing.JButton();
        btn_Prev = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_New = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Quản lí bài thi");

        jLabel4.setText("Mã đề:");

        jLabel6.setText("Mức độ:");

        cbb_Mucdo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("ID_Câu hỏi:");

        cbb_IDCauHoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tb_BaiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_BaiThi", "MaDe", "Mucdo", "Id_CauHoi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_BaiThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_BaiThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_BaiThi);

        btn_First.setText("|<");
        btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FirstActionPerformed(evt);
            }
        });

        btn_Prev.setText("<<");
        btn_Prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrevActionPerformed(evt);
            }
        });

        btn_Next.setText(">>");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Last.setText(">|");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_New.setText("New");
        btn_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Made, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbb_Mucdo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_IDCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_First)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Prev)
                                .addGap(40, 40, 40)
                                .addComponent(btn_Next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Last)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addComponent(btn_Them)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Sua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Xoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_New)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_Made, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbb_Mucdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbb_IDCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_First)
                    .addComponent(btn_Prev)
                    .addComponent(btn_Next)
                    .addComponent(btn_Last)
                    .addComponent(btn_Them)
                    .addComponent(btn_Sua)
                    .addComponent(btn_Xoa)
                    .addComponent(btn_New))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_BaiThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_BaiThiMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            shows();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tb_BaiThiMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        them();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        sua();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewActionPerformed
        lamNew();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_NewActionPerformed

    private void btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FirstActionPerformed
       tb_BaiThi.setRowSelectionInterval(0, 0);
        shows();
    }//GEN-LAST:event_btn_FirstActionPerformed

    private void btn_PrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrevActionPerformed
         try {
            int dem = tb_BaiThi.getSelectedRow() - 1;
            if (dem < 0) {
                dem = listBT.size() - 1;
            }
            tb_BaiThi.setRowSelectionInterval(dem, dem);
            shows();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_PrevActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
         try {
            int dem = tb_BaiThi.getSelectedRow() + 1;
            if (dem >= listBT.size()) {
                dem = 0;
            }
            tb_BaiThi.setRowSelectionInterval(dem, dem);
            shows();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        tb_BaiThi.setRowSelectionInterval(listBT.size() - 1, listBT.size() - 1);
        shows();
    }//GEN-LAST:event_btn_LastActionPerformed

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
            java.util.logging.Logger.getLogger(JForm_QLBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JForm_QLBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JForm_QLBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JForm_QLBaiThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JForm_QLBaiThi dialog = new JForm_QLBaiThi(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_First;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_New;
    private javax.swing.JButton btn_Next;
    private javax.swing.JButton btn_Prev;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbb_IDCauHoi;
    private javax.swing.JComboBox<String> cbb_Mucdo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb_BaiThi;
    private javax.swing.JTextField txt_Made;
    // End of variables declaration//GEN-END:variables
}
