package com.company;

import com.google.gson.Gson;
import postres.bd.Pet;
import postres.bd.PetDB;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get")
public class GetServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String id = request.getParameter("id");
        try
        {
            Pet pet = PetDB.selectOne(id);
            if (pet != null)
            {
                out.print(gson.toJson(pet));
            }
            else
            {
                out.print(gson.toJson(new HttpResponseStatus("OK", "No results with " + id)));
            }
        }
        catch(Exception ex)
        {
            out.print(gson.toJson(new HttpResponseStatus("ERROR", ex.getMessage())));
        }
        finally
        {
            out.close();
        }
    }
}