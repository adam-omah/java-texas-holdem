package People;

public class person {
    String name;
    int age;

    public person(){
        setAge(0);
        setName("No Name Supplied");
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAge(int age) {
        if (age >=0 && age <= 160){
            this.age = age;
        }else
            this.age = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
