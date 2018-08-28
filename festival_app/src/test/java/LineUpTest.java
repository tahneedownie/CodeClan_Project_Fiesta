import models.LineUp;
import models.Venue;

import java.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineUpTest {

    @Test
    public void getLineUpDate() {
        Venue circusHub = new Venue("Circus Hub", "Meadows", 100);
        LineUp lineup = new LineUp(LocalDate.of(2018,8,26));

        assertEquals("2018-08-26", lineup.getDate().toString());
    }




}
