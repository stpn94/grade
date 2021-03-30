package grade_dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import grade_dao_Impl.ScoreDaoImpl;
import grade_dto.ScoreDto;
import grade_dto.StudentDto;
import grade_dto.SubjectDto;

public class ScoreDaoTest {
	private static ScoreDao dao = ScoreDaoImpl.getInstance();

	@Test
	public void testUpdateScore() {
		System.out.printf("%s()%n","testUpdateScore"); //제목찍어주기
		
		List<ScoreDto> list = new ArrayList<ScoreDto>();
		StudentDto student =new StudentDto(2031, list);
		
		list.add(new ScoreDto(new SubjectDto(4),100));
		list.add(new ScoreDto(new SubjectDto(5),90));
		list.add(new ScoreDto(new SubjectDto(6),80));
		list.add(new ScoreDto(new SubjectDto(7),70));
		list.add(new ScoreDto(new SubjectDto(8),60));
		
		int res = dao.updateScore(student);
		Assert.assertEquals(1, res);
		System.out.println(res);
	}

}
