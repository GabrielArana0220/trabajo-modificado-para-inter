package com.ecodeup.articulo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecodeup.articulos.dao.ArticuloDAO;
import com.ecodeup.articulos.model.Articulo;
import com.ecodeup.articulos.model.CierreApertura;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/adminArticulo")
public class AdminArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticuloDAO articuloDAO;
	CierreApertura cierreApertura;	
	String contrasena;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			articuloDAO = new ArticuloDAO(jdbcURL, jdbcUsername, jdbcPassword);
			cierreApertura = new CierreApertura();
			contrasena = new String("suckmydick");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminArticulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			case "password":
				password(request, response);
				break;
			case "set_date":
				set_date(request,response);
				break;
			case "change_password":
				change_password(request,response);
				break;
			case "page_password":
				page_password(request,response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	String dateString1 = format.format(cierreApertura.getDate1());
        	String dateString2 = format.format(cierreApertura.getDate2());
        	Date   actual     = new Date();
        	Date   date       = format.parse (dateString1);
        	Date   date2      = format.parse (dateString2);
        	if(date2.after(actual) && date.before(actual)){
        		Articulo articulo = new Articulo(0, request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("cantidad")), Double.parseDouble(request.getParameter("precio")));
        		articuloDAO.insertar(articulo);
        		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        		dispatcher.forward(request, response);
                System.out.println(
                    "Dato insertado");
            } 
        	else
        	{
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/errorad.jsp");
        		dispatcher.forward(request, response);
                System.out.println(
                    "lo siento, sistema cerrado");
        	}
        	
			
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		
		
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	String dateString1 = format.format(cierreApertura.getDate1());
        	String dateString2 = format.format(cierreApertura.getDate2());
        	Date   actual     = new Date();
        	Date   date       = format.parse (dateString1);
        	Date   date2      = format.parse (dateString2);
        	if(date2.after(actual) && date.before(actual)){
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
    			dispatcher.forward(request, response);
                System.out.println(
                    "Acceso abierto");
            } 
        	else
        	{
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/errorad.jsp");
    			dispatcher.forward(request, response);
                System.out.println(
                    "lo siento, sistema cerrado");
        	}
    		
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		String comp = request.getParameter("fname");
		if(contrasena.equals(comp)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
			List<Articulo> listaArticulos= articuloDAO.listarArticulos();
			request.setAttribute("lista", listaArticulos);
			request.setAttribute("dateOpen", cierreApertura.getDate1());
			request.setAttribute("dateClose", cierreApertura.getDate2());
			dispatcher.forward(request, response);
		}
		else {
			String ad= "Contrasena incorrecta, por favor intente de nuevo";
			request.setAttribute("ad",ad);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/validacion.jsp");
			dispatcher.forward(request, response);
		}
	}	
		
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("articulo", articulo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Articulo articulo = new Articulo(Integer.parseInt(request.getParameter("id")), request.getParameter("codigo"), request.getParameter("nombre"), request.getParameter("descripcion"), Double.parseDouble(request.getParameter("existencia")), Double.parseDouble(request.getParameter("precio")));
		articuloDAO.actualizar(articulo);
		index(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Articulo articulo = articuloDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		articuloDAO.eliminar(articulo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);}
	
	private void password(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/validacion.jsp");
		dispatcher.forward(request, response);
	}
	private void set_date(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        	Date dateString = format.parse(request.getParameter("year")+"-"+request.getParameter("mes")+"-"+request.getParameter("dia"));
        	Date date2String = format.parse(request.getParameter("year2")+"-"+request.getParameter("mes2")+"-"+request.getParameter("dia2"));
        	cierreApertura.setDate1(dateString);
        	cierreApertura.setDate2(date2String);
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
        	List<Articulo> listaArticulos= articuloDAO.listarArticulos();
    		request.setAttribute("lista", listaArticulos);
    		request.setAttribute("dateOpen", cierreApertura.getDate1());
    		request.setAttribute("dateClose", cierreApertura.getDate2());
    		dispatcher.forward(request, response);
    		
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	private void change_password(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		if (request.getParameter("fname").equals(request.getParameter("fname2"))) {
			contrasena = request.getParameter("fname");
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
			List<Articulo> listaArticulos= articuloDAO.listarArticulos();
			request.setAttribute("lista", listaArticulos);
			request.setAttribute("dateOpen", cierreApertura.getDate1());
			request.setAttribute("dateClose", cierreApertura.getDate2());
			dispatcher.forward(request, response);
		}
		else {
			String ad ="Contrasenas diferentes, vuelvelo a intentar";
			request.setAttribute("ad", ad);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/cambiarpassword.jsp");
			dispatcher.forward(request, response);
		}
	}
	private void page_password(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/cambiarpassword.jsp");
		dispatcher.forward(request, response);
	}
	
}
