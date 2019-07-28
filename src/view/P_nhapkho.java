/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DBUtils;
import controller.h_Utils;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Nhapkho;

/**
 *
 * @author DELL
 */
public class P_nhapkho extends javax.swing.JPanel {

    /**
     * Creates new form P_nhapkho
     */
    h_Utils panel = new h_Utils();

    public void init_index() {

        if (panel.getNhanvien().getRole().equals("admin")) {
            btn_them.setEnabled(false);
            btn_suadonhang.setEnabled(false);
            btn_xoadonhang.setEnabled(false);
        }

    }

    int index = -1;
    List<Nhapkho> list = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model_searchNV = new DefaultTableModel();

    String maphieu;
    String hangcungcap;
    String ngaynhap;
    float tongtien;
    String xuatxu;
    String hansd;
    String manv1;
    String vitri;

    String ngay;

    boolean searchNV = false;

    public void setdata() {
        try {
            model.addColumn("Mã Phiếu Hàng");
            model.addColumn("Nhà Cung Cấp");
            model.addColumn("Ngày Nhập");
            model.addColumn("Tổng Tiền");
            model.addColumn("Xuất Xứ");
            model.addColumn("Hạn Sử Dụng");
            model.addColumn("Mã Nhân Viên");
            model.addColumn("Vị Trí");
            model_searchNV.addColumn("Mã nhân viên");
            model_searchNV.addColumn("Tên nhân viên");
            txt_madh.setEditable(false);
            txt_manv.setEditable(false);
            txt_ngaynhap.setEditable(false);
            txt_hansd.setEditable(true);
            tbl_bang.setModel(model);
            load_list();
            tbl_bang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    index = tbl_bang.getSelectedRow();
                    if (!searchNV && index >= 0) {
                        txt_madh.setEditable(false);
                        txt_madh.setText(tbl_bang.getValueAt(index, 0).toString());
                        if (tbl_bang.getValueAt(index, 1).toString().equalsIgnoreCase("Vinamilk-CT")) {
                            cbb_ncc.setSelectedIndex(0);
                        } else {
                            cbb_ncc.setSelectedIndex(1);
                        }
                        txt_ngaynhap.setText(tbl_bang.getValueAt(index, 2).toString());
                        txt_tongtien.setText(tbl_bang.getValueAt(index, 3).toString());
                        txt_xuatxu.setText(tbl_bang.getValueAt(index, 4).toString());
                        txt_hansd.setText(tbl_bang.getValueAt(index, 5).toString());
                        txt_manv.setText(tbl_bang.getValueAt(index, 6).toString());
                        if (tbl_bang.getValueAt(index, 7).toString().equalsIgnoreCase("Kệ 1")) {
                            cbb_vitri.setSelectedItem(0);
                        } else {
                            cbb_vitri.setSelectedItem(1);
                        }
                        tbl_bang.scrollRectToVisible(tbl_bang.getCellRect(index, 0, true));
                    }
                    if (searchNV) {
                        txt_manv.setText(tbl_bang.getValueAt(index, 0).toString());
                        index = -1;
                    }
                }
            });
        } catch (NullPointerException e2) {
            System.out.println(e2);

        }
        tbl_bang.setRowSelectionInterval(0, 0);

    }

    public void load_list() {
        model.setRowCount(0);
        try {
            Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select maPhieu, hangCungCap, ngayNhap, tongTien, xuatXu,hanSD , MaNV, viTri from Nhapkho order by maPhieu desc";
            ResultSet rs = statement.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                maphieu = rs.getString(1);
                hangcungcap = rs.getString(2);
                ngaynhap = rs.getString(3);
                tongtien = rs.getFloat(4);
                xuatxu = rs.getString(5);
                hansd = rs.getString(6);
                manv1 = rs.getString(7);
                vitri = rs.getString(8);

                list.add(new Nhapkho(maphieu, hangcungcap, ngaynhap, tongtien, xuatxu, hansd, manv1, vitri));
                Vector row = new Vector();
                row.add(maphieu);
                row.add(hangcungcap);
                row.add(ngaynhap);
                row.add(String.format("%.0f", tongtien));
                row.add(xuatxu);
                row.add(hansd);
                row.add(manv1);
                row.add(vitri);

                model.addRow(row);
            }

        } catch (SQLException e1) {
            System.out.println("Loadlist: " + e1);
        }
    }

    public boolean txt() {
        try {
            
            
            if (txt_madh.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Mã không được để trống");
                
                return false;
            }
            if (txt_tongtien.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Tổng tiền không được để trống");
                
                return false;
            }
         
            if (txt_manv.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
               
                return false;
            }
            
            
            
            if (!txt_tongtien.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "bạn phải nhập số ở tổng tiền");
                return false;
            }

           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi" + e);
            return false;
        }
        return true;
    }

    public P_nhapkho() {
        initComponents();
        setdata();
        //init_index();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_xuatxu = new javax.swing.JTextField();
        txt_hansd = new javax.swing.JTextField();
        txt_manv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_madh = new javax.swing.JTextField();
        txt_ngaynhap = new javax.swing.JTextField();
        txt_tongtien = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_suadonhang = new javax.swing.JButton();
        btn_xoadonhang = new javax.swing.JButton();
        cbb_ncc = new javax.swing.JComboBox();
        btn_lammoi = new javax.swing.JButton();
        btn_manv = new javax.swing.JButton();
        btn_tim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bang = new javax.swing.JTable();
        cbb_vitri = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 204));
        setMaximumSize(new java.awt.Dimension(915, 540));
        setMinimumSize(new java.awt.Dimension(915, 540));

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(915, 35));
        jPanel1.setMinimumSize(new java.awt.Dimension(915, 35));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(915, 35));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ ĐƠN NHẬP KHO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jLabel6.setText("Xuất xứ");

        jLabel7.setText("Hạn sử dụng");

        jLabel8.setText("Mã Nhân Viên:");

        jLabel9.setText("Vị Trí");

        txt_xuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_xuatxuActionPerformed(evt);
            }
        });

        txt_hansd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hansdActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã Phiếu Hàng:");

        jLabel3.setText("Thương Hiệu");

        jLabel4.setText("Ngày Nhập:");

        jLabel5.setText("Tổng Tiền:");

        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add-Folder-icon.png"))); // NOI18N
        btn_them.setText("Thêm Phiếu Hàng");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_suadonhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/system-software-update-icon.png"))); // NOI18N
        btn_suadonhang.setText("Sửa Phiếu Hàng");
        btn_suadonhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suadonhangActionPerformed(evt);
            }
        });

        btn_xoadonhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Actions-dialog-close-icon.png"))); // NOI18N
        btn_xoadonhang.setText("Xóa Phiếu Hàng");
        btn_xoadonhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoadonhangActionPerformed(evt);
            }
        });

        cbb_ncc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vinamilk-CT", "Vinamilk-CN", "Cô Gái Hà Lan", "Ông Thọ", "TH-trueMilk", " " }));

        btn_lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new-icon.png"))); // NOI18N
        btn_lammoi.setText("Làm Mới Phiếu ");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        btn_manv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search-icon.png"))); // NOI18N
        btn_manv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_manvActionPerformed(evt);
            }
        });

        btn_tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search-icon.png"))); // NOI18N
        btn_tim.setText("Tìm Phiếu Hàng");
        btn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timActionPerformed(evt);
            }
        });

        tbl_bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_bang);

        cbb_vitri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kệ 1", "Kệ 2", "Kệ 3", "Tồn Kho", "Khuyến Mại" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ngaynhap)
                            .addComponent(txt_tongtien)
                            .addComponent(txt_madh)
                            .addComponent(cbb_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_hansd)
                                .addComponent(txt_xuatxu)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txt_manv, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_manv)))
                            .addComponent(cbb_vitri, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_lammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_suadonhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_xoadonhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_tim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_madh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_xuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txt_hansd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_suadonhang)
                    .addComponent(cbb_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoadonhang)
                    .addComponent(txt_ngaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_manv))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9)
                        .addComponent(txt_tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbb_vitri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_lammoi)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tim)))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (txt()) {

            try {

                for (int i = 0; i < list.size(); i++) {
                    if (txt_madh.getText().equalsIgnoreCase(list.get(i).getMaphieu())) {
                        JOptionPane.showMessageDialog(this, "Mã phiếu nhập đã có vui lòng thử mã phiếu khác");

                    }
                }

                Connection connection = DBUtils.getConnection();
                String sql = "insert into Nhapkho values(?,?,?,?,?,?,?,?)";
                PreparedStatement pst1 = connection.prepareStatement(sql);
                pst1.setString(1, txt_madh.getText());
                pst1.setString(2, cbb_ncc.getSelectedItem().toString());
                pst1.setString(3, txt_ngaynhap.getText());
                pst1.setString(4, txt_tongtien.getText());
                pst1.setString(5, txt_xuatxu.getText());
                pst1.setString(6, txt_hansd.getText());
                pst1.setString(7, txt_manv.getText());
                pst1.setString(8, cbb_vitri.getSelectedItem().toString());
                pst1.executeUpdate();

                JOptionPane.showMessageDialog(this, "Lưu thành công!");

                load_list();
                searchNV = false;
                tbl_bang.setModel(model);

            } catch (Exception e4) {
                System.out.println("Lỗi" + e4);
            }

            // TODO add your handling code here:
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_xoadonhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoadonhangActionPerformed
        // TODO add your handling code here:
        if (index != -1) {
            int ret = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (ret == JOptionPane.YES_OPTION) {
                try {
                    Connection connection = DBUtils.getConnection();
                    Statement statement = connection.createStatement();
                    String sql2 = "Delete From Nhapkho where Maphieu = N'" + list.get(index).getMaphieu() + "'";
                    statement.executeUpdate(sql2);
                    load_list();
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    tbl_bang.setRowSelectionInterval(0, 0);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(this, "Lỗi xóa");
                }
            }
        }
    }//GEN-LAST:event_btn_xoadonhangActionPerformed

    private void btn_suadonhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suadonhangActionPerformed
        // TODO add your handling code here:
        if (txt()) {

            try {

                Connection connection = DBUtils.getConnection();
                String sql = "update nhapkho set hangCungCap=?, ngayNhap=?, tongTien=?, xuatXu=?, hanSD=?, MaNV=?, viTri=?"
                        + " where maPhieu=?";
                PreparedStatement pst1 = connection.prepareStatement(sql);
                pst1.setString(1, cbb_ncc.getSelectedItem().toString());
                pst1.setString(2, txt_ngaynhap.getText());
                pst1.setString(3, txt_tongtien.getText());
                pst1.setString(4, txt_xuatxu.getText());
                pst1.setString(5,txt_hansd.getText());
                pst1.setString(6, txt_manv.getText());
                pst1.setString(7, cbb_vitri.getSelectedItem().toString());
                pst1.setString(8, txt_madh.getText());

                pst1.executeUpdate();

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

                load_list();
                searchNV = false;
                tbl_bang.setModel(model);
            } catch (Exception e4) {
                System.out.println("Lỗi của update" + e4);
            }

        }

    }//GEN-LAST:event_btn_suadonhangActionPerformed

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance();
        txt_ngaynhap.setText(cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));
        txt_madh.setText("");
        txt_madh.setEditable(true);
        txt_tongtien.setText("");
        txt_xuatxu.setText("");
        txt_hansd.setText("");
        txt_manv.setText("");
        tbl_bang.setModel(model);
        searchNV = false;
        load_list();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timActionPerformed
        // TODO add your handling code here:
        boolean find_check = false;
        try {
            String find_id = JOptionPane.showInputDialog(this, "Nhập ngày muốn tìm kiếm");
            while (find_id.equals("")) {
                JOptionPane.showMessageDialog(this, "Không để trống ngày nhập!");
                find_id = JOptionPane.showInputDialog(this, "Nhập ngày muốn tìm kiếm");
            }
            for (int i = 0; i < list.size(); i++) {
                if (find_id.equalsIgnoreCase(list.get(i).getNgaynhap())) {

                    find_check = true;
                    tbl_bang.setRowSelectionInterval(i, i);
                }
            }

            if (!find_check) {
                JOptionPane.showMessageDialog(this, "ngày nhập không có trong dữ liệu!");
            }

        } catch (NullPointerException e2) {
        }
    }//GEN-LAST:event_btn_timActionPerformed

    private void btn_manvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_manvActionPerformed
        // TODO add your handling code here:
        try {
            String find_id = JOptionPane.showInputDialog(this, "Nhập Tên hoặc mã nhân viên cần tìm");
            boolean find_check = false;
            while (find_id.equals("")) {
                JOptionPane.showMessageDialog(this, "Không để trống mã!");
                find_id = JOptionPane.showInputDialog(this, "Nhập Tên hoặc mã nhân viên cần tìm");
            }

            try {
                model_searchNV.setRowCount(0);
                Connection connection = DBUtils.getConnection();
                String sql = "select * from Nhanvien where MaNV = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, find_id);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Vector row = new Vector();
                    find_check = true;
                    row.add(rs.getString(1));
                    row.add(rs.getString(2));
                    model_searchNV.addRow(row);
                }

                String sql2 = "select * from Nhanvien where Tennv like ?";
                PreparedStatement pst2 = connection.prepareStatement(sql2);
                pst2.setString(1, "%" + find_id + "%");
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    Vector row = new Vector();
                    find_check = true;
                    row.add(rs2.getString(1));
                    row.add(rs2.getString(2));
                    model_searchNV.addRow(row);
                }

                tbl_bang.setModel(model_searchNV);
                searchNV = true;

                if (!find_check) {
                    JOptionPane.showMessageDialog(this, "Không có nhân viên này!");
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_btn_manvActionPerformed

    private void txt_hansdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hansdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hansdActionPerformed

    private void txt_xuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_xuatxuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_xuatxuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_manv;
    private javax.swing.JButton btn_suadonhang;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_xoadonhang;
    private javax.swing.JComboBox cbb_ncc;
    private javax.swing.JComboBox<String> cbb_vitri;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_bang;
    private javax.swing.JTextField txt_hansd;
    private javax.swing.JTextField txt_madh;
    private javax.swing.JTextField txt_manv;
    private javax.swing.JTextField txt_ngaynhap;
    private javax.swing.JTextField txt_tongtien;
    private javax.swing.JTextField txt_xuatxu;
    // End of variables declaration//GEN-END:variables
}
