package grade_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grade_dao.SubjectDao;
import grade_database.JdbcUtil;
import grade_dto.SubjectDto;

public class SubjectDaoImpl implements SubjectDao {
	private static final SubjectDaoImpl instanse = new SubjectDaoImpl();
	
	public static SubjectDaoImpl getInstance() {
		return instanse;
	}
	
	private SubjectDaoImpl() {
	}
	
	@Override
	public List<SubjectDto> selectSubjectByAll() {
		String sql = "select subjNo, subjName from subject";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<SubjectDto> list = new ArrayList<>();
				do {
					list.add(getSubject(rs));
				} while (rs.next());
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private SubjectDto getSubject(ResultSet rs) throws SQLException {
		int subjNo = rs.getInt("subjNo");
		String subjName = rs.getString("subjName");
		return new SubjectDto(subjNo, subjName);
	}

	@Override
	public int insertSubject(SubjectDto subject) {
		String sql = "insert into subject values (?,?)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, subject.getSubjNo());
			pstmt.setString(2, subject.getSubjName());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteSubjectBySubjName(String subjName) {
		String sql = "delete from subject where subjName = ? ";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, subjName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
		}
		return 0;
	}

}