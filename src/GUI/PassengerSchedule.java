package GUI;

/**
 * Created by Sp05_ on 2017-03-15.
 */

import java.sql.*;
import java.util.ArrayList;


public class PassengerSchedule {

   //java.sql.Connection con = Connection.getInstance().getConnection();

   private int scheduleKey;
   ArrayList<Entertainment>  entertainmentList;

    public PassengerSchedule(int scheduleKey) {
    this.scheduleKey = scheduleKey;
    entertainmentList = new ArrayList<Entertainment>();
    }

    //delete and activity to the schedule
    public void deleteEntertainment(int EID) {

    }

    public void getScheduleEntertainment(int entID) {

    }


}
