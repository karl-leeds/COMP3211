package Events.JDBC;

import Events.bean.Events;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class For_Events {

    private static DataSource ds;
    static{
        try{
            Properties pro = new Properties();
            pro.load(For_Events.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection conn) throws SQLException {
        if(conn!=null) {
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stat) throws SQLException {
        if(stat != null) {
            try {
                stat.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet result) throws SQLException {
        if(result!=null) {
            try {
                result.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static List<Events> get(Connection conn, Events obj) throws SQLException, ParseException {
        //initialize
        List<Events> result_list = new ArrayList<Events>();
        Statement stat = null;
        ResultSet result = null;

        //generate the sql string
        String sql = "select * from `events`";
        String[] temp = {"","","","","","",""};
        if(!Objects.equals(obj.getEvent_title(), "null")){
            temp[0] = "`event_title` = '" + obj.getEvent_title() + "'";
        }
        if(!Objects.equals(obj.getEvent_description(), "null")){
            temp[1] = "`event_description` = '" + obj.getEvent_description() + "'";
        }
        if(!Objects.equals(obj.getUser_name(), "null")){
            temp[2] = "`user_name` = '" + obj.getUser_name() + "'";
        }
        if(obj.getEvent_id() != -1){
            temp[3] = "`event_id` = '" + obj.getEvent_id() + "'";
        }

        int record = 0;
        for(int i = 0; i < 4; i++){
            if(record == 1 && !temp[i].equals("")){
                temp[i] = " and " + temp[i];
            }
            if(!temp[i].equals("")){
                record = 1;
            }
            temp[6] = temp[6] + temp[i];
        }

        if(!temp[6].equals("")){
            sql = sql + " where " + temp[6] + ";";
        }else{
            sql = sql + ";";
        }

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_start = sdf.parse(obj.getStart_time());
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.setTime(date_start);

        Date date_end = sdf.parse(obj.getEnd_time());
        Calendar calendar_end = Calendar.getInstance();
        calendar_end.setTime(date_end);

        try {
            //use sql string to get data from database
            stat = conn.createStatement();
            result = stat.executeQuery(sql);


            //initialize variable
            Events eve;

            //retrive data to the event object and add to a list
            while(result.next()){
                long id = result.getLong("event_id");
                String title = result.getString("event_title");
                String description = result.getString("event_description");
                String name = result.getString("user_name");
                String start_t = result.getString("start_time");
                String end_t = result.getString("end_time");

                Date date_start_s = sdf.parse(start_t);
                Calendar calendar_start_s = Calendar.getInstance();
                calendar_start_s.setTime(date_start_s);




                if(obj.getStart_time().equals("2000-01-01 00:00:00") && obj.getEnd_time().equals("2000-01-01 00:00:00")){
                    eve = new Events();
                    eve.setEvent_id(id);
                    eve.setUser_name(name);
                    eve.setEvent_title(title);
                    eve.setEvent_description(description);
                    eve.setStart_time(start_t);
                    eve.setEnd_time(end_t);


                    result_list.add(eve);
                }else {
                    if (calendar_start_s.before(calendar_end) && calendar_start_s.after(calendar_start)) {
                        eve = new Events();
                        eve.setEvent_id(id);
                        eve.setUser_name(name);
                        eve.setEvent_title(title);
                        eve.setEvent_description(description);
                        eve.setStart_time(start_t);
                        eve.setEnd_time(end_t);

                        result_list.add(eve);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(result);
            close(stat);
        }
        return result_list;
    }

    public static int delete(Connection conn,Events obj) throws Exception {
        //generate sql string
        String sql = "DELETE FROM `events` where ";
        //search event data to check whether the data is in the database
        List<Events> result_list = get(conn,obj);
        if(result_list.size() == 0){
            return 0;
        }
        Statement as = null;

        try{
            //use sql to delete data in the database
            as = conn.createStatement();
            for(int i = 0; i < result_list.size(); i++){
                int aa = as.executeUpdate(sql + "`event_id` = '" + result_list.get(i).getEvent_id() + "';");
            }

            return 1;//means delete success
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(as);
        }
        return 0;//means delete unprocessed
    }

    public static int insert(Connection conn,Events obj) throws Exception {
        //initialize
        Statement as = null;
        //search database check whether or not the data is in the database
        if(obj.getEvent_id() != -1){
            Events search = new Events();
            search.setEvent_id(obj.getEvent_id());
            List<Events> search_result = get(conn,search);
            if(search_result.size() != 0){
                throw new Exception("cannot have the same event number");
            }
        }

        //generate insert sql string
        String sql = "INSERT INTO `events`(`event_title`,`event_description`,`user_name`,`start_time`,`end_time`) values ('"
                +obj.getEvent_title() + "','" + obj.getEvent_description()+"','"
                +obj.getUser_name() + "','"+obj.getStart_time() + "','"
                +obj.getEnd_time() + "');";




        try{
            //use insert sql string to insert data and do input validation check
            as = conn.createStatement();
            as.executeUpdate(sql);




            return 1;//means success
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(as);
        }
        return 0;//means unsuccess
    }

    public static int update(Connection conn,Events search_obj,Events update_obj) throws Exception {
        //initialize variable
        Statement as = null;
        //search data to check whether the data is in the database
        List<Events> search_result = get(conn,search_obj);

        //generate sql string
        String[] temp = {"","","","","","",""};

        if(!update_obj.getEvent_title().equals("null")){
            temp[0] = " `event_title` = '" + update_obj.getEvent_title() + "'";
        }
        if(!update_obj.getEvent_description().equals("null")){
            temp[1] = " `event_description` = '" + update_obj.getEvent_description() + "'";
        }
        if(!update_obj.getUser_name().equals("null")){
            temp[2] = " `user_name`  = '" + update_obj.getUser_name() + "'";
        }
        if(update_obj.getEvent_id() != -1){
            temp[3] = " `event_id` = '" + update_obj.getEvent_id() + "'";
        }
        if(!update_obj.getStart_time().equals("null")){
            temp[4] = "`start_time`  = '" + update_obj.getStart_time()+"'";
        }
        if(!update_obj.getEnd_time().equals("null")){
            temp[5] = " `end_time` = '" + update_obj.getEnd_time() +"'";
        }

        if(temp[0].equals("") && temp[1].equals("") && temp[2].equals("") && temp[3].equals("") && temp[4].equals("") && temp[5].equals("")){
            throw new Exception("please using correct event as parameter");

        }

        int record = 0;
        for(int i = 0; i < 6; i++){
            if(record == 1 && !temp[i].equals("")){
                temp[i] = ", " + temp[i];
            }
            if(!temp[i].equals("")){
                record = 1;
            }
            temp[6] = temp[6] + temp[i];
        }

        try{
            //use sql string to update database data
            as = conn.createStatement();
            String sql = "UPDATE `events` SET " + temp[6] + " where ";
            String sql_temp;
            for(int i = 0; i < search_result.size(); i++){
                sql_temp = sql + "`event_id` = '"+search_result.get(i).getEvent_id()+"';";


                as.executeUpdate(sql_temp);
            }

            return 1;//means success
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(as);
        }
        return 0;//means unsuccess
    }





}
