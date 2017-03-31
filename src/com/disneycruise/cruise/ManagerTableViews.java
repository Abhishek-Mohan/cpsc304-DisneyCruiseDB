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

    public

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
