package com.revature.SoSoulGood.Web.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.SoSoulGood.Exceptions.InvalidRequestException;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Models.Menu;
import com.revature.SoSoulGood.Services.MenuServices;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MenuServlet extends HttpServlet {

    private final MenuServices menuServices;
    private final ObjectMapper mapper;
    public MenuServlet(MenuServices menuServices, ObjectMapper mapper) {
        this.menuServices = menuServices;
        this.mapper = mapper;
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

        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the follow id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        if(req.getParameter("id") != null){
            Menu menu;
            try {
                menu = menuServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(menu);
            resp.getWriter().write(payload);
            return;
        }

        List<Menu> menus = menuServices.readAll();
        String payload = mapper.writeValueAsString(menus);

        resp.getWriter().write(payload);
    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        //if(!checkAuth(req, resp)) return;
        Menu authMenu = (Menu) req.getSession().getAttribute("authMenu");

        Menu reqMenu = mapper.readValue(req.getInputStream(), Menu.class);


        Menu updatedMenu = menuServices.update(reqMenu);

        String payload = mapper.writeValueAsString(updatedMenu);
        resp.getWriter().write(payload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Menu persistedMenu;
        try {
            Menu menu = mapper.readValue(req.getInputStream(), Menu.class);
            persistedMenu = menuServices.create(menu);
        } catch (InvalidRequestException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
            return;
        }
        String payload = mapper.writeValueAsString(persistedMenu);

        resp.getWriter().write("Persisted the provided Menu as show below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if(req.getParameter("id") == null){
            resp.getWriter().write("In order to delete, please provide your user id as a verification into the url with ?id=your@id.here");
            resp.setStatus(401);
            return;
        }

        String username = req.getParameter("id");
        Menu authMenu = (Menu) req.getSession().getAttribute("authMenu");


        try {
            menuServices.delete(username);
            resp.getWriter().write("Delete Menu from the database");
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
