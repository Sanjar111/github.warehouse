package com.warehouse.dao.warehouse;

import com.fasterxml.jackson.databind.JsonNode;
import com.warehouse.exception.EtBadRequestException;
import com.warehouse.exception.EtResourceNotFoundException;
import com.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private static final String SQL_FIND_ALL = "SELECT * FROM WAREHOUSE";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM WAREHOUSE WHERE WAREHOUSE_ID = ?";
    private static final String SQL_CREATED = "INSERT INTO WAREHOUSE(WAREHOUSE_ID, WAREHOUSE_NAME, SUBCATEGORY_ID_LIST, ADDRESS, LOCATION, PHONE, STATUS) VALUES(NEXTVAL('WAREHOUSE_SEQ'), ?, ?::JSON, ?, ?::JSON, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE WAREHOUSE SET WAREHOUSE_NAME = ?, SUBCATEGORY_ID_LIST = ?::JSON, ADDRESS = ?, LOCATION = ?::JSON, PHONE = ?, STATUS = ? WHERE WAREHOUSE_ID = ?";
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Warehouse> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new WarehouseRowMapper());
    }

    @Override
    public Warehouse findById(Integer warehouse_id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new WarehouseRowMapper(), new Object[]{warehouse_id});
        } catch (Exception e) {
            throw new EtResourceNotFoundException("warehouse not found");
        }
    }

    @Override
    public Integer create(String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException {
        try {

            ArrayList<JsonNode> listOfMap = new ArrayList<JsonNode>();
            for (int i =0; i < location.length; i++){
                listOfMap.add(location[i]);
            }

            ArrayList<Integer> listOfString = new ArrayList<Integer>();
            for (int i =0; i < subcategory_id_list.length; i++){
                listOfString.add(subcategory_id_list[i]);
            }

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->{
                PreparedStatement ps = connection.prepareStatement(SQL_CREATED, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, warehouse_name);
                ps.setObject(2, listOfString.toString());
                ps.setString(3, address);
                ps.setObject(4,  listOfMap.toString());
                ps.setString(5, phone);
                ps.setInt(6, status);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("WAREHOUSE_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }

    }

    @Override
    public void update(Integer warehouse_id, String warehouse_name, Integer[] subcategory_id_list, String address, JsonNode[] location, String phone, Integer status) throws EtBadRequestException {
        try {

            ArrayList<JsonNode> locationOfMap = new ArrayList<JsonNode>();
            for (int i =0; i < location.length; i++){
                locationOfMap.add(location[i]);
            }

            ArrayList<Integer> subcategoryOfString = new ArrayList<Integer>();
            for (int i =0; i < subcategory_id_list.length; i++){
                subcategoryOfString.add(subcategory_id_list[i]);
            }

            jdbcTemplate.update(SQL_UPDATE, new Object[]{warehouse_name, subcategoryOfString.toString(), address, locationOfMap.toString(), phone, status, warehouse_id});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request update");
        }
    }

}


