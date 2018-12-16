package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Offer {

	private int year;
	private int semester;
	private String courseid;
	private String coursename;
	private String division;
	private int grade;
}
