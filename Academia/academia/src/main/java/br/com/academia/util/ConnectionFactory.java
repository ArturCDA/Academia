package br.com.academia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/academia";

    private static final String USER = "postgres";
    private static final String PASSWORD = "0608";

    private ConnectionFactory() {
    }

    public static Connection getConnection() {
    try {
        Class.forName("org.postgresql.Driver"); 
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao conectar ao PostgreSQL", e);
    }
}

}

