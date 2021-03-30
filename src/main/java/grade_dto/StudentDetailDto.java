package grade_dto;

import java.util.Arrays;
import java.util.Date;

public class StudentDetailDto {
	int stdNo;
	boolean gender;
	Date birthday;
	private byte[] pic;

	public StudentDetailDto() {
		// TODO Auto-generated constructor stub
	}


	public StudentDetailDto(int stdNo, boolean gender, Date birthday, byte[] pic) {
		this.stdNo = stdNo;
		this.gender = gender;
		this.birthday = birthday;
		this.pic = pic;
	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return String.format("StudentDetailDto [stdNo=%s, gender=%s, birthday=%s, pic=%s]", stdNo, gender, birthday,
				Arrays.toString(pic));
	}

}
