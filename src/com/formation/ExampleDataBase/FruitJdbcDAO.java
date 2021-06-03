package com.formation.ExampleDataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FruitJdbcDAO implements CrudDao<Long,Fruit>{
    private Connection connection;
    public FruitJdbcDAO(){
        this.connection = ConnectionManager.getConnection();;
    }

    @Override
    public Long create(Fruit fruit) {
        String query = "INSERT INTO Fruits (name, expirationDate) values (?,?)";
        Long insertedId = null;
        try (PreparedStatement pst = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
           pst.setString(1, fruit.getName());
           pst.setObject(2,fruit.getDate());
           boolean isInsertRow = pst.execute();

               ResultSet keys = pst.getGeneratedKeys();
               keys.next();
            insertedId = keys.getLong(1);

            connection.commit();
        }
         catch (SQLException e1) {
            e1.printStackTrace();
             try {
                 connection.rollback();
             } catch (SQLException e2) {
                 e2.printStackTrace();
             }

         }
        return insertedId;
    }

    @Override
    public Fruit findById(Long along) {
        Fruit fruit = new Fruit();
        String query = "SELECT * FROM fruits WHERE id = ? " ;
        try (PreparedStatement pst  = connection.prepareStatement(query)) {
            pst.setLong(1,along);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                fruit.setId(rs.getLong("id"));
                fruit.setName(rs.getString("name"));
                fruit.setDate(rs.getObject("expirationDate",LocalDate.class));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return fruit;
    }

    @Override
    public List<Fruit> findAll() {
        List<Fruit> listfruit = new ArrayList<>();
        String query = "SELECT * FROM fruits";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Fruit fruit = new Fruit();
                fruit.setId(rs.getLong("id"));
                fruit.setName(rs.getString("name"));
                fruit.setDate(rs.getObject("expirationDate",LocalDate.class));
                listfruit.add(fruit);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listfruit;
    }

    @Override
    public Boolean update(Fruit fruit) {
        int updateRows = 0;
        String query = "UPDATE Fruits SET name = ?, expirationDate = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, fruit.getName());
            pst.setObject(2,fruit.getDate());
            pst.setLong(3,fruit.getId());
            updateRows = pst.executeUpdate();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updateRows > 0;
    }

    @Override
    public Boolean delete(Long along) {
        boolean idDeteted = false;
        String query = "DELETE FROM  Fruits  WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setLong(1,along);
            idDeteted= pst.execute();
            connection.commit();

        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        return idDeteted;
    }
    private Fruit mapToFruit(ResultSet rs) throws SQLException{
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        LocalDate date  = rs.getObject("expirationDate",LocalDate.class);
        return new Fruit(id,name,date);
    }
}
