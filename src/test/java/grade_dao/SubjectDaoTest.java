package grade_dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import grade_dao_Impl.SubjectDaoImpl;
import grade_dto.SubjectDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubjectDaoTest {
	private static SubjectDao dao = SubjectDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println("============================");
	}
	
	@Test
	public void test03SelectSubjectByAll() {
		System.out.printf("%s()%n", "testSelectSubjectByAll");
		List<SubjectDto> subjList = dao.selectSubjectByAll();
		Assert.assertNotNull(subjList);

		subjList.stream().forEach(System.out::println);
		
	}

	@Test
	public void test01InsertSubject() {
		System.out.printf("%s()%n", "testInsertSubject");
		SubjectDto newSubject = new SubjectDto(9, "역사");
		int res = dao.insertSubject(newSubject);
		Assert.assertEquals(1, res);
		System.out.println(dao.insertSubject(newSubject));
		
	}

	@Test
	public void test02DeleteSubjectBySubjName() {
		System.out.printf("%s()%n", "testDeleteSubject");
		int res = dao.deleteSubjectBySubjName("역사");
		Assert.assertEquals(1, res);
		dao.selectSubjectByAll().stream().forEach(System.out::println);
		
	}

}
