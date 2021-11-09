package com.warehouse.dao.block;

import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlockRepositoryImpl implements BlockRepository{

    private static final String SQL_FIND_ALL = "SELECT * FROM BLOCK";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM BLOCK WHERE BLOCK_ID = ?";
    private static final String SQL_CREATED = "INSERT INTO BLOCK(BLOCK_ID, BLOCK_NAME, WAREHOUSE_ID, SUBCATEGORY_ID_LIST, STATUS) VALUES(NEXTVAL('BLOCK_SEQ'), ?, ?, ?::JSON, ?)";
    private static final String SQL_UPDATE = "UPDATE BLOCK SET BLOCK_NAME = ?, WAREHOUSE_ID = ?, SUBCATEGORY_ID_LIST = ?::JSON, STATUS = ? WHERE BLOCK_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Block> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL,  new BlockRowMapper());
    }

    @Override
    public Block findById(Integer block_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new BlockRowMapper(), new Object[]{block_id});
        } catch (Exception e){
            throw new EtResourceNotFoundException("block not found");
        }
    }

    @Override
    public Integer create(String block_name, Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i =0; i < subcategory_id_list.length; i++){
                listOfString.add(subcategory_id_list[i]);
            }


            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, block_name);
                ps.setInt(2,warehouse_id);
                ps.setObject(3, listOfString.toString());
                ps.setInt(4,status);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("BLOCK_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer block_id, String block_name, Integer warehouse_id, Integer[] subcategory_id_list, Integer status) throws EtBadRequestException {
        try {
            ArrayList<Integer> listOfString = new ArrayList<>();
            for (int i =0; i < subcategory_id_list.length; i++){
                listOfString.add(subcategory_id_list[i]);
            }

            jdbcTemplate.update(SQL_UPDATE, new Object[]{block_name, warehouse_id, listOfString.toString(), status, block_id});
        }catch (Exception e){
            throw new EtBadRequestException("Invalid request update");
        }
    }

}
