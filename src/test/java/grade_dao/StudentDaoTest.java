package grade_dao;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import grade_dao_Impl.StudentDaoImpl;
import grade_dto.BanDto;
import grade_dto.StudentDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest {
	private StudentDao dao = StudentDaoImpl.getInstance();

	@Test
	public void test04SelectStudentByAll() {
		System.out.printf("%s()%n", "testSelectStudentByAll");
		List<StudentDto> stdList = dao.selectStudentByAll();
		Assert.assertNotNull(stdList);

		for (StudentDto t : stdList) {
			System.out.println(t);
		}
	}
	
//	@Test
	public void test05selectStudentByJumsuZero() {
		System.out.printf("%s()%n", "test04selectStudentByJumsuZero");
		List<StudentDto> stdList = dao.selectStudentByJumsuZero();
		Assert.assertNotNull(stdList);

		for (StudentDto t : stdList) {
			System.out.println(t);
		}
	}
	

	@Test
	public void test06SelectStudentByNo() {
		System.out.printf("%s()%n", "testSelectStudentByNo");
		StudentDto std = new StudentDto(2030);

		StudentDto stdList = dao.selectStudentByNo(std);
		Assert.assertNotNull(stdList);
		System.out.println(stdList);
	}
	
	@Test
	public void test07SelectStudentByName() {
		System.out.printf("%s()%n", "testSelectStudentByName");
		StudentDto std = new StudentDto("임성준");
		
		List<StudentDto> stdList = dao.selectStudentByName(std);
		Assert.assertNotNull(stdList);
		for (StudentDto t : stdList) {
			System.out.println(t);
		}
	}
	
	@Test
	public void test06SelectStudentByBan() {
		System.out.printf("%s()%n", "test06SelectStudentByBan");
		BanDto banCode = new BanDto("A01");
		List<StudentDto> stdList = dao.selectStudentByBan(banCode);
		Assert.assertNotNull(stdList);
		for (StudentDto t : stdList) {
			System.out.println(t);
		}
	}
	@Test
	public void test07SelectStudents() {
		System.out.printf("%s()%n", "test07SelectStudents");
		StudentDto search = new StudentDto(new BanDto("A01"),"국어",20);
		System.out.println(search);
		List<StudentDto> stdList = dao.selectStudents(search);
		Assert.assertNotNull(stdList);
		for (StudentDto t : stdList) {
			System.out.println(t);
		}
	}
	
	@Test
	public void test01InsertStudent() {
		System.out.printf("%s()%n", "test01InsertStudent");
		StudentDto newStd = new StudentDto(2037,"아니야",new BanDto(2));
		int res = dao.insertStudent(newStd);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByName(newStd));
	}

	@Test
	public void test02UpdateStudent() {
		System.out.printf("%s()%n", "testUpdateStudent");
		StudentDto upStd = new StudentDto(2037,"아이야", new BanDto(1));
		int res = dao.updateStudent(upStd);
		Assert.assertEquals(1, res);

		System.out.println(dao.selectStudentByName(upStd));
	}

	@Test
	public void test03DeleteStudent() {
		System.out.printf("%s()%n", "testDeleteStudent");
		StudentDto delStd = new StudentDto(2037);
		int res = dao.deleteStudent(delStd);
		Assert.assertEquals(1, res);
	}

}
