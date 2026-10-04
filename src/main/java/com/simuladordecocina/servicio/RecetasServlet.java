package com.simuladordecocina.servicio;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/recetas")
public class RecetasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter salida = response.getWriter();

        String id = request.getParameter("id");

        if ("1".equals(id)) {

            salida.println("{");
            salida.println("  \"id\": 1,");
            salida.println("  \"nombre\": \"Arroz con pollo\",");
            salida.println("  \"descripcion\": \"Receta tradicional de arroz con pollo\",");
            salida.println("  \"tiempo\": \"45 minutos\"");
            salida.println("}");

        } else if ("2".equals(id)) {

            salida.println("{");
            salida.println("  \"id\": 2,");
            salida.println("  \"nombre\": \"Sopa de verduras\",");
            salida.println("  \"descripcion\": \"Sopa preparada con verduras frescas\",");
            salida.println("  \"tiempo\": \"30 minutos\"");
            salida.println("}");

        } else if ("3".equals(id)) {

            salida.println("{");
            salida.println("  \"id\": 3,");
            salida.println("  \"nombre\": \"Pasta con pollo\",");
            salida.println("  \"descripcion\": \"Pasta acompañada de pollo y salsa\",");
            salida.println("  \"tiempo\": \"35 minutos\"");
            salida.println("}");

        } else {

            salida.println("[");
            salida.println("  {");
            salida.println("    \"id\": 1,");
            salida.println("    \"nombre\": \"Arroz con pollo\",");
            salida.println("    \"descripcion\": \"Receta tradicional de arroz con pollo\",");
            salida.println("    \"tiempo\": \"45 minutos\"");
            salida.println("  },");
            salida.println("  {");
            salida.println("    \"id\": 2,");
            salida.println("    \"nombre\": \"Sopa de verduras\",");
            salida.println("    \"descripcion\": \"Sopa preparada con verduras frescas\",");
            salida.println("    \"tiempo\": \"30 minutos\"");
            salida.println("  },");
            salida.println("  {");
            salida.println("    \"id\": 3,");
            salida.println("    \"nombre\": \"Pasta con pollo\",");
            salida.println("    \"descripcion\": \"Pasta acompañada de pollo y salsa\",");
            salida.println("    \"tiempo\": \"35 minutos\"");
            salida.println("  }");
            salida.println("]");
        }
    }@Override
            protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

                response.setContentType("application/json;charset=UTF-8");

                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String tiempo = request.getParameter("tiempo");

                PrintWriter salida = response.getWriter();

                if (nombre == null || nombre.trim().isEmpty()
                        || descripcion == null || descripcion.trim().isEmpty()
                        || tiempo == null || tiempo.trim().isEmpty()) {

                    salida.println("{");
                    salida.println("  \"error\": \"Los datos de la receta son obligatorios\"");
                    salida.println("}");
                    return;
                }

                salida.println("{");
                salida.println("  \"mensaje\": \"Receta registrada correctamente\",");
                salida.println("  \"nombre\": \"" + nombre + "\",");
                salida.println("  \"descripcion\": \"" + descripcion + "\",");
                salida.println("  \"tiempo\": \"" + tiempo + "\"");
                salida.println("}");
            }
}
