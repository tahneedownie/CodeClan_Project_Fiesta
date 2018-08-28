package db;

import models.LineUp;
import models.Venue;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBVenue {

    private static Session session;

    public static boolean addLineUpToVenue(Venue venue, LineUp lineUp){
        boolean wasItAdded = venue.addLineUpToVenue(lineUp);
        DBHelper.update(venue); // REMEMBER THIS WILL CASCADE UPDATE TO PROJECT
        return wasItAdded;
    }

    public static List<LineUp> getVenuesLineups(Venue venue) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<LineUp> results = null;
        try {
            Criteria cr = session.createCriteria(LineUp.class);
            cr.add(Restrictions.eq("venue", venue));
            results =  cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


}
