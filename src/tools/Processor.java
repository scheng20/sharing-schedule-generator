package tools;

import models.Day;
import models.Group;
import models.GroupList;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Processor 
{
    // ------------------------------- FIELDS --------------------------------

	// The Event Name
	private String eventName = "";
	
	// The Event Date
	private LocalDate eventDate = LocalDate.of(2019,Month.OCTOBER, 15);
	
	// Create the marketing week
	private ArrayList<Day> marketingWeek = new ArrayList<Day>();
	
	// Instantiate a special list used to store the groups
	private GroupList list = new GroupList();
	
	// Instantiate a new weekGenerator
	private WeekCreator weekGenerator = new WeekCreator();
	
	// Instantiate a scheduleBuilder
	private ScheduleCreator builder = new ScheduleCreator();
	
	// Statistics
	private int totalToShare = 0;
	private int totalSlots = 0;
	private int actualShares = 0;
	
	// Group Presets
	private Boolean useBizTech2019_2020Preset = false;

    // ------------------------------ FUNCTIONS ------------------------------
	public void generateWeek()
	{
		marketingWeek = weekGenerator.generateWeek(eventDate);
	}
	
	public void addGroup(String groupName, String groupFaculty, String groupYear, String groupTier) {

		Group tobeAddedGroup = new Group (groupName, groupFaculty, groupYear, groupTier);
		list.add(tobeAddedGroup);
	}
	
	public void buildGroup()
	{
		list.compile();
	}
	
	public void loadPresets() {

		// Enable any presets
		if (useBizTech2019_2020Preset) {

            list.deleteAll();
            loadBizTechPreset();
		}
		list.compile();
	}

	public void loadBizTechPreset() {

	    // Create the BizTech Preset Groups
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

        // Add these groups to the list
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
	
	public void compileStatsBefore() {

		// Calculate total number of shares needs to be shared
        for (Group currentGroup: list.getAll()) {
            totalToShare += currentGroup.shareTimes;
        }
						
		// Calculate total number of slots available
        for (Day currentDay : marketingWeek) {
            totalSlots = totalSlots + currentDay.getShareSlots();
        }
	}
	
	public void compileStatsAfter() {

		// Calculate the actual number of shares this week
		for (Day currentDay: marketingWeek) {
			actualShares = actualShares + currentDay.getSharedGroups().size();
		}
	}
	
	public void buildSchedule() {

        marketingWeek = builder.buildSchedule(marketingWeek, list);
	}
	
	public String[] getDates() {

		ArrayList<String> list = new ArrayList<String>();
		String[] result = {};

        for (Day currentDay : marketingWeek) {

            String currentDate = (currentDay.getDate()).format(DateTimeFormatter.ofPattern("MMMM d"));
            list.add(currentDate);
        }
		
		result = list.toArray(new String[list.size()]);  
		
		return result;
	}
	
	public String[] getDayOfWeek() {

		ArrayList<String> list = new ArrayList<String>();
		String[] result = {};

        for (Day currentDay : marketingWeek) {

            String currentDayofWeek = (currentDay.getDate()).getDayOfWeek().name();
            list.add(currentDayofWeek);
        }
		
		result = list.toArray(new String[list.size()]);  
		
		return result;
	}

	// Gets a row of groups for the schedule table
	public String[] getScheduleRow(int row) {

		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};

        for (Day currentDay : marketingWeek) {

            if (!currentDay.getSharedGroups().isEmpty() && (currentDay.getSharedGroups().size() > row)) {
                alist.add(currentDay.getSharedGroups().get(row));
            } else {
                alist.add("");
            }

        }
		
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
	}
	
	// Gets a row of group data for the group table
	public String[] getGroupData(int row) {

		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};
		
		Group currentGroup = list.getAll().get(row);
		alist.add(currentGroup.getName());
		alist.add(currentGroup.getFaculty());
		alist.add(currentGroup.getYear());
		
		if (currentGroup.getTier() == "T") {
			alist.add("Yes");
		} else {
			alist.add("No");
		}
		
		alist.add(Integer.toString(currentGroup.shareTimes));
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
	}
	
	// Returns an array just with the group names
	public String[] getAllGroupNames() {

		ArrayList<String> alist = new ArrayList<String>();
		String[] result = {};
		
		for (Group currentGroup : list.getAll()) {
			alist.add(currentGroup.getName());
		}
		
		result = alist.toArray(new String[alist.size()]);  
		
		return result;
		
	}
	
	// Resets the target groups
	public void resetTargetGroups() {

		for (Group currentGroup : list.getTopTier())
		{
			currentGroup.shareTimes = 1;
			currentGroup.setTier("N");
			list.getMidTier().add(currentGroup);
		}
		
		list.getTopTier().clear();
	}


    // Updates the new target groups
    public void setTargetGroup(String GroupName)
    {
        for (int i = 0; i < list.getAll().size(); i++) {

            Group currentGroup = list.getAll().get(i);

            if (currentGroup.getName().equalsIgnoreCase(GroupName)) {
                currentGroup.setTier("T");
                currentGroup.shareTimes = 2;
                list.getTopTier().add(currentGroup);
                list.getMidTier().remove(currentGroup);

                // This stuff reorganizes the list so there is no need for recompiling
                list.getAll().remove(currentGroup);
                list.getAll().add(0, currentGroup);
            }
        }
    }

    // ------------------------- GETTERS AND SETTERS -------------------------

    public String getEventName() {
	    return eventName;
    }

    public LocalDate getEventDate() {
	    return eventDate;
    }

    public GroupList getList() {
	    return list;
    }

    public int getTotalToShare() {
	    return totalToShare;
    }

    public int getTotalSlots() {
	    return totalSlots;
    }

    public int getActualShares() {
	    return actualShares;
    }

    public boolean isUseBizTech2019_2020Preset() {
	    return useBizTech2019_2020Preset;
    }

    public void setUseBizTech2019_2020Preset(Boolean result) {
	    useBizTech2019_2020Preset = result;
    }

    public void setEventName(String name) {
	    eventName = name;
    }

    public void setTotalToShare(int result) {
	    totalToShare = result;
    }

    public void setTotalSlots(int result) {
        totalSlots = result;
    }

    public void setActualShares(int result) {
        actualShares = result;
    }

    public void setEventDate (LocalDate date) {
	    eventDate = date;
    }

}
