package model;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String isAdmin;
    
       public User(Integer id, String username, String email, String isAdmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }
       
    public User(Integer id, String username, String password, String email, String isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, String isAdmin, String email) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    public User(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
