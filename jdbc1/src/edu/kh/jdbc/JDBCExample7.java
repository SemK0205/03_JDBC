package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample7 {
	
	public static void main(String[] args) {
		
		// EMPLOYEE	테이블에서
		// 사번, 이름, 성별, 급여, 직급명, 부서명을 조회
		// 단, 입력 받은 조건에 맞는 결과만 조회하고 정렬할 것 <- Scanner
				
		// - 조건 1 : 성별 (M, F) 
		// - 조건 2 : 급여 범위
		// - 조건 3 : 급여 오름차순/내림차순
				
		// [실행화면]
		// 조회할 성별(M/F) : F <- up
		// 급여 범위(최소, 최대 순서로 작성) :
		// 3000000
		// 4000000
		// 급여 정렬(1.ASC, 2.DESC) : 2
				
		// 사번 | 이름   | 성별 | 급여    | 직급명 | 부서명
		//--------------------------------------------------------
		// 218  | 이오리 | F    | 3890000 | 사원   | 없음
		// 203  | 송은희 | F    | 3800000 | 차장   | 해외영업2부
		// 212  | 장쯔위 | F    | 3550000 | 대리   | 기술지원부
		// 222  | 이태림 | F    | 3436240 | 대리   | 기술지원부
		// 207  | 하이유 | F    | 3200000 | 과장   | 해외영업1부
		// 210  | 윤은해 | F    | 3000000 | 사원   | 해외영업1부

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
			String userName = "kh"; 
			String password = "kh1234"; 
			
			conn = DriverManager.getConnection(url, userName, password);
			
			System.out.print("조회할 성별(M/F) : ");
			
			char ch = sc.next().toUpperCase().charAt(0);
			
			int i = 0;
			
			if(ch == 'M') {
				i = 1;
			}else {
				i = 2;
			}
			
			System.out.println("급여 범위(최소, 최대 순서로 작성) : ");
			int min = sc.nextInt(); 
			int max = sc.nextInt(); 
			
			String order = null;
			System.out.print("급여 정렬(1.ASC, 2.DESC) : ");
			int salaryOrder = sc.nextInt();
			if(salaryOrder == 1) {
				order = "ASC";
			}else{
				order = "DESC";
			}
			
			String sql = """
					SELECT EMP_ID 사번, EMP_NAME 이름, CASE WHEN
					SUBSTR(EMP_NO, 8,1) = 1 THEN 'M'
					ELSE 'F' END 성별, SALARY 급여, JOB_NAME 직급명, NVL(DEPT_TITLE,'없음') 부서명
					FROM EMPLOYEE
					JOIN JOB USING(JOB_CODE)
					LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
					WHERE EMP_ID IN (SELECT EMP_ID
								FROM EMPLOYEE 
								WHERE SUBSTR(EMP_NO, 8,1) = """ + i +") AND SALARY BETWEEN " + min + " AND "+ max + 
								" ORDER BY SALARY " +order;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String empId = rs.getString("사번");
				String empName = rs.getString("이름");
				String gender = rs.getString("성별");
				int salary = rs.getInt("급여");
				String jobName = rs.getString("직급명");
				String deptTitle = rs.getString("부서명");
				
				System.out.printf("%s | %s | %s | %d | %s | %s\n",empId, empName, gender, salary, jobName, deptTitle);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			try {
				
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
				if(sc != null) sc.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}

}
