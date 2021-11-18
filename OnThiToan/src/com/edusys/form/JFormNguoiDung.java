/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class JFormNguoiDung extends javax.swing.JDialog {

    com.edusys.dao.NguoiDungDAO nguoiDungDAO;
    public static String getHoTen = null;
    public static String getGioiTinh = null;
    public static String getSDT = null;
    public static String getNgaySinh = null;
    public static String getEmail = null;

    public JFormNguoiDung(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setInit();
    }
    void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Thông tin người dùng");
        rd_nam.setSelected(true);
        tf_ngaysinh.setText("dd-MM-yyyy");
    }

    public void getForm() {
        getHoTen = tf_name.getText();
        if (rd_nam.isSelected()) {
            getGioiTinh = rd_nam.getText();
        } else if (rd_nu.isSelected()) {
            getGioiTinh = rd_nu.getText();
        }
        getSDT = tf_sdt.getText();
        getNgaySinh = tf_ngaysinh.getText();
        getEmail = tf_email.getText();
    }

    public void insert() {
        try {

            if (tf_name.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống họ tên!");
                return;
            } else if (tf_sdt.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống số điện thoại!");
                return;
            } else if (tf_ngaysinh.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống ngày sinh!");
                return;
            } else if (tf_email.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Không để trống email!");
                return;
            }
            String checkSo = "[0-9]{1,}";
            if (tf_name.getText().matches(checkSo)) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đúng định dạng họ tên!");
                return;
            }
            String checkHT = "[,'/;+-_.]{1,}";
            if (tf_name.getText().matches(checkHT)) {
                JOptionPane.showMessageDialog(rootPane, "Họ tên không chứa ký tự đặc biệt!");
                return;
            }
            if (!tf_sdt.getText().matches(checkSo)) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đúng định dạng số điện thoại!");
                return;
            } else if (tf_sdt.getText().length() < 10 || tf_sdt.getText().length() > 15) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đúng độ dài số điện thoại!");
                return;
            }
            try {
                SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
                Date dates = s.parse(tf_ngaysinh.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Lỗi định dạng ngày sinh!");
                return;
            }
            String checkEmail = "[A-Za-z0-9+-._]{1,}+@+[A-Za-z]{2,}+\\.+[A-Za-z]{2,}";
            if (!tf_email.getText().matches(checkEmail)) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đúng định dạng email!");
                return;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thông tin?", "Thông tin người dùng", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                getForm();
                dispose();
                System.out.println(getHoTen);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        tf_name.setText("");
        tf_ngaysinh.setText("");
        tf_sdt.setText("");
        tf_email.setText("");
        rd_nam.setSelected(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_ngaysinh = new javax.swing.JTextField();
        tf_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bt_next = new javax.swing.JButton();
        tf_sdt = new javax.swing.JTextField();
        bt_Cancel = new javax.swing.JButton();
        tf_name = new javax.swing.JTextField();
        rd_nam = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        rd_nu = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Giới tính:");

        jLabel3.setText("SĐT:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 255));
        jLabel4.setText("Nhập thông tin");

        jLabel5.setText("Ngày sinh:");

        tf_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_emailActionPerformed(evt);
            }
        });

        jLabel6.setText("Email:");

        bt_next.setText("Tiếp theo");
        bt_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nextActionPerformed(evt);
            }
        });

        bt_Cancel.setText("Cancel");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_nam);
        rd_nam.setText("Nam");

        jLabel1.setText("Họ tên:");

        buttonGroup1.add(rd_nu);
        rd_nu.setText("Nữ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_next)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_Cancel))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_name)
                                        .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rd_nam)
                                        .addGap(10, 10, 10)
                                        .addComponent(rd_nu)))))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rd_nam)
                    .addComponent(rd_nu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_next)
                    .addComponent(bt_Cancel))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_emailActionPerformed

    private void bt_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nextActionPerformed
        insert();
    }//GEN-LAST:event_bt_nextActionPerformed

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát không??", "Thoát", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_bt_CancelActionPerformed

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
            java.util.logging.Logger.getLogger(JFormNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFormNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFormNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFormNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFormNguoiDung dialog = new JFormNguoiDung(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bt_next;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_ngaysinh;
    private javax.swing.JTextField tf_sdt;
    // End of variables declaration//GEN-END:variables
}
