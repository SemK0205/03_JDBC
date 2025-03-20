package edu.kh.jdbc.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;

public class UserView {
	
	/** User 관리 프로그램 메인 메뉴
	 */
	
	private Scanner sc = new Scanner(System.in);
	private UserService service = new UserService();
	
	
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
				System.out.println("7. 여러 User 등록하기");
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
				case 7: multiInsertUser(); break;
				
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


	/** 1. User 등록
	 * 
	 */
	private void insertUser() throws Exception {
		
		System.out.println("\n===== User 등록 =====");
		
		boolean flag = true;
		String userId = null;
		while(true) {
		System.out.print("등록할 ID 입력 : ");
		userId = sc.next();
		
		flag = service.dupUser(userId);
		
		if(flag) {
			System.out.println("사용 가능한 아이디 입니다.");
			break;
		}else {
			System.out.println("중복된 아이디 입니다.");
		}
		
		}
		
		System.out.print("등록할 PassWord 입력 : ");
		String userPw = sc.next();
		
		System.out.print("등록할 이름 입력 : ");
		String userName = sc.next();
		
		User user = new User(userId, userPw, userName);
		
		int result = service.insertUser(user);
		
		if(result > 0) System.out.println("유저 등록 성공");
		else System.out.println("유저 등록 실패");
		
		
	}


	/** 2. User 전체 조회
	 * 
	 */
	private void selectAll() throws Exception {
		
		System.out.println("\n===== 유저 전체 조회 =====");
		
		List<User> userList = service.selectAll();
		
		for(User e : userList) {
			System.out.println(e);
		}
		if(userList.isEmpty()) System.out.println("유저가 없습니다.");
		
	}


	/** 3. User 중 이름에 검색어가 포함된 회원 조회
	 * 
	 */
	private void selectName() throws Exception {
		
		System.out.println("\n=====  User 중 이름에 검색어가 포함된 회원 조회 =====");
		
		System.out.print("검색에 포함할 문자 : ");
		String includeName = sc.next();
		
		List<User> userList = service.selectName(includeName);
		
		for(User e : userList) {
			System.out.println(e);
		}
		if(userList.isEmpty()) System.out.println("해당 검색어가 포함된 유저가 없습니다.");
		
		
		
	}


	/** 4. USER_NO를 입력 받아 일치하는 User 조회
	 * 
	 */
	private void selectUser() throws Exception {
		
		System.out.println("\n===== USER_NO를 입력 받아 일치하는 User 조회 =====");
		
		System.out.print("User_NO 입력 : ");
		int input = sc.nextInt();
		
		User user = service.selectUser(input);
		
		if(user == null) System.out.println("해당 유저가 없습니다.");
		else System.out.println(user);
		
		
		
		
	}


	/** 5. USER_NO를 입력 받아 일치하는 User 삭제
	 * 
	 */
	private void deleteUser() {
		
		System.out.println("\n===== USER_NO를 입력 받아 일치하는 User 삭제 =====");
		
		System.out.print("삭제할 유저의 USER_NO 입력 : ");
		int input = sc.nextInt();
		
		int result = service.deleteUser(input);
		
		
		
		
	}


	private void updateName2() {
		// TODO Auto-generated method stub
		
	}


	private void multiInsertUser() {
		// TODO Auto-generated method stub
		
	}

}
