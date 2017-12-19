package storyTests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import meetingrooms.Service;
import microsoft.exchange.webservices.data.core.ExchangeService;

public class Story2_test {
	Service service = new Service();

	ExchangeService services = new ExchangeService();
	String string = "---------------------------------\nRoom: .*$\nSUBJECT: .*$\nFROM: .*CET.*$\nTILL: .*CETs.*$\n---------------------------------";
	
	@Before
	public void setUp() {
		try {
			service.logIn("HSR-Yangtze@ucll.be", services);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void sysoutShowsAppointments() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000);
		try {
			assertEquals(string, service.stringFindAppointments("HSR-.*@ucll.be", services, startDate, endDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@
}
