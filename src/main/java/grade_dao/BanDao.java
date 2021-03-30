package grade_dao;

import java.util.List;

import grade_dto.BanDto;

public interface BanDao {
	
	List<BanDto> selectBanByAll(); //현재 반 목록리스트

	int insertBan(BanDto Ban); //반 추가
	
	int deleteBanByBanCode(String Ban); //반별번호로 반 지우기
}
