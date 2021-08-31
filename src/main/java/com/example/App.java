package com.example;

import com.example.controller.Controller;
import com.example.model.DatabaseInterface;
import com.example.model.DerbyDatabase;

// import java.util.List;

// import com.example.model.DatabaseInterface;
// import com.example.model.DerbyDatabase;
// import com.example.model.Person;
import com.example.view.Window;


public class App 
{
    public static void main( String[] args )
    {
        // List<Person> personList = null;
        // DatabaseInterface dbb = new DerbyDatabase();
        // personList = dbb.getPersonList();
        // System.out.println(personList.size());
        // for(Person person: personList) {
        //     System.out.println(person.getFirstName()+ " " + person.getLastName());
        // }
        DatabaseInterface db = new DerbyDatabase();
        Controller controller = new Controller(db);
        Window window = new Window("LibManagment", controller);
    }
}
