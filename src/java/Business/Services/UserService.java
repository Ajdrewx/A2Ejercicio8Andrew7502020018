package Business.Services;

import Domain.Model.User;
import Business.Exceptions.UserNotFoundException;
import Business.Exceptions.DuplicateUserException;
import Infraestructure.Persistence.UserCRUD;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserCRUD userCrud;

    public UserService() {
        this.userCrud = new UserCRUD();
    }

    public List<User> getAllUsers() throws SQLException {
        return userCrud.getAllUsers();
    }

    public void createUser(String code, String name, String apellidos, String rol, String email, String telefono, String estado, String fechaRegistro)
            throws DuplicateUserException, SQLException {
        User user = new User(code, "", name, apellidos, rol, email, telefono, estado, fechaRegistro);
        userCrud.addUser(user);
    }

    public void updateUser(String code, String name, String apellidos, String rol, String email, String password, String telefono, String estado, String fechaRegistro)
            throws UserNotFoundException, SQLException {
        // Ahora se pasan todos los campos nuevos al crear el objeto User
        User user = new User(code, password, name, apellidos, rol, email, telefono, estado, fechaRegistro);
        userCrud.updateUser(user);
    }

    public void deleteUser(String code) throws UserNotFoundException, SQLException {
        userCrud.deleteUser(code);
    }

    public User getUserByCode(String code) throws UserNotFoundException, SQLException {
        return userCrud.getUserByCode(code);
    }

    public User loginUser(String email, String password) throws UserNotFoundException, SQLException {
        User user = userCrud.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new UserNotFoundException("Credenciales incorrectas. No se encontró el usuario o la contraseña es incorrecta.");
        }
    }

    public List<User> searchUsers(String searchTerm) {
        return userCrud.searchUsers(searchTerm);
    }
}
