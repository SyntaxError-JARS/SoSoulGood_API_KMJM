package com.revature.SoSoulGood.Web.Servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface Authable {

    static boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authCustomer") == null){
            resp.getWriter().write("Unauthorize request - not logged in");
            resp.setStatus(401); // Not authorized
            return false;
        }
        return true;
    }
}
