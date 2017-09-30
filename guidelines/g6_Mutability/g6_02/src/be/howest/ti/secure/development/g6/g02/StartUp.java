package be.howest.ti.secure.development.g6.g02;


import be.howest.ti.secure.development.g6.g02.domain.Person;

import java.util.Calendar;
import java.util.Date;

public class StartUp {

    public static void main(String[] args) {
        new StartUp().run();
    }

    public void run() {
        System.out.println(this.getClass().toString());

        Calendar birthday = Calendar.getInstance();
        birthday.set(1986, Calendar.OCTOBER, 21);
        Person p = new Person("Mattias De Wael", birthday.getTime());

        System.out.println(p);
        System.out.printf("%s is %s", p.getName(), p.isOfAge()?"of age":"underage");

        attackUsingVerySimpleJavaCode(p);

        System.out.println(p);
        System.out.printf("%s is %s", p.getName(), p.isOfAge()?"of age":"underage");
    }

    private void attackUsingVerySimpleJavaCode(Person p) {
        // Nothing fancy ...
        p.getBirthday().setTime(new Date().getTime());
    }

}