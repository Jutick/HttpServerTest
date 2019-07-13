package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import postres.bd.*;


@WebServlet("/create")
public class AddServlet extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Gson gson = new Gson();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();
        try
        {
            int inserted = 0;
            JsonParser parser = new JsonParser();
            JsonElement petElement = parser.parse(reader);
            JsonArray pets = petElement.getAsJsonArray();

            for (int i = 0; i < pets.size(); i++)
            {
                JsonObject pet = pets.get(i).getAsJsonObject();
                inserted += PetDB.insert(getPet(pet));
            }

            if (inserted == pets.size())
            {
                out.print(gson.toJson(new HttpResponseStatus("OK", "Added" + " " + inserted)));
            }
            else {
                out.print(gson.toJson(new HttpResponseStatus("WARNING", "Added" + " " + inserted + " of " + pets.size())));
            }

        }
        catch(Exception ex)
        {
            out.print(gson.toJson(new HttpResponseStatus("ERROR", ex.getMessage())));
        }
        finally
        {
            out.close();
            reader.close();
        }
    }

    private Pet getPet(JsonObject pet)
    {
        try
        {
            String name = pet.get("name").getAsString();
            String id = pet.get("id").getAsString();
            Breed breed = new Breed(pet.get("breed").getAsString());
            Food food = new Food(pet.get("food").getAsString());

            return new Pet(id, name, breed, food);
        }
        catch (Exception ex)
        {
            return  new Pet();
        }
    }

}