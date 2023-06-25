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
        SELECT * FROM medicines
        """;
    LinkedList<Medicine> allMedicine = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
         ResultSet resultSet = preparedStatement.executeQuery(sqlSyntax)) {
      while (resultSet.next()) {
        Medicine medicine = new Medicine();
        medicine.setId(resultSet.getLong("id"));
        medicine.setFullName(resultSet.getString("full_name"));
        medicine.setBrand(resultSet.getString("brand"));
        medicine.setPrice(resultSet.getInt("price"));
        medicine.setStock(resultSet.getInt("stock"));
        medicine.setCreatedAt(resultSet.getTimestamp("created_at"));
        medicine.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        allMedicine.add(medicine);
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allMedicine;
  }


  @Override
  public Long createMedicine(Connection connection, Medicine medicine) throws DatabaseError, ActionError {
    String sqlSyntax = """
        INSERT INTO medicines(full_name, brand, price, stock) VALUES(?,?,?,?)
        """;
    long generatedKeys;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, medicine.getFullName());
      preparedStatement.setString(2, medicine.getBrand());
      preparedStatement.setInt(3, medicine.getPrice());
      preparedStatement.setInt(4, medicine.getStock());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        generatedKeys = resultSet.getLong(1);
      } else {
        resultSet.close();
        throw new ActionError("create medicine", "create medicine failed");
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return generatedKeys;
  }

  @Override
  public void updateMedicine(Connection connection, Medicine medicine) throws DatabaseError {
    String sqlSyntax = """
        UPDATE medicines SET full_name=?, brand=?, price=?, stock=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, medicine.getFullName());
      preparedStatement.setString(2, medicine.getBrand());
      preparedStatement.setInt(3, medicine.getPrice());
      preparedStatement.setInt(4, medicine.getStock());
      preparedStatement.setTimestamp(5, medicine.getUpdatedAt());
      preparedStatement.setLong(6, medicine.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteMedicine(Connection connection, Long medicineId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM medicines WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, medicineId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
