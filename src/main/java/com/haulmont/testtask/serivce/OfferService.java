package com.haulmont.testtask.serivce;

import com.haulmont.testtask.Config;
import com.haulmont.testtask.dao.OfferDAO;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.entity.Offer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferService implements OfferDAO {

    private final Connection db;

    public OfferService(Config config) {
        this.db = config.getDb();
    }

    @Override
    public void createOne(Offer offer) {

        String sql = "INSERT INTO OFFER (ID, CLIENT_ID, CREDIT_ID, CREDIT_AMOUNT) values (?, ?, ?, ?)";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, offer.getId());
            ps.setString(2, offer.getClientID());
            ps.setString(3, offer.getCreditID());
            ps.setString(4, offer.getCreditAmount());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Offer.class.getSimpleName() + " Error: createOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offerList = new ArrayList<>();
        String sql = "SELECT * FROM OFFER";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Offer offer = new Offer();
                offer.setId(rs.getString("ID"));
                offer.setClientID(rs.getString("CLIENT_ID"));
                offer.setCreditID(rs.getString("CREDIT_ID"));
                offer.setCreditAmount(rs.getString("CREDIT_AMOUNT"));
                offerList.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerList;
    }

    @Override
    public Offer findById(String id) {
        ResultSet rs = null;
        Offer offer = new Offer();
        String sql = "SELECT * FROM OFFER WHERE id = ?";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                offer.setId(rs.getString("ID"));
                offer.setClientID(rs.getString("CLIENT_ID"));
                offer.setCreditID(rs.getString("CREDIT_ID"));
                offer.setCreditAmount(rs.getString("CREDIT_AMOUNT"));
            } else {
                throw new IllegalArgumentException(Offer.class.getSimpleName() + " Error: findById");
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
        return offer;
    }

    @Override
    public void updateOne(Offer offer) {
        String sql = "UPDATE OFFER SET CLIENT_ID = ?, CREDIT_ID = ?, CREDIT_AMOUNT = ? WHERE ID = ?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, offer.getClientID());
            ps.setObject(2, offer.getCreditID());
            ps.setString(3, offer.getCreditAmount());
            ps.setString(4, offer.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Offer.class.getSimpleName() + " Error: updateOne");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(Offer offer) {
        String sql = "DELETE FROM OFFER WHERE ID=?";
        try (PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, offer.getId());
            if (ps.executeUpdate() != 1) {
                throw new IllegalArgumentException(Offer.class.getSimpleName() + " Error: deleteOne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getClient(String id) {
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
        return client.getFIO();
    }

    @Override
    public String getCredit(String id) {
        ResultSet rs = null;
        Credit credit = new Credit();
        String sql = "SELECT * FROM CLIENT WHERE id = ?";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                credit.setId(rs.getString("ID"));
                credit.setName(rs.getString("NAME"));
                credit.setBankID(rs.getString("BANK_ID"));
                credit.setLimit(rs.getString("LIMIT"));
                credit.setInterestRate(rs.getString("INTEREST_RATE"));

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
        return credit.getName();
    }
}
