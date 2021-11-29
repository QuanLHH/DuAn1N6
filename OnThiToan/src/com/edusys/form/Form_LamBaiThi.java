/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Form_LamBaiThi extends javax.swing.JFrame {
    ArrayList<CauHoi> listCH = new ArrayList<>();
    Timer time;
    public Form_LamBaiThi() {
        initComponents();
        setInit();
    }
    void setInit(){
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ôn toán - Làm bài thi");
        setTime();
    }
    public void fillCauHoi(){
        
    }
    void setTime(){
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                int m =Integer.valueOf((lb_Time1.getText()));
                int s =Integer.valueOf((lb_Time2.getText()));
                s--;

                if(s<=0&&m>0){
                    s=59;
                    m--;
                }
                if(m==0&&s==0){
                    time.stop();
                    lb_Time.setText("");
                    lb_Time1.setText("Hết");
                    lb_Time2.setText("giờ");
                    JOptionPane.showMessageDialog(rootPane, "Hết giờ!");
                    bt_Stop.setEnabled(false);
                    bt_NopBai.setEnabled(false);
                }
                lb_Time1.setText(m+"");
                lb_Time2.setText(s+"");
            }
        });
        time.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_SoCau = new javax.swing.JTextField();
        bt_Stop = new javax.swing.JButton();
        bt_NopBai = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_cauKho2 = new javax.swing.JLabel();
        lb_cauKho1 = new javax.swing.JLabel();
        lb_cauKho = new javax.swing.JLabel();
        lb_cauKho3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lb_Time = new javax.swing.JLabel();
        lb_Img = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_Time1 = new javax.swing.JLabel();
        lb_Time2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        SP_BaiThi = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        JP_1 = new javax.swing.JPanel();
        rd_D1 = new javax.swing.JRadioButton();
        rd_C1 = new javax.swing.JRadioButton();
        tf_D1 = new javax.swing.JLabel();
        tf_C1 = new javax.swing.JLabel();
        rd_B1 = new javax.swing.JRadioButton();
        lb_CauHoi1 = new javax.swing.JLabel();
        tf_B1 = new javax.swing.JLabel();
        rd_A1 = new javax.swing.JRadioButton();
        tf_A1 = new javax.swing.JLabel();
        lb_cau1 = new javax.swing.JLabel();
        JP_2 = new javax.swing.JPanel();
        rd_D2 = new javax.swing.JRadioButton();
        rd_C2 = new javax.swing.JRadioButton();
        tf_D2 = new javax.swing.JLabel();
        tf_C2 = new javax.swing.JLabel();
        rd_B2 = new javax.swing.JRadioButton();
        lb_CauHoi2 = new javax.swing.JLabel();
        tf_B2 = new javax.swing.JLabel();
        rd_A2 = new javax.swing.JRadioButton();
        tf_A2 = new javax.swing.JLabel();
        lb_Cau2 = new javax.swing.JLabel();
        JP_3 = new javax.swing.JPanel();
        rd_D3 = new javax.swing.JRadioButton();
        rd_C3 = new javax.swing.JRadioButton();
        tf_D3 = new javax.swing.JLabel();
        tf_C3 = new javax.swing.JLabel();
        rd_B3 = new javax.swing.JRadioButton();
        lb_CauHoi3 = new javax.swing.JLabel();
        tf_B3 = new javax.swing.JLabel();
        rd_A3 = new javax.swing.JRadioButton();
        tf_A3 = new javax.swing.JLabel();
        lb_cau3 = new javax.swing.JLabel();
        JP_4 = new javax.swing.JPanel();
        rd_D04 = new javax.swing.JRadioButton();
        rd_C04 = new javax.swing.JRadioButton();
        tf_D4 = new javax.swing.JLabel();
        tf_C4 = new javax.swing.JLabel();
        rd_B04 = new javax.swing.JRadioButton();
        lb_CauHoi4 = new javax.swing.JLabel();
        tf_B4 = new javax.swing.JLabel();
        rd_A04 = new javax.swing.JRadioButton();
        tf_A4 = new javax.swing.JLabel();
        lb_Cau4 = new javax.swing.JLabel();
        JP_5 = new javax.swing.JPanel();
        rd_D5 = new javax.swing.JRadioButton();
        rd_C5 = new javax.swing.JRadioButton();
        tf_D5 = new javax.swing.JLabel();
        tf_C5 = new javax.swing.JLabel();
        rd_B5 = new javax.swing.JRadioButton();
        lb_CauHoi5 = new javax.swing.JLabel();
        tf_B5 = new javax.swing.JLabel();
        rd_A5 = new javax.swing.JRadioButton();
        tf_A5 = new javax.swing.JLabel();
        lb_cau5 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Câu số:");

        bt_Stop.setBackground(new java.awt.Color(204, 204, 204));
        bt_Stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Stop.png"))); // NOI18N

        bt_NopBai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Accept.png"))); // NOI18N

        jLabel2.setText("Số câu khó:");

        jLabel3.setText("Tạm dừng");

        jLabel4.setText("Nộp bài");

        jLabel5.setBackground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("Tổng câu:");

        jLabel6.setText("Số câu TB:");

        jLabel7.setText("Số câu dễ:");

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/next.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_SoCau, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(bt_Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_NopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_cauKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cauKho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cauKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cauKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_NopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tf_SoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bt_Stop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(23, 23, 23))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_cauKho, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lb_cauKho1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lb_cauKho2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_cauKho3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lb_Time.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Time.setText(":");
        lb_Time.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_Img.setBackground(new java.awt.Color(204, 204, 204));
        lb_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Clock.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("  Time còn lại");

        lb_Time1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Time1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Time1.setText("45");
        lb_Time1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_Time2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Time2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Time2.setText("00");
        lb_Time2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lb_Img)
                .addGap(47, 51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lb_Time1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(lb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lb_Time2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lb_Img, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_Time1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Time2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SP_BaiThi.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_BaiThi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        JP_1.setBackground(new java.awt.Color(255, 255, 255));
        JP_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rd_D1.setText("D:");

        rd_C1.setText("C:");

        tf_D1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D1.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C1.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_B1.setText("B:");

        lb_CauHoi1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B1.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_A1.setText("A:");

        tf_A1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A1.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_cau1.setText("Câu 1:");

        javax.swing.GroupLayout JP_1Layout = new javax.swing.GroupLayout(JP_1);
        JP_1.setLayout(JP_1Layout);
        JP_1Layout.setHorizontalGroup(
            JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_1Layout.createSequentialGroup()
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_1Layout.createSequentialGroup()
                                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rd_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rd_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rd_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_1Layout.createSequentialGroup()
                                .addComponent(rd_A1)
                                .addGap(18, 18, 18)
                                .addComponent(tf_A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_cau1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_CauHoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_1Layout.setVerticalGroup(
            JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(lb_CauHoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb_cau1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_A1))
                .addGap(11, 11, 11)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_B1)
                    .addComponent(tf_B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(rd_C1)
                        .addGap(11, 11, 11)
                        .addComponent(rd_D1))
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(tf_C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tf_D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        JP_2.setBackground(new java.awt.Color(255, 255, 255));
        JP_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rd_D2.setText("D:");

        rd_C2.setText("C:");

        tf_D2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D2.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C2.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_B2.setText("B:");

        lb_CauHoi2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B2.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_A2.setText("A:");

        tf_A2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A2.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau2.setText("Câu 2:");

        javax.swing.GroupLayout JP_2Layout = new javax.swing.GroupLayout(JP_2);
        JP_2.setLayout(JP_2Layout);
        JP_2Layout.setHorizontalGroup(
            JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_2Layout.createSequentialGroup()
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_2Layout.createSequentialGroup()
                                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rd_B2)
                                    .addComponent(rd_C2)
                                    .addComponent(rd_D2))
                                .addGap(18, 18, 18)
                                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_D2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_C2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_B2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_2Layout.createSequentialGroup()
                                .addComponent(rd_A2)
                                .addGap(18, 18, 18)
                                .addComponent(tf_A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_Cau2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_CauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_2Layout.setVerticalGroup(
            JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(lb_CauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb_Cau2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_A2))
                .addGap(11, 11, 11)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_B2)
                    .addComponent(tf_B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(rd_C2)
                        .addGap(11, 11, 11)
                        .addComponent(rd_D2))
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(tf_C2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tf_D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        JP_3.setBackground(new java.awt.Color(255, 255, 255));
        JP_3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rd_D3.setText("D:");

        rd_C3.setText("C:");

        tf_D3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D3.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C3.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_B3.setText("B:");

        lb_CauHoi3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B3.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_A3.setText("A:");

        tf_A3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A3.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_cau3.setText("Câu 3:");

        javax.swing.GroupLayout JP_3Layout = new javax.swing.GroupLayout(JP_3);
        JP_3.setLayout(JP_3Layout);
        JP_3Layout.setHorizontalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_3Layout.createSequentialGroup()
                                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rd_B3)
                                    .addComponent(rd_C3)
                                    .addComponent(rd_D3))
                                .addGap(18, 18, 18)
                                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_D3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_C3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_B3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_3Layout.createSequentialGroup()
                                .addComponent(rd_A3)
                                .addGap(18, 18, 18)
                                .addComponent(tf_A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lb_cau3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JP_3Layout.setVerticalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_CauHoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_cau3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_A3))
                .addGap(11, 11, 11)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_B3)
                    .addComponent(tf_B3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addComponent(rd_C3)
                        .addGap(11, 11, 11)
                        .addComponent(rd_D3))
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addComponent(tf_C3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tf_D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        JP_4.setBackground(new java.awt.Color(255, 255, 255));
        JP_4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rd_D04.setText("D:");

        rd_C04.setText("C:");

        tf_D4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D4.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C4.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_B04.setText("B:");

        lb_CauHoi4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B4.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_A04.setText("A:");

        tf_A4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A4.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau4.setText("Câu 4:");

        javax.swing.GroupLayout JP_4Layout = new javax.swing.GroupLayout(JP_4);
        JP_4.setLayout(JP_4Layout);
        JP_4Layout.setHorizontalGroup(
            JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_4Layout.createSequentialGroup()
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_4Layout.createSequentialGroup()
                                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rd_B04)
                                    .addComponent(rd_C04)
                                    .addComponent(rd_D04))
                                .addGap(18, 18, 18)
                                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_D4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_C4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_B4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_4Layout.createSequentialGroup()
                                .addComponent(rd_A04)
                                .addGap(18, 18, 18)
                                .addComponent(tf_A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_Cau4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi4, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_4Layout.setVerticalGroup(
            JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(lb_CauHoi4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb_Cau4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_A04))
                .addGap(11, 11, 11)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_B04)
                    .addComponent(tf_B4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(rd_C04)
                        .addGap(11, 11, 11)
                        .addComponent(rd_D04))
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(tf_C4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tf_D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        JP_5.setBackground(new java.awt.Color(255, 255, 255));
        JP_5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rd_D5.setText("D:");

        rd_C5.setText("C:");

        tf_D5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D5.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C5.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_B5.setText("B:");

        lb_CauHoi5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B5.setPreferredSize(new java.awt.Dimension(34, 20));

        rd_A5.setText("A:");

        tf_A5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A5.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_cau5.setText("Câu 5:");

        javax.swing.GroupLayout JP_5Layout = new javax.swing.GroupLayout(JP_5);
        JP_5.setLayout(JP_5Layout);
        JP_5Layout.setHorizontalGroup(
            JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_5Layout.createSequentialGroup()
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_5Layout.createSequentialGroup()
                                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rd_B5)
                                    .addComponent(rd_C5)
                                    .addComponent(rd_D5))
                                .addGap(18, 18, 18)
                                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_D5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_C5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_5Layout.createSequentialGroup()
                                .addComponent(rd_A5)
                                .addGap(18, 18, 18)
                                .addComponent(tf_A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_cau5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_5Layout.setVerticalGroup(
            JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(lb_CauHoi5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb_cau5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_A5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_A5))
                .addGap(11, 11, 11)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_B5)
                    .addComponent(tf_B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(rd_C5)
                        .addGap(11, 11, 11)
                        .addComponent(rd_D5))
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(tf_C5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(tf_D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(JP_5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JP_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JP_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JP_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        SP_BaiThi.setViewportView(jPanel6);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/nexts.png"))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/left-arrow.png"))); // NOI18N

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/previous.png"))); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/right-arrow.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SP_BaiThi))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SP_BaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_LamBaiThi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JP_1;
    private javax.swing.JPanel JP_2;
    private javax.swing.JPanel JP_3;
    private javax.swing.JPanel JP_4;
    private javax.swing.JPanel JP_5;
    private javax.swing.JScrollPane SP_BaiThi;
    private javax.swing.JButton bt_NopBai;
    private javax.swing.JButton bt_Stop;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lb_Cau2;
    private javax.swing.JLabel lb_Cau4;
    private javax.swing.JLabel lb_CauHoi1;
    private javax.swing.JLabel lb_CauHoi2;
    private javax.swing.JLabel lb_CauHoi3;
    private javax.swing.JLabel lb_CauHoi4;
    private javax.swing.JLabel lb_CauHoi5;
    private javax.swing.JLabel lb_Img;
    private javax.swing.JLabel lb_Time;
    private javax.swing.JLabel lb_Time1;
    private javax.swing.JLabel lb_Time2;
    private javax.swing.JLabel lb_cau1;
    private javax.swing.JLabel lb_cau3;
    private javax.swing.JLabel lb_cau5;
    private javax.swing.JLabel lb_cauKho;
    private javax.swing.JLabel lb_cauKho1;
    private javax.swing.JLabel lb_cauKho2;
    private javax.swing.JLabel lb_cauKho3;
    private javax.swing.JRadioButton rd_A04;
    private javax.swing.JRadioButton rd_A1;
    private javax.swing.JRadioButton rd_A2;
    private javax.swing.JRadioButton rd_A3;
    private javax.swing.JRadioButton rd_A5;
    private javax.swing.JRadioButton rd_B04;
    private javax.swing.JRadioButton rd_B1;
    private javax.swing.JRadioButton rd_B2;
    private javax.swing.JRadioButton rd_B3;
    private javax.swing.JRadioButton rd_B5;
    private javax.swing.JRadioButton rd_C04;
    private javax.swing.JRadioButton rd_C1;
    private javax.swing.JRadioButton rd_C2;
    private javax.swing.JRadioButton rd_C3;
    private javax.swing.JRadioButton rd_C5;
    private javax.swing.JRadioButton rd_D04;
    private javax.swing.JRadioButton rd_D1;
    private javax.swing.JRadioButton rd_D2;
    private javax.swing.JRadioButton rd_D3;
    private javax.swing.JRadioButton rd_D5;
    private javax.swing.JLabel tf_A1;
    private javax.swing.JLabel tf_A2;
    private javax.swing.JLabel tf_A3;
    private javax.swing.JLabel tf_A4;
    private javax.swing.JLabel tf_A5;
    private javax.swing.JLabel tf_B1;
    private javax.swing.JLabel tf_B2;
    private javax.swing.JLabel tf_B3;
    private javax.swing.JLabel tf_B4;
    private javax.swing.JLabel tf_B5;
    private javax.swing.JLabel tf_C1;
    private javax.swing.JLabel tf_C2;
    private javax.swing.JLabel tf_C3;
    private javax.swing.JLabel tf_C4;
    private javax.swing.JLabel tf_C5;
    private javax.swing.JLabel tf_D1;
    private javax.swing.JLabel tf_D2;
    private javax.swing.JLabel tf_D3;
    private javax.swing.JLabel tf_D4;
    private javax.swing.JLabel tf_D5;
    private javax.swing.JTextField tf_SoCau;
    // End of variables declaration//GEN-END:variables
}
