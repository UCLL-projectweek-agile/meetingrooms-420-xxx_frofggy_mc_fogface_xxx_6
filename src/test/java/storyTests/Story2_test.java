package storyTests;

import org.junit.Before;
import org.junit.Test;

import meetingrooms.Service;

public class Story2_test {
	@Before
	public void setUp() {
		Service service = new Service();
		String string = "Room:.*$/n"
				+ "SUBJECT:.*$/n"
				+ "FROM:.*CET.*$/n"
				+ "TILL:.*CETs.*$/n";
	}
	
	@Test
	public void sysoutShowsAppointments() {
		
	}
}
