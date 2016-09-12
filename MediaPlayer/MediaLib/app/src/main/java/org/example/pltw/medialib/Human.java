package org.example.pltw.medialib;

import java.util.Calendar;

public class Human {

	public String name;
	public int age, mOB, dOB, yOB;

	public Human(String name, int mOB, int dOB, int yOB) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		this.name = name;
		this.age = currentYear - yOB;
		this.mOB = mOB;
		this.dOB = dOB;
		this.yOB = yOB;
	}


}



