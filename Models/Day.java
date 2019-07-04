package schedule_generator;

import java.time.LocalDate;
import java.util.*;

public class Day 
{
	//Initialize Some Properties
	LocalDate date;
	String dayofWeek;
	Boolean hotDay = false;
	int shareSlots = 0;
	List<String> sharedGroups = new ArrayList<String>();
	List<String> sharedFaculties = new ArrayList<String>();
	List<String> sharedYears = new ArrayList<String>();
	List<Group> addedGroups = new ArrayList<Group>();
	
	//Constructor
	public Day (LocalDate Date, Boolean Hot, int Slots)
	{
		date = Date;
		hotDay = Hot;
		shareSlots = Slots;
		dayofWeek = Date.getDayOfWeek().name();
	}
	
	//Functions
	public void addGroup(Group group)
	{
		String groupName = group.name;
		String groupFaculty = group.faculty;
		String groupYear = group.year;
		
		addedGroups.add(group);
		sharedGroups.add(groupName);
		sharedFaculties.add(groupFaculty);
		sharedYears.add(groupYear);
		
		// Decrease the amount of shares left for this day
		shareSlots --;
		group.shareTimes --;
		
	}
	
	public boolean canAdd(Group group)
	{
		// Count how many appearances of this group's faculty are in this day
		int sameFaculty = 0;
		
		String thisFaculty = group.faculty;
		
		for (int i = 0; i < sharedFaculties.size(); i ++)
		{
			String currentF = sharedFaculties.get(i);
			
			if (currentF.equalsIgnoreCase(thisFaculty))
			{
				sameFaculty ++;
			}
		}
		
		// Count how many appearances of this group's year are in this day
		int sameYear = 0;
		
		String thisYear = group.year;
		
		for (int i = 0; i < sharedYears.size(); i ++)
		{
			String currentY = sharedYears.get(i);
			
			if (currentY.equalsIgnoreCase(thisYear))
			{
				sameYear ++;
			}
		}
		
		
		if (shareSlots > 0 && sameFaculty < 2 && sameYear < 1 && group.shareTimes > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	public void deleteAllGroups()
	{
		sharedGroups.clear();
		sharedFaculties.clear();
		sharedYears.clear();
		addedGroups.clear();
	}
}
