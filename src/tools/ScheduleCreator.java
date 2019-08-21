package tools;

import models.Day;
import models.Group;
import models.GroupList;

import java.util.ArrayList;

public class ScheduleCreator 
{
    // ------------------------------- FIELDS --------------------------------
	private ArrayList<Day> schedule = new ArrayList<Day>();

    // ------------------------------ FUNCTIONS ------------------------------
	public boolean sufficientGap(Group group, ArrayList<Day> Week, int dayCount)
	{
		Boolean result = false;
		
		// If the dayCount is between 2 and 4, then check both ways
		if (dayCount >=2 && dayCount <= 4)
		{
			result = (CheckForwardTwo(group, Week, dayCount) && CheckBackTwo(group, Week, dayCount));
		}
		// If the dayCount is 1, then check the past 1 day and check forward 2 days
		else if (dayCount == 1)
		{
			result = (CheckBackOne(group, Week, dayCount) && CheckForwardTwo(group, Week, dayCount));
		}	
		// If the dayCount is 5, then check the past 2 days and forward 1 day
		else if (dayCount == 5)
		{
			result = (CheckBackTwo(group, Week, dayCount) && CheckForwardOne(group, Week, dayCount));
		}
		// If the dayCount is 0, then only check forward 2 days
		else if (dayCount == 0)
		{
			result = CheckForwardTwo(group, Week, dayCount);
		}
		// If the dayCount is 6, then only check back 2 days
		else 
		{
			result = CheckBackTwo(group, Week, dayCount);
		}

		return result;
	}
	
