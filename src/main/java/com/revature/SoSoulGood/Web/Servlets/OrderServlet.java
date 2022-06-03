package com.revature.SoSoulGood.Web.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.SoSoulGood.Exceptions.InvalidRequestException;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Models.CreditCard;
import com.revature.SoSoulGood.Models.Customer;
import com.revature.SoSoulGood.Models.Menu;
import com.revature.SoSoulGood.Models.Order;
import com.revature.SoSoulGood.Services.MenuServices;
import com.revature.SoSoulGood.Services.CustomerServices;
import com.revature.SoSoulGood.Services.OrderServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {

    private final OrderServices orderServices;
    private final ObjectMapper mapper;


    public OrderServlet(OrderServices orderServices, ObjectMapper mapper) {
        this.orderServices=orderServices;
        this.mapper=mapper;
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if (req.getParameter("id") != null) {
            Order order;
            try {
                order = orderServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(order);
            resp.getWriter().write(payload);
            return;
        }

        List<Order> order = orderServices.readAll();
        String payload = mapper.writeValueAsString(order);

        resp.getWriter().write(payload);
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        //Ordersif(!checkAuth(req, resp)) return;
        Order authOrder = (Order) req.getSession().getAttribute("authOrder");

        Order reqOrder = mapper.readValue(req.getInputStream(), Order.class);

        //if(authOrders.getUsername().equals(reqOrders.getUsername())) {

        Order updatedOrder = orderServices.update(reqOrder);

        String payload = mapper.writeValueAsString(updatedOrder);
        resp.getWriter().write(payload);
        //} else {
//            resp.getWriter().write("id provided does not match the user currently logged in");
//            resp.setStatus(403);
        //}

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Order persistedOrder;
        try {
            Order order = mapper.readValue(req.getInputStream(), Order.class);
            persistedOrder = orderServices.create(order);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedOrder);

        resp.getWriter().write("Persisted the provided Orders as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        //if(!checkAuth(req,resp)) return;
        if(req.getParameter("id") == null){
            resp.getWriter().write("In order to delete, please provide your user id as a verification into the url with ?id=your@id.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("id");
        Order authOrder = (Order) req.getSession().getAttribute("authOrder");

//        if(!authOrdersCard.getUsername().equals(username)){
//            resp.getWriter().write("username provided does not match the user logged in, double check for confirmation of deletion");
//            return;
//        }

        try {
            orderServices.delete(username);
            resp.getWriter().write("Delete Orders from the database");
            req.getSession().invalidate();
        } catch (ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(500);
        }
    }

}
