package grade_service;

import java.util.List;

import grade_dao.BanDao;
import grade_dao.StudentDao;
import grade_dao_Impl.BanDaoImpl;
import grade_dao_Impl.StudentDaoImpl;
import grade_dto.BanDto;
import grade_dto.StudentDto;

public class StudentService {
	private StudentDao stdDao = StudentDaoImpl.getInstance();
	private BanDao banDao = BanDaoImpl.getInstance();
	
	public List<StudentDto> showStudents() {
		return stdDao.selectStudentByAll();
	}
	public StudentDto showStudentsByNo(StudentDto student){
		return stdDao.selectStudentByNo(student);
	}
	
	public List<StudentDto> showStudentsByJumsuZero(){
		return stdDao.selectStudentByJumsuZero();
	}
	
	public List<StudentDto> showStudentsByBan(BanDto ban){
		return stdDao.selectStudentByBan(ban);
	}
	
	public List<BanDto> showBanList(){
		return banDao.selectBanByAll();
	}
	
	public void addStudent(StudentDto student) {
		stdDao.insertStudent(student);
	}

	public void modifyStudent(StudentDto student) {
		stdDao.updateStudent(student);
	}

	public void removeStudent(StudentDto student) {
		stdDao.deleteStudent(student);
	}
}
