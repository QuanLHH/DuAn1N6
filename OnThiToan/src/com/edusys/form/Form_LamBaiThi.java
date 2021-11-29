/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import PakagesClass.DeThi;
import com.edusys.dao.BaiThiDAO;
import com.edusys.dao.DeThiDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class Form_LamBaiThi extends javax.swing.JFrame {

    ArrayList<CauHoi> listCH = new ArrayList<>();
    ArrayList<DeThi> listDT = new ArrayList<>();
    Form_ChonBaiThi formChonDT;
    com.edusys.dao.DeThiDAO deThiDao = new DeThiDAO();
    DefaultTableModel model;
    Timer time;
    String getMaDe = null;
    int getDoKho = 0;
    int i = 0;
    int tongSoCau = 0;

    public Form_LamBaiThi() {
        initComponents();
        setInit();
        setExit();
    }

    void setInit() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ôn toán - Làm bài thi");
        setTime();
        this.getMaDe = formChonDT.getMaDe;
        this.getDoKho = formChonDT.getDoKho;
        this.formChonDT = new Form_ChonBaiThi();
        this.listDT = deThiDao.selectDeThi(getMaDe, getDoKho);
        this.tongSoCau = deThiDao.selectSoCau(getMaDe, getDoKho);
        setCauHoi(i);
        fillCauHoi();
        prev.setEnabled(false);
        page1.setText("1");
        page2.setText((tongSoCau / 5) + "");
        System.out.println("Mã đề:" + getMaDe + ", độ khó:" + getDoKho + ", tổng câu:" + tongSoCau);
    }

    void setExit() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
    }

    void setSoCau(String so) {
        lb_Cau1.setText(so + "");
        lb_Cau2.setText(so + "");
        lb_Cau3.setText(so + "");
        lb_Cau4.setText(so + "");
        lb_Cau5.setText(so + "");
    }
    void next() {
        int page = Integer.valueOf(page1.getText());
        page1.setText((page + 1) + "");
        prev.setEnabled(true);
        try {
            if (page >= (tongSoCau / 5) - 1) {
                next.setEnabled(false);
            }
            setSoCau((Integer.valueOf(lb_Cau1.getText()) + 5)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void prev() {
        int page = Integer.valueOf(page1.getText());
        next.setEnabled(true);
        page1.setText((page - 1) + "");
        try {
            if (page <= 2) {
                page1.setText("1");
                prev.setEnabled(false);
                setSoCau((Integer.valueOf(lb_Cau1.getText()) - 5)+"");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextDeThi() {
        try {
            System.out.println("Cs=" + i);
            i = i + 5;
            setCauHoi(i);
        } catch (Exception e) {
            return;
        }
    }

    public void prevDeThi() {
        try {
            i = i - 5;
            System.out.println("Cs=" + i);
            setCauHoi(i);
        } catch (Exception e) {
            return;
        }
    }

    void setCauHoi(int so) {
        lb_CauHoi1.setText(listDT.get(so).getCauHoi());
        tf_A1.setText(listDT.get(so).getDapAn1());
        tf_B1.setText(listDT.get(so).getDapAn2());
        tf_C1.setText(listDT.get(so).getDapAn3());
        tf_D1.setText(listDT.get(so).getDapAn4());

        lb_CauHoi2.setText(listDT.get(so + 1).getCauHoi());
        tf_A2.setText(listDT.get(so + 1).getDapAn1());
        tf_B2.setText(listDT.get(so + 1).getDapAn2());
        tf_C2.setText(listDT.get(so + 1).getDapAn3());
        tf_D2.setText(listDT.get(so + 1).getDapAn4());

        lb_CauHoi3.setText(listDT.get(so + 2).getCauHoi());
        tf_A3.setText(listDT.get(so + 2).getDapAn1());
        tf_B3.setText(listDT.get(so + 2).getDapAn2());
        tf_C3.setText(listDT.get(so + 2).getDapAn3());
        tf_D3.setText(listDT.get(so + 2).getDapAn3());

        lb_CauHoi4.setText(listDT.get(so + 3).getCauHoi());
        tf_A4.setText(listDT.get(so + 3).getDapAn1());
        tf_B4.setText(listDT.get(so + 3).getDapAn2());
        tf_C4.setText(listDT.get(so + 3).getDapAn3());
        tf_D4.setText(listDT.get(so + 3).getDapAn4());

        lb_CauHoi5.setText(listDT.get(so + 4).getCauHoi());
        tf_A5.setText(listDT.get(so + 4).getDapAn1());
        tf_B5.setText(listDT.get(so + 4).getDapAn2());
        tf_C5.setText(listDT.get(so + 4).getDapAn3());
        tf_D5.setText(listDT.get(so + 4).getDapAn4());
    }

    public void fillCauHoi() {
        String tong = Integer.toString(deThiDao.selectSoCau(getMaDe, getDoKho));
        String De = Integer.toString(deThiDao.selectSoCauDe(getMaDe, getDoKho));
        String TB = Integer.toString(deThiDao.selectSoCauTB(getMaDe, getDoKho));
        String Kho = Integer.toString(deThiDao.selectSoCauKho(getMaDe, getDoKho));

        lb_tongCau.setText(tong);
        soCauDe.setText(De);
        soCauTB.setText(TB);
        soCauKho.setText(Kho);
    }

    void setTime() {
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                int m = Integer.valueOf((lb_Time1.getText()));
                int s = Integer.valueOf((lb_Time2.getText()));
                s--;

                if (s <= 0 && m > 0) {
                    s = 59;
                    m--;
                }
                if (m == 0 && s == 0) {
                    time.stop();
                    lb_Time.setText("");
                    lb_Time1.setText("Hết");
                    lb_Time2.setText("giờ");
                    JOptionPane.showMessageDialog(rootPane, "Hết giờ!");
                    bt_Stop.setEnabled(false);
                    bt_NopBai.setEnabled(false);
                }
                lb_Time1.setText(m + "");
                lb_Time2.setText(s + "");
            }
        });
        time.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        bt_Stop = new javax.swing.JButton();
        bt_NopBai = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlb = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        soCauTB = new javax.swing.JLabel();
        soCauKho = new javax.swing.JLabel();
        lb_tongCau = new javax.swing.JLabel();
        soCauDe = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bt_NopBai1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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
        lb_Cau1 = new javax.swing.JLabel();
        jlb_cau1 = new javax.swing.JLabel();
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
        jlb_cau2 = new javax.swing.JLabel();
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
        lb_Cau3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
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
        lb_Cau5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jButton4 = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        next = new javax.swing.JButton();
        page1 = new javax.swing.JLabel();
        page2 = new javax.swing.JLabel();
        page2d = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        bt_Stop.setBackground(new java.awt.Color(204, 204, 204));
        bt_Stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Stop.png"))); // NOI18N

        bt_NopBai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Accept.png"))); // NOI18N

        jLabel2.setText("Số câu khó:");

        jLabel3.setText("Tạm dừng");

        jLabel4.setText("Nộp bài");

        jlb.setBackground(new java.awt.Color(51, 51, 255));
        jlb.setText("Tổng câu:");

        jLabel6.setText("Số câu TB:");

        jLabel7.setText("Số câu dễ:");

        bt_NopBai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Refresh.png"))); // NOI18N
        bt_NopBai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_NopBai1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Music.png"))); // NOI18N

        jLabel5.setText("   New");

        jLabel1.setText("Mở nhạc");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Music.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(bt_NopBai1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(bt_Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_NopBai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(soCauTB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(soCauKho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(soCauDe, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_tongCau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_NopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Stop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_NopBai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jlb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_tongCau, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(soCauKho, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(soCauTB, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(soCauDe, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lb_Time1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(lb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lb_Time2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lb_Img)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        buttonGroup1.add(rd_D1);
        rd_D1.setText("D:");
        rd_D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_D1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_C1);
        rd_C1.setText("C:");
        rd_C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_C1ActionPerformed(evt);
            }
        });

        tf_D1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D1.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C1.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup1.add(rd_B1);
        rd_B1.setText("B:");
        rd_B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_B1ActionPerformed(evt);
            }
        });

        lb_CauHoi1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B1.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup1.add(rd_A1);
        rd_A1.setText("A:");
        rd_A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_A1ActionPerformed(evt);
            }
        });

        tf_A1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A1.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau1.setText("1");

        jlb_cau1.setText("Câu");

        javax.swing.GroupLayout JP_1Layout = new javax.swing.GroupLayout(JP_1);
        JP_1.setLayout(JP_1Layout);
        JP_1Layout.setHorizontalGroup(
            JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_1Layout.createSequentialGroup()
                .addComponent(jlb_cau1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(lb_Cau1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(lb_CauHoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_1Layout.createSequentialGroup()
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rd_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rd_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rd_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(rd_A1)
                        .addGap(18, 18, 18)
                        .addComponent(tf_A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlb_cau1)))
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

        buttonGroup2.add(rd_D2);
        rd_D2.setText("D:");
        rd_D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_D2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rd_C2);
        rd_C2.setText("C:");
        rd_C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_C2ActionPerformed(evt);
            }
        });

        tf_D2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D2.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C2.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup2.add(rd_B2);
        rd_B2.setText("B:");
        rd_B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_B2ActionPerformed(evt);
            }
        });

        lb_CauHoi2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B2.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup2.add(rd_A2);
        rd_A2.setText("A:");
        rd_A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_A2ActionPerformed(evt);
            }
        });

        tf_A2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A2.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau2.setText("2");

        jlb_cau2.setText("Câu");

        javax.swing.GroupLayout JP_2Layout = new javax.swing.GroupLayout(JP_2);
        JP_2.setLayout(JP_2Layout);
        JP_2Layout.setHorizontalGroup(
            JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_2Layout.createSequentialGroup()
                .addComponent(jlb_cau2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(lb_Cau2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_CauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(tf_A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlb_cau2)))
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

        buttonGroup3.add(rd_D3);
        rd_D3.setText("D:");

        buttonGroup3.add(rd_C3);
        rd_C3.setText("C:");

        tf_D3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D3.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C3.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup3.add(rd_B3);
        rd_B3.setText("B:");
        rd_B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_B3ActionPerformed(evt);
            }
        });

        lb_CauHoi3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B3.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup3.add(rd_A3);
        rd_A3.setText("A:");
        rd_A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_A3ActionPerformed(evt);
            }
        });

        tf_A3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A3.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau3.setText("3");

        jLabel11.setText("Câu");

        javax.swing.GroupLayout JP_3Layout = new javax.swing.GroupLayout(JP_3);
        JP_3.setLayout(JP_3Layout);
        JP_3Layout.setHorizontalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addComponent(lb_Cau3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_3Layout.createSequentialGroup()
                        .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rd_B3)
                            .addComponent(rd_C3)
                            .addComponent(rd_D3))
                        .addGap(18, 18, 18)
                        .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addComponent(rd_A3)
                        .addGap(18, 18, 18)
                        .addComponent(tf_A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JP_3Layout.setVerticalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_CauHoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
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

        buttonGroup4.add(rd_D04);
        rd_D04.setText("D:");
        rd_D04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_D04ActionPerformed(evt);
            }
        });

        buttonGroup4.add(rd_C04);
        rd_C04.setText("C:");
        rd_C04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_C04ActionPerformed(evt);
            }
        });

        tf_D4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D4.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C4.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup4.add(rd_B04);
        rd_B04.setText("B:");
        rd_B04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_B04ActionPerformed(evt);
            }
        });

        lb_CauHoi4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B4.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup4.add(rd_A04);
        rd_A04.setText("A:");
        rd_A04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_A04ActionPerformed(evt);
            }
        });

        tf_A4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A4.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau4.setText("4");

        jLabel12.setText("Câu");

        javax.swing.GroupLayout JP_4Layout = new javax.swing.GroupLayout(JP_4);
        JP_4.setLayout(JP_4Layout);
        JP_4Layout.setHorizontalGroup(
            JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(lb_Cau4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi4, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_4Layout.createSequentialGroup()
                        .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rd_B04)
                            .addComponent(rd_C04)
                            .addComponent(rd_D04))
                        .addGap(18, 18, 18)
                        .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(rd_A04)
                        .addGap(18, 18, 18)
                        .addComponent(tf_A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
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

        buttonGroup5.add(rd_D5);
        rd_D5.setText("D:");

        buttonGroup5.add(rd_C5);
        rd_C5.setText("C:");

        tf_D5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D5.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C5.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup5.add(rd_B5);
        rd_B5.setText("B:");

        lb_CauHoi5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B5.setPreferredSize(new java.awt.Dimension(34, 20));

        buttonGroup5.add(rd_A5);
        rd_A5.setText("A:");

        tf_A5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A5.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau5.setText("5");

        jLabel13.setText("Câu");

        javax.swing.GroupLayout JP_5Layout = new javax.swing.GroupLayout(JP_5);
        JP_5.setLayout(JP_5Layout);
        JP_5Layout.setHorizontalGroup(
            JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_5Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(lb_Cau5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_5Layout.createSequentialGroup()
                        .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rd_B5)
                            .addComponent(rd_C5)
                            .addComponent(rd_D5))
                        .addGap(18, 18, 18)
                        .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(rd_A5)
                        .addGap(18, 18, 18)
                        .addComponent(tf_A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)))
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(171, Short.MAX_VALUE))
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
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/left-arrow.png"))); // NOI18N
        prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                prevMousePressed(evt);
            }
        });
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });
        prev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prevKeyReleased(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/previous.png"))); // NOI18N

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/right-arrow.png"))); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        page1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        page1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        page1.setText("0");

        page2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        page2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        page2.setText("0");

        page2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        page2d.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        page2d.setText("/");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SP_BaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(prev, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(page1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(page2d)
                .addGap(3, 3, 3)
                .addComponent(page2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SP_BaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prev, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(page2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(page2d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(353, 353, 353))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        nextDeThi();
        next();
    }//GEN-LAST:event_nextActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        prevDeThi();
        prev();
    }//GEN-LAST:event_prevActionPerformed

    private void bt_NopBai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_NopBai1ActionPerformed
        Form_ChonBaiThi f = new Form_ChonBaiThi();
        f.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_NopBai1ActionPerformed

    private void prevMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevMousePressed

    private void prevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prevKeyReleased

    }//GEN-LAST:event_prevKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rd_A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_A1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_A1ActionPerformed

    private void rd_B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_B1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_B1ActionPerformed

    private void rd_C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_C1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_C1ActionPerformed

    private void rd_D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_D1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_D1ActionPerformed

    private void rd_A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_A2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_A2ActionPerformed

    private void rd_B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_B2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_B2ActionPerformed

    private void rd_C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_C2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_C2ActionPerformed

    private void rd_D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_D2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_D2ActionPerformed

    private void rd_A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_A3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_A3ActionPerformed

    private void rd_B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_B3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_B3ActionPerformed

    private void rd_A04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_A04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_A04ActionPerformed

    private void rd_B04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_B04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_B04ActionPerformed

    private void rd_D04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_D04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_D04ActionPerformed

    private void rd_C04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_C04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_C04ActionPerformed

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
    private javax.swing.JButton bt_NopBai1;
    private javax.swing.JButton bt_Stop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jlb;
    private javax.swing.JLabel jlb_cau1;
    private javax.swing.JLabel jlb_cau2;
    private javax.swing.JLabel lb_Cau1;
    private javax.swing.JLabel lb_Cau2;
    private javax.swing.JLabel lb_Cau3;
    private javax.swing.JLabel lb_Cau4;
    private javax.swing.JLabel lb_Cau5;
    private javax.swing.JLabel lb_CauHoi1;
    private javax.swing.JLabel lb_CauHoi2;
    private javax.swing.JLabel lb_CauHoi3;
    private javax.swing.JLabel lb_CauHoi4;
    private javax.swing.JLabel lb_CauHoi5;
    private javax.swing.JLabel lb_Img;
    private javax.swing.JLabel lb_Time;
    private javax.swing.JLabel lb_Time1;
    private javax.swing.JLabel lb_Time2;
    private javax.swing.JLabel lb_tongCau;
    private javax.swing.JButton next;
    private javax.swing.JLabel page1;
    private javax.swing.JLabel page2;
    private javax.swing.JLabel page2d;
    private javax.swing.JButton prev;
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
    private javax.swing.JLabel soCauDe;
    private javax.swing.JLabel soCauKho;
    private javax.swing.JLabel soCauTB;
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
    // End of variables declaration//GEN-END:variables
}
