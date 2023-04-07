package Tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchTagForm
 */
@WebServlet("/TagController")
public class TagController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String context = request.getContextPath();
		DB_Connect dc = new DB_Connect();
		String _tag = request.getParameter("tag_name_input");
		
		//
		System.out.println("get: "+_tag);
		ArrayList<Integer> ar  = dc.TagSearch(_tag);
		request.getRequestDispatcher("tag_main.jsp").forward(request, response); 
		//response.sendRedirect("tag_main.jsp");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*try {
			//db연결 클래스 생성. DAO - 생성할 클래스1
			//input태그 입력값 받아오기. (.getParameter(name))
			
			//입력값 전달을 위한 클래스 생성. DTO - 생성할 클래스 2
			//DTO 값 get-set? 초기화?
			
			//태그값 비교하는 클래스 호출 (파라미터, DTO데이터)
			DB_Connect dbc = new DB_Connect();
			//tag_name_input가 이름인 요소의 값 가져오기
			request.setCharacterEncoding("UTF-8");
			String _tag = request.getParameter("tag_name_input");
			//null이 아니라면
			if(_tag != "null") {
				//inputData클래스 생성
				inputData iData = new inputData();
				//입력한 정보를 파라미터로 하여 쿼리 실행
				dbc.Tagsearch(_tag.toString());
			}else {
				System.out.println("Link_failed");
			}
		}catch(Exception e) {
			System.out.println("exception");
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
