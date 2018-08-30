package db;

import models.LineUp;
import models.Venue;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBVenue {

    private static Session session;

    public static boolean notSavingSameVenue(Venue venue) {
        if (venue.canAddSameVenue()) {
            DBHelper.save(venue);
            return true;
        }
        return false;
    }

    public static void addLineUpToVenue(Venue venue, LineUp lineUp){
     //   boolean wasItAdded = venue.addLineUpToVenue(lineUp);
        venue.addLineUp(lineUp);
   //     DBHelper.update(venue);// REMEMBER THIS WILL CASCADE UPDATE TO PROJECT
        lineUp.setVenue(venue);
        DBHelper.update(lineUp);
    //    return wasItAdded;
    }

    public static List<LineUp> getVenuesLineupsbyDate(Venue venue) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<LineUp> results = null;
        try {
            Criteria cr = session.createCriteria(LineUp.class);
            cr.add(Restrictions.eq("venue", venue)).addOrder(Order.desc("date"));
            results =  cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


}
