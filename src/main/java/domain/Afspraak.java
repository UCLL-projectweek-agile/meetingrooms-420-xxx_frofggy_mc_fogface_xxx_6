/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;

import java.util.Date;
import microsoft.exchange.webservices.data.core.PropertySet;

/**
 *
 * @author Daan
 */
public class Afspraak {
	
	private int afspraakid;
	private String lokaalid;
	private String subject;
	private Date startDate2_0;
	private Date endDate2_0;
	
    private Lokaal lokaal;
    private String desc;
    private Calendar startDate;
    private Calendar endDate;
    
    private int startMinute;
    private int startHour;
    private int endMinute;
    private int endHour;
    
    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;
    
<<<<<<< HEAD
    public Afspraak(int afspraakid, String lokaalid, Date startdate, Date enddate, String subject){
    	this.setAfspraakid(afspraakid);
    	this.setLokaalid(lokaalid);
    	this.setStartDate(toCalendar(startdate));
    	this.setEndDate(toCalendar(enddate));
    	this.setSubject(subject);
    }
=======
    
    
>>>>>>> 0eca2c0c4f422ef5c517991b55c86ed36f264310

    public Afspraak(Lokaal lokaal, microsoft.exchange.webservices.data.core.service.item.Appointment appt) throws Exception {
        this.setLokaal(lokaal);
        appt.load(PropertySet.FirstClassProperties);
        this.setStartDate(toCalendar(appt.getStart()));
        this.setEndDate(toCalendar(appt.getEnd()));
        this.setDesc(appt.getSubject());
        this.setStartHour();
        this.setStartMinute();
        this.setEndHour();
        this.setEndMinute();
        this.setStartYear();
        this.setStartDay();
        this.setStartMonth();
        this.setEndYear();
        this.setEndMonth();
        this.setEndDay();
    }
    

    public Afspraak(Lokaal lokaal, Calendar startDate, Calendar endDate) {
        this.lokaal = lokaal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Afspraak() {

    }

    public Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public Lokaal getLokaal() {
        return lokaal;
    }

    public void setLokaal(Lokaal lokaal) {
        this.lokaal = lokaal;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public boolean berekenBeschikbaarheid() {
        //lokaal
        return true;
    }

    @Override
    public String toString() {
        return lokaal.toString() + " Start: " + startHour+":"+ startMinute + " End: " + endHour+":"+ endMinute;
    }
    
    public void setStartHour(){
        startHour = startDate.get(Calendar.HOUR_OF_DAY);
    }

    public int getStartHour() {
        return startHour;
    }
    
    public void setStartMinute(){
        startMinute = startDate.get(Calendar.MINUTE);
    }
    
    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute() {
        this.endMinute = endDate.get(Calendar.MINUTE);
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour() {
        this.endHour = endDate.get(Calendar.HOUR_OF_DAY);
    }

    
    
    
    

	public int getStartYear() {
		return startYear;
	}


	public void setStartYear() {
		this.startYear = startDate.get(Calendar.YEAR);
	}


	public int getStartMonth() {
		return startMonth;
	}


	public void setStartMonth() {
		this.startMonth = startDate.get(Calendar.MONTH);
	}


	public int getStartDay() {
		return startDay;
	}


	public void setStartDay() {
		this.startDay = startDate.get(Calendar.DAY_OF_MONTH);
	}


	public int getEndYear() {
		return endYear;
	}


	public void setEndYear() {
		this.endYear = endDate.get(Calendar.YEAR);
	}


	public int getEndMonth() {
		return endMonth;
	}


	public void setEndMonth() {
		this.endMonth = endDate.get(Calendar.MONTH);
	}


	public int getEndDay() {
		return endDay;
	}


	public void setEndDay() {
		this.endDay = endDate.get(Calendar.DAY_OF_MONTH);
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public int getAfspraakid() {
		return afspraakid;
	}


	public void setAfspraakid(int afspraakid) {
		this.afspraakid = afspraakid;
	}



	public String getLokaalid() {
		return lokaalid;
	}



	public void setLokaalid(String lokaalid) {
		this.lokaalid = lokaalid;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getStartDate2_0() {
		return startDate2_0;
	}

	public void setStartDate2_0(Date startDate2_0) {
		this.startDate2_0 = startDate2_0;
	}

	public Date getEndDate2_0() {
		return endDate2_0;
	}

	public void setEndDate2_0(Date endDate2_0) {
		this.endDate2_0 = endDate2_0;
	}
	
	
    
}
