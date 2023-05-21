package com.example.demo.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersExportDTO {

    @XmlElement(name = "user")
    List<UserExportDTO> users;


    public UsersExportDTO() {
    }

    public UsersExportDTO(List<UserExportDTO> users) {
        this.users = users;
    }

    public List<UserExportDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserExportDTO> users) {
        this.users = users;
    }
}
