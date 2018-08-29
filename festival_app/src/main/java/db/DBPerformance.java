package db;

import models.LineUp;
import models.Performance;
import models.Venue;
import models.Visitor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBPerformance {

    private static Session session;
    private static Transaction transaction;

    public static List<Performance> orderByTime(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Performance> performances = null;
        try {
            Criteria cr = session.createCriteria(Performance.class);
            cr.addOrder(Order.desc("time"));
            performances = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return performances;
    }

    public static Double getAverageDuration(){
        session = HibernateUtil.getSessionFactory().openSession();
        Double average = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Performance.class);
            cr.setProjection(Projections.avg("duration"));
            average = (Double)cr.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return average;
    }

    public static List<LineUp> getPerformanceLineUps(Performance performance){
        session = HibernateUtil.getSessionFactory().openSession();
        List<LineUp> lineUps = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Performance.class);
            cr.createAlias("lineUp", "lineUp");
            cr.add(Restrictions.eq("lineUp.performance_id", performance.getId()));
            lineUps = cr.list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lineUps;
    }

    public static List<Venue> getPerformancesVenues(Performance performance){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Venue> venues = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Venue.class);
            cr.createAlias("lineUp", "lineUp");
            cr.add(Restrictions.eq("lineUp.performance_id", performance.getId()));
//            TODO: CHECK LOGIC BELOW
            cr.add(Restrictions.eq("venue", performance.getLineUp().getVenue()));
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            venues = cr.list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return venues;
    }

    public static boolean addVisitorToPerformance(Performance performance, Visitor visitor){
        boolean wasItAdded = performance.addVisitor(visitor);
        DBHelper.update(performance); // REMEMBER THIS WILL CASCADE UPDATE TO PROJECT
        return wasItAdded;
    }

    public static List<Visitor> getPerformancesVisitors(Performance performance){
        List<Visitor> results = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(Visitor.class);
            cr.createAlias("performances", "performance"); // ADDED
            cr.add(Restrictions.eq("performance.id", performance.getId())); // ADDED
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            results = cr.list();
        } catch (HibernateException ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;

    }

}
