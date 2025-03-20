package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;

// View : 사용자와 직접 상호작용하는 화면(UI)를 담당,
// 입력을 받고 결과를 출력하는 역할
public class UserView {
	
		
		// 필드
		private Scanner sc = new Scanner(System.in);
		private UserService service = new UserService();
		
		// 메서드
		
		/**
		 * JDBCTemplate 사용 테스트 
		 */
		public void test() {
			
			// 입력된 ID와 일치하는 USER 정보 조회
			System.out.print("ID 입력 : ");
			String input = sc.next();
			
			// 서비스 호출 후 결과 반환 받기
			User user = service.selectId(input);
			
			// 결과에 따라 사용자에게 보여줄 응답화면 결정
			if( user == null) {
				System.out.println("없어용");
			}else {
				System.out.println(user);
			}
			
		}
		


/** User 관리 프로그램 메인 메뉴
 */
public void mainMenu() {
	
	int input = 0;
	
	do {
		try {
			
			System.out.println("\n===== User 관리 프로그램 =====\n");
			System.out.println("1. User 등록(INSERT)");
			System.out.println("2. User 전체 조회(SELECT)");
			System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회 (SELECT)");
			System.out.println("4. USER_NO를 입력 받아 일치하는 User 조회(SELECT)");
			System.out.println("5. USER_NO를 입력 받아 일치하는 User 삭제(DELETE)");
			System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
			System.out.println("7. User 등록(아이디 중복 검사)");
			System.out.println("8. 여러 User 등록하기");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 선택 : ");
			input = sc.nextInt();
			sc.nextLine(); // 버퍼에 남은 개행문자 제거
			
			switch(input) {
			case 1: insertUser(); break;
			case 2: selectAll(); break;
			case 3: selectName(); break;
			case 4: selectUser(); break;
			case 5: deleteUser(); break;
			case 6: updateName2(); break;
			case 7: insertUser2(); break;
			case 8: multiInsertUser(); break;
			
			case 0 : System.out.println("\n[프로그램 종료]\n"); break;
			default: System.out.println("\n[메뉴 번호만 입력하세요]\n");
			}
			
			System.out.println("\n-------------------------------------\n");
			
		} catch (InputMismatchException e) {
			// Scanner를 이용한 입력 시 자료형이 잘못된 경우
			System.out.println("\n***잘못 입력 하셨습니다***\n");
			
			input = -1; // 잘못 입력해서 while문 멈추는걸 방지
			sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
			
		} catch (Exception e) {
			// 발생되는 예외를 모두 해당 catch 구문으로 모아서 처리
			e.printStackTrace();
		}
		
	}while(input != 0);
	
}



private void multiInsertUser() throws Exception{
	
	System.out.println("=======유저 다수 등록=======");
	
	System.out.print("등록할 유저 수 : ");
	int num = sc.nextInt();
	
	List<User> userList = new ArrayList<User>(num);
	
	boolean flag = true;
	
	int num1 = 0;
	
	while(num1 < num) {
		
		String id = null;
		while(true) {
			System.out.print((num1+1)+"번째 유저 아이디 입력 : ");
			id = sc.next();
			int user = service.dupUser(id);
			
			if(user == 0) {
				break;
			}else {
				System.out.println ("중복된 아이디 입니다");
			}
			
		}
		
		System.out.print((num1+1)+"번째 유저 비밀번호 입력 : ");
		String pw = sc.next();
		
		System.out.print((num1+1)+"번째 유저 이름 입력 : ");
		String name = sc.next();
		
		userList.add(new User(id, pw, name));
		
		num1++;
		System.out.println("---------------------------------");
	}
	
	service.multiInsertUser(userList, flag);
	
	if(flag) System.out.println("모든 유저 생성 완료");
	else  System.out.println("모든 유저 생성 실패");
	
	/*
	while(true) {
		
		String id = "";
		while(true) {
			System.out.print("유저 아이디 입력 : ");
			id = sc.next();
			int user = service.dupUser(id);
			
			if(user == 0) {
				break;
			}else {
				System.out.println ("중복된 아이디 입니다");
			}
			
		}
		
		System.out.print("유저 비밀번호 입력 : ");
		String pw = sc.next();
		
		System.out.print("유저 이름 입력 : ");
		String name = sc.next();
		
		String user = service.insertUser(id, pw, name);
		
		System.out.println(user);
		
		System.out.println("유저 등록을 계속 하시겠습니까? (Y/N) : ");
		char ch = sc.next().toUpperCase().charAt(0);
		
		if(ch == 'N') {
			return;
		}
		
	}
	*/
}



private void insertUser2() throws Exception{
	
	System.out.println("=======유저 등록=======");
	
	String id = "";
	while(true) {
		System.out.print("유저 아이디 입력 : ");
		id = sc.next();
		int user = service.dupUser(id);
		
		if(user == 0) {
			break;
		}else {
			System.out.println("중복된 아이디 입니다");
		}
		
	}
	
	System.out.print("유저 비밀번호 입력 : ");
	String pw = sc.next();
	
	System.out.print("유저 이름 입력 : ");
	String name = sc.next();
	
	String user = service.insertUser(id, pw, name);
	
	System.out.println(user);
	return;
}


private void updateName2() throws Exception{
	
	System.out.println("=======유저 이름 수정=======");
	
	System.out.print("ID : ");
	String userId = sc.next();
	
	System.out.print("PW : ");
	String userPw = sc.next();
	
	// 입력받은 ID, PW가 일치하는 회원이 존재하는지 조회(SELECT)
	int userNo = service.selectUserNo(userId, userPw);
	
	// 위에서 조회된 회원의 이름 수정
	
	if(userNo == 0) {
		System.out.println("ID 또는 PW가 틀렸습니다.");
		return;
	}else {
		System.out.println("수정할 이름 : ");
		String userName = sc.next();
		
		int user = service.updateName(userNo, userName);
		
		if(user>0) System.out.println("수정 성공");
		else System.out.println("수정 실패");
		return;
		
	}
	
	
}



private void updateName() {
	
	System.out.println("=======유저 이름 수정=======");
	
	System.out.print("수정할 유저아이디 입력 : ");
	String updateId = sc.next();
	System.out.print("수정할 유저비밀번호 입력 : ");
	String updatePw = sc.next();
	System.out.print("수정할 유저이름 입력 : ");
	String updateName = sc.next();
	
	String user = service.updateUser(updateId, updatePw, updateName);
	
	if(user == null) {
		System.out.println("수정할 번호가 없습니다");
	}else {
		System.out.println("수정 성공");
	}
	return;
}



private void deleteUser() {
	
	System.out.println("=======유저 삭제=======");
	
	System.out.print("삭제할 유저번호 입력 : ");
	int input = sc.nextInt();
	
	String user = service.deleteUser(input);
	
	if(user == null) {
		System.out.println("삭제할 번호가 없습니다");
	}else {
		System.out.println("삭제 성공");
	}
	return;
}



private void selectUser() {
	
	System.out.println("=======유저 조회=======");
	
	System.out.print("번호 입력 : ");
	int input = sc.nextInt();
	
	User user = service.selectUser(input);
	
	if( user == null) {
		System.out.println("없어용");
	}else {
		System.out.println(user);
	}
	return;
}



private void selectName() throws Exception{
	
	System.out.println("=======유저 찾기=======");
	
	System.out.println("찾을 이름의 일부 입력 : ");
	String select = sc.next();
	
	List<User> user = service.selectName(select);
	
	if(user.isEmpty()) {
		System.out.println("없어용");
	}else {
		for(User e : user) {
			System.out.println(e);
		}
	}
	return;
	
}



private void selectAll() {
	
	System.out.println("=======유저 전체 조회=======");
	
	List<User> user = service.selectAll();
	
	if(user.isEmpty()) {
		System.out.println("없어용");
		return;
	}else {
		for(User e :user) {
			
			System.out.println(e);
			
		}
		return;
	}
	
	
}



private void insertUser() throws Exception {
	
	System.out.println("=======유저 등록=======");
	
	System.out.print("유저 아이디 입력 : ");
	String id = sc.next();
	
	System.out.print("유저 비밀번호 입력 : ");
	String pw = sc.next();
	
	System.out.print("유저 이름 입력 : ");
	String name = sc.next();
	
	/* 입력받은 값 3개를 한번에 묶어서 전달할 수 있도록
	User DTO 객체를 생성한 후 필드에 값을 세팅
	User user = new User();
	user.setUserId(id);
	user.setUserPw(pw);
	user.setUserName(name);
	
	서비스 호출 후 결과 반환(int, 결과 행의 개수)받기
	int result = service.insertUser(user);
	
	*/
	String user = service.insertUser(id, pw, name);
	
	System.out.println(user);
	
	return;
}



} // mainMenu() 종료

