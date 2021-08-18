package controller;

import ui.LoginFrm;
import ui.MainFrm;
import vo.UserVO;

public class MainController {
    public static void main(String[] args) {
        MainController.forwardControl("Login");
    }
    private static MainController mainController;
    private UserVO session;
    //인스턴스를 복수개로 못만들게함(단하나임)(하나를 계속돌려씀)
    // (값을 공유하기위해) 이패턴을 싱글턴패턴 이라고한다
    private MainController(){}
    public static MainController getInstance(){
        if(mainController == null) mainController = new MainController();
        return mainController; //singleton pattern 오직 1개의 인스턴스만 활용
        //이로서 maincontroller는 getInstence로만 접근가능
    }

    public UserVO getSession() {return session;}
    public void setSession(UserVO session) {this.session = session;}

    public static void forwardControl(String cmd){
        if (cmd.equals("Login")) {
            new LoginFrm();
        } else if (cmd.equals("Main")) {
            new MainFrm();
        } else if (cmd.equals("Join")) {

        }
    }
}
