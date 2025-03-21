package edu.kh.jdbc.view;

import java.util.Scanner;

import edu.kh.jdbc.dto.Member;
import edu.kh.jdbc.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	private MemberService service = new MemberService();
	private int loginInfo = 0;
	
	
	public void mainView(){
		try {
			int input = 0;
			
			do {
				System.out.println("===== KH 자습용 TODO - LIST =====");
				
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("3. 내 TODO 전체 조회");
				System.out.println("4. 새로운 TODO 추가");
				System.out.println("5. TODO 수정 (제목, 내용)");
				System.out.println("6. 완료여부 변경 (Y <-> N)");
				System.out.println("7. 현재 TODO 삭제");
				System.out.println("8. 로그아웃");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("이용하고 싶은 번호 입력 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : signUp(); break;
				case 2 : logIn(); break;
				case 3 : selectAllTodo(); break;
				case 4 : newTodo(); break;
				case 5 : updateTodo(); break;
				case 6 : done(); break;
				case 7 : deleteTodo(); break;
				case 8 : logout(); break;
				case 0 : sc.close(); return;
				default : System.out.println("없는 항목 입니다. 다시 입력해 주세요.");;
				}
				
			}while(input != 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	/** 8. 로그아웃
	 * 
	 */
	private void logout() {
		
		loginInfo = 0;
		System.out.println("로그아웃 되었습니다.");
		
	}

	/** 7. TODO 삭제하기
	 * 
	 */
	private void deleteTodo() throws Exception {
		
		if(loginInfo == 0) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.println("===== 현재 TODO 삭제하기 =====");
		
		System.out.println();
		System.out.println("현재 내 TODOLIST 목록");
		int result = 0;
		
		Member member = null;
		member = service.selectAllTodo(loginInfo);
		if(member != null)System.out.println(member);
		else System.out.println("현재 TODO_LIST는 비어있습니다.");
		System.out.println();
		System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
		char ch = sc.next().toUpperCase().charAt(0);
		
		if(ch == 'Y') {
			
			result = service.deleteTodo(loginInfo);
			if(result > 0) System.out.println("해당 TODO를 삭제 완료했습니다.");
			else System.out.println("삭제 실패..");
			
		}else {
			return;
		}
		
	}

	/** 6. 완료여부 변경
	 * 
	 */
	private void done() throws Exception {
		
		if(loginInfo == 0) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.println("===== 완료여부 변경 (Y <-> N) =====");
		System.out.println();
		System.out.println("현재 내 TODOLIST 목록");
		int result = 0;
		
		Member member = null;
		member = service.selectAllTodo(loginInfo);
		if(member != null)System.out.println(member);
		else System.out.println("현재 TODO_LIST는 비어있습니다.");
		
		System.out.println("1. 완료로 바꾸기");
		System.out.println("2. 완료된 TODO 미완료로 수정하기");
		System.out.print("이용하고 싶은 번호 입력 : ");
		int input = sc.nextInt();
		
		
		if(input == 1) {
			result = service.updateDone(loginInfo);
			if(result > 0) System.out.println("해당 TODO를 완료했습니다.");
			else System.out.println("완료로 수정 실패..");
		}else {
			result = service.updateNotDone(loginInfo);
			if(result > 0) System.out.println("해당 TODO를 미완료로 수정했습니다.");
			else System.out.println("미완료로 수정 실패..");
		}
	}

	/** 5. TODO 수정
	 * 
	 */
	private void updateTodo() throws Exception {
		// UPDATE int
		if(loginInfo == 0) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		int result1 = 0;
		int result2 = 0;
		
		System.out.println("===== TODO 수정하기 (제목, 내용) =====");
		
		while(true) {
		System.out.println("1. 제목 수정하기");
		System.out.println("2. 내용 수정하기");
		System.out.println("0. 뒤로가기");
		System.out.print("이용하고 싶은 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		switch(input) {
		case 1: 
			System.out.print("수정하실 제목을 입력하세요 : ");
			String title = sc.nextLine();
			result1 = service.updateTitle(title, loginInfo); 
			if(result1 != 0) System.out.println("제목 수정 성공!");
			else System.out.println("제목 수정 실패..");
		break;
		case 2: 
			System.out.println("수정하실 내용을 입력하세요 : ");
			String detail = sc.nextLine();
			result2 =  service.updateDetail(detail, loginInfo); 
			if(result2 != 0) System.out.println("내용 수정 성공!");
			else System.out.println("제목 수정 실패..");
		
		break;
		case 0:  return;
		default : System.out.println("없는 번호 입니다. 다시 입력해 주세요.");
		}
		
		}
		
		
		
	}

	/** 4. 새로운 TODO 추가
	 * 
	 */
	private void newTodo() throws Exception {
		// INSERT Member
		if(loginInfo == 0) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		int result = 0;
		
		System.out.println("===== 새로운 TODO 추가 =====");
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		System.out.print("할일 : ");
		String todo = sc.nextLine();
		
		result = service.newTodo(title, todo, loginInfo); 
		
		if(result != 0) System.out.println("TODO 추가 성공!");
		else System.out.println("추가 실패..");
		
	}

	/** 3. 내 TODO_LIST 조회
	 * @throws Exception
	 */
	private void selectAllTodo() throws Exception {
		//SELECT -> USER
		if(loginInfo == 0) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.println("===== 내 TODO_LIST 조회 =====");
		
		Member member = service.selectAllTodo(loginInfo);
		
		if(member != null)System.out.println(member);
		else System.out.println("현재 TODO_LIST는 비어있습니다.");
		
		
	}

	/** 2. 로그인 (SELECT)
	 * @throws Exception
	 */
	private void logIn() throws Exception {
		// 로그인 성공 시 회원 이름 출력, Member_No를 계속 가지고 다님
		System.out.println("===== kh.TODO_LIST 로그인 =====");
		
		String memberId = null;
		String memberPw = null;
		
		Member member = null;
		
		while(true) {
		System.out.print("ID 입력 : ");
		memberId = sc.next();
		System.out.print("PW 입력 : ");
		memberPw = sc.next();
		
		member = service.agreement(memberId,memberPw);
		
		if(member != null) break;
		else System.out.println("아이디 또는 비밀번호 오류");
		}
		
		System.out.println(member.getMemberName()+"님 환영합니다.");
		loginInfo = member.getMemberNo();
	}

	/** 1. 회원가입(INSERT)
	 * @throws Exception
	 */
	private void signUp() throws Exception {
		System.out.println("===== kh.TODO_LIST 회원가입 =====");
		
		System.out.print("사용하실 ID 입력 : ");
		String memberId = sc.next();
		System.out.print("사용하실 PW 입력 : ");
		String memberPw = sc.next();
		System.out.print("사용자의 이름 입력 : ");
		String memberName = sc.next();
		System.out.print("사용자의 주민등록번호 입력 : ");
		String memberSsn = sc.next();
		int result = service.signUp(memberId, memberPw, memberName, memberSsn);
		
		if(result == 0) System.out.println("회원가입 실패..");
		else System.out.println(memberName+"님 회원가입 성공.");
		
		
	}
	
	
	

}
