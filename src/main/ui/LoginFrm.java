package ui;

import controller.MainController;
import dao.DaoUser;
import sun.applet.Main;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//서로 관계없는 타입을 interface로 묶는다
public class LoginFrm extends BasicFrm implements ActionListener {
    private JPanel pnlCenter, pnlSouth, pnlNorth;
    private JLabel lbId,lbPw;
    private JTextField tfId;
    private JPasswordField pfPw;
    private JButton btnLogin, btnCancel, btnJoin;

    public LoginFrm() {super(300, 170, "Login");}

    @Override
    public void init() {
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();
        pnlNorth = new JPanel();
        lbId = new JLabel("ID: ");
        lbPw = new JLabel("PW: ");
        tfId = new JTextField(10);
        pfPw = new JPasswordField(10);
        tfId.setText("ADMIN");
        pfPw.setText("1");
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        btnJoin = new JButton("Join");
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        btnJoin.addActionListener(this);



    }

    @Override
    public void arrange() {
        pnlNorth.add(lbId);
        pnlNorth.add(tfId);
        pnlCenter.add(lbPw);
        pnlCenter.add(pfPw);
        pnlSouth.add(btnLogin);
        pnlSouth.add(btnCancel);
        pnlSouth.add(btnJoin);
        add(pnlCenter,"Center");
        add(pnlSouth, "South");
        add(pnlNorth,"North");
    }
    public void actionPerformed(ActionEvent e){
        String id = tfId.getText();
        String pw = new String(pfPw.getPassword());
        if(e.getSource() == btnLogin){
            if(id==null || id.equals("")){
                JOptionPane.showMessageDialog(null,"아이디를 확인하세요");
                tfId.requestFocus();
                return;
            }
            if(pw==null || pw.equals("")){
                JOptionPane.showMessageDialog(null,"비밀번호를 확인하세요");
                pfPw.requestFocus();
                return;
            }
            UserVO userVO = new DaoUser().checkLogin(id,pw);//UserVO가 넘어온다.
            //좌측은 선언
            if(userVO==null){
                JOptionPane.showMessageDialog(null,"없는 아이디 입니다.");
                tfId.setText(""); //아이디 털어주기
                pfPw.setText(""); //비밀번호 털어주기
                tfId.requestFocus();
                return;
            }
            dispose();
            MainController.forwardControl("Main");
        }else if (e.getSource() == btnCancel) {
            System.exit(0);
        }else if (e.getSource() == btnJoin){

        }
    }
}

