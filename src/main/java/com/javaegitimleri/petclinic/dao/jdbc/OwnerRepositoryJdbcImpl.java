package com.javaegitimleri.petclinic.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;

@Repository
public class OwnerRepositoryJdbcImpl implements OwnerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Owner> rowMapper=new RowMapper<Owner>(){

		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			Owner owner=new Owner();
			owner.setId(rs.getLong("id"));
			owner.setFirstName(rs.getString("first_name"));
			owner.setLastName(rs.getString("last_name"));
			return owner;
		}
		
	};
	
	@Override
	public List<Owner> findAll() {
		String sql="Select * From T_Owner";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Owner findById(long id) {
		String sql="Select * from T_owner where id=?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql,rowMapper,id));
	}
	

	@Override
	public List<Owner> findByLastName(String lastName) {
		String sql="Select * from T_owner where last_name like ?";
		return jdbcTemplate.query(sql, rowMapper,"%" + lastName + "%");
	}

	@Override
	public void create(Owner owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		String sql="delete from T_Owner where ID=?";
		jdbcTemplate.update(sql,id);
		

	}

}
