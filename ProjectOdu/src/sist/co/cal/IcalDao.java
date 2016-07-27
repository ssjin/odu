package sist.co.cal;

import java.util.List;

public interface IcalDao {
	
	boolean addCalendar(CalendarDTO cal);
	CalendarDTO caldetail(int seq);
	List<CalendarDTO> getCalendarList(String id, String yyyyMM);
	boolean deleteCalendar(int seq);
}
