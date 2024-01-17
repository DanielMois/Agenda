package com.mois.agenda.factory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTIES_FILE = "config.properties";

    public static Connection createConnectionToMySQL() throws ClassNotFoundException, SQLException {
        Properties properties = loadProperties();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), PROPERTIES_FILE);
            try (InputStream input = Files.newInputStream(filePath)) {
                properties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar as propriedades.", e);
        }
        return properties;
    }




    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection con = createConnectionToMySQL();
        if (con!=null) {
            System.out.println("Conexão obtida com sucesso");
            con.close();
        }

    }

}
