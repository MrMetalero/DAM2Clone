package models;

public class User {

private String name;
private String lastname;
private String email;


public User(String name, String lastname){
    this.name = name;
    this.lastname = lastname;
    
}


public String getEmail() {
    return email;
}


public void setEmail(String email) {
    this.email = email;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getLastname() {
    return lastname;
}


public void setLastname(String lastname) {
    this.lastname = lastname;
}






}
