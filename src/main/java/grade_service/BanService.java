package grade_service;

import java.util.List;

import grade_dao.BanDao;
import grade_dao_Impl.BanDaoImpl;
import grade_dto.BanDto;

public class BanService {
	private BanDao banDao = BanDaoImpl.getInstance();

	public List<BanDto> showBans() {
		return banDao.selectBanByAll();
	}

	public void addBan(BanDto ban) {
		banDao.insertBan(ban); //반 추가
	}

	public void removeBan(String Ban) {
		banDao.deleteBanByBanCode(Ban); //반별번호로 반 지우기
	}
}
