package domain;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String phone;

    public User(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public User(Long id, String name, String surname, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
