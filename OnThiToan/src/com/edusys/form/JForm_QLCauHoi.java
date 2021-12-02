/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.form;

import PakagesClass.CauHoi;
import com.edusys.dao.CauHoiDAO;
import com.edusys.utils.XImage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taola
 */
public class JForm_QLCauHoi extends javax.swing.JDialog {

    com.edusys.dao.CauHoiDAO CauHoiDao = new CauHoiDAO();
    com.edusys.utils.XImage xImage = new XImage();
    ArrayList<CauHoi> listCH = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    public int id_cauhoi = 0;
    public boolean check = false;
    public static String doKho = null;
    public static String theLoai = null;

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
        this.listCH = CauHoiDao.selectALL();
        this.model = (DefaultTableModel) tb_cauHoi.getModel();
        setCbbMucDo();
        setCbbTheLoai();
        setCbbTenBai();
        fillTable();
    }

    public void setCbbMucDo() {
        ArrayList<CauHoi> list = CauHoiDao.selectDoKho();
        cbb_mucDo.removeAllItems();
        for (CauHoi x : list) {
            setDoKho(x.getDoKho());
            cbb_mucDo.addItem(doKho);
        }
    }

    public void setCbbTheLoai() {
        ArrayList<CauHoi> list = CauHoiDao.selectRole_ID();
        cbb_TheLoai.removeAllItems();
        cbb_ViewTheLoai.removeAllItems();
        for (CauHoi x : list) {
            setTheLoai(x.getRole_ID());
            cbb_TheLoai.addItem(theLoai);
            cbb_ViewTheLoai.addItem(theLoai);
        }
        fillTableTheLoai();
    }

    public void setCbbTenBai() {
        ArrayList<CauHoi> list = CauHoiDao.selecttenbai();
        cbb_viewTenBai.removeAllItems();
        cbb_TenBai.removeAllItems();
        for (CauHoi x : list) {
            cbb_viewTenBai.addItem(x.getTenBai());
            cbb_TenBai.addItem(x.getTenBai());
        }
    }

    public void fillTableTenBai() {
        String item = (String) cbb_viewTenBai.getSelectedItem();
        this.listCH = CauHoiDao.selectByTenBai(item);
        this.model.setRowCount(0);
        for (CauHoi x : listCH) {
            setTheLoai(x.getRole_ID());
            setDoKho(x.getDoKho());
            model.addRow(new Object[]{x.getID_CauHoi(), doKho, x.getTenBai(), theLoai});
        }
    }

    public void fillTableTheLoai() {
        int id = cbb_ViewTheLoai.getSelectedIndex();
        this.listCH = CauHoiDao.selectByTheLoai(id);
        model.setRowCount(0);
        for (CauHoi x : listCH) {
            setTheLoai(x.getRole_ID());
            setDoKho(x.getDoKho());
            model.addRow(new Object[]{x.getID_CauHoi(), doKho, x.getTenBai(), theLoai});
        }
    }

    public void fillTable() {
        this.listCH = CauHoiDao.selectALL();
        model.setRowCount(0);
        for (CauHoi x : listCH) {
            setTheLoai(x.getRole_ID());
            setDoKho(x.getDoKho());
            model.addRow(new Object[]{x.getID_CauHoi(), doKho, x.getTenBai(), theLoai});
        }
    }

    public void shows() {
        int dem = tb_cauHoi.getSelectedRow();
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        CauHoi ch = CauHoiDao.selectById(id);
        tp_CauHoi.setText(ch.getCauHoi());
        tf_DapAn1.setText(ch.getDapAn1());
        tf_DapAn2.setText(ch.getDapAn2());
        tf_DapAn3.setText(ch.getDapAn3());
        tf_DapAn4.setText(ch.getDapAn4());
        String doKho = model.getValueAt(dem, 1).toString();
        cbb_mucDo.setSelectedItem(doKho);
        tf_DapAn1.setText(ch.getDapAn1());
        if (ch.getDapAnDung().equalsIgnoreCase("A")) {
            rd_A.setSelected(true);
            rd_B.setText("");
            rd_C.setText("");
            rd_D.setText("");
        } else if (ch.getDapAnDung().equalsIgnoreCase("B")) {
            rd_B.setSelected(true);
            rd_A.setText("");
            rd_C.setText("");
            rd_D.setText("");
        } else if (ch.getDapAnDung().equalsIgnoreCase("C")) {
            rd_C.setSelected(true);
            rd_A.setText("");
            rd_B.setText("");
            rd_D.setText("");
        } else if (ch.getDapAnDung().equalsIgnoreCase("D")) {
            rd_D.setSelected(true);
            rd_A.setText("");
            rd_B.setText("");
            rd_D.setText("");
        }
        String itemTL = model.getValueAt(dem, 3).toString();
        if(itemTL.equalsIgnoreCase("Bài tập")){
            cbb_TheLoai.setSelectedIndex(0);
        }else if(itemTL.equalsIgnoreCase("Đề thi")){
            cbb_TheLoai.setSelectedIndex(1);
        }
    }

    public CauHoi getForm() {
        CauHoi ch = new CauHoi();
        int dem = tb_cauHoi.getSelectedRow();
        int doKho = cbb_mucDo.getSelectedIndex() + 1;
        int tl = cbb_TheLoai.getSelectedIndex();
        String tenBai = (String) cbb_TenBai.getSelectedItem();
        boolean role = true;
        if (tl == 0) {
            role = false;
        } else if (tl == 1) {
            role = true;
        }
        String dapAn = null;
        if (rd_A.isSelected()) {
            dapAn = "A";
        } else if (rd_B.isSelected()) {
            dapAn = "B";
        } else if (rd_C.isSelected()) {
            dapAn = "C";
        } else if (rd_D.isSelected()) {
            dapAn = "D";
        }
        ch.setRole_ID(role);
        ch.setCauHoi(tp_CauHoi.getText());
        ch.setDoKho(doKho);
        if (cbb_TheLoai.getSelectedIndex() == 0) {
            ch.setTenBai(tenBai);
        }
        ch.setDapAn1(tf_DapAn1.getText());
        ch.setDapAn2(tf_DapAn2.getText());
        ch.setDapAn3(tf_DapAn3.getText());
        ch.setDapAn4(tf_DapAn4.getText());
        ch.setDapAnDung(dapAn);
        return ch;
    }

    public boolean check() {
        try {
            if (checkCauHoi() == true && checkDapAn1() == true && checkDapAn2() == true
                    && checkDapAn3() == true && checkDapAn4() == true) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void insert() {

        try {
            if (check() == true && checkDapAn() == true) {
                CauHoi ch = getForm();
                CauHoiDao.insert(ch);
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int dem = tb_cauHoi.getSelectedRow();
        //check
        try {
            if (dem < 0) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn row cần update!");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CauHoi ch = getForm();
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        //update
        try {
            if (check() == true && checkDapAn() == true) {
                int i = JOptionPane.showConfirmDialog(rootPane, "Update câu hỏi ID: " + model.getValueAt(dem, 0).toString() + "?", "", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    ch.setID_CauHoi(id);
                    CauHoiDao.update(ch);
                    JOptionPane.showMessageDialog(rootPane, "Update thành công!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        int dem = tb_cauHoi.getSelectedRow();
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "Xóa câu hỏi ID: " + model.getValueAt(dem, 0).toString() + "?", "", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
                CauHoiDao.delete(id);
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");

            }

        } catch (Exception e) {
            e.printStackTrace();
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
                        sleep(70);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    public void refresh() {
        cbb_mucDo.setSelectedIndex(0);
        cbb_TheLoai.setSelectedIndex(0);
        tp_CauHoi.setText("");
        tf_DapAn1.setText("");
        tf_DapAn2.setText("");
        tf_DapAn3.setText("");
        tf_DapAn4.setText("");
        rd_A.setSelected(true);
    }

    public void next() {
        try {
            int dem = tb_cauHoi.getSelectedRow() + 1;
            if (dem >= listCH.size()) {
                dem = 0;
            }
            tb_cauHoi.setRowSelectionInterval(dem, dem);
            shows();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void prev() {
        try {
            int dem = tb_cauHoi.getSelectedRow() - 1;
            if (dem < 0) {
                dem = listCH.size() - 1;
            }
            tb_cauHoi.setRowSelectionInterval(dem, dem);
            shows();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void first() {
        tb_cauHoi.setRowSelectionInterval(0, 0);
        shows();
    }

    public void last() {
        tb_cauHoi.setRowSelectionInterval(listCH.size() - 1, listCH.size() - 1);
        shows();
    }

    // set text, color label
    void setLabelCauHoi(String chuoi) {
        lb_checkCauHoi.setText(chuoi);
        lb_checkCauHoi.setForeground(Color.red);
    }

    void setLabelTenBai(String chuoi) {
        lb_checkTenBai.setText(chuoi);
        lb_checkTenBai.setForeground(Color.red);
    }

    void setLabelDapAn1(String chuoi) {
        lb_CheckDapAn1.setText(chuoi);
        lb_CheckDapAn1.setForeground(Color.red);
    }

    void setLabelDapAn2(String chuoi) {
        lb_CheckDapAn2.setText(chuoi);
        lb_CheckDapAn2.setForeground(Color.red);
    }

    void setLabelDapAn3(String chuoi) {
        lb_CheckDapAn3.setText(chuoi);
        lb_CheckDapAn3.setForeground(Color.red);
    }

    void setLabelDapAn4(String chuoi) {
        lb_CheckDapAn4.setText(chuoi);
        lb_CheckDapAn4.setForeground(Color.red);
    }

    public boolean checkCauHoi() {
        try {
            if (tp_CauHoi.getText().equals("")) {
                setLabelCauHoi("Không để trống câu hỏi");
                check = false;
            } else {
                setLabelCauHoi("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkDapAn1() {
        try {
            if (tf_DapAn1.getText().equals("")) {
                setLabelDapAn1("Điền đáp án A");
                check = false;
            } else {
                setLabelDapAn1("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkDapAn2() {
        try {
            if (tf_DapAn2.getText().equals("")) {
                setLabelDapAn2("Điền đáp án B");
                check = false;
            } else {
                setLabelDapAn2("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkDapAn3() {
        try {
            if (tf_DapAn3.getText().equals("")) {
                setLabelDapAn3("Điền đáp án C");
                check = false;
            } else {
                setLabelDapAn3("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkDapAn4() {
        try {
            if (tf_DapAn4.getText().equals("")) {
                setLabelDapAn4("Điền đáp án D");
                check = false;
            } else {
                setLabelDapAn4("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public String setDoKho(int dkho) {
        if (dkho == 1) {
            doKho = "Dễ";
        } else if (dkho == 2) {
            doKho = "Trung bình";
        } else if (dkho == 3) {
            doKho = "Khó";
        }
        return doKho;
    }

    public boolean checkDapAn() {
        try {
            if (rd_A.isSelected() == false && rd_B.isSelected() == false && rd_C.isSelected() == false && rd_D.isSelected() == false) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn đáp án đúng!");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public String setTheLoai(boolean tloai) {
        if (tloai == false) {
            theLoai = "Bài tập";
        } else if (tloai == true) {
            theLoai = "Đề thi";
        }
        return theLoai;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_DapAn2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pn_total = new javax.swing.JPanel();
        lb_ql = new javax.swing.JLabel();
        jtp_Show = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        bt_Them = new javax.swing.JButton();
        bt_Refresh = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbb_mucDo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bt_Update = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbb_TheLoai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tp_CauHoi = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tf_DapAn2 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tf_DapAn3 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tf_DapAn4 = new javax.swing.JTextPane();
        lb_CheckDapAn1 = new javax.swing.JLabel();
        lb_CheckDapAn2 = new javax.swing.JLabel();
        lb_CheckDapAn3 = new javax.swing.JLabel();
        lb_CheckDapAn4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tf_DapAn1 = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        lb_checkTenBai = new javax.swing.JLabel();
        lb_checkCauHoi = new javax.swing.JLabel();
        rd_A = new javax.swing.JCheckBox();
        rd_B = new javax.swing.JCheckBox();
        rd_C = new javax.swing.JCheckBox();
        rd_D = new javax.swing.JCheckBox();
        cbb_TenBai = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_cauHoi = new javax.swing.JTable();
        Last1 = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Prev = new javax.swing.JButton();
        First = new javax.swing.JButton();
        bt_Delete = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbb_viewTenBai = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        cbb_ViewTheLoai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        lb_DapAn2.setText("jLabel13");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pn_total.setBackground(new java.awt.Color(204, 204, 255));

        lb_ql.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        lb_ql.setForeground(new java.awt.Color(51, 51, 255));
        lb_ql.setText("                             Quản lý câu hỏi                          ");

        bt_Them.setText("Thêm");
        bt_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemActionPerformed(evt);
            }
        });

        bt_Refresh.setText("Refresh");
        bt_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_RefreshActionPerformed(evt);
            }
        });

        jLabel2.setText("Mức độ:");

        cbb_mucDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_mucDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_mucDoActionPerformed(evt);
            }
        });

        jLabel1.setText("Câu hỏi:");

        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Đáp án");

        bt_Update.setText("Sửa");
        bt_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_UpdateActionPerformed(evt);
            }
        });

        jLabel5.setText("Thể loại:");

        cbb_TheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_TheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TheLoaiActionPerformed(evt);
            }
        });

        tp_CauHoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tp_CauHoiKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tp_CauHoi);

        jLabel6.setText("A");

        jLabel7.setText("B");

        jLabel8.setText("C");

        jLabel9.setText("D");

        tf_DapAn2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_DapAn2KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tf_DapAn2);

        tf_DapAn3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_DapAn3KeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tf_DapAn3);

        tf_DapAn4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_DapAn4KeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tf_DapAn4);

        lb_CheckDapAn1.setPreferredSize(new java.awt.Dimension(40, 14));

        lb_CheckDapAn2.setPreferredSize(new java.awt.Dimension(40, 14));

        lb_CheckDapAn3.setPreferredSize(new java.awt.Dimension(40, 14));

        lb_CheckDapAn4.setPreferredSize(new java.awt.Dimension(40, 14));

        tf_DapAn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_DapAn1KeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tf_DapAn1);

        buttonGroup1.add(rd_A);
        rd_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_AActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_B);
        rd_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_BActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_C);
        rd_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_CActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_D);
        rd_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_DActionPerformed(evt);
            }
        });

        cbb_TenBai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_TenBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TenBaiActionPerformed(evt);
            }
        });

        jLabel13.setText("Tên bài:");

        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Đáp án đúng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rd_B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lb_CheckDapAn2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(151, 151, 151)
                                        .addComponent(bt_Them)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bt_Update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bt_Refresh)
                                        .addContainerGap())
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lb_CheckDapAn4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rd_D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lb_CheckDapAn3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rd_C, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(lb_checkCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(cbb_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13))
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lb_CheckDapAn1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14)
                                .addComponent(lb_checkTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addContainerGap())
                            .addComponent(rd_A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lb_checkCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lb_checkTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rd_A, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_CheckDapAn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rd_B, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_CheckDapAn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_C, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8)))
                .addGap(2, 2, 2)
                .addComponent(lb_CheckDapAn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_D, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_CheckDapAn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Them)
                    .addComponent(bt_Update)
                    .addComponent(bt_Refresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jtp_Show.addTab("Cập nhật", jPanel2);

        tb_cauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_ Câu hỏi", "Độ khó", "Tên bài", "Thể loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_cauHoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cauHoiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_cauHoi);
        if (tb_cauHoi.getColumnModel().getColumnCount() > 0) {
            tb_cauHoi.getColumnModel().getColumn(0).setHeaderValue("ID_ Câu hỏi");
            tb_cauHoi.getColumnModel().getColumn(1).setHeaderValue("Độ khó");
            tb_cauHoi.getColumnModel().getColumn(2).setHeaderValue("Tên bài");
            tb_cauHoi.getColumnModel().getColumn(3).setHeaderValue("Thể loại");
        }

        Last1.setText(">|");
        Last1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Last1ActionPerformed(evt);
            }
        });

        Next.setText(">>");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Prev.setText("<<");
        Prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevActionPerformed(evt);
            }
        });

        First.setText("|<");
        First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstActionPerformed(evt);
            }
        });

        bt_Delete.setText("Xóa");
        bt_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_DeleteActionPerformed(evt);
            }
        });

        jLabel12.setText("Tên bài:");

        cbb_viewTenBai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_viewTenBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_viewTenBaiActionPerformed(evt);
            }
        });

        jButton1.setText("Fill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbb_ViewTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_ViewTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ViewTheLoaiActionPerformed(evt);
            }
        });

        jLabel10.setText("Thể loại:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(207, Short.MAX_VALUE)
                        .addComponent(bt_Delete)
                        .addGap(75, 75, 75)
                        .addComponent(First)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Last1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(41, 41, 41)
                                .addComponent(cbb_viewTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_ViewTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbb_ViewTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_viewTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(First)
                    .addComponent(Prev)
                    .addComponent(Next)
                    .addComponent(Last1)
                    .addComponent(bt_Delete))
                .addGap(51, 51, 51))
        );

        jtp_Show.addTab("Danh sách", jPanel3);

        javax.swing.GroupLayout pn_totalLayout = new javax.swing.GroupLayout(pn_total);
        pn_total.setLayout(pn_totalLayout);
        pn_totalLayout.setHorizontalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtp_Show)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addComponent(lb_ql)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_totalLayout.setVerticalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_ql)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtp_Show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 542, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Last1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Last1ActionPerformed
        last();
    }//GEN-LAST:event_Last1ActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        next();
    }//GEN-LAST:event_NextActionPerformed

    private void PrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevActionPerformed
        prev();
    }//GEN-LAST:event_PrevActionPerformed

    private void FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstActionPerformed
        first();
    }//GEN-LAST:event_FirstActionPerformed

    private void tb_cauHoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cauHoiMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            shows();
            check();
            jtp_Show.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tb_cauHoiMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void bt_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DeleteActionPerformed
        delete();
        fillTableTenBai();

    }//GEN-LAST:event_bt_DeleteActionPerformed

    private void cbb_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TheLoaiActionPerformed


    }//GEN-LAST:event_cbb_TheLoaiActionPerformed

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_UpdateActionPerformed
        update();
        fillTableTheLoai();

    }//GEN-LAST:event_bt_UpdateActionPerformed

    private void cbb_mucDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_mucDoActionPerformed
        int i = cbb_mucDo.getSelectedIndex() + 1;
        System.out.println(i);
    }//GEN-LAST:event_cbb_mucDoActionPerformed

    private void bt_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_RefreshActionPerformed
        refresh();
        cbb_mucDo.setSelectedIndex(0);
    }//GEN-LAST:event_bt_RefreshActionPerformed

    private void bt_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemActionPerformed
        insert();
        fillTableTheLoai();

    }//GEN-LAST:event_bt_ThemActionPerformed

    private void cbb_viewTenBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_viewTenBaiActionPerformed
        fillTableTenBai();
    }//GEN-LAST:event_cbb_viewTenBaiActionPerformed

    private void tp_CauHoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tp_CauHoiKeyReleased
        checkCauHoi();
    }//GEN-LAST:event_tp_CauHoiKeyReleased

    private void tf_DapAn1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_DapAn1KeyReleased
        checkDapAn1();
    }//GEN-LAST:event_tf_DapAn1KeyReleased

    private void tf_DapAn2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_DapAn2KeyReleased
        checkDapAn2();
    }//GEN-LAST:event_tf_DapAn2KeyReleased

    private void tf_DapAn3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_DapAn3KeyReleased
        checkDapAn3();
    }//GEN-LAST:event_tf_DapAn3KeyReleased

    private void tf_DapAn4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_DapAn4KeyReleased
        checkDapAn4();
    }//GEN-LAST:event_tf_DapAn4KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fillTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rd_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_CActionPerformed
        rd_A.setText("");
        rd_B.setText("");
        rd_C.setText("Đáp án đúng");
        rd_D.setText("");
    }//GEN-LAST:event_rd_CActionPerformed

    private void rd_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_AActionPerformed
        rd_A.setText("Đáp án đúng");
        rd_B.setText("");
        rd_C.setText("");
        rd_D.setText("");
    }//GEN-LAST:event_rd_AActionPerformed

    private void rd_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_BActionPerformed
        rd_A.setText("");
        rd_B.setText("Đáp án đúng");
        rd_C.setText("");
        rd_D.setText("");
    }//GEN-LAST:event_rd_BActionPerformed

    private void rd_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_DActionPerformed
        rd_A.setText("");
        rd_B.setText("");
        rd_C.setText("");
        rd_D.setText("Đáp án đúng");
    }//GEN-LAST:event_rd_DActionPerformed

    private void cbb_TenBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TenBaiActionPerformed

    }//GEN-LAST:event_cbb_TenBaiActionPerformed

    private void cbb_ViewTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ViewTheLoaiActionPerformed
        fillTableTheLoai();
    }//GEN-LAST:event_cbb_ViewTheLoaiActionPerformed

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
    private javax.swing.JButton First;
    private javax.swing.JButton Last1;
    private javax.swing.JButton Next;
    private javax.swing.JButton Prev;
    private javax.swing.JButton bt_Delete;
    private javax.swing.JButton bt_Refresh;
    private javax.swing.JButton bt_Them;
    private javax.swing.JButton bt_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbb_TenBai;
    private javax.swing.JComboBox<String> cbb_TheLoai;
    private javax.swing.JComboBox<String> cbb_ViewTheLoai;
    private javax.swing.JComboBox<String> cbb_mucDo;
    private javax.swing.JComboBox<String> cbb_viewTenBai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jtp_Show;
    private javax.swing.JLabel lb_CheckDapAn1;
    private javax.swing.JLabel lb_CheckDapAn2;
    private javax.swing.JLabel lb_CheckDapAn3;
    private javax.swing.JLabel lb_CheckDapAn4;
    private javax.swing.JLabel lb_DapAn2;
    private javax.swing.JLabel lb_checkCauHoi;
    private javax.swing.JLabel lb_checkTenBai;
    private javax.swing.JLabel lb_ql;
    private javax.swing.JPanel pn_total;
    private javax.swing.JCheckBox rd_A;
    private javax.swing.JCheckBox rd_B;
    private javax.swing.JCheckBox rd_C;
    private javax.swing.JCheckBox rd_D;
    private javax.swing.JTable tb_cauHoi;
    private javax.swing.JTextPane tf_DapAn1;
    private javax.swing.JTextPane tf_DapAn2;
    private javax.swing.JTextPane tf_DapAn3;
    private javax.swing.JTextPane tf_DapAn4;
    private javax.swing.JTextPane tp_CauHoi;
    // End of variables declaration//GEN-END:variables
}
