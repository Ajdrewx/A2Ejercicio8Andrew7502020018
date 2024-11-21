package Infraestructure.Persistence;

import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UserNotFoundException;
import Domain.Model.User;
import Infraestructure.Database.ConnectionDbMySql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD {

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM estudiantes";
        try {
            Connection con = ConnectionDbMySql.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userList.add(
                    new User(
                        rs.getString("code"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("apellidos"),
                        rs.getString("rol"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getString("fecha_registro")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Método para agregar un nuevo usuario
    public void addUser(User user) throws SQLException, DuplicateUserException {
        String query = "INSERT INTO estudiantes (code, password, name, apellidos, rol, email, telefono, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, user.getCode());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getApellidos());
            stmt.setString(5, user.getRol());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getTelefono());
            stmt.setString(8, user.getEstado());
            stmt.setString(9, user.getFechaRegistro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Manejamos una posible excepción de clave duplicada
            if (e.getErrorCode() == 1062) { // Código de error de clave duplicada en MySQL
                throw new DuplicateUserException("El usuario con el código o email ya existe.");
            } else {
                throw e;
            }
        }
    }

    // Método para actualizar un usuario
    public void updateUser(User user) throws SQLException, UserNotFoundException {
        String query = "UPDATE estudiantes SET password=?, name=?, apellidos=?, rol=?, email=?, telefono=?, estado=?, fecha_registro=? WHERE code=?";
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getApellidos());
            stmt.setString(4, user.getRol());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getTelefono());
            stmt.setString(7, user.getEstado());
            stmt.setString(8, user.getFechaRegistro());
            stmt.setString(9, user.getCode());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("El usuario con el código " + user.getCode() + " no existe.");
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    // Método para eliminar un usuario
    public void deleteUser(String code) throws SQLException, UserNotFoundException {
        String query = "DELETE FROM estudiantes WHERE code=?";
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, code);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new UserNotFoundException("El usuario con el código " + code + " no existe.");
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    // Método para obtener un usuario por código
    public User getUserByCode(String code) throws SQLException, UserNotFoundException {
        String query = "SELECT * FROM estudiantes WHERE code=?";
        User user = null;
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("code"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("apellidos"),
                    rs.getString("rol"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("estado"),
                    rs.getString("fecha_registro")
                );
            } else {
                throw new UserNotFoundException("El usuario con el código " + code + " no existe.");
            }
        }
        return user;
    }

    // Método para autenticar un usuario por email y contraseña
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = null;
        String query = "SELECT * FROM estudiantes WHERE email=? AND password=?";
        try {
            Connection con = ConnectionDbMySql.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getString("code"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("apellidos"),
                    rs.getString("rol"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("estado"),
                    rs.getString("fecha_registro")
                );
            } else {
                throw new UserNotFoundException("Credenciales incorrectas. No se encontró el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Método para buscar usuarios por nombre o email
    public List<User> searchUsers(String searchTerm) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM estudiantes WHERE name LIKE ? OR email LIKE ?";
        try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userList.add(
                    new User(
                        rs.getString("code"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("apellidos"),
                        rs.getString("rol"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getString("fecha_registro")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Método para obtener un usuario por email
public User getUserByEmail(String email) throws SQLException, UserNotFoundException {
    User user = null;
    String query = "SELECT * FROM estudiantes WHERE email=?";
    try (Connection con = ConnectionDbMySql.getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            user = new User(
                rs.getString("code"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("apellidos"),
                rs.getString("rol"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getString("estado"),
                rs.getString("fecha_registro")
            );
        } else {
            throw new UserNotFoundException("El usuario con el email " + email + " no existe.");
        }
    }
    return user;
}
}

