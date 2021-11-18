/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;


import PakagesClass.QuenMK;
import com.edusys.dao.QuenMKDAO;
import java.awt.Color;
import java.util.Random;
import javax.swing.border.LineBorder;
import utils.mesageDiaLogHelper;



import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.LineBorder;
import utils.Validate;

public class FormQuenMK extends javax.swing.JFrame {

   int randomcode;
    public FormQuenMK() {
       
       initComponents();
       init();
        setExit();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        code = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtVerify = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mkmoi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mklai = new javax.swing.JTextField();
        btsend = new javax.swing.JButton();
        btverycode = new javax.swing.JButton();
        btreset = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Quên Mật Khẩu");

        jLabel2.setText("Enter user");

        jLabel3.setText("Enter email");

        jLabel4.setText("Very code");

        jLabel5.setText("Mật khẩu mới");

        jLabel6.setText("Nhập lại mật khẩu");

        btsend.setText("Send");
        btsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsendActionPerformed(evt);
            }
        });

        btverycode.setText("very code");
        btverycode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverycodeActionPerformed(evt);
            }
        });

        btreset.setText("reset pass");
        btreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btresetActionPerformed(evt);
            }
        });

        jButton4.setText("back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtTime.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(mklai, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(mkmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(200, 200, 200)
                                                .addComponent(jLabel1))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel2))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel3))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(email))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTime))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel5))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btreset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btsend)
                                .addGap(132, 132, 132)
                                .addComponent(btverycode)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mkmoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mklai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsend)
                    .addComponent(btverycode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btreset)
                    .addComponent(jButton4))
                .addContainerGap())
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
      //  new eduSysJDialogLogIn(null, true).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsendActionPerformed
        // TODO add your handling code here:
        sendEmail();
    }//GEN-LAST:event_btsendActionPerformed

    private void btverycodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverycodeActionPerformed
        // TODO add your handling code here:
        VerifyCode();
    }//GEN-LAST:event_btverycodeActionPerformed

    private void btresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btresetActionPerformed
        // TODO add your handling code here:
        resetPass();
    }//GEN-LAST:event_btresetActionPerformed

  
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
            java.util.logging.Logger.getLogger(FormQuenMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuenMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuenMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuenMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the FormQuenMK */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuenMK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btreset;
    private javax.swing.JButton btsend;
    private javax.swing.JButton btverycode;
    private javax.swing.JTextField code;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField mklai;
    private javax.swing.JTextField mkmoi;
    private javax.swing.JLabel txtTime;
    private javax.swing.JTextField txtVerify;
    // End of variables declaration//GEN-END:variables

   
        public void init() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        btnStatus();

    }

    private void btnStatus() {
        mkmoi.setEnabled(false);
        mklai.setEnabled(false);
        txtVerify.setEnabled(false);

        btverycode.setEnabled(false);
        btreset.setEnabled(false);
    }
     QuenMKDAO dao = new QuenMKDAO();

   
    public void sendEmail() {
        try {
            QuenMK nd = dao.selectById(code.getText());
            if(Validate.checkEmety(this, code,"Điền tài khoản ","Error")==false){
                return;
            }else if (nd!=null){
                 mesageDiaLogHelper.showErrorDialog(this, "Tài khoản này không tồn tại!!!", "Error!!");
                code.setBorder(new LineBorder(Color.RED));
                return;
            }
            else if (Validate.checkEmety(this,email,"Điền emaill","Eror")==false) {
                email.setBorder(new LineBorder(Color.WHITE));
                return;
            }else if (Validate.checkEmail(this, email, "Emaill sai định dạng")==false) {
                return;
            } else {
                Random rand = new Random();
                randomcode = rand.nextInt(999999);
                String host = "smtp.gmail.com";
                String user = "dangdinhvu221";
                String pass = "vu123456";
                String to = email.getText();
                String subject = "Reseting Code";
                String message = "Your reset code is " + randomcode;
                boolean sessionDebug = false;
                Properties props = System.getProperties();

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.user", user);
                props.put("mail.smtp.password", pass);
                props.put("mail.smtp.port", "587");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession = Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = (Message) new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(user));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setText(message);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                mesageDiaLogHelper.showMessageDialog(null, "mã đã được gửi đến email", "Thông báo!");
                setTimeBtn();
            }  
        } catch (Exception ex) {
            ex.printStackTrace();
            mesageDiaLogHelper.showErrorDialog(this, "email không hợp lệ", "Error!!");
        }

    }
    Thread t;
    private void setTimeBtn() {
        btverycode.setEnabled(true);
        txtVerify.setEnabled(true);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                btsend.setEnabled(false);
                for (int i = 59; i >= 0; i--) {
                    txtTime.setText(i + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        break;
                    }
                }
              
            }
        });
        t.start();
    }
     public void VerifyCode() {
        
        if (Validate.checkEmety(this,txtVerify,"Nhập very code","Error")==false) {
            return;
        } else if (Integer.valueOf(txtVerify.getText()) != randomcode) {
            mesageDiaLogHelper.showErrorDialog(this, "Mã không đúng. Vui lòng nhập lại", "Error!!");    
        } else {
            mesageDiaLogHelper.showMessageDialog(this, "Mã hợp lệ!", "Error!!");
            this.t.stop();
           btverycode.setEnabled(false);
            btreset.setEnabled(true);
            mkmoi.setEnabled(true);
            mklai.setEnabled(true);
     }
    }
         public void resetPass() {
        if (mkmoi.getText().equals(mklai.getText())) {
            try {
                QuenMK nv = getForm();
                dao.updateResetPass(nv);
                mesageDiaLogHelper.showMessageDialog(this, "Reset Successfully", "Thông báo");
                
                this.dispose();
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mesageDiaLogHelper.showErrorDialog(this, "password do not match", "Error!!");
        }
    }

    private QuenMK getForm() {
         
        QuenMK nd = new QuenMK();
        nd.setMatkhau(mkmoi.getText());
        nd.setHoten(code.getText());
        return nd;
    }
    public void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }
}

