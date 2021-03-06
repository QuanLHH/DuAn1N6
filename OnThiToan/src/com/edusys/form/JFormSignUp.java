package com.edusys.form;

import PakagesClass.NguoiDung;
import PakagesClass.TaiKhoan;
import com.edusys.dao.NguoiDungDAO;
import com.edusys.dao.TaiKhoanDAO;
import com.edusys.utils.XDate;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JFormSignUp extends javax.swing.JDialog {

    com.edusys.dao.TaiKhoanDAO taiKhoanDAO;
    com.edusys.dao.NguoiDungDAO nguoiDungDAO;
    public static int ID_MaND = 0;

    public JFormSignUp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setInit();

    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Đăng ký tài khoản");
        taiKhoanDAO = new TaiKhoanDAO();
        nguoiDungDAO = new NguoiDungDAO();
        tf_pass.setEchoChar('*');
        tf_pass2.setEchoChar('*');
        ImageIcon img = new ImageIcon("hinh/eye.png");
        rd_pass.setIcon(img);
        ImageIcon img2 = new ImageIcon("hinh/eye.png");
        rd_pass2.setIcon(img2);
    }

    public NguoiDung setNguoiDung() {

        NguoiDung nd = new NguoiDung();

        nd.setHoTen(JFormNguoiDung.getHoTen);
        nd.setGioiTinh(JFormNguoiDung.getGioiTinh);
        nd.setSDT(JFormNguoiDung.getSDT);
        nd.setNgaySinh(XDate.toDate(JFormNguoiDung.getNgaySinh, "dd-MM-yyyy"));
        nd.setEmail(JFormNguoiDung.getEmail);

        nd.setHoTen(JFormNguoiDung.getHoTen);
        nd.setGioiTinh(JFormNguoiDung.getGioiTinh);
        nd.setSDT(JFormNguoiDung.getSDT);
        nd.setNgaySinh(XDate.toDate(JFormNguoiDung.getNgaySinh, "dd-MM-yyyy"));
        nd.setEmail(JFormNguoiDung.getEmail);

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
                if (tf_name.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Không để trống tài khoản!");
                    return;
                } else if (tf_pass.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu!");
                    return;
                } else if (tf_pass2.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Không để trống mật khẩu c2!");
                    return;
                }
                String checkTK = "[,'/;+-_.]{1,}";
                if (tf_name.getText().matches(checkTK)) {
                    JOptionPane.showMessageDialog(rootPane, "Tài khoản không chứa ký tự đặc biệt!");
                    return;
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
                refresh();

                dispose();

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
        jLabel4 = new javax.swing.JLabel();
        bt_dangky = new javax.swing.JButton();
        bt_Cancel = new javax.swing.JButton();
        tf_pass = new javax.swing.JPasswordField();
        tf_pass2 = new javax.swing.JPasswordField();
        rd_pass = new javax.swing.JCheckBox();
        rd_pass2 = new javax.swing.JCheckBox();

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

        rd_pass.setText("jCheckBox1");
        rd_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_passActionPerformed(evt);
            }
        });

        rd_pass2.setText("jCheckBox1");
        rd_pass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_pass2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_pass, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(tf_pass2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rd_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rd_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(bt_dangky)
                        .addGap(37, 37, 37)
                        .addComponent(bt_Cancel)))
                .addContainerGap(86, Short.MAX_VALUE))
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
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_pass))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_pass2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
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

    private void rd_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_passActionPerformed
 
        if(rd_pass.isSelected()){
            tf_pass.setEchoChar((char)0);
            ImageIcon img = new ImageIcon("hinh/hide.png");
            rd_pass.setIcon(img);
        }else{
            tf_pass.setEchoChar('*');
            ImageIcon img = new ImageIcon("hinh/eye.png");
            rd_pass.setIcon(img);
        }
        
    }//GEN-LAST:event_rd_passActionPerformed

    private void rd_pass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_pass2ActionPerformed
        if(rd_pass2.isSelected()){
            tf_pass2.setEchoChar((char)0);
            ImageIcon img = new ImageIcon("hinh/hide.png");
            rd_pass2.setIcon(img);
        }else{
            tf_pass2.setEchoChar('*');
            ImageIcon img = new ImageIcon("hinh/eye.png");
            rd_pass2.setIcon(img);
        }
    }//GEN-LAST:event_rd_pass2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFormSignUp dialog = new JFormSignUp(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox rd_pass;
    private javax.swing.JCheckBox rd_pass2;
    private javax.swing.JTextField tf_name;
    private javax.swing.JPasswordField tf_pass;
    private javax.swing.JPasswordField tf_pass2;
    // End of variables declaration//GEN-END:variables
}
