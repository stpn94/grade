package grade_service;

import java.util.List;

import grade_dao.SubjectDao;
import grade_dao_Impl.SubjectDaoImpl;
import grade_dto.SubjectDto;

public class SubjectService {
	private SubjectDao subjDao = SubjectDaoImpl.getInstance();
	
	public List<SubjectDto> showSubjects() {
		return subjDao.selectSubjectByAll();
	}
	public void addSubject(SubjectDto subj) {
		subjDao.insertSubject(subj);
	}

	public void removeSubject(String subj) {
		subjDao.deleteSubjectBySubjName(subj);
	}
}
