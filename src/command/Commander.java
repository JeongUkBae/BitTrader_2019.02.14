package command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import enums.Action;
import proxy.Proxy;
import proxy.RequestProxy;

public class Commander {
	public static Command order(Map<String, Proxy> pxy) {
		
		System.out.println("===== 5.커맨더 진입 =====");
		
		Command cmd = null;
		RequestProxy req = (RequestProxy) pxy.get("req");
		HttpServletRequest request = req.getRequest();
		System.out.println("cmd::"+request.getParameter("cmd"));
		switch(Action.valueOf(request.getParameter("cmd").toUpperCase())) {
		case MOVE:
			System.out.println("===== 3.커맨더 진입 > 스위치 MOVE 진입 =====");
			cmd = new Command(pxy);
			break;
			
		case REGISTER: case SIGNUP :
			System.out.println("REGISTER: case SIGNUP 탐=======");
			cmd = new CreateCommand(pxy);
			break;
			
		case ACCESS: case SIGNIN:
			System.out.println("ACCESS: case SIGNIN 탐=======");
			cmd = new ExistCommand(pxy);
			break;
			
		case CUST_LIST:
			System.out.println("Cust_list 탐!!");
			cmd = new ListCommand(pxy);
			break;

		default :
			
			break;
		
		}
		System.out.println("커맨더내부 :"+Receiver.cmd.getView());
		return cmd;
	}
}
