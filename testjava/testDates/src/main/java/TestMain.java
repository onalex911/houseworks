import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Date;

public class TestMain {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        Date now = new Date();
//        Date now = new Date();
        Date minDate = new Date(now.getTime() - (24 * 60 * 60 * 1000));
        LocalDate nowLD = LocalDate.now();
        LocalDate dateInThePastLD = LocalDate.of(now.getYear(), now.getMonth(), now.getDay());
        Date dateInThePast = faker.date().birthday();
        System.out.println(dateInThePast);
        System.out.println(nowLD);
        //System.out.println(nowLD.toEpochDay());
    }
}
