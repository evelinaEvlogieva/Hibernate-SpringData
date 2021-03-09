import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection){
        this.connection = connection;
    }

    public void run() {
//        try {
//            this.getVillainsName();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            this.getMinionsNameAndAge();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            this.setTownNamesChanged();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            this.addMinion();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            this.increaseMinionsAge();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void increaseMinionsAge() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String updateAgeQuery = "UPDATE minions\n" +
                "SET age = age + 1\n" +
                "WHERE id = ?;";

        String updateNameQuery = "UPDATE minions\n" +
                "SET name = lower(name)\n" +
                "where id = ?;";

        String selectQuery = "SELECT name, age\n" +
                "FROM minions;";

        PreparedStatement updateAgeStatement = this.connection.prepareStatement(updateAgeQuery);

        PreparedStatement updateNameStatement = this.connection.prepareStatement(updateNameQuery);

        Statement selectStatement = this.connection.createStatement();


        for (int i = 0; i < input.length; i++) {
            updateAgeStatement.setInt(1, input[i]);
            updateAgeStatement.executeUpdate();
            updateNameStatement.setInt(1, input[i]);
            updateNameStatement.executeUpdate();
        }

        ResultSet result =  selectStatement.executeQuery(selectQuery);

        while (result.next()){
            System.out.printf("%s %d%n",
                    result.getString("name"),
                    result.getInt("age"));
        }

    }


    private void getVillainsName() throws SQLException {
        String query = "SELECT `name`, count(mv.minion_id) as `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING `count` > 15\n" +
                "ORDER BY `count` DESC;";


        Statement statement = this.connection.createStatement();

        ResultSet result = statement.executeQuery(query);

        while (result.next()){
            System.out.printf("%s %d%n",
                    result.getString("name"),
                    result.getInt("count"));
        }

    }

    private void getMinionsNameAndAge() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String query = "SELECT m.name, m.age, v.name\n" +
                "FROM minions AS m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "JOIN villains v on mv.villain_id = v.id\n" +
                "WHERE mv.villain_id = ?;";

        PreparedStatement statement = this.connection.prepareStatement(query);

        String input = scanner.nextLine();

        statement.setInt(1, Integer.parseInt(input));

        ResultSet result = statement.executeQuery();

        if (!result.next()){
            System.out.printf("No villain with ID %s exists in the database.", input );
        } else {
            System.out.printf("Villain: %s%n", result.getString("v.name"));
            Integer count = 1;
            while (result.next()){
                System.out.printf("%d. %s %d%n",
                        count, result.getString("m.name"),
                        result.getInt("m.age"));
                count++;

            }
        }
    }

    private void setTownNamesChanged() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String updateQuery = "UPDATE towns\n" +
                "SET `name` = upper(`name`)\n" +
                "WHERE `country` = ?;";

        String selectQuery = "SELECT `name`" +
                "from towns\n" +
                "where `country` = ?;";

        String countQuery = "SELECT count(name) AS count\n" +
                "FROM towns\n" +
                "WHERE country = ?;";

        PreparedStatement updateStatement = this.connection.prepareStatement(updateQuery);
        PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
        PreparedStatement countStatement = this.connection.prepareStatement(countQuery);

        String input = scanner.nextLine();

        updateStatement.setString(1, input);
        updateStatement.executeUpdate();

        selectStatement.setString(1, input);
        ResultSet result = selectStatement.executeQuery();

        countStatement.setString(1, input);
        ResultSet resultC = countStatement.executeQuery();

       if (!resultC.next()){
           System.out.printf("No town names were affected.%n");
       } else {
           System.out.printf("%d town names were affected.%n",
                   resultC.getInt("count"));
           while (result.next()){
               System.out.printf("%s%n", result.getString("name"));
           }
       }

    }

    // не е довършена
    private void addMinion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String[] minionData = scanner.nextLine().split(" ");
        String[] villainData = scanner.nextLine().split(" ");

        String name = minionData[1];
        int age = Integer.parseInt(minionData[2]);
        String town = minionData[3];
        String villain = villainData[1];

        String checkNameQuery = "SELECT name\n" +
                "FROM minions\n" +
                "where name = ?";

        PreparedStatement nameStatement = this.connection.prepareStatement(checkNameQuery);

        nameStatement.setString(1, name);

        ResultSet nameResult = nameStatement.executeQuery();

        if (!nameResult.isBeforeFirst()){
            System.out.println("the name is NOT in DB");
        } else {
            System.out.println("the name IS in DB");
        }

    }

}
