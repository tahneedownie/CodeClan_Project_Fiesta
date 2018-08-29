package db;

import models.LineUp;
import models.Performance;
import models.Venue;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.sound.sampled.Line;
import java.util.List;

public class DBLineUp {

    private static Session session;

    public static boolean addPerformanceToLineUp(LineUp lineUp, Performance performance){
        boolean wasItAdded = lineUp.addPerformance(performance);
        DBHelper.update(lineUp); // REMEMBER THIS WILL CASCADE UPDATE TO PROJECT
        return wasItAdded;
    }

    public static List<Performance> getLineUpsPerformanceList(LineUp lineUp) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Performance> results = null;
        try {
            Criteria cr = session.createCriteria(Performance.class);
            cr.add(Restrictions.eq("lineUp", lineUp));
            results =  cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

}
