package grade_dao;

import java.util.List;

import grade_dto.BanDto;
import grade_dto.ScoreDto;
import grade_dto.StudentDto;

public interface StudentDao {

	List<StudentDto> selectStudentByAll();

	List<StudentDto> selectStudentByName(StudentDto student); // 학생이름으로 검색
	
	StudentDto selectStudentByNo(StudentDto student); // 학생번호로 검색
	
	List<StudentDto> selectStudentByBan(BanDto banCode); // 반으로 검색
	
	List<StudentDto> selectStudentByJumsuZero(); // 점수로 검색
	
	int insertStudent(StudentDto student); // 학생 추가

	int updateStudent(StudentDto student); // 학생 정보 수정

	int deleteStudent(StudentDto student); // 학생 삭제

}