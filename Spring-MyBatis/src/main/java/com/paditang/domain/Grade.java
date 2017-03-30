package com.paditang.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.paditang.domain.validatingGroup.ValidatingGroup1;
import com.paditang.domain.validatingGroup.ValidatingGroup2;

public class Grade {
	
	@NotNull(message="{grade.subject.isNull}",groups=ValidatingGroup1.class)
	private String subject;
	
	@Min(value=0)
	@Max(value=100,message="error grade,grade must be less than 100",groups=ValidatingGroup2.class)
	private Integer count;
	
	@NotEmpty
	private String student;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Grade [subject=" + subject + ", count=" + count + ", student="
				+ student + "]";
	}
	
	
}
