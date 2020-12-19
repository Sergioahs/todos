import static spark.Spark.*;

import java.util.*;
import com.google.gson.*;

public class App {

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());

        String projectDir = System.getProperty("user.dir");
        staticFiles.externalLocation(projectDir + "/src/main/resources/public");

        // Ruta de la pagina principal
        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        // Obtener citas
        get("/todo", (req, res) -> {

            return gson.toJson(DAO.getTodos());
        });

        // Guardar cita en base de datos
        post("/todo", (req, res) -> {

            String data = req.body();
            Todo todo = gson.fromJson(data, Todo.class);
            
            String id = UUID.randomUUID().toString();
            todo.setID(id);

            return DAO.crearTodo(todo);
        });
        
        // Eliminar cita
        delete("/todo/:id", (req, res) -> {
            return DAO.eliminarTodo(req.params("id"));
        });

        // Actualizar cita
        put("/todo", (req, res) -> {
            String data = req.body();
            Todo todo = gson.fromJson(data, Todo.class);

            return DAO.actualizarTodo(todo);
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7072;
    }
}