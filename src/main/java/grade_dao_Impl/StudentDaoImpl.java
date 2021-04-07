package grade_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grade_dao.StudentDao;
import grade_database.JdbcUtil;
import grade_dto.BanDto;
import grade_dto.ScoreDto;
import grade_dto.StudentDto;
import grade_dto.SubjectDto;
import grade_ui.exception.SqlConstraintException;

public class StudentDaoImpl implements StudentDao {
	private static StudentDaoImpl instance = new StudentDaoImpl();

	public static StudentDaoImpl getInstance() {
		if (instance == null) {
			instance = new StudentDaoImpl();
		}
		return instance;
	}

	private StudentDaoImpl() {
	}

	@Override
	public List<StudentDto> selectStudentByAll() {
		String sql = "select stdNo, stdName, banCode,"
				+ " subj4, subj국어, 국어, subj5,subj영어,영어, subj6,subj수학, 수학, subj7,subj사회, 사회, subj8,subj과학, 과학," + " 평균"
				+ " from vw_student_table;";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<StudentDto> list = new ArrayList<>();
				do {
					list.add(getStudent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StudentDto getStudent(ResultSet rs) throws SQLException {
		int stdNo = rs.getInt("stdNo");
		String stdName = rs.getString("stdName");
		BanDto banCode = null;
		banCode = new BanDto(rs.getString("banCode"));
		List<ScoreDto> scores = new ArrayList<ScoreDto>();
		scores.add(new ScoreDto(new SubjectDto(rs.getInt("subj4"), rs.getString("subj국어")), rs.getInt("국어")));
		scores.add(new ScoreDto(new SubjectDto(rs.getInt("subj5"), rs.getString("subj영어")), rs.getInt("영어")));
		scores.add(new ScoreDto(new SubjectDto(rs.getInt("subj6"), rs.getString("subj수학")), rs.getInt("수학")));
		scores.add(new ScoreDto(new SubjectDto(rs.getInt("subj7"), rs.getString("subj사회")), rs.getInt("사회")));
		scores.add(new ScoreDto(new SubjectDto(rs.getInt("subj8"), rs.getString("subj과학")), rs.getInt("과학")));
		double avg = rs.getDouble("평균");

		return new StudentDto(stdNo, stdName, banCode, scores, avg);
	}

	@Override
	public List<StudentDto> selectStudentByName(StudentDto student) {
		String sql = "select stdNo, stdName, banCode,"
				+ " subj4,subj국어, 국어, subj5,subj영어, 영어, subj6,subj수학, 수학, subj7,subj사회, 사회, subj8,subj과학, 과학," + " 평균"
				+ " from vw_student_table" + " where stdName = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getStdName());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<StudentDto> list = new ArrayList<>();
					do {
						list.add(getStudent(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertStudent(StudentDto student) {
		String sql = "INSERT INTO student VALUES (?, ? ,? )";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			pstmt.setString(2, student.getStdName());
			pstmt.setInt(3, student.getBan().getBanNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int updateStudent(StudentDto student) {
		String sql = "update student set stdName = ? , ban= ? where stdNo=?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getStdName());
			pstmt.setInt(2, student.getBan().getBanNo());
			pstmt.setInt(3, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteStudent(StudentDto student) {
		String sql = "delete from student where stdno=?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<StudentDto> selectStudentByBan(BanDto student) {
		String sql = "select stdNo, stdName, banCode,"
				+ " subj4,subj국어, 국어, subj5,subj영어, 영어, subj6,subj수학, 수학, subj7,subj사회, 사회, subj8,subj과학, 과학," + " 평균"
				+ " from vw_student_table" + " where banCode = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getBanCode());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<StudentDto> list = new ArrayList<>();
					do {
						list.add(getStudent(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentDto> selectStudentByJumsuZero() {
		String sql = "select stdNo, stdName, banCode,"
				+ " subj4, subj국어, 국어, subj5, subj영어, 영어, subj6, subj수학, 수학, subj7, subj사회, 사회, subj8, subj과학, 과학, 평균"
				+ " from vw_student_table where 국어+영어+수학+사회+과학=0";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<StudentDto> list = new ArrayList<>();
				do {
					list.add(getStudent(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentDto selectStudentByNo(StudentDto student) {
		String sql = "select stdNo, stdName, banCode,"
				+ " subj4,subj국어, 국어, subj5,subj영어, 영어, subj6,subj수학, 수학, subj7,subj사회, 사회, subj8,subj과학, 과학," + " 평균"
				+ " from vw_student_table" + " where stdNo = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {

					return getStudent(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	+ " where banCode = (? ,'A01' and 'A02')" 
//	+ " order by ifnull(? , 평균) desc limit ?";
	@Override
	public List<StudentDto> selectStudents(StudentDto student) {
		String sql = "select stdNo, stdName, banCode," 
					+ " 국어, 영어, 수학, 사회, 과학, 평균" 
					+ " from vw_student_table"
					+ " order by ?  desc limit ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getOrder());
			pstmt.setInt(2, student.getLimit());
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<StudentDto> list = new ArrayList<>();
					do {
						list.add(getSearch(rs));
					} while (rs.next());
					return list;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public List<StudentDto> selectStudentByName(StudentDto student) {
//		String sql = "select stdNo, stdName, banCode,"
//				+ " subj4,subj국어, 국어, subj5,subj영어, 영어, subj6,subj수학, 수학, subj7,subj사회, 사회, subj8,subj과학, 과학," + " 평균"
//				+ " from vw_student_table" + " where stdName = ?";
//		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
//			pstmt.setString(1, student.getStdName());
//			try (ResultSet rs = pstmt.executeQuery()) {
//				if (rs.next()) {
//					List<StudentDto> list = new ArrayList<>();
//					do {
//						list.add(getStudent(rs));
//					} while (rs.next());
//					return list;
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	private StudentDto getSearch(ResultSet rs) throws SQLException {
		int stdNo = rs.getInt("stdNo");
		String stdName = rs.getString("stdName");
		BanDto banCode = new BanDto(rs.getString("banCode"));
		List<ScoreDto> scores = new ArrayList<ScoreDto>();
		scores.add(new ScoreDto(rs.getInt("국어")));
		scores.add(new ScoreDto(rs.getInt("영어")));
		scores.add(new ScoreDto(rs.getInt("수학")));
		scores.add(new ScoreDto(rs.getInt("사회")));
		scores.add(new ScoreDto(rs.getInt("과학")));
		double avg = rs.getDouble("평균");
		return new StudentDto(stdNo, stdName, banCode, scores, avg);
	}

}
