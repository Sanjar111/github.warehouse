package com.warehouse.dao.bin;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.bin.Bin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BinRepositoryImpl implements BinRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM BIN";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM BIN WHERE BIN_ID = ?";
    private static final String SQL_CREATED = "INSERT INTO BIN (BIN_ID,BIN_NUMBER,TOTAL_SIZE,BUSY_SIZE,FREE_SIZE,LEVEL_ID,STATUS) VALUES(NEXTVAL('BIN_SEQ'),?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE BIN SET BIN_NUMBER = ?, TOTAL_SIZE = ?, BUSY_SIZE = ?, FREE_SIZE = ?, LEVEL_ID = ?, STATUS = ?" + " WHERE BIN_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Bin> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, binRowMapper);
    }

    @Override
    public Bin findById(Integer bin_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, binRowMapper, new Object[]{bin_id});
        } catch (Exception e) {
            throw new EtResourceNotFoundException("bin not found");
        }
    }

    @Override
    public Integer create(Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, bin_number);
                ps.setInt(2, total_size);
                ps.setInt(3, busy_size);
                ps.setInt(4, free_size);
                ps.setInt(5, level_id);
                ps.setInt(6, status);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("BIN_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer bin_id, Integer bin_number, Integer total_size, Integer busy_size, Integer free_size, Integer level_id, Integer status) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{bin_number, total_size, busy_size, free_size, level_id, status, bin_id});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request update");
        }
    }
    private RowMapper<Bin> binRowMapper = ((rs, rowNum) ->{
        return new Bin(rs.getInt("BIN_ID"),
                rs.getInt("BIN_NUMBER"),
                rs.getInt("TOTAL_SIZE"),
                rs.getInt("BUSY_SIZE"),
                rs.getInt("FREE_SIZE"),
                rs.getInt("LEVEL_ID"),
                rs.getInt("STATUS"),
                rs.getString("CREATED_TIME"));

    });
}





