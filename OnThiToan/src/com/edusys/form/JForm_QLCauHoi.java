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
    public static String path = null;
    public static boolean check = true;

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

    public CauHoi getFormInsert() {
        CauHoi ch = new CauHoi();
        int dem = tb_cauHoi.getSelectedRow();
        int doKho = cbb_mucDo.getSelectedIndex() + 1;
        int tl = cbb_TheLoai.getSelectedIndex();
        boolean role=true;
        if(tl==0){
            role=false;
        }else if(tl==1){
            role=true;
        }
        ch.setDoKho(doKho);
        ch.setRole_ID(role);
        ch.setTenBai(tf_TenBai.getText());
        ch.setCauHoi(path);
        ch.setDapAn(tf_dapAn.getText());
        return ch;
    }

    public CauHoi getFormUpdate() {
        int dem = tb_cauHoi.getSelectedRow();
        CauHoi ch = new CauHoi();
        int doKho = cbb_mucDo.getSelectedIndex() + 1;
        int id = Integer.valueOf(model.getValueAt(dem, 0).toString());
        int tl = cbb_TheLoai.getSelectedIndex();
        boolean role=true;
        if(tl==0){
            role=false;
        }else if(tl==1){
            role=true;
        }
        ch.setID_CauHoi(id);
        ch.setDoKho(doKho);
        ch.setRole_ID(role);
        ch.setTenBai(tf_TenBai.getText());
        ch.setCauHoi(path);
        ch.setDapAn(tf_dapAn.getText());
        return ch;
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
        path = model.getValueAt(dem, 1).toString();
        ImageIcon img = new ImageIcon(path);
        lb_image.setIcon(img);
        int item = Integer.valueOf(model.getValueAt(dem, 2).toString());
        if (item == 1) {
            cbb_mucDo.setSelectedIndex(0);
        } else if (item == 2) {
            cbb_mucDo.setSelectedIndex(1);
        } else if (item == 3) {
            cbb_mucDo.setSelectedIndex(2);
        }
        tf_TenBai.setText(model.getValueAt(dem, 3).toString());
        tf_dapAn.setText(model.getValueAt(dem, 4).toString());
        int theLoai = 0;
        if (model.getValueAt(dem, 5).toString().equalsIgnoreCase("bài tập")) {
            theLoai = 0;
        } else if (model.getValueAt(dem, 5).toString().equalsIgnoreCase("đề thi")) {
            theLoai = 1;
        }
        cbb_TheLoai.setSelectedIndex(theLoai);
    }

    public void insert() {
        try {
            checkImg();
            checkTenBai();
            checkDapAn();
            if (check == true) {
                CauHoi ch = getFormInsert();
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
        try {
            CauHoi ch = getFormUpdate();

            checkImg();
            checkTenBai();
            checkDapAn();
            if (check == true) {
                int i = JOptionPane.showConfirmDialog(rootPane, "Update câu hỏi ID: " + model.getValueAt(dem, 0).toString() + "?", "", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    CauHoiDao.update(ch);
                    JOptionPane.showMessageDialog(rootPane, "Update thành công!");
                    fillTable();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        int dem = tb_cauHoi.getSelectedRow();
        try {
            CauHoi ch = getFormUpdate();
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

    public void setImg() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter f = new FileNameExtensionFilter("Image", "png", "jpg", "ico");
        jfc.setFileFilter(f);
        jfc.setMultiSelectionEnabled(false);
        int i = jfc.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            xImage.save(file);
            path = "Hinh/CauHoi/" + jfc.getSelectedFile().getName();
            ImageIcon img = new ImageIcon(path);
            lb_image.setIcon(img);
        }

        lb_cauhoi.setText("");
    }

    public void refresh() {
        path = null;
        ImageIcon img = new ImageIcon("");
        lb_image.setIcon(img);
    }

    void checkImg() {
        if (path == null) {
            lb_cauhoi.setText("Chưa chọn file ảnh");
            lb_cauhoi.setForeground(Color.red);
            check = false;
            return;
        }
    }

    void checkTenBai() {
        if (tf_TenBai.getText().equals("")) {
            lb_loaibai.setText("Nhập loại bài câu hỏi");
            lb_loaibai.setForeground(Color.red);
            check = false;
            return;
        } else {
            lb_loaibai.setText("");
            check = true;
        }

    }

    void checkDapAn() {
        if (tf_dapAn.getText().equals("")&&cbb_TheLoai.getSelectedIndex()==0) {
            lb_DapAN.setText("Nhập đáp án");
            lb_DapAN.setForeground(Color.red);
            check = false;
            return;
        } else {
            lb_DapAN.setText("");
            check = true;
        }
        String checkDapAn = "[ABCDabcd]{1}";
        if (!tf_dapAn.getText().matches(checkDapAn)&&cbb_TheLoai.getSelectedIndex()==0) {
            lb_DapAN.setText("Đáp án là A,B,C,D");
            lb_DapAN.setForeground(Color.red);
            check = false;
            return;
        }
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
        lb_image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_dapAn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_TenBai = new javax.swing.JTextField();
        bt_Update = new javax.swing.JButton();
        lb_cauhoi = new javax.swing.JLabel();
        lb_loaibai = new javax.swing.JLabel();
        lb_DapAN = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbb_TheLoai = new javax.swing.JComboBox<>();
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

        lb_image.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lb_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_imageMouseClicked(evt);
            }
        });

        jLabel3.setText("Đáp án:");

        tf_dapAn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_dapAnKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lb_cauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_image, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(27, 27, 27)
                                .addComponent(cbb_mucDo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_TheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lb_loaibai, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bt_Them))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_dapAn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lb_DapAN, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(bt_Update)
                                .addGap(41, 41, 41)
                                .addComponent(bt_Refresh)
                                .addGap(80, 80, 80)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_image, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(lb_cauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_dapAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tf_TenBai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_loaibai, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_DapAN, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_Them)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_Refresh)
                        .addComponent(bt_Update)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jtp_Show.addTab("Cập nhật", jPanel2);

        tb_cauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_ Câu hỏi", "Câu hỏi", "Độ khó", "Tên bài", "Đáp án", "Thể loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(First)
                    .addComponent(Prev)
                    .addComponent(Next)
                    .addComponent(Last1)
                    .addComponent(bt_Delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtp_Show.addTab("Danh sách", jPanel3);

        javax.swing.GroupLayout pn_totalLayout = new javax.swing.GroupLayout(pn_total);
        pn_total.setLayout(pn_totalLayout);
        pn_totalLayout.setHorizontalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addComponent(jtp_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_ql)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_totalLayout.setVerticalGroup(
            pn_totalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_totalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_ql)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtp_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void lb_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_imageMouseClicked
        setImg();

    }//GEN-LAST:event_lb_imageMouseClicked

    private void tb_cauHoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cauHoiMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            shows();
            jtp_Show.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tb_cauHoiMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void bt_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_UpdateActionPerformed
        update();
    }//GEN-LAST:event_bt_UpdateActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void bt_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DeleteActionPerformed
        delete();
    }//GEN-LAST:event_bt_DeleteActionPerformed

    private void tf_TenBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TenBaiActionPerformed

    }//GEN-LAST:event_tf_TenBaiActionPerformed

    private void tf_TenBaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_TenBaiKeyReleased
        if (cbb_TheLoai.getSelectedIndex() == 0) {
            checkTenBai();

        }
    }//GEN-LAST:event_tf_TenBaiKeyReleased

    private void tf_dapAnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_dapAnKeyReleased
        if (cbb_TheLoai.getSelectedIndex() == 0) {
            checkDapAn();

        }
    }//GEN-LAST:event_tf_dapAnKeyReleased

    private void cbb_TheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TheLoaiActionPerformed
        if(cbb_TheLoai.getSelectedIndex()==1){
            tf_dapAn.setText("");
            tf_dapAn.setEnabled(false);
        }else if(cbb_TheLoai.getSelectedIndex()==0){
            tf_dapAn.setEnabled(true);
        }
    }//GEN-LAST:event_cbb_TheLoaiActionPerformed

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jtp_Show;
    private javax.swing.JLabel lb_DapAN;
    private javax.swing.JLabel lb_cauhoi;
    private javax.swing.JLabel lb_image;
    private javax.swing.JLabel lb_loaibai;
    private javax.swing.JLabel lb_ql;
    private javax.swing.JPanel pn_total;
    private javax.swing.JTable tb_cauHoi;
    private javax.swing.JTextField tf_TenBai;
    private javax.swing.JTextField tf_dapAn;
    // End of variables declaration//GEN-END:variables
}
