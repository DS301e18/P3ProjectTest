package model;

public class Manager extends Employee {

    /**
     * Field
     **/
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role = "Chef";

    public Manager(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        addObject(this);
    }

    public Manager() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public void removeManager() {
        if ((SystemAdministrator.collectManagers() != null)){
            for (int i = 0; i < SystemAdministrator.collectManagers().size(); i++) {
                removeObject(SystemAdministrator.collectManagers().get(i));
            }
        }

        removeObject(this);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
