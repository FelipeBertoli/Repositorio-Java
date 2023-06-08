package models.query;

import models.entity.DBEntity;
import models.connection.DBConnection;
import java.sql.*;
import java.util.*;

public class DBQuery {

    public List<DBEntity> Consult() {
        String sql = "SELECT * FROM tb_nome";
        List<DBEntity> entities = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.openConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                DBEntity entity = new DBEntity();
                entity.setId(result.getInt("id"));
                entity.setNome(result.getString("nome"));

                entities.add(entity);
            }

            for (DBEntity e : entities) {
                System.out.println(e.getId() + " ==> " + e.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entities;
    }

    public void Save(DBEntity entity) {
        String sql = "INSERT INTO tb_nome(nome)" + " VALUES(?)";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DBConnection.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, entity.getNome());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void Update(DBEntity entity) {
        String sql = "UPDATE tb_nome SET nome = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.openConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getNome());
            statement.setInt(2, entity.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void Delete(int id) {
        String sql = "DELETE FROM tb_nome WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DBConnection.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
