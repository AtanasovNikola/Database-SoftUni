package com.example.demo.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryImportDTO {

    @XmlElement(name = "category")
    private List<CategoryImportNameDTO> categories;

    public CategoryImportDTO(){}

    public List<CategoryImportNameDTO> getCategories() {
        return categories;
    }
}
