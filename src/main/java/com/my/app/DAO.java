import java.sql.*;
import java.util.*;

public class DAO {
    
    private static Conexion conexion = new Conexion();

    public static String crearTodo(Todo todo) {

        PreparedStatement st = null;
        Connection con = null;
        String msj = "";
        con = conexion.getConnection();

        try {
            String sql = "insert into todos values (?,?,?)";
            
            st = con.prepareStatement(sql);
            st.setString(1, todo.getID());
            st.setString(2, todo.getNombre());
            st.setString(3, todo.getDescripcion());

            if(st.executeUpdate() > 0){
                msj = "El usuario fue agregado";
            }
            else{
                msj = "No se pudo agregar el usuario";
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(st != null) {
                try {
                    st.close();
                } 
                catch (SQLException e) {
                    e.printStackTrace();
                }
                st = null;
            }
            try {
                con.close();
                System.out.println("conexion cerrada");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msj;
    }

    public static List<Todo> getTodos() {
        
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;
        List<Todo> resultados = new ArrayList<Todo>();

        con = conexion.getConnection();

        try {
            String sql = "select * from todos";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                
                Todo c = new Todo();

                c.setID(rs.getString("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));

                resultados.add(c);
            }  
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {

            if(rs != null) {
                try {
                    rs.close();
                } 
                catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }

            if(st != null) {
                try {
                    st.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                st = null;
            }
            
            try {
                con.close();
                System.out.println("conexion cerrada");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultados;
    }

    public static String eliminarTodo(String id) {
        
        PreparedStatement st = null;
        Connection con = null;
        String msj = "";

        con = conexion.getConnection();

        try{

            String sql = "delete from todos where id = ?";
            st = con.prepareStatement(sql);
            st.setString(1, id);

            if(st.executeUpdate() > 0) {
                msj = "El usuario fue eliminado";
            }
            else {
                msj = "No se pudo eliminar el usuario";
            }
        }
        catch(Exception e){
         
            e.printStackTrace();
        }
        finally{
            if(st != null){
                try {
                    st.close();
                } 
                catch (SQLException e) {
                    e.printStackTrace();
                }
                st = null;
            }
            try {
                con.close();
                System.out.println("conexion cerrada");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msj;
    }

    public static String actualizarTodo(Todo todo) {

        PreparedStatement st = null;
        Connection con = null;
        String msj = "";
        con = conexion.getConnection();

        try {
            String sql = "update todos set nombre=?, descripcion=? where id=?";
            
            st = con.prepareStatement(sql);
            st.setString(3, todo.getID());
            st.setString(1, todo.getNombre());
            st.setString(2, todo.getDescripcion());

            if(st.executeUpdate() > 0){
                msj = "La todo fue actualizada";
            }
            else{
                msj = "No se pudo actualizar la todo";
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if(st != null) {
                try {
                    st.close();
                } 
                catch (SQLException e) {
                    e.printStackTrace();
                }
                st = null;
            }
            try {
                con.close();
                System.out.println("conexion cerrada");
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return msj;
    }
}
