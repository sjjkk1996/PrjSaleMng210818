package ui;

import dao.DaoUser;
import vo.UserVO;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlUser extends JPanel {
    private JTextField tfName;
    private JTextField tfId;
    private JPasswordField pfPw;
    private JPasswordField rePw;
    private JTextField tfSearch;
    private JTable table;
    public PnlUser() {
        setLayout(null);
        DaoUser dao = new DaoUser();
        JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uC790 \uB4F1\uB85D");
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

        JButton btnRegist = new JButton("\uB4F1\uB85D");
        btnRegist.addActionListener(new ActionListener() {
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
                dao.registUser(new UserVO(name, id, pw));
            }
        });
        btnRegist.setBounds(98, 349, 118, 23);
        add(btnRegist);

        JLabel lblNewLabel_5 = new JLabel("\uC0AC\uC6A9\uC790 \uBAA9\uB85D");
        lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        lblNewLabel_5.setBounds(417, 10, 161, 58);
        add(lblNewLabel_5);

        tfSearch = new JTextField();
        tfSearch.setBounds(417, 101, 161, 21);
        add(tfSearch);
        tfSearch.setColumns(10);

        JButton btnSearch = new JButton("\uC870\uD68C");
        btnSearch.setBounds(592, 100, 97, 23);
        add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(417, 132, 441, 258);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
    }
}
