package kr.ac.hansung.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Offer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class OfferDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) form convertcsv";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);

	}

	public Offer getOffer(int year, int semester) {
		String sqlStatement = "select *from convertcsv where year=?, semester=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { year, semester }, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Offer offer = new Offer();
				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setCourseid(rs.getString("email"));
				offer.setCoursename(rs.getString("text"));
				offer.setDivision(rs.getString("division"));
				offer.setGrade(rs.getInt("grade"));
				return offer;
			}

		});
	}

	public List<Offer> getOffers() {
		String sqlStatement = "select *from convertcsv";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Offer offer = new Offer();
				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setCourseid(rs.getString("email"));
				offer.setCoursename(rs.getString("text"));
				offer.setDivision(rs.getString("division"));
				offer.setGrade(rs.getInt("grade"));

				return offer;
			}

		});
	}

	public boolean insert(Offer offer) {
		
		//String name = offer.getName();
		int year = offer.getYear();
		int semester = offer.getSemester();
		String courseid = offer.getCourseid();
		String coursename = offer.getCoursename();
		String division = offer.getDivision();
		int grade = offer.getGrade();

		String sqlStatement = "insert into convertcsv (year, semester, courseid, coursename, division, grade) values (?,?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { year, semester, courseid, coursename, division, grade }) == 1);
	}

	public boolean update(Offer offer) {
		int year = offer.getYear();
		int semester = offer.getSemester();
		String courseid = offer.getCourseid();
		String coursename = offer.getCoursename();
		String division = offer.getDivision();
		int grade = offer.getGrade();

		String sqlStatement = "update convertcsv set year=?, semester=?, coursename=?, division=?, grade=? where courseid=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { year, semester, coursename, division, grade, courseid }) == 1);
	}

	public boolean delete(int id) {
		String sqlStatement = "delete from convertcsv where if=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}
}