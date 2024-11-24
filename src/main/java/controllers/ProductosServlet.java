package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import services.LoginService;
import services.LoginServiceImplement;
import services.ProductoService;
import services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//anotaciones
@WebServlet("/productos")

public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Productos> productos = service.Listar();

        //hago una condicion si la cookie es distinto a null
        //si es verdadero obtengo la cookie caso contrario creo un nuevo objeto
        //de la cookie
        //Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //Optional<String> cookieOptional = Arrays.stream(cookies)
          //              .filter (c -> "username".equals(c.getName()))
                //convertimos la cookie a tipo string
            //            .map(Cookie::getValue)
              //                  .findAny();

        LoginService auth= new LoginServiceImplement();
        Optional<String> cookieOptional=auth.getUserName(req);

        resp.setContentType("text/html;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                //Creo la plantilla html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Listar Producto</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listar Producto</h1>");
                if (cookieOptional.isPresent()) {
                    out.println("<div style='color:blue'> Hola "+ cookieOptional.get()+"</div>");
                }

                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID PRODUCTO</th>");
                out.println("<th>NOMBRE</th>");
                out.println("<th>CATEGORIA</th>");
                if (cookieOptional.isPresent()) {
                    out.println("<th>PRECIO</th>");
                }
                out.println("</tr>");
                productos.forEach(p->{
                    out.println("<tr>");
                    out.println("<td>"+p.getIdProducto()+"</td");
                    out.println("<td>"+p.getNombreProducto()+"</td");
                    out.println("<td>"+p.getCategoria()+"</td");
                    out.println("<td>"+p.getPrecioProducto()+"</td");
                    out.println("</tr>");
                });
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
    }
}
