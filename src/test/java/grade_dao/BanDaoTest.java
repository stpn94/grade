package grade_dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import grade_dao_Impl.BanDaoImpl;
import grade_dto.BanDto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BanDaoTest {
	private static BanDao dao = BanDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println("=======================");
	}

	@Test
	public void test03SelectBanByAll() {
		System.out.println("testSelectBanByAll");
		List<BanDto> banList = dao.selectBanByAll();
		Assert.assertNotNull(banList);

//		banList.stream().forEach(System.out::println);
		for (BanDto t : banList) {
			System.out.println(t);
		}
	}

	@Test
	public void test01InsertBan() {
		System.out.println("testInsertBan");
		BanDto newBan = new BanDto(3, "A03");
		int res = dao.insertBan(newBan);
		Assert.assertEquals(1, res);
		System.out.println(newBan);
	}

	@Test
	public void test02DeleteBanByBanCode() {
		System.out.println("testDeleteBanByBanCode");
		int res = dao.deleteBanByBanCode("A03");
		Assert.assertEquals(1, res);
	}

}
