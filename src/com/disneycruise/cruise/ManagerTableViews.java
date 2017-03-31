package com.disneycruise.cruise;
import com.disneycruise.database.Database;

import javax.activation.MailcapCommandMap;
import java.sql.*;

/**
 * Created by Sp05_ on 2017-03-21.
 */
public class ManagerTableViews {

    java.sql.Connection conn = Database.getInstance().getConnection();
    boolean isCleaningManager;
    boolean isEntertainmentManager;
    boolean isEntertainmentSchedule;
    boolean isCleaningSchedule;

    public ManagerTableViews() {
    }


    public ResultSet getManagerTableView() {
        ResultSet rs = null;
        String query = "SELECT * " +
                "FROM manager ";

        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
//            rs.close();
//            s.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;
    }

    public ResultSet getManagerScheduleView(String man_id) {
        isEntertainmentManager = false;
        isCleaningManager = false;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3 = null;
        Statement s1;

        System.out.println(man_id);

        String query1 = "SELECT * " +
                "FROM entertainmentschedule " +
            "WHERE man_id = '" + man_id + "' ";

        System.out.println(query1);

        String query2 = "SELECT * " +
                "FROM cleaningschedule " +
                "WHERE man_id = '" + man_id + "' ";
        try {
            s1 = conn.createStatement();
            rs1 = s1.executeQuery(query1);
            if (rs1.next()) {
                isEntertainmentManager = true;
            }

            Statement s2 = conn.createStatement();
            rs2 = s2.executeQuery(query2);
            if (rs2.next()) {
                isCleaningManager = true;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        String query = null;
        if (isEntertainmentManager) {
            query = "SELECT M.mname, M.man_id, ES.esid " +
                    "FROM manager M, entertainmentschedule ES " +
                    "WHERE M.man_id = '" + man_id + "' AND ES.man_id = M.man_id";
            System.out.println("made in query");
            System.out.println(query);
        }
        if (isCleaningManager) {
            query = "SELECT M.mname, M.man_id, CS.csid, CS.cs_stime, CS.cs_etime " +
                    "FROM manager M, cleaningschedule CS " +
                    "WHERE M.man_id = '" + man_id + "' AND CS.man_id = M.man_id";
            System.out.println(query);
        }
        try {
            Statement stmt = conn.createStatement();
            System.out.println(isEntertainmentManager);
            System.out.println(isCleaningManager);
            if (isCleaningManager != false || isEntertainmentManager != false) {
                rs3 = stmt.executeQuery(query);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs3;
    }

    public ResultSet getManagerCrewScheduleByManID(String man_id) {
        isEntertainmentManager = false;
        isCleaningManager = false;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3 = null;
        Statement s1;
        Statement s2;
        System.out.println(man_id);

        String query1 = "select * " +
                "from entertainmentschedule ES, managecrew MC " +
                "where ES.man_id = MC.man_id AND MC.man_id = '" + man_id + "' ";

        System.out.println(query1);

        String query2 = "select * " +
                "from cleaningschedule CS, managecrew MC " +
                "where CS.man_id = MC.man_id AND MC.man_id = '" + man_id + "' ";
        System.out.println(query2);
        try {
            s1 = conn.createStatement();
            rs1 = s1.executeQuery(query1);
            if (rs1.next()) {
                isEntertainmentManager = true;
            }

            s2 = conn.createStatement();
            rs2 = s2.executeQuery(query2);
            if (rs2.next()) {
                isCleaningManager = true;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        String query = null;
        if (isEntertainmentManager) {
            query = "SELECT * " +
                        "FROM crew C, manager M, managecrew MC, entertainmentschedule ES, entertainmentschedulecontent ESC " +
                        "WHERE M.man_id = MC.man_id AND MC.crew_id = C.crew_id AND M.man_id = ES.man_id AND ESC.esid = ES.esid AND M.man_id = '" + man_id + "' ";
            System.out.println(query);
        }
        if (isCleaningManager) {
            query = "SELECT * " +
                    "FROM crew C, manager M, managecrew MC, cleaningschedule CS " +
                    "WHERE M.man_id = MC.man_id AND MC.crew_id = C.crew_id AND M.man_id = CS.man_id AND M.man_id = '" + man_id + "' ";
            System.out.println(query);
        }
        try {
            Statement stmt = conn.createStatement();
            System.out.println(isEntertainmentManager);
            System.out.println(isCleaningManager);
            if (isCleaningManager != false || isEntertainmentManager != false) {
                rs3 = stmt.executeQuery(query);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs3;
    }

    public ResultSet getManagerCrewScheduleByCrewID(String crew_id) {
        isEntertainmentManager = false;
        isCleaningManager = false;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3 = null;
        Statement s1;
        Statement s2;

        String query1 = "select * " +
                "from entertainmentschedule ES, managecrew MC " +
                "where ES.man_id = MC.man_id AND MC.crew_id = '" + crew_id + "' ";

        System.out.println(query1);

        String query2 = "select * " +
                "from cleaningschedule CS, managecrew MC " +
                "where CS.man_id = MC.man_id AND MC.crew_id = '" + crew_id + "' ";
        System.out.println(query2);
        try {
            s1 = conn.createStatement();
            rs1 = s1.executeQuery(query1);
            if (rs1.next()) {
                isEntertainmentManager = true;
            }

            s2 = conn.createStatement();
            rs2 = s2.executeQuery(query2);
            if (rs2.next()) {
                isCleaningManager = true;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        String query = null;
        if (isEntertainmentManager) {
            query = "SELECT * " +
                    "FROM crew C, manager M, managecrew MC, entertainmentschedule ES, entertainmentschedulecontent ESC " +
                    "WHERE M.man_id = MC.man_id AND MC.crew_id = C.crew_id AND M.man_id = ES.man_id AND ESC.esid = ES.esid AND ESC.esid = C.esid AND MC.crew_id = '" + crew_id + "' ";
            System.out.println(query);
        }
        if (isCleaningManager) {
            query = "SELECT * " +
                    "FROM crew C, manager M, managecrew MC, cleaningschedule CS " +
                    "WHERE M.man_id = MC.man_id AND MC.crew_id = C.crew_id AND M.man_id = CS.man_id AND MC.crew_id = '" + crew_id + "' ";
            System.out.println(query);
        }
        try {
            Statement stmt = conn.createStatement();
            System.out.println(isEntertainmentManager);
            System.out.println(isCleaningManager);
            if (isCleaningManager != false || isEntertainmentManager != false) {
                rs3 = stmt.executeQuery(query);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs3;
    }

    public void createCrewSchedule(String csid, String esid, String eid, String startTime, String endTime, String crew_id, String man_id) {
        ResultSet rs = null;
        String query = null;
        if (!csid.isEmpty()) {
            isCleaningSchedule = true;
            query = "INSERT INTO cleaningschedule " + "( csid, " + "cs_stime, " +"cs_etime, " + "man_id ) " +
                    "VALUES (?, ?, ?, ?)";
        }
        System.out.println(query);
        if (!esid.isEmpty()) {
            isEntertainmentSchedule = true;
            query = "INSERT INTO entertainmentschedule " +
                    "( '" + esid + "' '" + man_id + "') ";
        }
        System.out.println(query);

        try {
            if (isCleaningSchedule) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, csid);
                stmt.setObject(2, startTime);
                stmt.setObject(3, endTime);
                stmt.setString(4, man_id);
                stmt.executeUpdate();
            }
            if (isEntertainmentSchedule) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, esid);
                stmt.setString(2, man_id);
                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void updateCrewSchedule(String csid, String esid, String eid, String startTime, String endTime, String crew_id, String man_id) {
        ResultSet rs = null;
        String query = null;

        if (!csid.isEmpty()) {
            isCleaningSchedule = true;
            query = "UPDATE cleaningschedule " + " SET csid = ?, cs_stime = ? , cs_etime = ? , man_id = ? ";
        }
        System.out.println(query);
        if (!esid.isEmpty()) {
            isEntertainmentSchedule = true;
            query = "UPDATE entertainmentschedulecontent " + "SET esid = ?, es_stime = ?, es_etime = ?  ";

        }
        System.out.println(query);
        try {
            PreparedStatement stmt =  conn.prepareStatement(query);
            if (isCleaningSchedule) {
                stmt.setString(1, csid);
                stmt.setObject(2, startTime);
                stmt.setObject(3, endTime);
                stmt.setString(4, man_id);
                stmt.executeUpdate();
            }
            if (isEntertainmentSchedule) {
              //  stmt.setString(1, esid);
             //   stmt.setString(2, eid);
                stmt.setObject(1, esid);
                stmt.setObject(2, startTime);
                stmt.setObject(3, endTime);

                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
   }

    public boolean getIsEntertainmentManager() {
        return isEntertainmentManager;
    }

    public boolean getIsCleaningManager() {
        return isCleaningManager;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
