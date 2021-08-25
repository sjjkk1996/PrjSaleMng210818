package ui;

import dao.DaoUser;
import vo.UserVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlCustomer extends JPanel {
    private JTextField tfName;
    private JTextField tfId;
    private JPasswordField pfPw;
    private JPasswordField rePw;
    private JTextField tfSearch;
    private JTable table;
    private DefaultTableModel model;

    public PnlCustomer() {
        setLayout(null);
        DaoUser dao = new DaoUser();
        model = new DefaultTableModel(
                new String[] {"CUSTOMER_ID","이름","주소","도시명","전화번호"},0);

        JLabel lblNewLabel = new JLabel("\uACE0\uAC1D \uB4F1\uB85D");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        lblNewLabel.setBounds(74, 10, 161, 58);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        lblNewLabel_1.setBounds(12, 101, 118, 18);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("ID");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        lblNewLabel_2.setBounds(12, 152, 118, 15);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Password");
        lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        lblNewLabel_3.setBounds(12, 203, 118, 15);
        add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("RePass");
        lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        lblNewLabel_4.setBounds(12, 253, 118, 15);
        add(lblNewLabel_4);

        tfName = new JTextField();
        tfName.setBounds(165, 101, 116, 21);
        add(tfName);
        tfName.setColumns(10);

        tfId = new JTextField();
        tfId.setBounds(165, 149, 116, 21);
        add(tfId);
        tfId.setColumns(10);

        pfPw = new JPasswordField();
        pfPw.setBounds(165, 200, 116, 21);
        add(pfPw);

        rePw = new JPasswordField();
        rePw.setBounds(165, 250, 116, 21);
        add(rePw);

        JButton btnRegist2 = new JButton("\uB4F1\uB85D");
        btnRegist2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = tfId.getText();
                String name = tfName.getText();
                String pw = new String(pfPw.getPassword());
                String rePass = new String(rePw.getPassword());
                if(name.equals(""))return;
                if(id.equals(""))return;
                if(pw.equals(""))return;
                if(rePass.equals(""))return;
                if(!pw.equals(rePass))return;
                if(dao.registUser(new UserVO(name, id, pw))){
                    JOptionPane.showMessageDialog(null,"등록에 성공했습니다.");
                }else{
                    JOptionPane.showMessageDialog(null,"등록에 실패했습니다.");
                }


            }
        });
        btnRegist2.setBounds(74, 418, 118, 23);
        add(btnRegist2);

        JLabel lblNewLabel_5 = new JLabel("\uACE0\uAC1D \uBAA9\uB85D");
        lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        lblNewLabel_5.setBounds(323, 10, 161, 58);
        add(lblNewLabel_5);

        tfSearch = new JTextField();
        tfSearch.setBounds(323, 78, 161, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        JButton btnSearch = new JButton("\uC870\uD68C");

        btnSearch.setBounds(496, 77, 97, 23);
        add(btnSearch);

        table = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(323, 106, 550, 335);
        add(scrollPane);
        scrollPane.setViewportView(table);

        btnSearch.addActionListener(e->{
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
            //여기서 while문은 테이블에 중복적으로 데이터들이 쌓이는 것을 방지해주는 코드
            while (table.getRowCount()>0){
                model.removeRow(0);//0번째로하면 맨위줄부터 순차적으로 삭제를하는데 while문으로 계속해서 반복하기때문에 결국 남는건없어진다.
            }
            String srch = tfSearch.getText();
            model = dao.getUserList(model, srch);
            table.setModel(model);
            model.fireTableDataChanged();
        });
    }
}