	// Check if given group has been shared within the next 2 days
	public Boolean CheckForwardTwo (Group group, ArrayList<Day> Week, int dayCount)
	{
		String currentGroupName = group.getName();
		Boolean result = false;
		
		for (int i = dayCount; i <= (dayCount + 2); i++)
		{
			Day currentDay = Week.get(i);
			
			ArrayList<String> sharedGroups = (ArrayList<String>) currentDay.getSharedGroups();
			
			if (sharedGroups.contains(currentGroupName)) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		
		return result;
	}
	
	// Check if given group has been shared within the past 2 days
	public Boolean CheckBackTwo (Group group, ArrayList<Day> Week, int dayCount)
	{
		String currentGroupname = group.getName();
		Boolean result = false;
		
		for (int i = (dayCount - 2); i <= dayCount; i++)
		{
			Day currentDay = Week.get(i);
			
			ArrayList<String> sharedGroups = (ArrayList<String>) currentDay.getSharedGroups();
			
			if (sharedGroups.contains(currentGroupname)) {
				result = false;
				break;
			} else {
				result = true;
			}
		}
		
		return result;
	}

    // Check whether or not the current group exists in the past day
	public Boolean CheckBackOne (Group group, ArrayList<Day> week, int dayCount)
	{
		return CheckOneHelper(group, week, dayCount, dayCount - 1);
	}

    // Check whether or not the current group exists in the next day
	public Boolean CheckForwardOne (Group group, ArrayList<Day> week, int dayCount)
	{
		return CheckOneHelper(group, week, dayCount, dayCount + 1);
	}

	// Checks whether or not the given group exists in the day indicated by the dayCursor
	public boolean CheckOneHelper(Group group, ArrayList<Day> Week, int dayCount, int dayCursor) {

        String currentGroupName = group.getName();
        Boolean result = false;

        Day dayCursorDay = Week.get(dayCursor);
        ArrayList<String> sharedGroups = (ArrayList<String>) dayCursorDay.getSharedGroups();

        result = !sharedGroups.contains(currentGroupName);

        return result;
    }
	
	// Given a already scheduled week (list of days), and a list of groups schedule the groups into
    // the given week.
	public ArrayList<Day> buildSchedule(ArrayList<Day> Week, GroupList Groups)
	{
		schedule = Week;
		
		// Get the first day
		Day firstDay = schedule.get(0);
		
		// If the first day is a "hot" day
		if (firstDay.isHotDay())
		{
			// Find 1 top tier groups and 2 mid tier groups to share
			firstDay.addGroup(Groups.getTopTier().get(0));
			
			for (int i = 0; i < Groups.getMidTier().size(); i++)
			{
				Group current = Groups.getMidTier().get(i);
				
				if (firstDay.canAdd(current))
				{
					firstDay.addGroup(current);
				}
			}
			
			// find the next "hot" day (within at least a 2 day gap), and share
			// 2 remaining top tier groups
			// 1 mid tier group
			
			for (int i = 2; i < schedule.size(); i ++)
			{
				Day currentDay = schedule.get(i);
				
				if (currentDay.isHotDay())
				{
					currentDay.addGroup(Groups.getTopTier().get(1));
					currentDay.addGroup(Groups.getTopTier().get(2));
					
					for (int j = 0; j < Groups.getMidTier().size(); j++)
					{
						Group current = Groups.getMidTier().get(j);
						
						if (currentDay.canAdd(current) && sufficientGap(current, schedule, i))
						{
							currentDay.addGroup(current);
						}
					}
					
					break;
				}
			}
			
		}
		else //if the first day is NOT a "hot" day
		{
			// Add 1 top tier group, and 1 mid tier group
			firstDay.addGroup(Groups.getTopTier().get(0));
			
			for (int i = 0; i < Groups.getMidTier().size(); i++)
			{
				Group current = Groups.getMidTier().get(i);
				
				if (firstDay.canAdd(current))
				{
					firstDay.addGroup(current);
				}
			}
			
			// Find the next "hot" day and share	
			// 2 remaining top tier groups, 1 mid tier group
			
			int cont = 0;
			
			for (int i = 0; i < schedule.size(); i++)
			{
				Day currentD = schedule.get(i);
				
				if (currentD.isHotDay())
				{
					currentD.addGroup(Groups.getTopTier().get(1));
					currentD.addGroup(Groups.getTopTier().get(2));
					
					for (int j = 0; j < Groups.getMidTier().size(); j++)
					{
						Group currentG = Groups.getMidTier().get(j);
						
						// KEY THING HERE!!! TAKE IN ACCOUNT OF BOUNDARY ERROR

						if (currentD.canAdd(currentG) && sufficientGap(currentG, schedule, i))
						{
								currentD.addGroup(currentG);
						}
					}
					
					cont = i;
					
					break;
				}
			}
			
			// Find the next "hot" day (within a two day gap) and share
			// First shared top tier group, other top tier group
			// 1 other mid tier group
			
			for (int i = cont + 2; i < schedule.size(); i++)
			{
				Day currentD = schedule.get(i);
				
				if (currentD.isHotDay())
				{
					currentD.addGroup(Groups.getTopTier().get(0));
					currentD.addGroup(Groups.getTopTier().get(2));
					
					for (int j = 0; j < Groups.getMidTier().size(); j++)
					{
						Group currentG = Groups.getMidTier().get(j);
						
						if (currentD.canAdd(currentG) && sufficientGap(currentG, schedule, i))
						{
							currentD.addGroup(currentG);
						}
					}
					
					break;
				}
			}
			
		}
		
		// DONE WITH ALL THE FIRST DAY AND HOT DAY STUFF FROM HERE ON NOW
		// Just fill the rest in now
		
		for (int i = 0; i < schedule.size(); i ++)
		{
			Day currentDay = schedule.get(i);
			
			for (int j = 0; j < Groups.getAll().size(); j++)
			{
				Group currentGroup = Groups.getAll().get(j);
				
				// ANOTHER KEY POINT TO WATCH OUT FOR HERE!!! Since the days start at 0, its impossible to look back
				
				if (currentDay.canAdd(currentGroup) && sufficientGap(currentGroup, schedule, i))
				{
						currentDay.addGroup(currentGroup);
				}
			}
		}
		return schedule;
	}

    // ------------------------- GETTERS AND SETTERS -------------------------

    public ArrayList<Day> getSchedule() {
	    return schedule;
	}
}
