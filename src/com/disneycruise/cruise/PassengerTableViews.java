package com.disneycruise.cruise;
import java.sql.*;

import com.disneycruise.database.Database;
import java.util.Date;

/**
 * Created by Sp05_ on 2017-03-24.
 */
public class PassengerTableViews {

    java.sql.Connection conn = Database.getInstance().getConnection();

    public PassengerTableViews() {

    }

    public ResultSet getPassengerTableView() {
    ResultSet rs = null;

        String query = "select * " +
                    " from passenger" ;
    try {
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
    } catch (SQLException se) {
        se.printStackTrace();
    }
        return rs;
    }

    public ResultSet getBrowseActivityTableView(String type, String location, String startTime, String endTime, String date) {
        ResultSet rs = null;
        boolean needAND = false;
        System.out.println(type);
        System.out.println(type.isEmpty());
        String query = "select * " +
                "from entertainment ";

        if (!type.isEmpty() || !location.isEmpty() || !startTime.isEmpty() || !endTime.isEmpty() || !date.isEmpty()) {
            query = query + "where ";
        }


        if (!type.isEmpty()) {
            query = query + "type = '" + type + "' ";
            needAND = true;
        }
        if (!location.isEmpty()) {
            if (needAND) {
                query = query + " AND ";
            }
            query = query + "eloc = '" + location + "' ";
            needAND = true;
        }
        if (!startTime.isEmpty()) {
            if (needAND) {
                query = query + " AND ";
            }
            query = query + "en_stime = '" + startTime + "' ";
            needAND = true;
        }
        if (!endTime.isEmpty()) {
            if (needAND) {
                query = query + " AND ";
            }
            query = query + "en_etime = '" + endTime + "' ";
            needAND = true;
        }
        if (!date.isEmpty()) {
            if (needAND) {
                query = query + " AND ";
            }
            query = query  +"edate = '" + date + "' ";
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

    public ResultSet getPopularActivityTables() {
    ResultSet rs = null;
        String query = "select * " +
                        "from entertainment " +
                        "where NOT EXISTS (select * from passenger " +
                                            "where  NOT EXISTS (select * from schedule S , passenger P, schedulecontent SC, entertainment E " +
                                                                "where SC.eid = E.eid AND SC.sid = S.sid AND S.pid = P.pid )) ";

        System.out.println(query);
        try {
            Statement stmt =  conn.createStatement();
          rs = stmt.executeQuery(query);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    return rs;
    }

    public ResultSet getPassengerScheduleView(String pid) {
        ResultSet rs = null;
        String query;
        if (pid.isEmpty()) {

            query = "select * " +
                    "from passenger P, schedule S, schedulecontent SC, entertainment E " +
                    "where P.pid = S.pid AND S.sid = SC.sid AND SC.eid = E.eid ";
        }
        else {
            query = "select * " +
                    "from passenger P, schedule S, schedulecontent SC, entertainment E " +
                    "where P.pid = S.pid AND S.sid = SC.sid AND SC.eid = E.eid AND P.pid = '" + pid + "' ";
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

    public ResultSet getRoommateScheduleView(String pid) {
        ResultSet rs = null;
        String query = "select S.sid, P1.pid, E.eid, E.ename, E.eloc, SC.sstime, SC.setime " +
                        "from passenger P, passenger P1, schedule S, schedulecontent SC, entertainment E " +
                        "where P.pid = '" + pid + "' " + "AND P.cid = P1.cid AND P1.pid = S.pid AND S.sid = SC.sid AND SC.eid = E.eid AND P1.pid <> P.pid ";
        System.out.println(query);
        try {
           Statement stmt =  conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    public ResultSet getTotalNumberofSchedulesView() {
    ResultSet rs = null;
    String query = "select P.pid, P.pname, COUNT(*) AS scheduleCount " +
                    "from passenger P, schedule S, schedulecontent SC " +
                    "where  P.pid = S.pid AND S.sid = SC.sid " +
                    "group by S.sid, P.pid, P.pname ";

    try {
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
    } catch (SQLException se) {
        se.printStackTrace();
    }

    return rs;
    }

    public void createScheduleContent(String sid, String eid, String sstime, String setime) {
        ResultSet rs = null;
        String query = null;
            query = "INSERT INTO schedulecontent " + "( sid, " + "eid, " + "sstime, " + "setime ) " +
                    "VALUES (?, ?, ?, ?) ";

        System.out.println(query);

        try {

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, sid);
                stmt.setString(2, eid);
                stmt.setDate(3, java.sql.Date.valueOf(sstime));
                stmt.setDate(4, java.sql.Date.valueOf(setime));

                stmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}
