package ir.util;
//package ir.util;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.lucene.queryParser.ParseException;
//
///**
// * Servlet implementation class Search
// */
//@WebServlet("/Search")
//public class Search extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Search() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		LuceneDAO dao = new LuceneDAO();
//		String query = request.getParameter("query");
//		if(query != null) {
//			try {
//				response.setContentType("text/html");
//				PrintWriter pw = response.getWriter();
//				pw.println(dao.search(query));
//				pw.close();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			doGet(request, response);
//		}
//	}
//
//}
