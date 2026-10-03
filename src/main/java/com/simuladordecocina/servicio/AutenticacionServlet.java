package com.simuladordecocina.servicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servicio web encargado del registro y autenticación de usuarios.
 */
@WebServlet(urlPatterns = {"/api/registro", "/api/login"})
public class AutenticacionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Mapa utilizado para almacenar temporalmente los usuarios registrados.
    private static final Map<String, String> usuarios = new ConcurrentHashMap<>();

    /**
     * Procesa las solicitudes POST realizadas al servicio.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Indicamos que la respuesta será texto y utilizará codificación UTF-8.
        response.setContentType("text/plain;charset=UTF-8");

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        PrintWriter salida = response.getWriter();

        // Verificamos que los campos hayan sido enviados.
        if (usuario == null || usuario.trim().isEmpty()
                || contrasena == null || contrasena.trim().isEmpty()) {

            salida.println("Error: usuario y contraseña son obligatorios.");
            return;
        }

        // Determinamos qué operación solicitó el usuario.
        String ruta = request.getServletPath();

        if ("/api/registro".equals(ruta)) {

            // Verificamos si el usuario ya está registrado.
            if (usuarios.containsKey(usuario)) {
                salida.println("Error: el usuario ya existe.");
            } else {
                // Registramos el nuevo usuario.
                usuarios.put(usuario, contrasena);
                salida.println("Usuario registrado correctamente.");
            }

        } else if ("/api/login".equals(ruta)) {

            // Buscamos la contraseña asociada al usuario.
            String contrasenaGuardada = usuarios.get(usuario);

            // Comparamos los datos recibidos con los datos registrados.
            if (contrasenaGuardada != null && contrasenaGuardada.equals(contrasena)) {
                salida.println("Autenticación satisfactoria.");
            } else {
                salida.println("Error en la autenticación.");
            }
        }
    }
}