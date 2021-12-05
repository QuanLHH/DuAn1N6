/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.BaiThiChiTiet;
import PakagesClass.CauHoi;
import PakagesClass.DeThi;
import PakagesClass.ThongTinBaiThi;
import com.edusys.dao.DeThiDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javazoom.jl.player.Player;

public class Form_LamBaiThi extends javax.swing.JFrame {

    ArrayList<DeThi> listDT = new ArrayList<>();
    Form_ChonBaiThi formChonDT;
    com.edusys.dao.DeThiDAO deThiDao = new DeThiDAO();
    DefaultTableModel model;
    ArrayList<String> listModel = new ArrayList<>();
    Thread time;
    Thread player;
    String getMaDe = null;
    int getDoKho = 0;
    int i = 0;
    public static int id_baiThi = 0;
    public static int id_CauHoi = 0;
    public static int tongSoCau = 0;
    public static int soCauDung = 0;
    public static int soCauSai = 0;
    public static int minutes = 0;
    public static int seconds = 0;
    public static float diem = 0;

    public Form_LamBaiThi() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ôn toán - Làm bài thi");
        setTime();
        setImage();
        setExit();
        setInit();
        SelectID_CauHoi();
    }

    void setInit() {
        this.bt_pause.setEnabled(false);
        this.bt_resume.setEnabled(false);
        this.bt_restart.setEnabled(false);
        this.first.setEnabled(false);
        this.model = (DefaultTableModel) tb_LamBaiThi.getModel();
        this.getMaDe = formChonDT.getMaDe;
        this.getDoKho = formChonDT.getDoKho;
        this.formChonDT = new Form_ChonBaiThi();
        this.listDT = deThiDao.selectDeThi(getMaDe, getDoKho);
        this.tongSoCau = deThiDao.selectSoCau(getMaDe, getDoKho);

        setCauHoi(i);
        fillCauHoi();
        fillTable();
        id_CauHoi = listDT.get(0).getID_CauHoi();
        id_baiThi = deThiDao.selectByIds(id_CauHoi, getMaDe, getDoKho);
        System.out.println("ID_CauHoi:" + id_CauHoi + "ID_BaiThi:" + id_baiThi);
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

    void setSoCau(int so) {
        lb_Cau1.setText(so + "");
        lb_Cau2.setText((so + 1) + "");
        lb_Cau3.setText((so + 2) + "");
        lb_Cau4.setText((so + 3) + "");
        lb_Cau5.setText((so + 4) + "");
    }

    void next() {
        int page = Integer.valueOf(page1.getText());
        page1.setText((page + 1) + "");
        prev.setEnabled(true);
        first.setEnabled(true);
        try {
            if (page >= (tongSoCau / 5) - 1) {
                next.setEnabled(false);
                last.setEnabled(false);
            }
            setSoCau((Integer.valueOf(lb_Cau1.getText()) + 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void prev() {
        int page = Integer.valueOf(page1.getText());
        next.setEnabled(true);
        last.setEnabled(true);
        page1.setText((page - 1) + "");
        try {
            if (page <= 2) {
                page1.setText("1");
                prev.setEnabled(false);
                first.setEnabled(false);
            }
            setSoCau((Integer.valueOf(lb_Cau1.getText()) - 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void first() {
        int page = Integer.valueOf(page1.getText());
        next.setEnabled(true);
        prev.setEnabled(false);
        first.setEnabled(false);
        last.setEnabled(true);
        page1.setText(1 + "");
        i = 0;
        setCauHoi(i);
        setSoCau(1);
    }

    void last() {
        int page = Integer.valueOf(page1.getText());
        int pages = Integer.valueOf(page2.getText());
        next.setEnabled(false);
        prev.setEnabled(true);
        last.setEnabled(false);
        first.setEnabled(true);
        page1.setText(pages + "");
        i = (tongSoCau - 5);
        setCauHoi(i);
        setSoCau(tongSoCau - 4);

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

    public void SelectID_CauHoi() {
        for (DeThi x : listDT) {

            System.out.println("ID_CauHoi: " + x.getID_CauHoi());
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

    public ThongTinBaiThi getFormTTBaiThi() {
        ThongTinBaiThi tt = new ThongTinBaiThi();
        BaiThiChiTiet dt = deThiDao.selectNewID_BaiThiCT();
        int ID_BaiThiCT = dt.getID_BaiThiCT();
        tt.setID_BaiThiCT(ID_BaiThiCT);
        tt.setMaDe(getMaDe);
        tt.setDoKho(getDoKho);
        tt.setDapAnChon(getMaDe);
        return tt;
    }

    public void insertThongTinBaiThi() {
        ThongTinBaiThi tt = getFormTTBaiThi();
        for (int i = 0; i < listDT.size(); i++) {
            listModel.add(model.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < listDT.size(); i++) {
            tt.setID_CauHoi(listDT.get(i).getID_CauHoi());
            tt.setDapAnChon(listModel.get(i));
            deThiDao.insertTTBaiThi(tt);
        }
    }

    public void checkBai() {
        minutes = 44 - Integer.valueOf(lb_Minutes.getText());
        seconds = 60 - Integer.valueOf(lb_Seconds.getText());
        float tong = tongSoCau;
        for (int i = 0; i < listDT.size(); i++) {
            if (listDT.get(i).getDapAnDung().equalsIgnoreCase(tb_LamBaiThi.getValueAt(i, 1).toString())) {
                soCauDung++;
            } else {
                soCauSai++;
            }

        }
        diem = ((10 / tong) * soCauDung);
    }

    public void lamTuoi(String soCau, JCheckBox a, JCheckBox b, JCheckBox c, JCheckBox d, ButtonGroup chuoi) {
        try {
            int cau = Integer.valueOf(soCau);
            String dapAn = (model.getValueAt(cau - 1, 1).toString());
            if (a.getText().equalsIgnoreCase(dapAn)) {
                a.setSelected(true);
            } else if (b.getText().equalsIgnoreCase(dapAn)) {
                b.setSelected(true);
            } else if (c.getText().equalsIgnoreCase(dapAn)) {
                c.setSelected(true);
            } else if (d.getText().equalsIgnoreCase(dapAn)) {
                d.setSelected(true);
            } else if (dapAn.equals("")) {
                chuoi.clearSelection();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void refresh() {
        String cau1 = lb_Cau1.getText();
        String cau2 = lb_Cau2.getText();
        String cau3 = lb_Cau3.getText();
        String cau4 = lb_Cau4.getText();
        String cau5 = lb_Cau5.getText();
        lamTuoi(cau1, cb_A1, cb_B1, cb_C1, cb_D1, buttonGroup1);
        lamTuoi(cau2, cb_A2, cb_B2, cb_C2, cb_D2, buttonGroup2);
        lamTuoi(cau3, cb_A3, cb_B3, cb_C3, cb_D3, buttonGroup3);
        lamTuoi(cau4, cb_A4, cb_B4, cb_C4, cb_D4, buttonGroup4);
        lamTuoi(cau5, cb_A5, cb_B5, cb_C5, cb_D5, buttonGroup5);

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

    public void fillTable() {
        model.setRowCount(0);
        for (int i = 1; i <= tongSoCau; i++) {
            model.addRow(new Object[]{i, ""});
        }
    }

    void setTime() {
        time = new Thread() {
            @Override
            public void run() {
                while (true) {

                    minutes = Integer.valueOf((lb_Minutes.getText()));
                    seconds = Integer.valueOf((lb_Seconds.getText()));
                    seconds--;

                    if (seconds <= 0 && minutes > 0) {
                        seconds = 59;
                        minutes--;
                    }
                    if (minutes == 0 && seconds <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "Hết giờ!");
                        SP_BaiThi.setVisible(false);
                        stop();
                    }
                    lb_Minutes.setText(minutes + "");
                    lb_Seconds.setText(seconds + "");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        time.start();
    }

    public void playMp3() {
        player = new Thread() {
            @Override
            public void run() {
                try {
                    Player play = new Player(new FileInputStream("hinh/mp3/tay_trai_chi_trang.mp3"));
                    play.play();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        player.start();
    }

    public void fillDapAnCau1(String chuoi) {
        int cau1 = Integer.valueOf(lb_Cau1.getText());
        model.setValueAt(chuoi, cau1 - 1, 1);
    }

    public void fillDapAnCau2(String chuoi) {
        int cau2 = Integer.valueOf(lb_Cau2.getText());
        model.setValueAt(chuoi, cau2 - 1, 1);
    }

    public void fillDapAnCau3(String chuoi) {
        int cau3 = Integer.valueOf(lb_Cau3.getText());
        model.setValueAt(chuoi, cau3 - 1, 1);
    }

    public void fillDapAnCau4(String chuoi) {
        int cau4 = Integer.valueOf(lb_Cau4.getText());
        model.setValueAt(chuoi, cau4 - 1, 1);
    }

    public void fillDapAnCau5(String chuoi) {
        int cau5 = Integer.valueOf(lb_Cau5.getText());
        model.setValueAt(chuoi, cau5 - 1, 1);
    }

    void setImage() {
        ImageIcon img1 = new ImageIcon("hinh/restart32px.png");
        this.khoiDongLai.setIcon(img1);
        ImageIcon img2 = new ImageIcon("hinh/Stop.png");
        this.bt_Stop.setIcon(img2);
        ImageIcon img3 = new ImageIcon("hinh/Accept.png");
        this.bt_NopBai.setIcon(img3);
        ImageIcon img4 = new ImageIcon("hinh/Music.png");
        this.bt_Start.setIcon(img4);
        ImageIcon img5 = new ImageIcon("hinh/pause-button.png");
        this.bt_pause.setIcon(img5);
        ImageIcon img6 = new ImageIcon("hinh/resume.png");
        this.bt_resume.setIcon(img6);
        ImageIcon nextImg = new ImageIcon("hinh/right-arrow.png");
        this.next.setIcon(nextImg);
        ImageIcon prevImg = new ImageIcon("hinh/left-arrow.png");
        this.prev.setIcon(prevImg);
        ImageIcon lastImg = new ImageIcon("hinh/nexts.png");
        this.last.setIcon(lastImg);
        ImageIcon firstImg = new ImageIcon("hinh/previous.png");
        this.first.setIcon(firstImg);
        ImageIcon restartImg = new ImageIcon("hinh/Refresh.png");
        this.bt_restart.setIcon(restartImg);
        ImageIcon dongHo = new ImageIcon("hinh/Clock.png");
        this.lb_dongHo.setIcon(dongHo);
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
        khoiDongLai = new javax.swing.JButton();
        bt_Start = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bt_pause = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        bt_resume = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        bt_restart = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lb_Time = new javax.swing.JLabel();
        lb_dongHo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_Minutes = new javax.swing.JLabel();
        lb_Seconds = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        SP_BaiThi = new javax.swing.JScrollPane();
        jpanel_tong = new javax.swing.JPanel();
        JP_1 = new javax.swing.JPanel();
        tf_D1 = new javax.swing.JLabel();
        tf_C1 = new javax.swing.JLabel();
        lb_CauHoi1 = new javax.swing.JLabel();
        tf_B1 = new javax.swing.JLabel();
        tf_A1 = new javax.swing.JLabel();
        lb_Cau1 = new javax.swing.JLabel();
        jlb_cau1 = new javax.swing.JLabel();
        cb_A1 = new javax.swing.JCheckBox();
        cb_B1 = new javax.swing.JCheckBox();
        cb_C1 = new javax.swing.JCheckBox();
        cb_D1 = new javax.swing.JCheckBox();
        JP_2 = new javax.swing.JPanel();
        tf_D2 = new javax.swing.JLabel();
        tf_C2 = new javax.swing.JLabel();
        lb_CauHoi2 = new javax.swing.JLabel();
        tf_B2 = new javax.swing.JLabel();
        tf_A2 = new javax.swing.JLabel();
        lb_Cau2 = new javax.swing.JLabel();
        jlb_cau2 = new javax.swing.JLabel();
        cb_D2 = new javax.swing.JCheckBox();
        cb_C2 = new javax.swing.JCheckBox();
        cb_B2 = new javax.swing.JCheckBox();
        cb_A2 = new javax.swing.JCheckBox();
        JP_3 = new javax.swing.JPanel();
        tf_D3 = new javax.swing.JLabel();
        tf_C3 = new javax.swing.JLabel();
        lb_CauHoi3 = new javax.swing.JLabel();
        tf_B3 = new javax.swing.JLabel();
        tf_A3 = new javax.swing.JLabel();
        lb_Cau3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cb_A3 = new javax.swing.JCheckBox();
        cb_B3 = new javax.swing.JCheckBox();
        cb_C3 = new javax.swing.JCheckBox();
        cb_D3 = new javax.swing.JCheckBox();
        JP_4 = new javax.swing.JPanel();
        tf_D4 = new javax.swing.JLabel();
        tf_C4 = new javax.swing.JLabel();
        lb_CauHoi4 = new javax.swing.JLabel();
        tf_B4 = new javax.swing.JLabel();
        tf_A4 = new javax.swing.JLabel();
        lb_Cau4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_D4 = new javax.swing.JCheckBox();
        cb_C4 = new javax.swing.JCheckBox();
        cb_B4 = new javax.swing.JCheckBox();
        cb_A4 = new javax.swing.JCheckBox();
        JP_5 = new javax.swing.JPanel();
        tf_D5 = new javax.swing.JLabel();
        tf_C5 = new javax.swing.JLabel();
        lb_CauHoi5 = new javax.swing.JLabel();
        tf_B5 = new javax.swing.JLabel();
        tf_A5 = new javax.swing.JLabel();
        lb_Cau5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cb_D5 = new javax.swing.JCheckBox();
        cb_C5 = new javax.swing.JCheckBox();
        cb_B5 = new javax.swing.JCheckBox();
        cb_A5 = new javax.swing.JCheckBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        last = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        first = new javax.swing.JButton();
        next = new javax.swing.JButton();
        page1 = new javax.swing.JLabel();
        page2 = new javax.swing.JLabel();
        page2d = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_LamBaiThi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        bt_Stop.setBackground(new java.awt.Color(204, 204, 204));
        bt_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_StopActionPerformed(evt);
            }
        });

        bt_NopBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_NopBaiActionPerformed(evt);
            }
        });

        jLabel2.setText("Số câu khó:");

        jLabel3.setText("Tạm dừng");

        jLabel4.setText("Nộp bài");

        jlb.setBackground(new java.awt.Color(51, 51, 255));
        jlb.setText("Tổng câu:");

        jLabel6.setText("Số câu TB:");

        jLabel7.setText("Số câu dễ:");

        khoiDongLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoiDongLaiActionPerformed(evt);
            }
        });

        bt_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_StartActionPerformed(evt);
            }
        });

        jLabel5.setText("   New");

        jLabel1.setText("Mở nhạc");

        bt_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pauseActionPerformed(evt);
            }
        });

        jLabel10.setText("  Pause");

        bt_resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resumeActionPerformed(evt);
            }
        });

        jLabel14.setText("Resume");

        bt_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_restartActionPerformed(evt);
            }
        });

        jLabel15.setText("Restart");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(khoiDongLai, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
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
                    .addComponent(bt_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_resume, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_restart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_NopBai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_Stop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(khoiDongLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_Start, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_pause, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bt_restart, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bt_resume, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))))
                .addGap(23, 23, 23))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lb_Time.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Time.setText(":");
        lb_Time.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_dongHo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("  Time còn lại");

        lb_Minutes.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Minutes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Minutes.setText("45");
        lb_Minutes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_Seconds.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_Seconds.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Seconds.setText("00");
        lb_Seconds.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lb_Minutes, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lb_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lb_Seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lb_dongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(lb_dongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_Minutes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Seconds, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SP_BaiThi.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_BaiThi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jpanel_tong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        JP_1.setBackground(new java.awt.Color(255, 255, 255));
        JP_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_D1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D1.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C1.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_CauHoi1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B1.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_A1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A1.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau1.setText("1");

        jlb_cau1.setText("Câu");

        buttonGroup1.add(cb_A1);
        cb_A1.setText("A");
        cb_A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_A1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(cb_B1);
        cb_B1.setText("B");
        cb_B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_B1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(cb_C1);
        cb_C1.setText("C");
        cb_C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_C1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(cb_D1);
        cb_D1.setText("D");
        cb_D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_D1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_1Layout = new javax.swing.GroupLayout(JP_1);
        JP_1.setLayout(JP_1Layout);
        JP_1Layout.setHorizontalGroup(
            JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_1Layout.createSequentialGroup()
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addComponent(jlb_cau1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Cau1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_D1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JP_1Layout.setVerticalGroup(
            JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_1Layout.createSequentialGroup()
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_Cau1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlb_cau1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_CauHoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_A1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_A1))
                        .addGap(11, 11, 11)
                        .addComponent(tf_B1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_B1)))
                .addGap(11, 11, 11)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_C1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_C1))
                .addGap(8, 8, 8)
                .addGroup(JP_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_D1))
                .addGap(69, 69, 69))
        );

        JP_2.setBackground(new java.awt.Color(255, 255, 255));
        JP_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_D2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D2.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C2.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_CauHoi2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B2.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_A2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A2.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau2.setText("2");

        jlb_cau2.setText("Câu");

        buttonGroup2.add(cb_D2);
        cb_D2.setText("D");
        cb_D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_D2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(cb_C2);
        cb_C2.setText("C");
        cb_C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_C2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(cb_B2);
        cb_B2.setText("B");
        cb_B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_B2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(cb_A2);
        cb_A2.setText("A");
        cb_A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_A2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_2Layout = new javax.swing.GroupLayout(JP_2);
        JP_2.setLayout(JP_2Layout);
        JP_2Layout.setHorizontalGroup(
            JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_2Layout.createSequentialGroup()
                .addComponent(jlb_cau2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(lb_Cau2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_C2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_D2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_B2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JP_2Layout.setVerticalGroup(
            JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_cau2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Cau2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JP_2Layout.createSequentialGroup()
                        .addComponent(lb_CauHoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_A2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_A2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_B2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_B2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_C2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_C2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(JP_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_D2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_D2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );

        JP_3.setBackground(new java.awt.Color(255, 255, 255));
        JP_3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_D3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D3.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C3.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_CauHoi3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B3.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_A3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A3.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau3.setText("3");

        jLabel11.setText("Câu");

        buttonGroup3.add(cb_A3);
        cb_A3.setText("A");
        cb_A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_A3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(cb_B3);
        cb_B3.setText("B");
        cb_B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_B3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(cb_C3);
        cb_C3.setText("C");
        cb_C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_C3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(cb_D3);
        cb_D3.setText("D");
        cb_D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_D3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_3Layout = new javax.swing.GroupLayout(JP_3);
        JP_3.setLayout(JP_3Layout);
        JP_3Layout.setHorizontalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addComponent(lb_Cau3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JP_3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_C3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_D3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_B3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JP_3Layout.setVerticalGroup(
            JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_CauHoi3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(lb_Cau3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_A3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_B3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_B3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_C3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_C3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(JP_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_D3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_D3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );

        JP_4.setBackground(new java.awt.Color(255, 255, 255));
        JP_4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_D4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D4.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C4.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_CauHoi4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B4.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_A4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A4.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau4.setText("4");

        jLabel12.setText("Câu");

        buttonGroup4.add(cb_D4);
        cb_D4.setText("D");
        cb_D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_D4ActionPerformed(evt);
            }
        });

        buttonGroup4.add(cb_C4);
        cb_C4.setText("C");
        cb_C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_C4ActionPerformed(evt);
            }
        });

        buttonGroup4.add(cb_B4);
        cb_B4.setText("B");
        cb_B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_B4ActionPerformed(evt);
            }
        });

        buttonGroup4.add(cb_A4);
        cb_A4.setText("A");
        cb_A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_A4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_4Layout = new javax.swing.GroupLayout(JP_4);
        JP_4.setLayout(JP_4Layout);
        JP_4Layout.setHorizontalGroup(
            JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_4Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addComponent(lb_Cau4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JP_4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_C4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_D4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_B4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_D4, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                            .addComponent(tf_C4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_B4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JP_4Layout.setVerticalGroup(
            JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_CauHoi4, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_Cau4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_A4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_A4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_B4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_B4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_C4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_C4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(JP_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_D4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_D4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );

        JP_5.setBackground(new java.awt.Color(255, 255, 255));
        JP_5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_D5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_D5.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_C5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_C5.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_CauHoi5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tf_B5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_B5.setPreferredSize(new java.awt.Dimension(34, 20));

        tf_A5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tf_A5.setPreferredSize(new java.awt.Dimension(34, 20));

        lb_Cau5.setText("5");

        jLabel13.setText("Câu");

        buttonGroup5.add(cb_D5);
        cb_D5.setText("D");
        cb_D5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_D5ActionPerformed(evt);
            }
        });

        buttonGroup5.add(cb_C5);
        cb_C5.setText("C");
        cb_C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_C5ActionPerformed(evt);
            }
        });

        buttonGroup5.add(cb_B5);
        cb_B5.setText("B");
        cb_B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_B5ActionPerformed(evt);
            }
        });

        buttonGroup5.add(cb_A5);
        cb_A5.setText("A");
        cb_A5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_A5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_5Layout = new javax.swing.GroupLayout(JP_5);
        JP_5.setLayout(JP_5Layout);
        JP_5Layout.setHorizontalGroup(
            JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_5Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_C5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_D5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_B5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_B5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_C5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_D5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(JP_5Layout.createSequentialGroup()
                        .addComponent(lb_Cau5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CauHoi5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JP_5Layout.setVerticalGroup(
            JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Cau5)
                        .addComponent(jLabel13))
                    .addComponent(lb_CauHoi5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_A5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_A5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cb_B5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_B5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_C5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_C5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_D5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tf_D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpanel_tongLayout = new javax.swing.GroupLayout(jpanel_tong);
        jpanel_tong.setLayout(jpanel_tongLayout);
        jpanel_tongLayout.setHorizontalGroup(
            jpanel_tongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_tongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel_tongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JP_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jpanel_tongLayout.setVerticalGroup(
            jpanel_tongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_tongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        SP_BaiThi.setViewportView(jpanel_tong);

        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

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

        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

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

        tb_LamBaiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Đáp án"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_LamBaiThi);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SP_BaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(SP_BaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prev, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(page2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(page2d, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1248, 1248, 1248))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 550, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        nextDeThi();
        next();
        refresh();
    }//GEN-LAST:event_nextActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        prevDeThi();
        prev();
        refresh();
    }//GEN-LAST:event_prevActionPerformed

    private void khoiDongLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khoiDongLaiActionPerformed
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn làm lại bài thi?", "", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            Form_LamBaiThi f = new Form_LamBaiThi();
            f.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_khoiDongLaiActionPerformed

    private void prevMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevMousePressed

    private void prevKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prevKeyReleased

    }//GEN-LAST:event_prevKeyReleased

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        last();
        refresh();
    }//GEN-LAST:event_lastActionPerformed

    private void bt_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_StartActionPerformed
        bt_pause.setEnabled(true);
        bt_restart.setEnabled(true);
        bt_Start.setEnabled(false);
        playMp3();
    }//GEN-LAST:event_bt_StartActionPerformed

    private void bt_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pauseActionPerformed
        player.suspend();
        bt_resume.setEnabled(true);
        bt_pause.setEnabled(false);
    }//GEN-LAST:event_bt_pauseActionPerformed

    private void bt_resumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resumeActionPerformed
        bt_pause.setEnabled(true);
        bt_resume.setEnabled(false);
        player.resume();
    }//GEN-LAST:event_bt_resumeActionPerformed

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        first();
        refresh();
    }//GEN-LAST:event_firstActionPerformed

    private void bt_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_StopActionPerformed
        time.suspend();
        int i = JOptionPane.showConfirmDialog(rootPane, "Ấn yes để tiếp tục", "Tiếp tục", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            time.resume();
        } else if (i == 1) {
            int x = JOptionPane.showConfirmDialog(rootPane, "Bạn muốn dừng làm bài?", "Xác nhận!", JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                dispose();
            } else if (x == 1) {
                time.resume();
            }
        }
    }//GEN-LAST:event_bt_StopActionPerformed

    private void cb_C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_C5ActionPerformed
        fillDapAnCau5("C");
    }//GEN-LAST:event_cb_C5ActionPerformed

    private void cb_A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_A1ActionPerformed
        fillDapAnCau1("A");
    }//GEN-LAST:event_cb_A1ActionPerformed

    private void cb_B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_B1ActionPerformed
        fillDapAnCau1("B");
    }//GEN-LAST:event_cb_B1ActionPerformed

    private void cb_C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_C1ActionPerformed
        fillDapAnCau1("C");
    }//GEN-LAST:event_cb_C1ActionPerformed

    private void cb_D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_D1ActionPerformed
        fillDapAnCau1("D");
    }//GEN-LAST:event_cb_D1ActionPerformed

    private void cb_A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_A2ActionPerformed
        fillDapAnCau2("A");
    }//GEN-LAST:event_cb_A2ActionPerformed

    private void cb_B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_B2ActionPerformed
        fillDapAnCau2("B");
    }//GEN-LAST:event_cb_B2ActionPerformed

    private void cb_C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_C2ActionPerformed
        fillDapAnCau2("C");
    }//GEN-LAST:event_cb_C2ActionPerformed

    private void cb_D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_D2ActionPerformed
        fillDapAnCau2("D");
    }//GEN-LAST:event_cb_D2ActionPerformed

    private void cb_A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_A3ActionPerformed
        fillDapAnCau3("A");
    }//GEN-LAST:event_cb_A3ActionPerformed

    private void cb_B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_B3ActionPerformed
        fillDapAnCau3("B");
    }//GEN-LAST:event_cb_B3ActionPerformed

    private void cb_C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_C3ActionPerformed
        fillDapAnCau3("C");
    }//GEN-LAST:event_cb_C3ActionPerformed

    private void cb_D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_D3ActionPerformed
        fillDapAnCau3("D");
    }//GEN-LAST:event_cb_D3ActionPerformed

    private void cb_A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_A4ActionPerformed
        fillDapAnCau4("A");
    }//GEN-LAST:event_cb_A4ActionPerformed

    private void cb_B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_B4ActionPerformed
        fillDapAnCau4("B");
    }//GEN-LAST:event_cb_B4ActionPerformed

    private void cb_C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_C4ActionPerformed
        fillDapAnCau4("C");
    }//GEN-LAST:event_cb_C4ActionPerformed

    private void cb_D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_D4ActionPerformed
        fillDapAnCau4("D");
    }//GEN-LAST:event_cb_D4ActionPerformed

    private void cb_A5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_A5ActionPerformed
        fillDapAnCau5("A");
    }//GEN-LAST:event_cb_A5ActionPerformed

    private void cb_B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_B5ActionPerformed
        fillDapAnCau5("B");
    }//GEN-LAST:event_cb_B5ActionPerformed

    private void cb_D5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_D5ActionPerformed
        fillDapAnCau5("D");
    }//GEN-LAST:event_cb_D5ActionPerformed

    private void bt_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_restartActionPerformed
        player.stop();
        playMp3();
        bt_pause.setEnabled(true);
        bt_resume.setEnabled(false);
    }//GEN-LAST:event_bt_restartActionPerformed

    private void bt_NopBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_NopBaiActionPerformed
        int dem = JOptionPane.showConfirmDialog(rootPane, "Nộp bài thi?", "Nộp bài", JOptionPane.YES_NO_OPTION);
        if (dem == 0) {

            checkBai();
            new JFrom_BaiThiChiTiet(this, true).setVisible(true);
            player.stop();
            insertThongTinBaiThi();
            dispose();
        }


    }//GEN-LAST:event_bt_NopBaiActionPerformed

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
    private javax.swing.JButton bt_Start;
    private javax.swing.JButton bt_Stop;
    private javax.swing.JButton bt_pause;
    private javax.swing.JButton bt_restart;
    private javax.swing.JButton bt_resume;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JCheckBox cb_A1;
    private javax.swing.JCheckBox cb_A2;
    private javax.swing.JCheckBox cb_A3;
    private javax.swing.JCheckBox cb_A4;
    private javax.swing.JCheckBox cb_A5;
    private javax.swing.JCheckBox cb_B1;
    private javax.swing.JCheckBox cb_B2;
    private javax.swing.JCheckBox cb_B3;
    private javax.swing.JCheckBox cb_B4;
    private javax.swing.JCheckBox cb_B5;
    private javax.swing.JCheckBox cb_C1;
    private javax.swing.JCheckBox cb_C2;
    private javax.swing.JCheckBox cb_C3;
    private javax.swing.JCheckBox cb_C4;
    private javax.swing.JCheckBox cb_C5;
    private javax.swing.JCheckBox cb_D1;
    private javax.swing.JCheckBox cb_D2;
    private javax.swing.JCheckBox cb_D3;
    private javax.swing.JCheckBox cb_D4;
    private javax.swing.JCheckBox cb_D5;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton first;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlb;
    private javax.swing.JLabel jlb_cau1;
    private javax.swing.JLabel jlb_cau2;
    private javax.swing.JPanel jpanel_tong;
    private javax.swing.JButton khoiDongLai;
    private javax.swing.JButton last;
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
    private javax.swing.JLabel lb_Minutes;
    private javax.swing.JLabel lb_Seconds;
    private javax.swing.JLabel lb_Time;
    private javax.swing.JLabel lb_dongHo;
    private javax.swing.JLabel lb_tongCau;
    private javax.swing.JButton next;
    private javax.swing.JLabel page1;
    private javax.swing.JLabel page2;
    private javax.swing.JLabel page2d;
    private javax.swing.JButton prev;
    private javax.swing.JLabel soCauDe;
    private javax.swing.JLabel soCauKho;
    private javax.swing.JLabel soCauTB;
    private javax.swing.JTable tb_LamBaiThi;
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
