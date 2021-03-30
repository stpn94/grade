package grade_dao;

import java.util.List;

import grade_dto.SubjectDto;

public interface SubjectDao {
	List<SubjectDto> selectSubjectByAll(); // 현재 과목 목록리스트
	
	int insertSubject(SubjectDto subject); // 과목 추가
	
	int deleteSubjectBySubjName(String subjName); // 과목이름으로 과목삭제
	
}
