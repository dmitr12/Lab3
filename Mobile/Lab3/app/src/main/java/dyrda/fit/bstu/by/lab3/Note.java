package dyrda.fit.bstu.by.lab3;

import java.util.Date;

public class Note {
    private String note;
    private Date date;

    public Note(Date date, String note){
        this.note=note;
        this.date=date;
    }

    public void setValue(String note){
        this.note=note;
    }

    public String getValue(){
        return this.note;
    }

    public void setDate(Date date){
        this.date=date;
    }

    public Date getDate(){
        return this.date;
    }
}
