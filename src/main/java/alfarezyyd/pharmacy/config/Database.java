package alfarezyyd.pharmacy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Database {

  private static final HikariDataSource hikariDataSource = new HikariDataSource(getHikariConfig());

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
