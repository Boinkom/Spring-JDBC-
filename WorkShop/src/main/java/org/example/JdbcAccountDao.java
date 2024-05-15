package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

@Repository
public class JdbcAccountDao implements AccountDao {

    private static final String GET_ACCOUNT_SQL = "SELECT ID, NAME, SURNAME, PHONE, EMAIL FROM \"table\" WHERE ID = ?";
    private static final String UPDATE_PHONE_SQL = "UPDATE \"table\" SET PHONE = ? WHERE ID = ?";
    private static final String UPDATE_EMAIL_SQL = "UPDATE \"table\" SET EMAIL = ? WHERE ID = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM \"table\" WHERE ID = ?";
    private static final String CREATE_ACCOUNT_SQL = "INSERT INTO \"table\" (ID, NAME, SURNAME, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL_ACCOUNTS_SQL = "SELECT ID, NAME, SURNAME, PHONE, EMAIL FROM \"table\"";

    private static final RowMapper<Contact> ACCOUNT_ROW_MAPPER = (rs, i) -> new Contact(
            rs.getString("ID"),
            rs.getString("NAME"),
            rs.getString("SURNAME"),
            rs.getString("PHONE"),
            rs.getString("EMAIL")
    );

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Contact addAccount(String id, String name, String surname, String phone, String email) {
        // Not implemented
        return null;
    }

    @Override
    public Contact addAccount(String name, String surname, String phone, String email) {
        String id = UUID.randomUUID().toString();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_ACCOUNT_SQL);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setString(4, phone);
            ps.setString(5, email);
            return ps;
        });

        return new Contact(id, name, surname, phone, email);
    }

    @Override
    public Contact getAccount(String accountId) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, ACCOUNT_ROW_MAPPER, accountId);
    }

    @Override
    public void setAmount(String accountId, String amount) {

    }

    @Override
    public void setPhone(String accountId, String phone) {
        jdbcTemplate.update(UPDATE_PHONE_SQL, phone, accountId);
    }

    @Override
    public void setEmail(String accountId, String email) {
        jdbcTemplate.update(UPDATE_EMAIL_SQL, email, accountId);
    }

    @Override
    public void deleteAccount(String accountId) {
        jdbcTemplate.update(DELETE_ACCOUNT_SQL, accountId);
    }

    @Override
    public List<Contact> getAllAccounts() {
        return jdbcTemplate.query(GET_ALL_ACCOUNTS_SQL, ACCOUNT_ROW_MAPPER);
    }
}
