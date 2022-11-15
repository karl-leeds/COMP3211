package Events.JDBC;

import Events.bean.Events;
import Students.JDBC.For_students;
import Students.bean.Students;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class For_Events {

    private static DataSource ds;
    static{
        try{
            Properties pro = new Properties();
            pro.load(For_students.class.getClassLoader().getResourceAsStream("druid.properties"));
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

    public static List<Events> get(Connection conn, Events obj) throws SQLException {
        //initialize
        List<Events> result_list = new ArrayList<Events>();
        Statement stat = null;
        ResultSet result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");

        //generate the sql string
        String sql = "select * from `events`";
        String[] temp = {"","","","","","",""};
        if(obj.getEvent_title() != null){
            temp[0] = "`event_title` = '" + obj.getEvent_title() + "'";
        }
        if(obj.getEvent_description() != null){
            temp[1] = "`event_description` = '" + obj.getEvent_description() + "'";
        }
        if(obj.getUser_name() != null){
            temp[2] = "`user_name` = '" + obj.getUser_name() + "'";
        }
        if(obj.getEvent_id() != -1){
            temp[3] = "`event_id` = '" + obj.getEvent_id() + "'";
        }
        if(obj.getStart_time() != null){
            temp[4] = "`start_time` = '" + obj.getStart_time()+"'";
        }
        if(obj.getEnd_time() != null){
            temp[5] = "`end_time` = '" + obj.getEnd_time()+"'";
        }

        int record = 0;
        for(int i = 0; i < 6; i++){
            if(record == 1 && temp[i] != ""){
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

                eve = new Events();
                eve.setEvent_id(id);
                eve.setUser_name(name);
                eve.setEvent_title(title);
                eve.setEvent_description(description);
                eve.setStart_time(start_t);
                eve.setEnd_time(end_t);

                result_list.add(eve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(stat);
            close(result);
        }
        return result_list;
    }

    public static int delete(Connection conn,Events obj) throws SQLException {
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
            //use insert sql string to insert data
            as = conn.createStatement();;
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
        if(update_obj.getEvent_title() != null){
            temp[0] = " `event_title` = '" + update_obj.getEvent_title() + "'";
        }
        if(update_obj.getEvent_description() != null){
            temp[1] = " `event_description` = '" + update_obj.getEvent_description() + "'";
        }
        if(update_obj.getUser_name() != null){
            temp[2] = " `user_name`  = '" + update_obj.getUser_name() + "'";
        }
        if(update_obj.getEvent_id() != -1){
            temp[3] = " `event_id` = '" + update_obj.getEvent_id() + "'";
        }
        if(update_obj.getStart_time() != null){
            temp[4] = "`start_time`  = '" + update_obj.getStart_time()+"'";
        }
        if(update_obj.getEnd_time() != null){
            temp[5] = " `end_time` = '" + update_obj.getEnd_time() +"'";
        }

        if(temp[0] == null && temp[1] == null && temp[2] == null && temp[3] == null && temp[4] == null && temp[5] == null  ){
            throw new Exception("please using correct event as parameter");

        }

        int record = 0;
        for(int i = 0; i < 6; i++){
            if(record == 1 && temp[i] != ""){
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
            for(int i = 0; i < search_result.size(); i++){
                as.executeUpdate(sql + "`event_id` = " +search_result.get(i).getEvent_id()+";");
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
