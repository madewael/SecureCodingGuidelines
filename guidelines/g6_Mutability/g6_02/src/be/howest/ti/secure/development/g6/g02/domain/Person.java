package be.howest.ti.secure.development.g6.g02.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Person {

    private final String name;
    private final Date birthday;

    public Person(String name, Date birthday){
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public String toString() {
        return String.format("%s (born on %s)", name, dateFormat.format(birthday));
    }

    public boolean isOfAge() {
        Calendar _18yearsAgo = Calendar.getInstance();
        _18yearsAgo.add(Calendar.YEAR, -18);

        return birthday.before( _18yearsAgo.getTime() );
    }


}
