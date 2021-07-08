package com.haulmont.testtask.serivce;

import com.haulmont.testtask.dao.BankDAO;
import com.haulmont.testtask.database.DatabaseHelper;
import com.haulmont.testtask.entity.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankService extends DatabaseHelper implements BankDAO {

    Connection connection = getConnection();

    @Override
    public void add(Bank bank) {
        PreparedStatement ps = null;

        String sql = "INSERT INTO BANK (ID, NAME) VALUES(?, ?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, bank.getId());
            ps.setString(2, bank.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            extracted(ps);
        }

    }

    @Override
    public List<Bank> getAll() {
        List<Bank> bankList = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM BANK";
        Statement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Bank bank = new Bank();

                bank.setId(resultSet.getLong("ID"));
                bank.setName(resultSet.getString("NAME"));

                bankList.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            extracted(statement);
        }
        return bankList;
    }


    @Override
    public Bank findById(Long id) {

        PreparedStatement ps = null;

        String sql = "SELECT * FROM BANK WHERE ID=?";
        Bank bank = new Bank();

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            bank.setId(resultSet.getLong("ID"));
            bank.setName(resultSet.getString("NAME"));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            extracted(ps);
        }

        return bank;
    }

    @Override
    public void update(Bank bank) {

        PreparedStatement ps = null;

        String sql = "UPDATE BANK SET NAME=? WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, bank.getName());
            ps.setLong(2, bank.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            extracted(ps);
        }

    }

    @Override
    public void delete(Bank bank) {

        PreparedStatement ps = null;
        String sql = "DELETE FROM BANK WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1 , bank.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            extracted(ps);
        }

    }


    private void extracted(Statement statement) {
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
