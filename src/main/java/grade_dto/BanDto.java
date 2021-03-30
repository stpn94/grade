package grade_dto;

public class BanDto {
	

	private int banNo;
	private String banCode;
	
	public BanDto() {
	}
	
	public BanDto(int banNo) {
		this.banNo = banNo;
	}

	public BanDto(String banCode) {
		this.banCode = banCode;
	}
	
	
	public BanDto(int banNo, String banCode) {
		this.banNo = banNo;
		this.banCode = banCode;
	}
	
	public int getBanNo() {
		return banNo;
	}
	public void setBanNo(int banNo) {
		this.banNo = banNo;
	}
	
	public String getBanCode() {
		return banCode;
	}
	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	@Override
	public String toString() {
		return String.format("BanDto [banNo=%s, banCode=%s]", banNo, banCode);
	}
	
	
}
