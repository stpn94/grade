package grade_dao_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grade_dao.BanDao;
import grade_database.JdbcUtil;
import grade_dto.BanDto;

public class BanDaoImpl implements BanDao {
	// 싱글톤
	private static final BanDaoImpl instance = new BanDaoImpl();

	public static BanDaoImpl getInstance() {
		return instance;
	}

	private BanDaoImpl() {
	}

	@Override
	public List<BanDto> selectBanByAll() {
		String sql = "select banNo, banCode from ban";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<BanDto> banList = new ArrayList<>();
				do {
					banList.add(getBan(rs));
				} while (rs.next());
				return banList;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	private BanDto getBan(ResultSet rs) throws SQLException {
		int BanNo = rs.getInt("BanNo");
		String BanCode = rs.getString("BanCode");
		return new BanDto(BanNo, BanCode);
	}

// ---------------------------------------------------------------------------
	@Override
	public int insertBan(BanDto banDto) {
		String sql = "insert into ban values (?,?)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, banDto.getBanNo());
			pstmt.setString(2, banDto.getBanCode());
			return pstmt.executeUpdate();
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public int deleteBanByBanCode(String banCode) {
		String sql = "DELETE from ban where banCode = ? ";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, banCode);
			return pstmt.executeUpdate();
		} catch (Exception e) {
		}
		return 0;
	}
}
