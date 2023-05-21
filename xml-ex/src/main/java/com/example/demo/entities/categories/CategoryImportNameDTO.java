package com.example.demo.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportNameDTO {
    @XmlElement(name = "name")
    private String name;

    public CategoryImportNameDTO(){}

    public String getName() {
        return name;
    }
}
