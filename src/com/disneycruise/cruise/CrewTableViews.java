package com.disneycruise.cruise;

import com.disneycruise.database.Database;
import java.sql.*;

/**
 * Created by Sp05_ on 2017-03-26.
 */
public class CrewTableViews {

    java.sql.Connection conn = Database.getInstance().getConnection();

    public CrewTableViews() {}

    public ResultSet getCrewTableViews() {
        ResultSet rs = null;

       // String query = "select * from managecrew";

        String query = "select * " +
                        "from crew ";
        try {
            Statement stmt = conn.createStatement();
             rs = stmt.executeQuery(query);

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    public ResultSet getCrewCleanScheduleByDepartment(String workPlace) {
        ResultSet rs = null;
        String query;

        if (workPlace.isEmpty()) {

            query = "select * " +
                    "from cleaningschedule CS, crew C " +
                    "where CS.csid = C.csid ";

        } else {
            query = "select * " +
                    "from cleaningschedule CS, crew C " +
                    "where CS.csid = C.csid AND C.department = '" + workPlace + "' ";
        }

        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return rs;
    }

    public ResultSet getCrewCleanScheduleByCrewID(String crew_id) {
        ResultSet rs = null;
        String query;

        if (crew_id.isEmpty()) {

            query = "select * " +
                    "from cleaningschedule CS, crew C " +
                    "where CS.csid = C.csid ";

        } else {
            query = "select * " +
                    "from cleaningschedule CS, crew C " +
                    "where CS.csid = C.csid AND C.crew_id = '" + crew_id + "' ";
        }

        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return rs;
    }

    public ResultSet getCrewEntertainmentScheduleByDepartment(String workPlace) {
        ResultSet rs = null;
        String query;

        if (workPlace.isEmpty()) {

            query = "select * " +
                    "from entertainment E, entertainmentschedule ES, entertainmentschedulecontent ESC, crew C " +
                    "where E.eid = ESC.eid AND ES.esid = C.esid AND ESC.esid = ES.esid ";

        } else {
            query = "select * " +
                    "from entertainment E, entertainmentschedule ES, crew C, entertainmentschedulecontent ESC " +
                    "where E.eid = ESC.eid AND ES.esid = C.esid AND ES.esid = ESC.esid AND C.department = '" + workPlace + "' ";
        }

        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return rs;
    }

    public ResultSet getCrewEntertainmentScheduleByCrewID(String crew_id) {
        ResultSet rs = null;
        String query;

        if (crew_id.isEmpty()) {

            query = "select * " +
                    "from entertainment E, entertainmentschedule ES, entertainmentschedulecontent ESC, crew C " +
                    "where E.eid = ESC.eid AND ES.esid = C.esid AND ESC.esid = ES.esid ";

        } else {
            query = "select * " +
                    "from entertainment E, entertainmentschedule ES, crew C, entertainmentschedulecontent ESC " +
                    "where E.eid = ESC.eid AND ES.esid = C.esid AND ES.esid = ESC.esid AND C.crew_id = '" + crew_id + "' ";
        }

        System.out.println(query);
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return rs;
    }

}
