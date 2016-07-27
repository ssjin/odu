package sist.co.cal;

import java.util.List;


public interface IcalDAO {
	boolean addCalendar(calDTO cal);
	boolean modcal(calDTO cal, int seq);
	List<calDTO> getCalendarList(String id, String yyyyMM);
	calDTO getCal(int seq);
	List<calDTO> getDayList(String id, String yyyymmdd);
	boolean deleteCal(int seq);
}
