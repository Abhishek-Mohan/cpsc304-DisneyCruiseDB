package com.disneycruise.cruise;


import java.sql.*;
import java.util.ArrayList;

public class passenger {

    private String pname;
    private int PID;
    private int age;

    //java.sql.Connection con = Connection.getInstance().getConnection();


    public passenger(int ID, String pname, int age) {
        this.PID = ID;
        this.pname = pname;
        this.age = age;
    }

    public String getPname() {
        return pname;
    }

    public int getPassengerID() {
        return PID;
    }

    public int getAge() {
        return age;
    }

    public void createSchedule() {
      //  passSch = new PassengerSchedule(876);
    }

    public void insertEntertainment(int eid, int startTime, int endTime) {

        //  int EID = entertainment.getactID();
        //  int startTime = entertainment.getStartTime();
        //  int endTime = entertainment.getEndTime();

//        for (int i = 0; i< entertainmentList.size(); i++) {
//            if ((startTime > entertainmentList.get(i).getStartTime() && startTime < entertainmentList.get(i).getEndTime())
//                    || (endTime > entertainmentList.get(i).getStartTime() && endTime < entertainmentList.get(i).getEndTime())) {
//                System.out.println("Time Conflict with activity currently on the schedule");
//                return;
//            }
//        }

        //   try {
        //   PreparedStatement ps = con.prepareStatement("INSERT INTO schedule" + "(sid, eid, sstime, setime) VALUES" + "(?,?,?,?)");
        //  ps.setInt(1, scheduleKey);
        //   ps.setInt(2, eid);
        //  ps.setInt(3, startTime);
        //  ps.setInt(4, endTime);
        //  ps.executeUpdate();
        //  } catch (SQLException se) {
        //      se.printStackTrace();
        //  }

        System.out.println("Entertainment was successfuly added");
    }

    public ResultSet viewSchedule() {
        ResultSet rs = null;
        String query = "select E.name, SC.sstime, SC.setime," +
                "from entertainment E, schedule S, schedulecontent SC, passenger P" +
                "where" + PID +" = S.sid AND S.eid = SC.eid AND E.eid = SC.eid";

//         try {
//           Statement s = con.createStatement();
//           rs = s.executeQuery(query);
//          } catch (SQLException se) {
//          se.printStackTrace();
//          }
        return rs;
    }



}