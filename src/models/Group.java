package models;

public class Group
{
    // ------------------------------- FIELDS --------------------------------
	private String name;
	private String faculty;
	private String year;
	private String tier;
	
	public int shareTimes = 0;

    // ----------------------------- CONSTRUCTOR -----------------------------
	public Group (String Name, String Faculty, String Year, String Tier) {
		name = Name;
		faculty = Faculty;
		tier = Tier;
		year = Year;
		
		// MAX 2 TIMES SYSTEM:
		switch (tier) {
			case "T":
				shareTimes = 2;
				break;
			case "N":
				shareTimes = 1;
				break;
		}
	}

    // ------------------------------ FUNCTIONS ------------------------------
	public void resetShareTimes() {
		if (tier.equalsIgnoreCase("T")) {
			shareTimes = 2;
		} else {
			shareTimes = 1;
		}
	}

    // ------------------------- GETTERS AND SETTERS -------------------------

    public String getName() {
	    return name;
    }

    public String getFaculty() {
	    return faculty;
    }

    public String getYear() {
	    return year;
    }

    public String getTier() {
	    return tier;
    }

    public void setTier(String tier) {
	    this.tier = tier;
    }
}
