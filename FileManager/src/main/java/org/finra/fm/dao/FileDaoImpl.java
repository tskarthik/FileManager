package org.finra.fm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.finra.fm.rest.FileMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository

public class FileDaoImpl implements FileDao {

	private JdbcTemplate jdbcTemplate;

	String selectFileInfoSql = "select * from FILE_INFO where id = ?";
	String insertFileInfoSql = "insert into FILE_INFO values(?,?,?,?,?,?)";

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public FileMetaData getFileMetaData(String fileId) {
		return jdbcTemplate.queryForObject(selectFileInfoSql, new Object[] { fileId }, new RowMapper<FileMetaData>() {

			@Override
			public FileMetaData mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileMetaData fileMetaData = new FileMetaData(rs.getString("id"), rs.getString("name"),
						rs.getString("content_type"), rs.getString("owner"), rs.getDate("created_date"));
				return fileMetaData;
			}
		});
	}

	@Override
	public void createFileMetaData(FileMetaData fileMetaData, String fileLocation) {
		jdbcTemplate.update(insertFileInfoSql, new Object[] { fileMetaData.getId(), fileMetaData.getName(),
				fileMetaData.getContentType(), fileMetaData.getOwner(), fileMetaData.getCreatedDate(), fileLocation});
	}

}
