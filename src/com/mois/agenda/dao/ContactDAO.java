package com.mois.agenda.dao;

import com.mois.agenda.factory.ConnectionFactory;
import com.mois.agenda.model.Contact;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    //CRUD: Create
    public void save(Contact contact) {

        String sql = "INSERT INTO contacts(name, age, registerDate) VALUES (?, ?, ?)";

        Connection connection = null;
        JdbcPreparedStatement pstm = null;

        try {
            connection = ConnectionFactory.createConnectionToMySQL();
            pstm = (JdbcPreparedStatement) connection.prepareStatement(sql);

            pstm.setString(1, contact.getName());
            pstm.setInt(2, contact.getAge());
            pstm.setDate(3, new Date(contact.getRegisterDate().getTime()));

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstm != null) {
                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //CRUD: Read
    public List<Contact> getContacts() throws SQLException {
        String sql = "SELECT * FROM contacts";

        List<Contact> contacts = new ArrayList<Contact>();

        Connection connection = null;
        JdbcPreparedStatement pstm = null;

        ResultSet rst = null;

        try {
            connection = ConnectionFactory.createConnectionToMySQL();

            pstm = (JdbcPreparedStatement) connection.prepareStatement(sql);

            rst = pstm.executeQuery();

            while (rst.next()) {
                Contact contact = new Contact();

                contact.setId(rst.getInt("id"));
                contact.setName(rst.getString("name"));
                contact.setAge(rst.getInt("age"));
                contact.setRegisterDate(rst.getDate("registerDate"));

                contacts.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (rst != null) {
                    rst.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    return contacts;
    }

    //CRUD: Update
    public void update(Contact contact) {

        String sql = "UPDATE contacts SET name = ?, age = ?, registerDate = ? WHERE ID = ?";

        Connection connection = null;
        JdbcPreparedStatement pstm = null;

        try{
            connection = ConnectionFactory.createConnectionToMySQL();

            pstm = (JdbcPreparedStatement) connection.prepareStatement(sql);

            pstm.setString(1, contact.getName());
            pstm.setInt(2, contact.getAge());
            pstm.setDate(3, new Date(contact.getRegisterDate().getTime()));

            pstm.setInt(4, contact.getId());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    //CRUD: Delete
    public void deleteById(int id) {

        String sql = "DELETE FROM contacts WHERE ID = ?";

        Connection connection = null;
        JdbcPreparedStatement pstm = null;

        try{
            connection = ConnectionFactory.createConnectionToMySQL();

            pstm = (JdbcPreparedStatement) connection.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

}
