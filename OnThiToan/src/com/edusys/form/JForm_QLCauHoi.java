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
            model.addRow(new Object[]{x.getID_CauHoi(), x.getCauHoi(), x.getDoKho(), x.getTenBai(), x.getDapAn(), theLoai});
        }
    }

    public void shows() {
        int dem = tb_cauHoi.getSelectedRow();
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        CauHoi ch = CauHoiDao.selectById(id);
        tf_DapAnSai1.setText(ch.getDapAnSai1());
        tf_DapAnSai2.setText(ch.getDapAnSai2());
        tf_DapAnSai3.setText(ch.getDapAnSai3());
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
            tf_dapAn.setText(ch.getDapAn());
        
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
        ch.setDapAn(tf_dapAn.getText());
        ch.setDapAnSai1(tf_DapAnSai1.getText());
        ch.setDapAnSai2(tf_DapAnSai3.getText());
        ch.setDapAnSai3(tf_DapAnSai3.getText());
        return ch;
    }

    public void insert() {
        try {

            CauHoi ch = getForm();
            CauHoiDao.insert(ch);
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
            fillTable();

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
        tf_dapAn.setText(""); 
        tf_DapAnSai1.setText("");
        tf_DapAnSai2.setText("");
        tf_DapAnSai3.setText("");
        
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane3 = new javax.swing.JScrollPane();
        tf_dapAn = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tf_DapAnSai1 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tf_DapAnSai2 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tf_DapAnSai3 = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_cauHoi = new javax.swing.JTable();
        Last1 = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Prev = new javax.swing.JButton();
        First = new javax.swing.JButton();
        bt_Delete = new javax.swing.JButton();

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
        jLabel3.setText("Đáp án:");

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

        jScrollPane1.setViewportView(tp_CauHoi);

        jLabel6.setText("True:");

        jLabel7.setText("False 1:");

        jLabel8.setText("False 2:");

        jLabel9.setText("False 3:");

        jScrollPane3.setViewportView(tf_dapAn);

        jScrollPane4.setViewportView(tf_DapAnSai1);

        jScrollPane5.setViewportView(tf_DapAnSai2);

        jScrollPane6.setViewportView(tf_DapAnSai3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(23, 23, 23)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_Them)
                .addGap(42, 42, 42)
                .addComponent(bt_Update)
                .addGap(41, 41, 41)
                .addComponent(bt_Refresh)
                .addGap(83, 83, 83))
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_Them)
                            .addComponent(bt_Update)
                            .addComponent(bt_Refresh)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(First)
                    .addComponent(Prev)
                    .addComponent(Next)
                    .addComponent(Last1)
                    .addComponent(bt_Delete))
                .addContainerGap())
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
                .addComponent(jtp_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (cbb_TheLoai.getSelectedIndex() == 0) {

        }
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
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jtp_Show;
    private javax.swing.JLabel lb_ql;
    private javax.swing.JPanel pn_total;
    private javax.swing.JTable tb_cauHoi;
    private javax.swing.JTextPane tf_DapAnSai1;
    private javax.swing.JTextPane tf_DapAnSai2;
    private javax.swing.JTextPane tf_DapAnSai3;
    private javax.swing.JTextField tf_TenBai;
    private javax.swing.JTextPane tf_dapAn;
    private javax.swing.JTextPane tp_CauHoi;
    // End of variables declaration//GEN-END:variables
}
