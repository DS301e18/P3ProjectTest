package model;

import util.AddRemove;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import util.SessionFactoryCfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Restaurant extends AddRemove {

    /**
     * Field
     */
    private int id;
    private String name;

    public Restaurant(String name) {
        this.name = name;

        addObject(this);
    }

    public Restaurant() {
    }

    /**
     * Methods
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantEmployee> collectEmployees() {
        List<RestaurantEmployee> restaurantEmployees = new ArrayList<>();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<RestaurantEmployee> restaurantEmployeesList = session.createQuery("FROM RestaurantEmployee ").list();
        for (RestaurantEmployee restaurantEmployee : restaurantEmployeesList) {
            if (this.getId() == restaurantEmployee.getRestaurantId()) {
                restaurantEmployees.add(restaurantEmployee);
            }
        }

        session.close();
        return restaurantEmployees;
    }

    public List<RestaurantStorage> collectStorages() {
        List<RestaurantStorage> restaurantStorages = new ArrayList<>();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<RestaurantStorage> restaurantStorageList = session.createQuery("FROM RestaurantStorage ").list();
        for (RestaurantStorage restaurantStorage : restaurantStorageList) {
            if (this.getId() == restaurantStorage.getRestaurantId()) {
                restaurantStorages.add(restaurantStorage);
            }
        }

        session.close();
        return restaurantStorages;
    }

    public List<Employee> sortEmployees() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<RestaurantEmployee> restaurantEmployee = collectEmployees();
        List<Employee> employeeList = session.createQuery("FROM Employee").list();
        List<Employee> allRestaurantEmployees = new ArrayList<>();

        for (int i = 0; i < restaurantEmployee.size(); i++) {
            for (Employee employee : employeeList) {
                if (employee.getId() == restaurantEmployee.get(i).getEmployeeId() && employee.getRole().equals("Medarbejder")) {
                    allRestaurantEmployees.add(employee);
                }
            }
        }
        allRestaurantEmployees.sort(Comparator.comparing(Employee::getFirstName));
        return allRestaurantEmployees;
    }

    public List<Manager> sortManagers() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<RestaurantEmployee> restaurantEmployee = collectEmployees();
        List<Manager> employeeList = session.createQuery("FROM Employee").list();
        List<Manager> allRestaurantEmployees = new ArrayList<>();

        for (int i = 0; i < restaurantEmployee.size(); i++) {
            for (Manager employee : employeeList) {
                if (employee.getId() == restaurantEmployee.get(i).getEmployeeId() && employee.getRole().equals("Chef")) {
                    allRestaurantEmployees.add(employee);
                }
            }
        }
        allRestaurantEmployees.sort(Comparator.comparing(Employee::getFirstName));
        return allRestaurantEmployees;
    }

    public List<Storage> allStorages() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<RestaurantStorage> restaurantStorage = collectStorages();
        List<Storage> storageList = session.createQuery("FROM Storage").list();
        List<Storage> allRestaurantStorages = new ArrayList<>();

        for (int i = 0; i < restaurantStorage.size(); i++) {
            for (Storage storage : storageList) {
                if (storage.getId() == restaurantStorage.get(i).getStorageId()) {
                    allRestaurantStorages.add(storage);
                }
            }
        }
        return allRestaurantStorages;
    }

    public void removeRestaurant() {
        if (allStorages() != null) {
            for (int i = 0; i < allStorages().size(); i++) {
                allStorages().get(i).remove();
            }
        }

        if (collectStorages() != null) {
            for (int i = 0; i < collectStorages().size(); i++) {
                collectStorages().get(i).remove();
            }
        }

        if (sortEmployees() != null) {
            for (int i = 0; i < sortEmployees().size(); i++) {
                removeObject(sortEmployees().get(i));
            }
        }

        if (collectEmployees() != null) {
            for (int i = 0; i < collectEmployees().size(); i++) {
                removeObject(collectEmployees().get(i));
            }
        }

        removeObject(this);
    }

    public void update(){
        updateObject(this);
    }

    @Override
    public String toString() {
        return name;
    }
}



