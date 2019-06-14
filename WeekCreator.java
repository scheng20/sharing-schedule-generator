package schedule_generator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class WeekCreator 
{
	// Initialize some properties
	//LocalDate eventDate;
	
	
	//Constructor
	public WeekCreator()
	{
	}
	
	// Based on the given date, generate an ArrayList of exactly
	// 8 days (so 7 marketing days) prior to the event. Include
	// all necessary information about the day (ex. if its a hot
	// day) based on that day's date and day of week. 
	
	public ArrayList<Day> generateWeek(LocalDate eventDate)
	{
		ArrayList<Day> week = new ArrayList<Day>();
		LocalDate marketingStarts = eventDate.minusDays(7);
		
		for (int i = 0; i < 7; i++)
		{
			LocalDate currentDate = marketingStarts.plusDays(i);
			DayOfWeek dayofWeek = currentDate.getDayOfWeek();
			String whichDay = dayofWeek.name();
			
			if (whichDay.equalsIgnoreCase("Sunday") ||
				whichDay.equalsIgnoreCase("Monday") ||
				whichDay.equalsIgnoreCase("Wednesday") ||
				whichDay.equalsIgnoreCase("Thursday"))
			{
				Day newDay = new Day(currentDate, true, 3);
				week.add(newDay);
			}
			else
			{
				Day newDay = new Day(currentDate, false, 2);
				week.add(newDay);
			}
			
		}
		
		return week;
		
	}
}
