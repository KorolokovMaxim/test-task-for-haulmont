package com.haulmont.testtask.serivce;


import com.haulmont.testtask.Config;
import com.haulmont.testtask.dao.CrudDAO;
import com.haulmont.testtask.entity.Bank;
import com.haulmont.testtask.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientService implements CrudDAO<Client, String> {

    private final Connection db;

    public ClientService(Config config) {
        this.db = config.getDb();
    }

    @Override
    public void createOne(Client client) {
        String sql = "INSERT INTO CLIENT (ID, FIO, BANK_ID, PHONE_NUMBER, EMAIL, PASSPORT) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, client.getId());
            ps.setString(2, client.getFIO());
            ps.setObject(3, client.getBankID());
            ps.setString(4, client.getPhoneNumber());
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getPassport());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Bank.class.getSimpleName() + " Error: createOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> listOfBank = new ArrayList<>();
        String sql = "SELECT * FROM CLIENT";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getString("ID"));
                client.setFIO(rs.getString("FIO"));
                client.setBankID(rs.getString("BANK_ID"));
                client.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                client.setEmail(rs.getString("EMAIL"));
                client.setPassport(rs.getString("PASSPORT"));
                listOfBank.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfBank;
    }

    @Override
    public Client findById(String id) {
        ResultSet rs = null;
        Client client = new Client();
        String sql = "SELECT * FROM CLIENT WHERE id = ?";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                client.setId(rs.getString("ID"));
                client.setFIO(rs.getString("FIO"));
                client.setBankID(rs.getString("BANK_ID"));
                client.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                client.setEmail(rs.getString("EMAIL"));
                client.setPassport(rs.getString("PASSPORT"));

            } else {
                throw new IllegalArgumentException(Client.class.getSimpleName() + " Error: findById");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

//    @Override
//    public String getBank(String id) {
//        ResultSet rs = null;
//        Bank bank = new Bank();
//        String sql = "SELECT ID, NAME FROM BANK WHERE id = ?";
//        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                bank.setId(rs.getString("ID"));
//                bank.setName(rs.getString("NAME"));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return bank.getName();
//    }


    @Override
    public void updateOne(Client client) {

        String sql = "UPDATE CLIENT SET FIO = ?, BANK_ID = ?, PHONE_NUMBER = ?, EMAIL = ?, PASSPORT = ? WHERE ID = ?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, client.getFIO());
            ps.setObject(2, client.getBankID());
            ps.setString(3, client.getPhoneNumber());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPassport());
            ps.setString(6, client.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Client.class.getSimpleName() + " Error: updateOne");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(Client client) {

        String sql = "DELETE FROM CLIENT WHERE ID=?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, client.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Client.class.getSimpleName() + " Error: deleteOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
