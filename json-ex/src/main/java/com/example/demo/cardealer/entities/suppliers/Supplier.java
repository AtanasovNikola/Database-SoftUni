package com.example.demo.cardealer.entities.suppliers;

import com.example.demo.cardealer.entities.parts.Part;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier",targetEntity = Part.class)
    private List<Part> parts;

    public Supplier(){
        this.parts = new ArrayList<>();
    }
    public Supplier(String name, boolean isImporter) {
        this();
        this.name = name;
        this.isImporter = isImporter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
