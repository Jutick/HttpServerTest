package postres.bd;

import java.sql.*;
import java.util.UUID;

public class PetDB
{
    private static String url = "jdbc:postgresql://localhost/Test";
    private static String username = "postgres";
    private static String password = "1234";

    public static int insert(Pet pet)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password))
            {
                String sql = "INSERT INTO pets (id, name) Values (?, ?); " +
                        "INSERT INTO breed(name, pets_id) Values(?,?);" +
                        "INSERT INTO food(name, pets_id) Values(?,?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql))
                {
                    preparedStatement.setObject(1, UUID.fromString(pet.getId()), java.sql.Types.OTHER);
                    preparedStatement.setString(2, pet.getName());
                    preparedStatement.setString(3, pet.getBreed().getName());
                    preparedStatement.setObject(4, UUID.fromString(pet.getId()), java.sql.Types.OTHER);
                    preparedStatement.setString(5, pet.getFood().getName());
                    preparedStatement.setObject(6, UUID.fromString(pet.getId()), java.sql.Types.OTHER);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return 0;
    }

    public static Pet selectOne(String id)
    {
        Pet pet = null;
        try
        {
            Class.forName("org.postgresql.Driver");;
            try (Connection conn = DriverManager.getConnection(url, username, password))
            {
                String sql = "SELECT pets.id, pets.name, breed.id, breed.name, food.id, food.name FROM pets " +
                        "JOIN breed ON pets.id = breed.pets_id " +
                        "JOIN food ON pets.id = food.pets_id " +
                        "WHERE pets.id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql))
                {
                    preparedStatement.setObject(1, UUID.fromString(id), java.sql.Types.OTHER);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next())
                    {
                        String petId = resultSet.getObject(1).toString();
                        String petName = resultSet.getString(2);
                        String breedId = resultSet.getObject(3).toString();
                        String breedName = resultSet.getString(4);
                        String foodId = resultSet.getObject(5).toString();
                        String foodName = resultSet.getString(6);

                        pet = new Pet(petId, petName, new Breed(breedId, breedName), new Food(foodId, foodName));
                    }
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return pet;
    }

}
