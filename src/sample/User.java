package sample;

import java.util.Date;

public class User {
    private Date birthDate;
    private String fio;

    public User(String fio, Date birthDate) {
        this.fio = fio;
        this.birthDate = birthDate;
    }

    public User() {
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
