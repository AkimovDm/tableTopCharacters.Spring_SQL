package characterbase.rest.tableTopCharacters.services;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;
import characterbase.rest.tableTopCharacters.model.CharacterModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Map<String, CharacterModel> CHARACTER_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CHARACTER_ID_HOLDER = new AtomicInteger();


    private static String URL="jdbc:postgresql://localhost:5432/first_db";
    private static String USER_NAME="postgres";
    private static String PASSWORD="12345";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Connected to DataBase");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void create(CharacterModel characterModel) {
        final String name = characterModel.getName().toLowerCase();
        characterModel.setId(CHARACTER_ID_HOLDER.incrementAndGet());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO charactermodel VALUES (1,?,?,?)");
            // insert into charactermodel values (4, 'Крейг Драго','DnD 5E','Человек');
            preparedStatement.setString(1,characterModel.getName());
            preparedStatement.setString(2,characterModel.getSystem());
            preparedStatement.setString(3,characterModel.getRace());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public List<CharacterModel> readAll() {
        List<CharacterModel> myCharacters=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM charactermodel";
            ResultSet resultSet=statement.executeQuery(sql);
            System.out.println("SQL request sended");
            if (!resultSet.next()) System.out.println("ResultSet is empty");

            while (resultSet.next()){


                CharacterModel characterModel=new CharacterModel();
                characterModel.setId(resultSet.getInt("id"));
                characterModel.setName(resultSet.getString("charactername"));
                characterModel.setSystem(resultSet.getString("system"));
                characterModel.setRace(resultSet.getString("race"));

                myCharacters.add(characterModel);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Returning myCharacter list from DB");
        return myCharacters;

    }

    @Override
    public CharacterModel read(String name) {
        CharacterModel characterModel=null;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT * FROM charactermodel WHERE charactername=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            characterModel=new CharacterModel();
            characterModel.setId(resultSet.getInt("id"));
            characterModel.setName(resultSet.getString("charactername"));
            characterModel.setSystem(resultSet.getString("system"));
            characterModel.setRace(resultSet.getString("race"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return characterModel;
    }


    @Override
    public boolean update(CharacterModel characterModel, String name) {
        PreparedStatement preparedStatement= null;
        boolean updated = false;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE charactermodel SET id=?, system=?, race=? WHERE charactername=?"
            );

        preparedStatement.setInt(1,characterModel.getId());
        preparedStatement.setString(2,characterModel.getSystem());
        preparedStatement.setString(3,characterModel.getRace());
        preparedStatement.setString(4, name);
        preparedStatement.executeUpdate();
        updated=true;

        } catch (SQLException e) {
            updated=false;
            throw new RuntimeException(e);

        }
            return true;
    }

    @Override
    public boolean delete(String name) {
        boolean updated=false;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "DELETE FROM charactermodel WHERE charactername=?");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            updated=true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return updated;
    }
}
