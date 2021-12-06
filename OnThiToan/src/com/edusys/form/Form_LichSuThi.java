/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.BaiThi;
import PakagesClass.BaiThiChiTiet;
import PakagesClass.ThongTinBaiThi;
import com.edusys.dao.BaiThiDAO;
import com.edusys.dao.DeThiDAO;
import com.edusys.dao.ThongTinBaiThiDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import utils.Auth2;

/**
 *
 * @author taola
 */
public class Form_LichSuThi extends javax.swing.JFrame {

    DefaultTableModel modelDS;
    DefaultTableModel modelCT;
    ArrayList<BaiThiChiTiet> listBTCT = new ArrayList<>();
    ArrayList<ThongTinBaiThi> listTTBT = new ArrayList<>();
    com.edusys.dao.DeThiDAO deThiDao = new DeThiDAO();
    com.edusys.dao.BaiThiDAO baiThiDao = new BaiThiDAO();
    com.edusys.dao.ThongTinBaiThiDAO thongTinBTDao = new ThongTinBaiThiDAO();
    Auth2 auth = new Auth2();
    String getMaDe = null;
    int getDoKho = 0;
    int getID_NguoiDung = 0;
    public static String getCauHoi=null;
    public static String getDapAnChon=null;
    public static String getDapAnDung=null;
    public Form_LichSuThi() {
        initComponents();
        setInit();
        setExit();
        runs();
    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Lịch sử thi");
        this.modelDS = (DefaultTableModel) tb_DanhSachBaiThi.getModel();
        this.modelCT = (DefaultTableModel) tb_thongTinBaiThi.getModel();
        this.getMaDe = Form_ChonBaiThi.getMaDe;
        this.getDoKho = Form_ChonBaiThi.getDoKho;
        this.getID_NguoiDung = auth.use.getID_MaND();
        this.listTTBT = thongTinBTDao.selectAllTTBaiThi(getID_NguoiDung);
        this.listBTCT = deThiDao.selectALLBaiThiCT();
        setCbbMaDe();
        setCbbDoKho();
        fillTableBTChiTiet();
    }

    public void setCbbMaDe() {
        ArrayList<BaiThi> list = baiThiDao.selectMaDe();
        cbb_MaDe.removeAllItems();
        for (BaiThi x : list) {
            String item = Integer.toString(x.getMaDe());
            cbb_MaDe.addItem(item);
        }
        fillTableBTChiTietByID();
    }

    public void setCbbDoKho() {
        cbb_DoKho.removeAllItems();
        cbb_DoKho.addItem("Dễ");
        cbb_DoKho.addItem("Trung bình");
        cbb_DoKho.addItem("Khó");
        
    }


    public void fillTableBTChiTietByID(){
        getMaDe = (String) cbb_MaDe.getSelectedItem();
        getDoKho = cbb_DoKho.getSelectedIndex()+1;
        this.listBTCT = deThiDao.selectBaiThiCTByID(getMaDe, getDoKho, getID_NguoiDung);
        this.modelDS.setRowCount(0);
        for (BaiThiChiTiet x : listBTCT) {
            modelDS.addRow(new Object[]{x.getID_BaiThiCT(), x.getSoCauDung(), x.getSoCauSai(), x.getDiem(), x.getNgayThi()});
        }
    }
    public void fillTableBTChiTiet() {
        this.modelDS.setRowCount(0);
        for (BaiThiChiTiet x : listBTCT) {
            modelDS.addRow(new Object[]{x.getID_BaiThiCT(), x.getSoCauDung(), x.getSoCauSai(), x.getDiem(), x.getNgayThi()});
        }
    }

    public void fillTableTTBaiThi() {

        int dem = tb_DanhSachBaiThi.getSelectedRow();
        int id_BTCT = Integer.valueOf(modelDS.getValueAt(dem, 0).toString());
        ArrayList<Object[]> list = thongTinBTDao.selectSQLThongTinThi(id_BTCT,getMaDe, getDoKho, getID_NguoiDung);
        modelCT.setRowCount(0);
        for(Object[] x:list){
            modelCT.addRow(x);
        }
    }

