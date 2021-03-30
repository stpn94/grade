package grade_dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import grade_dao_Impl.StudentDetailDaoImpl;
import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDetailDaoTest {
	private StudentDetailDao dao = StudentDetailDaoImpl.getInstance();

	@Test
	public void test02InsertStudentDetail() {
		System.out.println("test01InsertEmployeeDetail");

		StudentDetailDto empDetail = new StudentDetailDto(2001, true, new Date(), getImage("NoImage.jpg"));
		int res = dao.insertStudentDetail(empDetail);

		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
		// /image/imgName
		File file = new File(System.getProperty("user.dir") + File.separator + "image", imgName);
		try (InputStream is = new FileInputStream(file)) {
			pic = new byte[is.available()];// 파일로 부터 읽은 이미지의 바이트길이를 배열 생성한다.
			is.read(pic);
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return pic;
	}

	@Test
	public void test02SelectStudentDetailByNo() {
		System.out.printf("%s()%n", "test02SelectStudentDetailByNo");
		StudentDto newStd = new StudentDto(2001);
		StudentDetailDto searchStudentDetail = dao.selectStudentDetailByNo(newStd);
		Assert.assertNotNull(searchStudentDetail);
		System.out.println(searchStudentDetail);
	}

	@Test
	public void test03UpdateStudentDetail() {
		System.out.printf("%s()%n", "test03UpdateStudentDetail");

		Calendar cal = GregorianCalendar.getInstance();
		cal.getTime();

		StudentDetailDto stdDetail = new StudentDetailDto(2001, false, new Date(), getImage("배.jpg"));
		int res = dao.updateStudentDetail(stdDetail);

		Assert.assertEquals(1, res);

		System.out.println(dao.selectStudentDetailByNo(new StudentDto(2001)));
	}

	@Test
	public void test04DeleteStudentDetail() {
		System.out.printf("%s()%n", "test04DeleteStudentDetail");
		StudentDto student = new StudentDto(2001);
		int res = dao.deleteStudentDetail(student);

		Assert.assertEquals(1, res);
	}

}
