package org.springframework.demo;import org.springframework.beans.factory.annotation.Value;import org.springframework.context.annotation.PropertySource;import org.springframework.context.annotation.Scope;import org.springframework.stereotype.Component;import javax.annotation.PreDestroy;import javax.annotation.PostConstruct;import java.sql.DriverManager;import java.sql.PreparedStatement;import java.sql.ResultSet;@Component("dbc")@Scope("singleton")@PropertySource(value={"DBC.properties"})public class Connection {    @Value("${DBC.url}")    private String url;    @Value("${DBC.UserName}")    private String UserName;    @Value("${DBC.passs}")    private String passs;    private java.sql.Connection dBConnection;    public Connection(String url, String userName, String passs) {        this.url = url;        UserName = userName;        this.passs = passs;    }    public Connection() {    }    public  void openDB()throws Exception{        dBConnection = DriverManager.getConnection(url, UserName, passs);    }    @PostConstruct    public void init(){       try {        openDB();       }       catch (Exception e){        System.out.println(e.getMessage());       }    }    public  void closeDB()throws Exception{        dBConnection.close();    }    @PreDestroy    public void destroy(){        try {            closeDB();        }        catch (Exception e){            System.out.println(e.getMessage());        }    }    public ResultSet getUsers(){     ResultSet resultSet = null;        try {            PreparedStatement a = dBConnection.prepareStatement("SELECT * FROM person");            resultSet = a.executeQuery();        }        catch (Exception e){            System.out.println(e.getMessage());        }        return resultSet;    }    // UPDATE goods SET price = 150 WHERE num = 2    public void changeBalance(int i, int q){        try {            PreparedStatement a = dBConnection.prepareStatement("UPDATE person set balance = " + i +"where cardnum = "+ q );            System.out.println(a.executeUpdate());        }        catch (Exception e){            System.out.println(e.getMessage());        }    }    public void changePin(int i, int q){        try {            PreparedStatement a = dBConnection.prepareStatement("UPDATE person set pass = "+i +"where cardnum = "+ q );            System.out.println(a.executeUpdate());        }        catch (Exception e){            System.out.println(e.getMessage());        }    }}