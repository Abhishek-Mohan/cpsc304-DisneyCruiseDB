package com.disneycruise.cruise;

import java.sql.*;
import com.disneycruise.database.*;
/**
 * Created by abhis on 3/20/2017.
 */
public class manager {

    int man_id;
    String password;
    int mname;
    String department;
    int cid;
    Database db;
    Connection conn;

    public manager() {
        db = new Database();
        db.connectToDb("ora_z4n8", "a20232120");
        db.getConnection();
    }

   public ResultSet viewSchedulebyManID(int man_id) {
       ResultSet rs = null;
       String query = "select M.mname, SC.sstime, SC.setime," +
               "from manager M, entertainmentschedule ES, cleaningschedule CS " +
               "where " + man_id +" = M.man_id AND M.man_id = ES.man_id OR M.man_id = CS.man_id";

         try {
           Statement s = conn.createStatement();
           rs = s.executeQuery(query);
           s.close();
          } catch (SQLException se) {
          se.printStackTrace();
          }
       return rs;
   }


}
