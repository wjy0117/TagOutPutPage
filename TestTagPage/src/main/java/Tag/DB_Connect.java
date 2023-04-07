package Tag;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_Connect {

	public Connection dbConn() {
		// db접속객체
		Connection conn = null;
		try {
			// mysql jdbc driver 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// db연결 문자열 but 이방법은 보안에 취약하다.
			String url = "jdbc:mysql://localhost/mmn?characterEncoding=UTF-8&serverTimezone=UTC";
			String id = "root"; // mysql 접속아이디
			String pwd = "1234"; // mysql 접속 비번

			// db 접속
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("db접속 성공");
		} catch (Exception e) {
			// db관련작업은 반드시 익셉션 처리
			System.out.println("db접속 실패");
			e.printStackTrace();
		}
		return conn;
	}

	public ArrayList<Integer> TagSearch(String s) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ar = new ArrayList<Integer>();
		Connection conn = null;// db접속객체
		PreparedStatement pstmt = null; // SQL실행객체
		ResultSet res = null; // 결과셋처리객체
		
		try {
			conn = dbConn();
			// 가게, 태그, 태그-가게 테이블에서 입력한 태그명과 같은 태그의 가게코드를 가져와서 출력
			//.storeCode from tag_storeTbl  " +  + ")
			String sql = "select tag_storeTbl.storeCode from tag_storeTbl where tag_storeTbl.tagID = (Select tagTbl.tagID from tagTbl where tagTbl.tagName = '"+s+"')";
			System.out.println(sql);
			// 데이터베이스로 SQL 문을 보내기 위한 SQLServerStatement 개체생성
			pstmt = conn.prepareStatement(sql);

			// Select 문에서만 실행하며 데이터베이스에서 데이터를 가져와서 결과 집합을 반환
			res = pstmt.executeQuery(sql);
			
			// res의 길이만큼의 배열 생성 또는 arraylist 생성.
			while (res.next()) {
				StoreDTO td = new StoreDTO();
				td.setScode(res.getInt("storeCode"));
				//Integer.parseInt(td.toString())
				ar.add(td.getScode());
			}
			System.out.println(ar);
		} catch (Exception e) {
			System.out.println("Query_Failed");
			e.printStackTrace();

		} finally {
			// 리소스 정리작업
			try {
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		System.out.println();
		return ar;
	}
}
