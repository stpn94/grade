package grade_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import grade_dao.StudentDetailDao;
import grade_database.JdbcUtil;
import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;
import grade_ui.exception.SqlConstraintException;

public class StudentDetailDaoImpl implements StudentDetailDao {
	private static StudentDetailDaoImpl instance = new StudentDetailDaoImpl();

	public static StudentDetailDaoImpl getInstance() {
		if (instance == null) {
			instance = new StudentDetailDaoImpl();
		}
		return instance;
	}

	private StudentDetailDaoImpl() {
	}

	@Override
	public StudentDetailDto selectStudentDetailByNo(StudentDto student) {
		String sql = "select stdNo,pic,gender,birthday from std_detail where stdNo=?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getStudentDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StudentDetailDto getStudentDetail(ResultSet rs) throws SQLException {
		int stdNo = rs.getInt("stdNo");
		byte[] pic = rs.getBytes("pic");
		boolean gender = rs.getBoolean("gender");
		Date birthday = rs.getTimestamp("birthday");

		return new StudentDetailDto(stdNo, gender, birthday, pic);
	}

	@Override
	public int insertStudentDetail(StudentDetailDto stdDetail) {
		String sql = "insert into std_detail values (?,?,?,?);";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, stdDetail.getStdNo());
			pstmt.setBytes(2, stdDetail.getPic());
			pstmt.setBoolean(3, stdDetail.isGender());
			// util.date ->sql.date로 변환
			pstmt.setTimestamp(4, new Timestamp(stdDetail.getBirthday().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int updateStudentDetail(StudentDetailDto stdDetail) {
		String sql = "update std_detail set pic=?,gender =?,birthday =? where stdNo=?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBytes(1, stdDetail.getPic());
			pstmt.setBoolean(2, stdDetail.isGender());
			pstmt.setTimestamp(3, new Timestamp(stdDetail.getBirthday().getTime()));
			pstmt.setInt(4, stdDetail.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteStudentDetail(StudentDto student) {
		String sql = "delete from std_detail where stdNo =?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
