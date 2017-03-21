package GUI;

import java.sql.*;
/**
 * Created by Sp05_ on 2017-03-17.
 */
public class Manager {

    int man_id;
    String name;
    int pass;
    String department;


    public Manager(int man_id, String name, int password, String department) {
        this.man_id = man_id;
        this.name = name;
        this.pass = password;
        this.department = department;

    }

    public void findManagerSchedule(int man_id) {

    }

    public void findCrewsSchedule(int crew_id) {

    }
}
