package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Medicine;
import alfarezyyd.pharmacy.repository.MedicineRepository;

import java.sql.*;
import java.util.LinkedList;

public class MedicineRepositoryImpl implements MedicineRepository {
  @Override
  public LinkedList<Medicine> getAllMedicine(Connection connection) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM medicines WHERE deleted_at IS NULL
        """;
    LinkedList<Medicine> allMedicine = new LinkedList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlSyntax);
      while (resultSet.next()) {
        Medicine medicine = new Medicine();
        medicine.setId(resultSet.getLong("id"));
        medicine.setName(resultSet.getString("name"));
        medicine.setBrand(resultSet.getString("brand"));
        medicine.setPrice(resultSet.getInt("price"));
        allMedicine.add(medicine);
      }
      statement.close();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }
    return allMedicine;
  }

  @Override
  public LinkedList<Medicine> getAllDeletedMedicine(Connection connection) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM medicines WHERE updated_at IS NOT NULL
        """;
    LinkedList<Medicine> allMedicine = new LinkedList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlSyntax);
      while (resultSet.next()) {
        Medicine medicine = new Medicine();
        medicine.setId(resultSet.getLong("id"));
        medicine.setName(resultSet.getString("name"));
        medicine.setBrand(resultSet.getString("brand"));
        medicine.setPrice(resultSet.getInt("price"));
        allMedicine.add(medicine);
      }
      statement.close();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }

    return allMedicine;
  }

  @Override
  public Medicine getMedicineById(Connection connection, Long id) throws DatabaseError, ActionError {
    String sqlSyntax = """
        SELECT * FROM medicines WHERE id = ?
        """;
    Medicine medicine;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        medicine = new Medicine();
        medicine.setId(resultSet.getLong("id"));
        medicine.setName(resultSet.getString("name"));
        medicine.setDescription(resultSet.getString("description"));
        medicine.setBrand(resultSet.getString("brand"));
        medicine.setPrice(resultSet.getInt("price"));
        medicine.setStock(resultSet.getInt("stock"));
        medicine.setCreatedAt(resultSet.getTimestamp("created_at"));
        medicine.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        medicine.setDeletedAt(resultSet.getTimestamp("deleted_at"));
      } else {
        throw new ActionError("find medicine", "medicine not found");
      }
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }
    return medicine;
  }

  @Override
  public Long createMedicine(Connection connection, Medicine medicine) throws DatabaseError, ActionError {
    String sqlSyntax = """
        INSERT INTO medicines(name, description, brand, price, stock) VALUES(?,?,?,?,?)
        """;
    long generatedKeys = 0L;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, medicine.getName());
      preparedStatement.setString(2, medicine.getDescription());
      preparedStatement.setString(3, medicine.getBrand());
      preparedStatement.setInt(4, medicine.getPrice());
      preparedStatement.setInt(5, medicine.getStock());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        generatedKeys = resultSet.getLong(1);
      }
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return generatedKeys;
  }

  @Override
  public void updateMedicine(Connection connection, Medicine medicine) throws DatabaseError {
    String sqlSyntax = """
        UPDATE medicines SET name=?, description=?, brand=?, price=?, stock=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, medicine.getName());
      preparedStatement.setString(2, medicine.getDescription());
      preparedStatement.setString(3, medicine.getBrand());
      preparedStatement.setInt(4, medicine.getPrice());
      preparedStatement.setInt(5, medicine.getStock());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void softDeleteMedicine(Connection connection, Medicine medicine) throws DatabaseError {
    String sqlSyntax = """
        UPDATE medicines SET deleted_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setTimestamp(1, medicine.getDeletedAt());
      preparedStatement.setLong(2, medicine.getId());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void permanentlyDeleteMedicine(Connection connection, Long medicineId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM medicines WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, medicineId);
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode());
    }
  }
}
