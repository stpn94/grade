package grade_dao;

import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;

public interface StudentDetailDao {
	StudentDetailDto selectStudentDetailByNo(StudentDto student);

	int insertStudentDetail(StudentDetailDto empDetail);

	int updateStudentDetail(StudentDetailDto empDetail);

	int deleteStudentDetail(StudentDto student);
}
