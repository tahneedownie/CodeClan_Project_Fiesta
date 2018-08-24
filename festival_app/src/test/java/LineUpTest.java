import models.LineUp;
import models.Venue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineUpTest {

    @Test
    public void getLineUpDate() {
        Venue circusHub = new Venue("Circus Hub", "Meadows", 100);

        Date date = new Date();
        // find the epoch time for your date
        date.setTime(1560798000000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        LineUp lineup = new LineUp(date, circusHub);

        assertEquals("17-06-2019", dateFormat.format(lineup.getDate()));
    }




}
