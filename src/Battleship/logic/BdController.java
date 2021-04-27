/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battleship.logic;

import Battleship.gui.History;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BdController {
    private ArrayList<History> hist = new ArrayList<History>();
    
    public void createDatabase() {
        //соединение с базой данных
        Connection c;
        try {
          //устанавливаем соедиение с базой данных
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:database.db");
          createHisrotyTables();
        } catch ( Exception e ) {
          //в случае если соединиться не удалось
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    

    
    void createHisrotyTables() {
        Connection c = null;
        //Выражение которое выполняет SQL код и возращает результат
        Statement stmt;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:database.db");
          stmt = c.createStatement();
          //код на SQL
          String sql = "CREATE TABLE IF NOT EXISTS HISTORY " +
                            "(ID          INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
                            " WIN         CHAR(50)," + 
                            " SHIP        INT," + 
                            " POINT       INT)"; 
          //выполнить код sql
          stmt.executeUpdate(sql);
          //закрыть выражение
          stmt.close();
          //закрыть соединение с базой данной
          c.close();
        } catch ( Exception e ) {
          //если база данных не открыалсь или таблица не создалась
         
          try {
            //закрыть соединение с базой данной
            c.close();
          } catch (Exception e2) {}
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    public void insertHistory(String win, int ship, int point) {
        //соединение с базой данных
        Connection c;
        //Выражение которое выполняет SQL код и возращает результат
        Statement stmt;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:database.db");
          //отключили автоматическое выполнение операций над бд
          c.setAutoCommit(false);
          stmt = c.createStatement();
          String sql = "INSERT INTO HISTORY (WIN,SHIP,POINT) " +
                       "VALUES ('"+win+"', "+ship+","+point+");";
          stmt.executeUpdate(sql); // выполнить код sql

          stmt.close();
          //выполняем все вышеперечисленное
          c.commit();
          c.close();
        } catch ( Exception e ) {          
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    
    public void selectHistory() {
        Connection c;
        Statement stmt;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:database.db");
          c.setAutoCommit(false);
          stmt = c.createStatement();
          //сюда попадают данные которые являются результатом запроса
          ResultSet rs = stmt.executeQuery( "SELECT * FROM HISTORY;" );
          //перейти к следующему если он есть
          while ( rs.next() ) {
             int id = rs.getInt("id"); 
             String  win = rs.getString("win");
             int ship  = rs.getInt("ship");
             int  point = rs.getInt("point");
             hist.add(new History(win, ship, point));
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
    }
    
    public ArrayList<History> getHist(){
        return this.hist;
    } 
}
