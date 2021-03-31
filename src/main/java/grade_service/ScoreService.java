package grade_service;

import java.util.List;

import grade_dao.ScoreDao;
import grade_dao.StudentDao;
import grade_dao_Impl.ScoreDaoImpl;
import grade_dao_Impl.StudentDaoImpl;
import grade_dto.StudentDto;

public class ScoreService {
	
	private StudentDao stdDao = StudentDaoImpl.getInstance();
	private ScoreDao scoDao = ScoreDaoImpl.getInstance();
	
	public List<StudentDto> showStudents() {
		return stdDao.selectStudentByAll();
	}
	
	public void modifyScore(StudentDto student) {
		scoDao.updateScore(student);
	}
	
}
