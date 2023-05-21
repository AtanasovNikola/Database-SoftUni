package com.example.demo.entities.users;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserImportDTO {

@XmlElement(name = "user")
    private List<UserNameAndAgeDTO> users;

    public UserImportDTO() {
    }

    public List<UserNameAndAgeDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserNameAndAgeDTO> users) {
        this.users = users;
    }
}
