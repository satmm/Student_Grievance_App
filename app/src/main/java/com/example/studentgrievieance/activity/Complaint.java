package com.example.studentgrievieance.activity;

public class Complaint {
    private String name;
    private String USN;
    private String category;
    private String Department ;
    private String date;
    private String description;


    public Complaint() {
        // Default constructor required for Firebase
    }

    public Complaint(String name, String USN,String category,String Department,String date,String description) {
        this.name = name;
        this.USN = USN;
        this.category=category;
        this.Department=Department;
        this.date=date;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return USN;
    }

    public String getCategory() {
        return category;
    }
    public String getDepartment() {
        return Department;
    }
    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
