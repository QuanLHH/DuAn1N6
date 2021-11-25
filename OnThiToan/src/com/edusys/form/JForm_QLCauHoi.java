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
import java.io.File;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        model = (DefaultTableModel) tb_cauHoi.getModel();
        setCbbMucDo();
        setCbbTheLoai();
        setCbbTenBai();
        fillTable();
    }

    public void setCbbMucDo() {
        ArrayList<CauHoi> list = CauHoiDao.selectDoKho();
        cbb_mucDo.removeAllItems();
        for (CauHoi x : list) {
            String doKho = null;
            if (x.getDoKho() == 1) {
                doKho = "Dễ";
            } else if (x.getDoKho() == 2) {
                doKho = "Trung bình";
            } else if (x.getDoKho() == 3) {
                doKho = "Khó";
            }
            cbb_mucDo.addItem(doKho);
        }
    }

    public void setCbbTheLoai() {
        ArrayList<CauHoi> list = CauHoiDao.selectRole_ID();
        cbb_TheLoai.removeAllItems();
        for (CauHoi x : list) {
            String theLoai = null;
            if (x.getRole_ID() == false) {
                theLoai = "Bài tập";
            } else if (x.getRole_ID() == true) {
                theLoai = "Đề thi";
            }
            cbb_TheLoai.addItem(theLoai);
        }
    }

    public void setCbbTenBai() {
        ArrayList<CauHoi> list = CauHoiDao.selecttenbai();
        cbb_viewTenBai.removeAllItems();
        for (CauHoi x : list) {
            cbb_viewTenBai.addItem(x.getTenBai());
        }
    }

    public void fillTable() {
        this.listCH = CauHoiDao.selectALL();
        model.setRowCount(0);
        for (CauHoi x : listCH) {
            String theLoai = null;
            if (x.getRole_ID() == false) {
                theLoai = "Bài tập";
            } else if (x.getRole_ID() == true) {
                theLoai = "Đề thi";
            }
            model.addRow(new Object[]{x.getID_CauHoi(), x.getCauHoi(), x.getDoKho(), x.getTenBai(), theLoai});
        }
    }

    public void shows() {
        int dem = tb_cauHoi.getSelectedRow();
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        CauHoi ch = CauHoiDao.selectById(id);
        tf_DapAn1.setText(ch.getDapAn1());
        tf_DapAn2.setText(ch.getDapAn2());
        tf_DapAn3.setText(ch.getDapAn3());
        tf_DapAn4.setText(ch.getDapAn4());
        tf_DapAnDung.setText(ch.getDapAnDung());
        tp_CauHoi.setText(model.getValueAt(dem, 1).toString());
        int item = Integer.valueOf(model.getValueAt(dem, 2).toString());
        if (item == 1) {
            cbb_mucDo.setSelectedIndex(0);
        } else if (item == 2) {
            cbb_mucDo.setSelectedIndex(1);
        } else if (item == 3) {
            cbb_mucDo.setSelectedIndex(2);
        }
        String tenBai = model.getValueAt(dem, 3).toString();
        if (!tenBai.equals("")) {
            tf_TenBai.setText(tenBai);
        }
        tf_DapAn1.setText(ch.getDapAn1());

        int theLoai = 0;
        if (model.getValueAt(dem, 4).toString().equalsIgnoreCase("bài tập")) {
            theLoai = 0;
        } else if (model.getValueAt(dem, 4).toString().equalsIgnoreCase("đề thi")) {
            theLoai = 1;
        }
        cbb_TheLoai.setSelectedIndex(theLoai);
    }

    public CauHoi getForm() {
        CauHoi ch = new CauHoi();
        int dem = tb_cauHoi.getSelectedRow();
        int doKho = cbb_mucDo.getSelectedIndex() + 1;
        int tl = cbb_TheLoai.getSelectedIndex();
        boolean role = true;
        if (tl == 0) {
            role = false;
        } else if (tl == 1) {
            role = true;
        }
        ch.setRole_ID(role);
        ch.setCauHoi(tp_CauHoi.getText());
        ch.setDoKho(doKho);
        ch.setTenBai(tf_TenBai.getText());
        ch.setDapAn1(tf_DapAn1.getText());
        ch.setDapAn2(tf_DapAn2.getText());
        ch.setDapAn3(tf_DapAn3.getText());
        ch.setDapAn4(tf_DapAn4.getText());
        ch.setDapAnDung(tf_DapAnDung.getText());
        return ch;
    }

    public boolean check() {
        try {
            if (checkCauHoi() == true && checkTenBai() == true && checkDapAnDung() == true
                    && checkDapAn1() == true && checkDapAn2() == true && checkDapAn3() == true
                    && checkDapAn4() == true) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void insert() {
        try {
            if (check() == true) {
                CauHoi ch = getForm();
                CauHoiDao.insert(ch);
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
                fillTable();
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
            int i = JOptionPane.showConfirmDialog(rootPane, "Update câu hỏi ID: " + model.getValueAt(dem, 0).toString() + "?", "", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                ch.setID_CauHoi(id);
                CauHoiDao.update(ch);
                JOptionPane.showMessageDialog(rootPane, "Update thành công!");
                fillTable();
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
                fillTable();
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
                        sleep(100);
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
        tf_TenBai.setText("");
        tf_DapAn1.setText("");
        tf_DapAn2.setText("");
        tf_DapAn3.setText("");
        tf_DapAn4.setText("");
        tf_DapAnDung.setText("");
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

    void setLabelDapAnDung(String chuoi) {
        lb_checkDapAN.setText(chuoi);
        lb_checkDapAN.setForeground(Color.red);
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

    public boolean checkTenBai() {
        try {
            if (tf_TenBai.getText().equals("")) {
                setLabelTenBai("Không để trống tên bài");
                check = false;
            } else {
                setLabelTenBai("");
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean checkDapAnDung() {
        try {
            if (tf_DapAnDung.getText().equals("")) {
                setLabelDapAnDung("Điền đáp án");
                check = false;
            } else {
                setLabelDapAnDung("");
                check = true;
            }
            String regexDapAn = "[ABCDabcd]{1}";
            if (!tf_DapAnDung.getText().matches(regexDapAn)) {
                setLabelDapAnDung("Đáp án A, B, C, D");
                check = false;
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
                setLabelDapAn4("Điền đáp án C");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_DapAn2 = new javax.swing.JLabel();
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
        jLabel4 = new javax.swing.JLabel();
        tf_TenBai = new javax.swing.JTextField();
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
        tf_DapAnDung = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lb_CheckDapAn1 = new javax.swing.JLabel();
        lb_CheckDapAn2 = new javax.swing.JLabel();
        lb_CheckDapAn3 = new javax.swing.JLabel();
        lb_CheckDapAn4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tf_DapAn1 = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        lb_checkTenBai = new javax.swing.JLabel();
        lb_checkDapAN = new javax.swing.JLabel();
        lb_checkCauHoi = new javax.swing.JLabel();
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

        lb_DapAn2.setText("jLabel13");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lb_ql.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        lb_ql.setForeground(new java.awt.Color(51, 51, 255));
        lb_ql.setText("                      Quản lý câu hỏi                     ");

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

        jLabel4.setText("Tên bài:");

        tf_TenBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TenBaiActionPerformed(evt);
            }
        });
        tf_TenBai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_TenBaiKeyReleased(evt);
            }
        });

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

        tf_DapAnDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_DapAnDungActionPerformed(evt);
            }
        });
        tf_DapAnDung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_DapAnDungKeyReleased(evt);
            }
        });

        jLabel10.setText("Đáp án đúng:");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lb_checkTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_DapAnDung, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_checkDapAN, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lb_checkCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lb_CheckDapAn3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(bt_Them)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(bt_Update)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(bt_Refresh))
                                                    .addComponent(lb_CheckDapAn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane6))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(1, 1, 1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_CheckDapAn2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_CheckDapAn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lb_checkCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tf_DapAnDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, 0)
                                .addComponent(lb_checkDapAN, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_checkTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lb_CheckDapAn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lb_CheckDapAn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lb_CheckDapAn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_CheckDapAn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Them)
                    .addComponent(bt_Update)
                    .addComponent(bt_Refresh))
                .addGap(23, 23, 23))
        );

        jtp_Show.addTab("Cập nhật", jPanel2);

        tb_cauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_ Câu hỏi", "Câu hỏi", "Độ khó", "Tên bài", "Thể loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(41, 41, 41)
                .addComponent(cbb_viewTenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_Delete)
                        .addGap(75, 75, 75)
                        .addComponent(First)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Last1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbb_viewTenBai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_totalLayout.createSequentialGroup()
                        .addComponent(lb_ql)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtp_Show))
                .addContainerGap())
        );
        pn_totalLayout.setVerticalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_ql)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtp_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }//GEN-LAST:event_bt_DeleteActionPerformed

    private void cbb_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TheLoaiActionPerformed
        if (cbb_TheLoai.getSelectedIndex() == 1) {
            tf_TenBai.setText("");
            tf_TenBai.setEnabled(false);
        } else if (cbb_TheLoai.getSelectedIndex() == 0) {
            tf_TenBai.setEnabled(true);
        }
    }//GEN-LAST:event_cbb_TheLoaiActionPerformed

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_UpdateActionPerformed
        update();
    }//GEN-LAST:event_bt_UpdateActionPerformed

    private void tf_TenBaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_TenBaiKeyReleased
        checkTenBai();
    }//GEN-LAST:event_tf_TenBaiKeyReleased

    private void tf_TenBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TenBaiActionPerformed

    }//GEN-LAST:event_tf_TenBaiActionPerformed

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
        fillTable();
    }//GEN-LAST:event_bt_ThemActionPerformed

    private void tf_DapAnDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_DapAnDungActionPerformed
        // TODO add your handling code here:á
    }//GEN-LAST:event_tf_DapAnDungActionPerformed

    private void tf_DapAnDungKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_DapAnDungKeyReleased
        checkDapAnDung();
    }//GEN-LAST:event_tf_DapAnDungKeyReleased

    private void cbb_viewTenBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_viewTenBaiActionPerformed
        // TODO add your handling code here:
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
    private javax.swing.JComboBox<String> cbb_TheLoai;
    private javax.swing.JComboBox<String> cbb_mucDo;
    private javax.swing.JComboBox<String> cbb_viewTenBai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel lb_checkDapAN;
    private javax.swing.JLabel lb_checkTenBai;
    private javax.swing.JLabel lb_ql;
    private javax.swing.JPanel pn_total;
    private javax.swing.JTable tb_cauHoi;
    private javax.swing.JTextPane tf_DapAn1;
    private javax.swing.JTextPane tf_DapAn2;
    private javax.swing.JTextPane tf_DapAn3;
    private javax.swing.JTextPane tf_DapAn4;
    private javax.swing.JTextField tf_DapAnDung;
    private javax.swing.JTextField tf_TenBai;
    private javax.swing.JTextPane tp_CauHoi;
    // End of variables declaration//GEN-END:variables
}