    public void runs() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    String lbText = lb_text.getText();
                    lbText = lbText.charAt(lbText.length() - 1) + lbText.substring(0, lbText.length() - 1);
                    lb_text.setText(lbText);
                    try {
                        sleep(70);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tp_pane = new javax.swing.JTabbedPane();
        pn_DanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_DanhSachBaiThi = new javax.swing.JTable();
        cbb_DoKho = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbb_MaDe = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_thongTinBaiThi = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lb_text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_DanhSachBaiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_BaiThiCT", "Số câu đúng", "Số câu sai", "Điểm", "Ngày thi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_DanhSachBaiThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DanhSachBaiThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_DanhSachBaiThi);

        cbb_DoKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_DoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_DoKhoActionPerformed(evt);
            }
        });

        jLabel2.setText("Độ khó:");

        cbb_MaDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_MaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_MaDeActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã đề:");

        javax.swing.GroupLayout pn_DanhSachLayout = new javax.swing.GroupLayout(pn_DanhSach);
        pn_DanhSach.setLayout(pn_DanhSachLayout);
        pn_DanhSachLayout.setHorizontalGroup(
            pn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_DanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DanhSachLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_MaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_DoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        pn_DanhSachLayout.setVerticalGroup(
            pn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_DanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_MaDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_DoKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tp_pane.addTab("Danh sách", pn_DanhSach);

        tb_thongTinBaiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Câu hỏi", "Đáp án chọn", "Đáp án đúng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_thongTinBaiThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_thongTinBaiThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_thongTinBaiThi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        tp_pane.addTab("Chi tiết", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        lb_text.setFont(new java.awt.Font("Sitka Small", 0, 27)); // NOI18N
        lb_text.setForeground(new java.awt.Color(51, 51, 255));
        lb_text.setText("Xem lịch sử thi                                                  ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(lb_text, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_text)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tp_pane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tp_pane))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_DoKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_DoKhoActionPerformed
        fillTableBTChiTietByID();
    }//GEN-LAST:event_cbb_DoKhoActionPerformed

    private void cbb_MaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_MaDeActionPerformed
        setCbbDoKho();
        fillTableBTChiTietByID();
    }//GEN-LAST:event_cbb_MaDeActionPerformed

    private void tb_DanhSachBaiThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DanhSachBaiThiMouseClicked
        if(evt.getClickCount()==2&&!evt.isConsumed()){
            tp_pane.setSelectedIndex(1);
            fillTableTTBaiThi();
        }
    }//GEN-LAST:event_tb_DanhSachBaiThiMouseClicked

    private void tb_thongTinBaiThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_thongTinBaiThiMouseClicked
        int dem =tb_thongTinBaiThi.getSelectedRow();
        if(evt.getClickCount()==2&&!evt.isConsumed()){
            getCauHoi = modelCT.getValueAt(dem, 1).toString();
            getDapAnChon = modelCT.getValueAt(dem, 2).toString();
            getDapAnDung = modelCT.getValueAt(dem, 3).toString();
            new JForm_ChiTietLichSu(this, true).show();
            
        }
    }//GEN-LAST:event_tb_thongTinBaiThiMouseClicked

    public void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

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
            java.util.logging.Logger.getLogger(Form_LichSuThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_LichSuThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_LichSuThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_LichSuThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_LichSuThi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb_DoKho;
    private javax.swing.JComboBox<String> cbb_MaDe;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_text;
    private javax.swing.JPanel pn_DanhSach;
    private javax.swing.JTable tb_DanhSachBaiThi;
    private javax.swing.JTable tb_thongTinBaiThi;
    private javax.swing.JTabbedPane tp_pane;
    // End of variables declaration//GEN-END:variables
}
