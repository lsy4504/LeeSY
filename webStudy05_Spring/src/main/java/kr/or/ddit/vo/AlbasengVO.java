package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author pc01
 *	자바빈: java beans 규약에 따라 재사용 가능한 객체들(Value Object,Data Transfer Object)
 *	1. 값을 저장할 프로퍼티
 *	2. 캡슐화
 *	3. 캡슐화된 프로퍼티에 접근할 인터페이스 메소드 제공(getter,setter)
 *		->get[set] 변수명의 첫문자만 대문자로 한 카멜 표기법~<
 *	4. 상태 비교 방법 제공(equals)
 *	5. 상태 확인 방법 제공(toString)
 *	6. 직렬화 가능(전송이나 저장이 가능) 
 */
public class AlbasengVO implements Serializable {
	
	
	
	private String code; //pk
	private String name;
	private Integer age;
	private String tel;
	private String addr;
	private String grade;
	private String gender;
	private String[] license;
	private String career;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String hp) {
		this.tel = hp;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getLicense() {
		return license;
	}

	public void setLicense(String[] license) {
		this.license = license;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbasengVO other = (AlbasengVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "AlbasengVO [name=" + name + ", age=" + age + ", hp=" + tel + ", addr=" + addr + ", grade=" + grade
				+ ", gender=" + gender + ", license=" + Arrays.toString(license) + ", career=" + career + "]";
	}

}
