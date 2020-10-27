package dyrda.fit.bstu.by.testdb;

public class User {

    public int id, isChosen;
    public String name,secondName,mail;

    public User(int id, String name, String secondName, String mail, int isChosen){
        this.id=id;
        this.name=name;
        this.secondName=secondName;
        this.mail=mail;
        this.isChosen=isChosen;
    }
}
