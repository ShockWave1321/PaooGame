package Game.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBase
{
    private static int id = 0;
    Connection c = null;
    Statement stmt = null;
    String url = "./src/Game/DataBase/";
    private final String database = "GameDataBase.db";
    private final String mapUrl = "Resources/Maps/";
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
    public void CreateCharacterTable()
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Attributes " +
                    "(" +
                    "id INTEGER," +
                    "health INTEGER, " +
                    "mana INTEGER, " +
                    "experience INTEGER, " +
                    "money INTEGER," +
                    "xPos INTEGER," +
                    "yPos INTEGER" +
                    ")";
            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Character table created successfully");
    }
    public void InsertCharacterRecord(int life, int mana, int experience, int money, int xPos, int yPos)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO Attributes VALUES("+id+ ", "+life+", "+mana+", "+experience+", "+money+", "+xPos+", "+yPos+")";
            id++;
            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void UpdateCharacterRecord(int id, int life, int mana, int experience, int money, int xPos, int yPos)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "UPDATE Attributes SET" +
                    "life = "+life+", "+
                    "mana = "+mana+", "+
                    "experience = "+experience+", "+
                    "money = "+money+", "+
                    "xPos = "+xPos+", "+
                    "yPos = "+yPos+
                    "WHERE id = " + id + ";";
            stmt.executeQuery(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void SelectCharacterRecord(int id)
    {
        GetConnection(database);
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "SELECT * FROM Attributes WHERE id = " + id + ";";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                int health = rs.getInt("health");
                int mana = rs.getInt("mana");
                int experience = rs.getInt("experience");
                int money = rs.getInt("money");
                int xPos = rs.getInt("xPos");
                int yPos = rs.getInt("yPos");
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void DeleteCharacterRecord(int ident)
    {
        GetConnection(database);
        try {
            if(id > 0)
            {
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String sql = "DELETE FROM Attributes WHERE id = '" + ident + "';";
                id--;
                stmt.execute(sql);
                stmt.close();
                c.commit();
                c.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public void InitDataBase()
    {
        //DeleteDB("Map1");
        //DeleteDB("Map2");
        //DeleteDB("test_map");
        //CreateMapDatabase("Map1");
        //CreateMapDatabase("Map2");
        //CreateMapDatabase("test_map");
        CreateCharacterTable();
    }
}