package db;

import models.Artist;
import models.Performance;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBArtist {

    private static Session session;
    private static Transaction transaction;

    public static List<Artist> orderByLastName(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Artist> artists = null;
        try {
            Criteria cr = session.createCriteria(Artist.class);
            cr.addOrder(Order.desc("lastName"));
            artists = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artists;
    }

    public static List<Artist> orderByType(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Artist> artists = null;
        try {
            Criteria cr = session.createCriteria(Artist.class);
            cr.addOrder(Order.desc("type"));
            artists = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artists;
    }

    public static List<Performance> getArtistsPerformances(Artist artist){
        List<Performance> results = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(Performance.class);
            cr.createAlias("artists", "artist"); // ADDED
            cr.add(Restrictions.eq("artist.id", artist.getId())); // ADDED
            results = cr.list();
        } catch (HibernateException ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;

    }

}
