package controller;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.RestaurantEmployee;
import util.SessionFactoryCfg;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class StorageInitializerController {

    List<Storage> storageInfo = new ArrayList<>();

    public StorageInitializerController(HttpSession session) {

        try (Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession()) {

            //TODO: try to do, so an employee can belong to more than one restaurant
            //Check which restaurants the employee has access too
            Query aecQuery = hibSession.createQuery("From RestaurantEmployee where employeeId = :i");
            aecQuery.setParameter("i", session.getAttribute("employeeID"));
            List<RestaurantEmployee> aeclist = aecQuery.list();
            session.setAttribute("restaurantID", aeclist.get(0).getRestaurantId());

            //Instantiates which restaurant is chosen
            Query restaurantQuery = hibSession.createQuery("From Restaurant where id = :j");
            restaurantQuery.setParameter("j", session.getAttribute("restaurantID"));
            session.setAttribute("restaurant", restaurantQuery.list().get(0));
            Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");

            //Collect all storages belonging to the restaurant
            storageInfo = restaurant.allStorages();

        } catch (HibernateException e) {
            System.out.println("Couldn't get the list.");
            e.printStackTrace();

        }
    }

    public List<Storage> getStorageInfo() {
        return storageInfo;
    }
}
