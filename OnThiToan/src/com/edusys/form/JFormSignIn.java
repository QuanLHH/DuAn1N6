package com.edusys.form;

import PakagesClass.NguoiDung;
import PakagesClass.TaiKhoan;
import com.edusys.dao.NguoiDungDAO;
import com.edusys.dao.TaiKhoanDAO;
import com.edusys.utils.XDate;
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
import javax.swing.JOptionPane;

=======
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java

/**
 *
 * @author taola
 */
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
public class FormSignIn extends javax.swing.JFrame {
=======
public class JFormSignIn extends javax.swing.JDialog {
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java

    com.edusys.dao.TaiKhoanDAO taiKhoanDAO;
    com.edusys.dao.NguoiDungDAO nguoiDungDAO;
    public static int ID_MaND = 0;

<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
    public FormSignIn() {
=======
    public JFormSignIn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java
        initComponents();
        setInit();
        
    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Đăng ký tài khoản");
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
        loandForm();
        taiKhoanDAO = new TaiKhoanDAO();
        nguoiDungDAO = new NguoiDungDAO();
    }

    void loandForm() {
        new JFormNDSignIn(this, true).setVisible(true);
    }
=======
        taiKhoanDAO = new TaiKhoanDAO();
        nguoiDungDAO = new NguoiDungDAO();
    }
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java

    public NguoiDung setNguoiDung() {

        NguoiDung nd = new NguoiDung();
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
        nd.setHoTen(JFormNDSignIn.getHoTen);
        nd.setGioiTinh(JFormNDSignIn.getGioiTinh);
        nd.setSDT(JFormNDSignIn.getSDT);
        nd.setNgaySinh(XDate.toDate(JFormNDSignIn.getNgaySinh, "dd-MM-yyyy"));
        nd.setEmail(JFormNDSignIn.getEmail);
=======
        nd.setHoTen(JFormNguoiDung.getHoTen);
        nd.setGioiTinh(JFormNguoiDung.getGioiTinh);
        nd.setSDT(JFormNguoiDung.getSDT);
        nd.setNgaySinh(XDate.toDate(JFormNguoiDung.getNgaySinh, "dd-MM-yyyy"));
        nd.setEmail(JFormNguoiDung.getEmail);
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java
        return nd;
    }

    public TaiKhoan getForm() {
        NguoiDung nd = nguoiDungDAO.getMaxID();
        ID_MaND = nd.getID_MaND();
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(tf_name.getText());
        tk.setMatKhau(tf_pass.getText());
        tk.setMKCap2(tf_pass2.getText());
        tk.setVaiTro(false);
        tk.setID_MaND(ID_MaND);
        System.out.println(ID_MaND);
        return tk;
    }

    public void insert() {
        try {
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
            
            if(tf_name.getText().equals("")){
=======

            if (tf_name.getText().equals("")) {
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java
                JOptionPane.showMessageDialog(rootPane, "Không để trống tài khoản!");
                return ;
            }else if(tf_pass.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu!");
                return ;
            }else if(tf_pass2.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu c2!");
                return ;
            }
            String checkTK="[,'/;+-_.]{1,}";
            if(tf_name.getText().matches(checkTK)){
                JOptionPane.showMessageDialog(rootPane, "Tài khoản không chứa ký tự đặc biệt!");
                return ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "Xác nhận đăng ký?", "Đăng ký tài khoản", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                NguoiDung nd = setNguoiDung();
                nguoiDungDAO.insert(nd);
                TaiKhoan tk = getForm();
                taiKhoanDAO.insert(tk);
                JOptionPane.showMessageDialog(rootPane, "Đăng ký thành công!");
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
                refresh();
=======
                dispose();

>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java
            }
        } catch (Exception e) {

        }
    }

    public void refresh() {
        tf_name.setText("");
        tf_pass.setText("");
        tf_pass2.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tf_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_pass = new javax.swing.JTextField();
        tf_pass2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bt_dangky = new javax.swing.JButton();
        bt_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tên tài khoản:");

        jLabel2.setText("Mật khẩu:");

        jLabel3.setText("Mật khẩu cấp 2:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 255));
        jLabel4.setText("Đăng ký tài khoản");

        bt_dangky.setText("Đăng ký");
        bt_dangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dangkyActionPerformed(evt);
            }
        });

        bt_Cancel.setText("Cancel");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(bt_dangky)
                .addGap(37, 37, 37)
                .addComponent(bt_Cancel)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_name, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(tf_pass)
                    .addComponent(tf_pass2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_dangky)
                    .addComponent(bt_Cancel))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_dangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dangkyActionPerformed
        insert();

    }//GEN-LAST:event_bt_dangkyActionPerformed

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát không?", "Thoát", JOptionPane.YES_NO_OPTION);

        if (i == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_bt_CancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
<<<<<<< Updated upstream:OnThiToan/src/com/edusys/form/FormSignIn.java
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
            java.util.logging.Logger.getLogger(FormSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
=======
        /* Create and display the dialog */
>>>>>>> Stashed changes:OnThiToan/src/com/edusys/form/JFormSignIn.java
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFormSignIn dialog = new JFormSignIn(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bt_dangky;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_pass;
    private javax.swing.JTextField tf_pass2;
    // End of variables declaration//GEN-END:variables
}
