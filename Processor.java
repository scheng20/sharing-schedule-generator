package schedule_generator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;

public class Processor 
{
	// The Event Name
	String eventName = "";
	
	// The Event Date
	LocalDate eventDate = LocalDate.of(2019,Month.OCTOBER, 15);
	
	// Create the marketing week
	ArrayList<Day> marketingWeek = new ArrayList<Day>();
	
	// Instantiate a special list used to store the groups
	GroupList list = new GroupList();
	
	// Instantiate a new weekGenerator
	WeekCreator weekGenerator = new WeekCreator();
	
	// Instantiate a scheduleBuilder
	ScheduleCreator builder = new ScheduleCreator();
	
	// Statistics
	int totalToShare = 0;
	int totalSlots = 0; 
	int actualShares = 0;
	
	// Group Presets
	Boolean useBizTech2019_2020Preset = false;
	
	// Constructor
	public Processor()
	{
		
	}
	
	// Functions
	public void generateWeek()
	{
		marketingWeek = weekGenerator.generateWeek(eventDate);
	}
	
	public void addGroup(String groupName, String groupFaculty, String groupYear, String groupTier)
	{
		Group tobeAddedGroup = new Group (groupName, groupFaculty, groupYear, groupTier);
		list.add(tobeAddedGroup);
	}
	
	public void buildGroup()
	{
		list.compile();
	}
	
	public void loadPresets()
	{
		// Enable any presets
		if (useBizTech2019_2020Preset)
		{
			list.deleteAll();
					
			Group UBC_2020 = new Group("UBC 2020", "General", "2020", "N");
			Group UBC_2021 = new Group("UBC 2021", "General", "2021", "N");
			Group UBC_2022 = new Group("UBC 2022", "General", "2022", "N");
			Group UBC_2023 = new Group("UBC 2023", "General", "2023", "N");
					
			Group Sauder_2020 = new Group ("Sauder 2020", "Sauder", "2020", "T");
			Group Sauder_2021 = new Group ("Sauder 2021", "Sauder", "2021", "T");
			Group Sauder_2022 = new Group ("Sauder 2022", "Sauder", "2022", "T");
					
			Group BUCS = new Group ("BUCS", "BUCS", "All", "N");
					
			Group Science_2021 = new Group ("Science 2021", "Science", "2021", "N"); 
			Group Science_2022 = new Group ("Science 2022", "Science", "2022", "N"); 
					
			Group CompSci = new Group ("CompSci", "Computer Science", "All", "N");
					
					
			Group Arts_2021 = new Group ("Arts 2021", "Arts", "2021", "N");
			Group Arts_2022 = new Group ("Arts 2022", "Arts", "2022", "N"); 
					
			list.add(UBC_2020);
			list.add(UBC_2021);
			list.add(UBC_2022);
			list.add(UBC_2023);
					
			list.add(Sauder_2020);
			list.add(Sauder_2021);
			list.add(Sauder_2022);
					
			list.add(BUCS);
					
			list.add(Science_2021);
			list.add(Science_2022);
			
			list.add(CompSci);
					
			list.add(Arts_2021);
			list.add(Arts_2022);
		}
		list.compile();
	}
	
	public void compileStatsBefore()
	{
		// Calculate total number of shares needs to be shared
		for (int i = 0; i < list.all.size(); i ++)
		{
			Group currentGroup = list.all.get(i);
							
			totalToShare += currentGroup.shareTimes;
		}
						
		// Calculate total number of slots available
		for (int i = 0; i < marketingWeek.size(); i ++)
		{
			Day currentDay = marketingWeek.get(i);
							
			totalSlots = totalSlots + currentDay.shareSlots;
		}
	}
	
	public void compileStatsAfter()
	{
		// Calculate the actual number of shares this week
		for (int i = 0; i < marketingWeek.size(); i++)
		{
			Day currentDay = marketingWeek.get(i);
			
			actualShares = actualShares + currentDay.sharedGroups.size();
		}
	}
	
	public void buildSchedule()
	{
		ArrayList<Day> schedule = builder.buildSchedule(marketingWeek, list);
		marketingWeek = schedule;
	}
	
	public String[] getDates()
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] result = {};
		
		
		for (int i = 0; i < marketingWeek.size(); i++)
		{
			Day currentDay = marketingWeek.get(i);
			String currentDate = (currentDay.date).format(DateTimeFormatter.ofPattern("MMMM d"));

			list.add(currentDate);
		}
		
		result = list.toArray(new String[list.size()]);  
		
		return result;
	}
	
	public String[] getDayofWeek()
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] result = {};
		
		for (int i = 0; i < marketingWeek.size(); i++)
		{
			Day currentDay = marketingWeek.get(i);
			String currentDayofWeek = (currentDay.date).getDayOfWeek().name();

			list.add(currentDayofWeek);
		}
		
		result = list.toArray(new String[list.size()]);  
		
		return result;
	}
	
	// Gets a row of groups for the schedule
	public String[] getScheduleRow(int row)
	{
		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};
		
		for (int i = 0; i < marketingWeek.size(); i++)
		{
			Day currentDay = marketingWeek.get(i);
			
			if (!currentDay.sharedGroups.isEmpty() && (currentDay.sharedGroups.size() > row))
			{
				String currentGroup = currentDay.sharedGroups.get(row);
				alist.add(currentGroup);
			}
			else
			{
				alist.add("");
			}
		}
		
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
	}
	
	// Gets a row of group data for the group table
	public String[] getGroupData(int row)
	{
		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};
		
		Group currentGroup = list.all.get(row);
		alist.add(currentGroup.name);
		alist.add(currentGroup.faculty);
		alist.add(currentGroup.year);
		
		if (currentGroup.tier == "T")
		{
			alist.add("Yes");
		}
		else
		{
			alist.add("No");
		}
		
		alist.add(Integer.toString(currentGroup.shareTimes));
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
	}
	
	// Returns an array just with the group names
	public String[] getAllGroupNames()
	{
		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};
		
		for (int i = 0; i < list.all.size(); i ++)
		{
			Group currentGroup = list.all.get(i);
			alist.add(currentGroup.name);
		}
		
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
		
	}
	
	// Resets the target groups
	public void resetTargetGroups()
	{
		for (int i = 0; i < list.topTier.size(); i++)
		{
			Group currentGroup = list.topTier.get(i);
			currentGroup.shareTimes = 1;
			currentGroup.tier = "N";
			list.midTier.add(currentGroup);
		}
		
		list.topTier.clear();
	}
	
	
	// Updates the new target groups
	public void setTargetGroup(String GroupName)
	{
		for (int i = 0; i < list.all.size(); i++)
		{
			Group currentGroup = list.all.get(i);
			
			if (currentGroup.name.equalsIgnoreCase(GroupName))
			{
				currentGroup.tier = "T";
				currentGroup.shareTimes = 2;
				list.topTier.add(currentGroup);
				list.midTier.remove(currentGroup);
				
				// This stuff reorganizes the list so there is no need for recompiling
				list.all.remove(currentGroup);
				list.all.add(0, currentGroup);
			}
			
		}
	}
	

}
