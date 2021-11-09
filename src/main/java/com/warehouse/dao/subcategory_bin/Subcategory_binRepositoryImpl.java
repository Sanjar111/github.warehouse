package com.warehouse.dao.subcategory_bin;

import com.warehouse.dao.subcategory_bin.Subcategory_binRepository;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Subcategory_bin;
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
public class Subcategory_binRepositoryImpl implements Subcategory_binRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM SUBCATEGORY_BIN";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM SUBCATEGORY_BIN WHERE SUBCATEGORY_ID = ?" ;
    private static final String SQL_CREATED = "INSERT INTO SUBCATEGORY_BIN (SUBCATEGORY_ID, BIN_ID) VALUES(NEXTVAL('SUBCATEGORY_BIN_SEQ'),?)" ;
    private static final String SQL_UPDATE = "UPDATE SUBCATEGORY_BIN SET BIN_ID = ?" + "WHERE SUBCATEGORY_ID = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Subcategory_bin> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, subcategory_binRowMapper);
    }

    @Override
    public Subcategory_bin findById(Integer subcategory_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, subcategory_binRowMapper, new Object[]{subcategory_id});
        }catch (Exception e){
            throw new EtResourceNotFoundException("subcategory_bin not found");
        }
    }

    @Override
    public Integer create(Integer bin_id) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps =connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,bin_id);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("SUBCATEGORY_ID");
        }catch (Exception e){
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer subcategory_id,Integer bin_id) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{bin_id, subcategory_id});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request update");
        }
    }

    @Override
    public void update(Integer bin_id) {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{bin_id});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request update");
        }
    }

    private RowMapper<Subcategory_bin> subcategory_binRowMapper = ((rs, rowNum) -> {
        return new Subcategory_bin(rs.getInt("SUBCATEGORY_ID"),
                rs.getInt("BIN_ID"));
    });
    }
