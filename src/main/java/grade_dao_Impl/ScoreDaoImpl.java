package grade_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import grade_dao.ScoreDao;
import grade_database.JdbcUtil;
import grade_dto.StudentDto;

public class ScoreDaoImpl implements ScoreDao {
	private static final ScoreDaoImpl instanse = new ScoreDaoImpl();

	public static ScoreDaoImpl getInstance() {
		return instanse;
	}

	private ScoreDaoImpl() {
	}

	@Override
	public int updateScore(StudentDto student) {
		String sql = "update score set jumsu = ? where stdno = ? and subject = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			for (int i = 0; i < student.getJumsu().size(); i++) {
				pstmt.setInt(1, student.getJumsu().get(i).getJumsu());
				pstmt.setInt(2, student.getStdNo());
				pstmt.setInt(3, student.getJumsu().get(i).getSubject().getSubjNo());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
		}
		return 0;
	}


}
//@Override
//public int updateDepartment(Department department) {
//	String sql = "update department set deptname = ? , floor = ? where deptno = ?";
//	try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
//		pstmt.setString(1, department.getDeptName());
//		pstmt.setInt(2, department.getFloor());
//		pstmt.setInt(3, department.getDeptNo());
//		return pstmt.executeUpdate();
//	} catch (Exception e) {
//	}
//	return 0;
//}