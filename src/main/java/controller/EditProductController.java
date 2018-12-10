package controller;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/EditProductController")
public class EditProductController extends HttpServlet {

    /** Update product if button pressed*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Input parameters
        String name = req.getParameter("editProductName");
        int batchSize = Integer.parseInt(req.getParameter("editSize"));
        String cost = req.getParameter("editPrice");
        BigDecimal price;

        //Error handling, if the user forgot to set commas
        if(cost.contains(".") || cost.contains(",")){
            //Convert "," to "."
            if(cost.contains(",")){
                price = BigDecimal.valueOf(Double.parseDouble(cost.replace(",", ".")));
            }else {
                price = BigDecimal.valueOf(Double.parseDouble(cost));
            }
        }else{
            price = BigDecimal.valueOf(Double.parseDouble(cost + ".00"));
        }

        //Collect current attributes
        HttpSession session = req.getSession();
        Product product = (Product) session.getAttribute("productChosen");
        product.setName(name);
        product.setBatchSize(batchSize);
        product.setPrice(price);

        //Update
        product.update();

        //Close popup
        session.setAttribute("showEditProPopUp", false);

        resp.sendRedirect("webpanel.jsp");

    }

    /** If button pressed, show popup*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showEditProPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}