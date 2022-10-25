package People;

import java.util.GregorianCalendar;

//Person.java

/*
Person interface, pretty much the exact same as person interface
* supplied in lectured therefore do not count this as my own material.
*  */

public interface person {
    public abstract String getName();
    public abstract String getAddress();
    public abstract GregorianCalendar getDateOfBirth();
    public abstract void setName(String nm);
    public abstract void setAddress(String addr);
    public abstract void setDateOfBirth(GregorianCalendar dob);
}
