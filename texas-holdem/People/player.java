package People;

import java.util.GregorianCalendar;

public abstract class player implements person {
    private String name;
    private String address;
    private GregorianCalendar dateOfBirth;

    public static int playerTotal = 0;


    public abstract String getCategory();
    public abstract int getFunds();
    public abstract int getRanking();

    public player(String name, String address, GregorianCalendar dateOfBirth) {
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
