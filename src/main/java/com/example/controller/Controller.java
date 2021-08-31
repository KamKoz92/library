package com.example.controller;

import java.util.List;

import com.example.model.DatabaseInterface;
import com.example.model.Person;

public class Controller {
    private DatabaseInterface db;


    public Controller(DatabaseInterface db) {
        this.db = db;
    }

    public List<Person> getUsers(String keyword) {
        List<Person> personList = null;
        personList = db.getPersonList();
        return personList;
    }
    
}
