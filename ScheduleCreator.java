package schedule_generator;

import java.util.*;

public class ScheduleCreator 
{
	// Initialize some properties
	ArrayList<Day> schedule = new ArrayList<Day>();
	
	//Constructor
	public ScheduleCreator()
	{		
		
	}
	
	public boolean sufficientGap(Group group, ArrayList<Day> Week, int dayCount)
	{
		Boolean result = false;
		
		// If the dayCount is between 2 and 4, then check both ways
		if (dayCount >=2 && dayCount <= 4)
		{
			// Again... does this work?
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
		String currentGroupname = group.name;
		Boolean result = false;
		
		for (int i = dayCount; i <= (dayCount + 2); i++)
		{
			Day currentDay = Week.get(i);
			
			ArrayList<String> sharedGroups = (ArrayList<String>) currentDay.sharedGroups;
			
			if (sharedGroups.contains(currentGroupname))
			{
				result = false;
				break;
			}
			else
			{
				result = true;
			}
		}
		
		return result;
	}
	
	// Check if given group has been shared within the past 2 days
	public Boolean CheckBackTwo (Group group, ArrayList<Day> Week, int dayCount)
	{
		String currentGroupname = group.name;
		Boolean result = false;
		
		for (int i = (dayCount - 2); i <= dayCount; i++)
		{
			Day currentDay = Week.get(i);
			
			ArrayList<String> sharedGroups = (ArrayList<String>) currentDay.sharedGroups;
			
			if (sharedGroups.contains(currentGroupname))
			{
				result = false;
				break;
			}
			else
			{
				result = true;
			}
		}
		
		return result;
	}
	
	public Boolean CheckBackOne (Group group, ArrayList<Day> Week, int dayCount)
	{
		String currentGroupname = group.name;
		Boolean result = false;
		
		Day pastDay = Week.get(dayCount - 1);
		ArrayList<String> sharedGroups = (ArrayList<String>) pastDay.sharedGroups;
		
		// Hmmm can we just leave this as is? YES WE CAN!
		result = !sharedGroups.contains(currentGroupname);

		return result;
	}
	
	public Boolean CheckForwardOne (Group group, ArrayList<Day> Week, int dayCount)
	{
		String currentGroupname = group.name;
		Boolean result = false;
		
		Day nextDay = Week.get(dayCount + 1);
		ArrayList<String> sharedGroups = (ArrayList<String>) nextDay.sharedGroups;
		
		// Hmmm can we just leave this as is?
		result = !sharedGroups.contains(currentGroupname);

		return result;
	}
	
	
	// Given a already scheduled week (list of days), and a list of groups
	// schedule the groups into the given week.
	public ArrayList<Day> buildSchedule(ArrayList<Day> Week, GroupList Groups)
	{
		schedule = Week;
		
		// Clear any existing groups in the schedule
		/*
		for (int i = 0; i < schedule.size(); i++)
		{
			Day currentDay = schedule.get(i);
			currentDay.deleteAllGroups();
		}*/
		
		// Get the first day
		Day firstDay = schedule.get(0);
		
		// STEP 4:
		// If the first day is a hot day
		if (firstDay.hotDay)
		{
			// OLD: find 2 top tier groups and 2 mid tier groups to share
			
			// NEW: find 1 top tier groups and 2 mid tier groups to share
			
			firstDay.addGroup(Groups.topTier.get(0));
			//firstDay.addGroup(Groups.topTier.get(1));
			
			for (int i = 0; i < Groups.midTier.size(); i++)
			{
				Group current = Groups.midTier.get(i);
				
				if (firstDay.canAdd(current))
				{
					firstDay.addGroup(current);
				}
			}
			
			// find the next "hot" day (within at least a 2 day gap), and share
			// OLD:
			// 1 remaining top tier group
			// 1 of already shared top tier groups
			// 2 different mid tier groups
			
			// NEW:
			// 2 remaining top tier groups
			// 1 mid tier group
			
			for (int i = 2; i < schedule.size(); i ++)
			{
				Day currentDay = schedule.get(i);
				
				if (currentDay.hotDay)
				{
					currentDay.addGroup(Groups.topTier.get(1));
					currentDay.addGroup(Groups.topTier.get(2));
					
					for (int j = 0; j < Groups.midTier.size(); j++)
					{
						Group current = Groups.midTier.get(j);
						
						if (currentDay.canAdd(current) && sufficientGap(current, schedule, i))
						{
							currentDay.addGroup(current);
						}
					}
					
					break;
				}
			}
			
		}
		else //if the first day is NOT a hot day
		{
			// OLD: Add 1 top tier group, and two mid tier groups
			// NEW: Add 1 top tier group, and 1 mid tier group
			firstDay.addGroup(Groups.topTier.get(0));
			
			for (int i = 0; i < Groups.midTier.size(); i++)
			{
				Group current = Groups.midTier.get(i);
				
				if (firstDay.canAdd(current))
				{
					firstDay.addGroup(current);
				}
			}
			
			//Find the next hot day and share
			// OLD: 2 remaining two tier groups, 2 mid tier groups
			
			// NEW: 2 remaining top tier groups, 1 mid tier group
			
			int cont = 0;
			
			for (int i = 0; i < schedule.size(); i++)
			{
				Day currentD = schedule.get(i);
				
				if (currentD.hotDay)
				{
					currentD.addGroup(Groups.topTier.get(1));
					currentD.addGroup(Groups.topTier.get(2));
					
					for (int j = 0; j < Groups.midTier.size(); j++)
					{
						Group currentG = Groups.midTier.get(j);
						
						// KEY THING HERE!!! TAKE IN ACCOUNT OF BOUNDARY ERROR

						if (currentD.canAdd(currentG) && sufficientGap(currentG, schedule, i))
						{
								currentD.addGroup(currentG);
						}

						/*
						if (currentD.canAdd(currentG))
						{
							currentD.addGroup(currentG);
						}*/
					}
					
					cont = i;
					
					break;
				}
			}
			
			// Find the next hot day (within a two day gap) and share
			// OLD:
			// First shared top tier group, other top tier group
			// two other mid tier groups
			
			// NEW:
			// First shared top tier group, other top tier group
			// 1 other mid tier group
			
			for (int i = cont + 2; i < schedule.size(); i++)
			{
				Day currentD = schedule.get(i);
				
				if (currentD.hotDay)
				{
					currentD.addGroup(Groups.topTier.get(0));
					currentD.addGroup(Groups.topTier.get(2));
					
					for (int j = 0; j < Groups.midTier.size(); j++)
					{
						Group currentG = Groups.midTier.get(j);
						
						if (currentD.canAdd(currentG) && sufficientGap(currentG, schedule, i))
						{
							currentD.addGroup(currentG);
						}
					}
					
					break;
				}
			}
			
		}
		
		// STEP 5:
		// DONE WITH ALL THE FIRST DAY AND HOT DAY STUFF FROM HERE ON NOW
		// Just fill the rest in now
		
		for (int i = 0; i < schedule.size(); i ++)
		{
			Day currentDay = schedule.get(i);
			
			// Cycle through the current day's added list of groups and set their
			// daysRemainingUntilNextShare = 2
			
			/*
			for (int k = 0; k < currentDay.addedGroups.size(); k++)
			{
				Group currentAddedGroup = currentDay.addedGroups.get(k);
				
				//currentAddedGroup.daysRemainingUntilNextShare = 2;
			}*/
			
			for (int j = 0; j < Groups.all.size(); j++)
			{
				Group currentGroup = Groups.all.get(j);
				
				// ANOTHER KEY POINT HERE!!! Since the days start at 0, its impossible to look back
				
				if (currentDay.canAdd(currentGroup) && sufficientGap(currentGroup, schedule, i))
				{
						currentDay.addGroup(currentGroup);
				}

				
			}
			
			/*
			for (int k = 0; k < currentDay.addedGroups.size(); k++)
			{
				Group currentAddedGroup = currentDay.addedGroups.get(k);
				
				//currentAddedGroup.daysRemainingUntilNextShare = 2;
			} */
			
			//update all groups for the day
			/*
			for (int k = 0; k < Groups.all.size(); k++)
			{
				Group currentGroup = Groups.all.get(k);
				currentGroup.update();
			} */
		}
		
		return schedule;
		
	}
		

}
