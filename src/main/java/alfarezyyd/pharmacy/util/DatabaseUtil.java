package alfarezyyd.pharmacy.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

public class DatabaseUtil {

  private static HikariDataSource hikariDataSource = new HikariDataSource(getHikariConfig());

  private static HikariConfig getHikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/java_pharmacy");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("");

    hikariConfig.setMaximumPoolSize(20);
    hikariConfig.setMinimumIdle(5);
    hikariConfig.setIdleTimeout(60_000);
    hikariConfig.setMaxLifetime(10 * 60_000);
    return hikariConfig;
  }

  public static HikariDataSource getHikariDataSource() {
    return hikariDataSource;
  }
}
