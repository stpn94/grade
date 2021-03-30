package grade_service;

import grade_dao.StudentDetailDao;
import grade_dao_Impl.StudentDetailDaoImpl;
import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;

public class StudentDetailService {
	private StudentDetailDao stdDetailDao = StudentDetailDaoImpl.getInstance();
	
	public StudentDetailDto selectStudentDetailByStdNo(StudentDto student) {
		return stdDetailDao.selectStudentDetailByNo(student);
	}
	
	public void addStudentDetail(StudentDetailDto stdDetail) {
		stdDetailDao.insertStudentDetail(stdDetail);
	}
	
	public void modifyStudentDetail(StudentDetailDto stdDetail) {
		stdDetailDao.updateStudentDetail(stdDetail);
	}
	
	public void removeStudentDetail(StudentDto student) {
		stdDetailDao.deleteStudentDetail(student);
	}

}