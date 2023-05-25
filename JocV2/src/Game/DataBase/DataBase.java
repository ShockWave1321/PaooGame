package Game.DataBase;

import Game.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DataBase
{
    Connection c = null;
    Statement stmt = null;
    String url = "./src/Game/DataBase/";
    private final String database = "GameDataBase.db";
    final String mapUrl = "Resources/Maps/";

    public DataBase()
    {
        InitDataBase();
    }

    public void GetConnection(String database)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+ url + database);
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public ArrayList<Object> ReadFileMap(String location, String map)
    {
        int rows = 0;
        int columns = 0;
        String[] lines = null;

        try {
            File file = new File(location + map+".txt");
            Scanner scanner = new Scanner(file);

            rows = scanner.nextInt();
            columns = scanner.nextInt();
            lines = new String[rows];

            for (int i = 0; i < rows; i++) {
                lines[i] ="" + scanner.nextInt();
                for (int j = 1; j < columns; j++) {
                    lines[i] +=" "+scanner.nextInt();
                }
            }
            scanner.close();
            //System.out.println(file.getName());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        ArrayList<Object> list = new ArrayList<>();
        list.add(rows);
        list.add(columns);
        list.add(lines);
        return list;
    }
    public void InsertMapDatabase(String map, int rows, int columns, String[] lines)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO " + map +" VALUES("+rows+", "+columns;
            for (int i = 0; i < rows; i++)
            {
                sql = sql + ", '" + lines[i] + "'";
            }
            sql += ");";

            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void CreateMapDatabase(String map)
    {
        List<Object> data = ReadFileMap(mapUrl, map);
        int rows = (int)data.get(0);
        int columns = (int)data.get(1);
        String[] lines = (String[]) data.get(2);

        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS "+ map +" (rows INTEGER, columns INTEGER, row0 TEXT";
            for (int i = 1; i < rows; i++)
            {
                sql = sql + ", row" + i + " TEXT";
            }
            sql += ");";

            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Map table created successfully");

        InsertMapDatabase(map, rows, columns, lines);
    }
    public ArrayList<Object> SelectMap(String name)
    {
        GetConnection(database);
        int rows = 0;
        int columns = 0;
        String[] lines = null;

        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM "+name);
            rows = rs.getInt(1);
            columns = rs.getInt(2);
            lines = new String[rows];
            for(int i = 0; i < rows; ++i)
            {
                lines[i] = rs.getString("row"+i);
            }
            rs.close();

            stmt.close();
            c.commit();
            c.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        ArrayList<Object> list = new ArrayList<>();
        list.add(rows);
        list.add(columns);
        list.add(lines);

        System.out.println("Operation done successfully");
        return list;
    }


    //Inserare in tabel HERO
    public void CreateCharacterTable()
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //System.out.println("Start");
            String sql = "CREATE TABLE IF NOT EXISTS HERO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "xPos REAL, " +
                    "yPos REAL, " +
                    "health INTEGER, " +
                    "mana INTEGER, " +
                    "experience INTEGER, " +
                    "money INTEGER" +
                    ")";

            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Character table created successfully");
    }
    public void InsertCharacterRecord(float xPos, float yPos, int health, int mana, int experience, int money)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO HERO (xPos, yPos, health, mana, experience, money) VALUES ("+xPos+","+yPos+","+health+","+mana+","+experience+","+money+")";

            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void UpdateCharacterRecord(int id, float xPos, float yPos, int health, int mana, int experience, int money)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "UPDATE HERO SET xPos = " + xPos
                    + ", yPos = " + yPos
                    + ", health = " + health
                    + ", mana = " + mana
                    + ", experience = " + experience
                    + ", money = " + money
                    + " WHERE id = " + id;

            int rowsUpdated = stmt.executeUpdate(sql);
            if (rowsUpdated > 0)
            {
                System.out.println("Record updated successfully for ID: " + id);
            }
            else
            {
                System.out.println("Record not found for ID: " + id);
            }
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public Map<String, Object> SelectCharacterRecord(int id)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            Map<String, Object> map = new HashMap<>();

            String sql = "SELECT * FROM HERO WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                map.put("xPos", rs.getFloat("xPos"));
                map.put("yPos", rs.getFloat("yPos"));
                map.put("health", rs.getInt("health"));
                map.put("mana", rs.getInt("mana"));
                map.put("experience", rs.getInt("experience"));
                map.put("money", rs.getInt("money"));
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();

            return map;
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
    public void DeleteCharacterRecord(int id)
    {
        GetConnection(database);
        try {
            if(id > 0)
            {
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String sql = "DELETE FROM HERO WHERE id = " + id;
                int rowsDeleted = stmt.executeUpdate(sql);
                if (rowsDeleted > 0)
                {
                    System.out.println("Record deleted successfully for ID: " + id);
                }
                else
                {
                    System.out.println("Record not found for ID: " + id);
                }
                stmt.close();
                c.commit();
                c.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void DeleteTable(String name)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "DROP TABLE IF EXISTS "+name;
            stmt.executeUpdate(sql);

            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table deleted");
    }
    public void InitDataBase()
    {
        CreateCharacterTable();
        /*InsertCharacterRecord(15.f,15.f,10,5,20,20);
        UpdateCharacterRecord(1,10.f,10.f,8,5,10,40);
        DeleteCharacterRecord(2);
        Map<String,Object> map = SelectCharacterRecord(3);
        System.out.println((int)map.get("experience"));
        DeleteTable("HERO");*/
    }
    //DeleteDB("Map1");
    //DeleteDB("Map2");
    //DeleteDB("test_map");
    //CreateMapDatabase("Map1");
    //CreateMapDatabase("Map2");
    //CreateMapDatabase("test_map");
}