package ui;

import models.Group;
import tools.Processor;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class GUI {
	
	// Initialize some class properties
	JFrame frmSharingScheduleGenerator;
	
	public Processor magicMachine = new Processor();
	private JTextField txt_groupName;
	private JTextField txt_groupFaculty;
	private JTextField txt_groupYear;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSharingScheduleGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 */
	public GUI() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{	
		
		frmSharingScheduleGenerator = new JFrame();
		frmSharingScheduleGenerator.setTitle("Sharing Schedule Generator");
		frmSharingScheduleGenerator.getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
		frmSharingScheduleGenerator.setResizable(false);
		frmSharingScheduleGenerator.setBounds(100, 100, 800, 600);
		frmSharingScheduleGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16));
		frmSharingScheduleGenerator.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// EVENT SETTINGS PANEL STUFF -----------------
		JPanel EventSettings = new JPanel();
		tabbedPane.addTab("Event Settings", null, EventSettings, null);
		EventSettings.setLayout(null);
		
		JLabel label_setEventName = new JLabel("Event Name: ");
		label_setEventName.setFont(new Font("Arial", Font.PLAIN, 16));
		label_setEventName.setBounds(40, 80, 100, 30);
		EventSettings.add(label_setEventName);
		
		JLabel label_setEventDate = new JLabel("Event Date: ");
		label_setEventDate.setFont(new Font("Arial", Font.PLAIN, 16));
		label_setEventDate.setBounds(40, 130, 100, 30);
		EventSettings.add(label_setEventDate);
		
		final JTextField txt_eventName;
		txt_eventName = new JTextField();
		txt_eventName.setToolTipText("Enter your event name here (or not, this field is optional).");
		txt_eventName.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_eventName.setBounds(140, 80, 500, 30);
		txt_eventName.setColumns(10);
		EventSettings.add(txt_eventName);
		
		JButton btnGenerateSchedule = new JButton("Generate Schedule!");
		btnGenerateSchedule.setToolTipText("Make the magic happen!");
		btnGenerateSchedule.setBounds(550, 450, 180, 40);
		EventSettings.add(btnGenerateSchedule);
		
		btnGenerateSchedule.setFont(new Font("Arial", Font.PLAIN, 16));
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(140, 130, 200, 30);
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
		EventSettings.add(dateChooser);
		
		final JLabel label_help = new JLabel("");
		label_help.setHorizontalAlignment(SwingConstants.TRAILING);
		label_help.setForeground(new Color(255, 58, 58));
		label_help.setFont(new Font("Arial", Font.PLAIN, 16));
		label_help.setBounds(110, 450, 400, 40);
		EventSettings.add(label_help);
		
		JLabel label_eventSettings = new JLabel("Event Settings");
		label_eventSettings.setFont(new Font("Arial", Font.PLAIN, 24));
		label_eventSettings.setBounds(40, 30, 210, 30);
		EventSettings.add(label_eventSettings);
		
		JLabel label_targetGroups = new JLabel("Target Groups:");
		label_targetGroups.setFont(new Font("Arial", Font.PLAIN, 16));
		label_targetGroups.setBounds(40, 180, 110, 30);
		EventSettings.add(label_targetGroups);
		
		final JComboBox cbox_target1 = new JComboBox();
		cbox_target1.setToolTipText("A group that will be shared on the days with the most active viewers (maximum exposure).");
		cbox_target1.setFont(new Font("Arial", Font.PLAIN, 16));
		cbox_target1.setBounds(200, 180, 135, 30);
		
		EventSettings.add(cbox_target1);
		
		final JComboBox cbox_target2 = new JComboBox();
		cbox_target2.setToolTipText("A group that will be shared on the days with the most active viewers (maximum exposure).");
		cbox_target2.setFont(new Font("Arial", Font.PLAIN, 16));
		cbox_target2.setBounds(380, 180, 135, 30);
		EventSettings.add(cbox_target2);
		
		final JComboBox cbox_target3 = new JComboBox();
		cbox_target3.setToolTipText("A group that will be shared on the days with the most active viewers (maximum exposure).");
		cbox_target3.setFont(new Font("Arial", Font.PLAIN, 16));
		cbox_target3.setBounds(560, 180, 135, 30);
		EventSettings.add(cbox_target3);
		
		JLabel label_moreSettings = new JLabel("More Settings:");
		label_moreSettings.setFont(new Font("Arial", Font.PLAIN, 16));
		label_moreSettings.setBounds(40, 280, 200, 30);
		EventSettings.add(label_moreSettings);
		
		final JCheckBox chckbox_ubcpreset = new JCheckBox("Use UBC BizTech 2019-2020 Groups Preset");
		chckbox_ubcpreset.setToolTipText("Automatically load in all of the groups that UBC BizTech 2019-2020 have access to.");
		chckbox_ubcpreset.setFont(new Font("Arial", Font.PLAIN, 16));
		chckbox_ubcpreset.setBounds(40, 320, 400, 30);
		EventSettings.add(chckbox_ubcpreset);
		
		// GROUP SETTINGS PANEL STUFF -----------------
		JPanel GroupSettings = new JPanel();
		tabbedPane.addTab("Group Settings", null, GroupSettings, null);
		GroupSettings.setLayout(null);
		
		JLabel label_groupEditor = new JLabel("Group Editor");
		label_groupEditor.setFont(new Font("Arial", Font.PLAIN, 24));
		label_groupEditor.setBounds(40, 30, 210, 30);
		GroupSettings.add(label_groupEditor);
		
		txt_groupName = new JTextField();
		txt_groupName.setToolTipText("Ex. UBC 2022, Sauder 2021, Computer Science Group or BUCS");
		txt_groupName.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_groupName.setColumns(10);
		txt_groupName.setBounds(40, 110, 200, 30);
		GroupSettings.add(txt_groupName);
		
		JLabel label_groupName = new JLabel("Group Name:");
		label_groupName.setFont(new Font("Arial", Font.PLAIN, 16));
		label_groupName.setBounds(40, 80, 200, 30);
		GroupSettings.add(label_groupName);
		
		JLabel label_faculty = new JLabel("Faculty:");
		label_faculty.setFont(new Font("Arial", Font.PLAIN, 16));
		label_faculty.setBounds(270, 80, 150, 30);
		GroupSettings.add(label_faculty);
		
		txt_groupFaculty = new JTextField();
		txt_groupFaculty.setToolTipText("Ex. Commerce, Science, Engineering, or General");
		txt_groupFaculty.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_groupFaculty.setColumns(10);
		txt_groupFaculty.setBounds(270, 110, 150, 30);
		GroupSettings.add(txt_groupFaculty);
		
		JLabel lblYearLevel = new JLabel("Year Level:");
		lblYearLevel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblYearLevel.setBounds(450, 80, 100, 30);
		GroupSettings.add(lblYearLevel);
		
		txt_groupYear = new JTextField();
		txt_groupYear.setToolTipText("Ex. 2022, 2021, or 2023");
		txt_groupYear.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_groupYear.setColumns(10);
		txt_groupYear.setBounds(450, 110, 100, 30);
		GroupSettings.add(txt_groupYear);
		
		final JLabel label_grouphelp = new JLabel("");
		label_grouphelp.setHorizontalAlignment(SwingConstants.TRAILING);
		label_grouphelp.setFont(new Font("Arial", Font.PLAIN, 16));
		label_grouphelp.setForeground(new Color(255, 58, 58));
		label_grouphelp.setBounds(135, 480, 400, 30);
		GroupSettings.add(label_grouphelp);
		
		JLabel lblAddedGroups = new JLabel("Added Groups");
		lblAddedGroups.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAddedGroups.setBounds(40, 160, 200, 30);
		GroupSettings.add(lblAddedGroups);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 200, 700, 250);
		GroupSettings.add(scrollPane_1);
		
		//----------------------------------------------
		// TABLE THAT DISPLAYS THE ADDED GROUPS
		String g_col[] = {"Group Name", "Faculty", "Year", "Target?", "ShareTimes"};
		final DefaultTableModel groupsTableModel = new DefaultTableModel(g_col, 0);
		
		final JTable groupsTable = new JTable(groupsTableModel);
		scrollPane_1.setViewportView(groupsTable);
		
		// This makes the table look prettier
		groupsTable.setFont(new Font("Arial", Font.PLAIN, 14));
		groupsTable.setRowHeight(35);
		groupsTable.getTableHeader().setReorderingAllowed(false);
		
		// This makes everything in the table centered
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
				
		for (int x=0 ;x<groupsTable.getColumnCount(); x++)
		{
			groupsTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		} 
		
		// Load the data into the table for presets
		chckbox_ubcpreset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (chckbox_ubcpreset.isSelected())
				{
					// Creates data for the group table
					magicMachine.useBizTech2019_2020Preset = true;
					magicMachine.loadPresets();
					
					for (int i = 0; i < magicMachine.list.all.size(); i++)
					{
						String rowContent[] = magicMachine.getGroupData(i);
						groupsTableModel.addRow(rowContent);
					}
					
					// Creates data for the target boxes
					String[] allGroupNames = magicMachine.getAllGroupNames();
					
					cbox_target1.setModel(new DefaultComboBoxModel(allGroupNames));
					cbox_target2.setModel(new DefaultComboBoxModel(allGroupNames));
					cbox_target3.setModel(new DefaultComboBoxModel(allGroupNames));
					
					cbox_target1.setSelectedItem(magicMachine.list.topTier.get(0).getName());
					cbox_target2.setSelectedItem(magicMachine.list.topTier.get(1).getName());
					cbox_target3.setSelectedItem(magicMachine.list.topTier.get(2).getName());
										
				}
				else
				{
					magicMachine.list.deleteAll();
					groupsTableModel.setRowCount(0);
					
					String[] empty = {};
					
					cbox_target1.setModel(new DefaultComboBoxModel(empty));
					cbox_target2.setModel(new DefaultComboBoxModel(empty));
					cbox_target3.setModel(new DefaultComboBoxModel(empty));
				}

			}
		});

		//----------------------------------------------
		
		//----------------------------------------------
		// BUTTON THAT ADDS NEW GROUPS 
		JButton btn_addGroup = new JButton("Add Group");
		btn_addGroup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String groupName = txt_groupName.getText();
				String groupFaculty = txt_groupFaculty.getText();
				String groupYear = txt_groupYear.getText();
				Boolean distinct = true;
						
				if (groupName.isEmpty())
				{
					label_grouphelp.setForeground(new Color(255, 58, 58));
					label_grouphelp.setText("Please enter a group name!");
				}
				else if ( groupFaculty.isEmpty())
				{
					label_grouphelp.setForeground(new Color(255, 58, 58));
					label_grouphelp.setText("Please enter a group faculty!");
				}
				else if (groupYear.isEmpty())
				{
					label_grouphelp.setForeground(new Color(255, 58, 58));
					label_grouphelp.setText("Please enter a group year!");
				}
				else
				{
					// Check if group name is distinct
					for (int i = 0; i < magicMachine.list.all.size(); i++)
					{
						Group currentGroup = magicMachine.list.all.get(i);
								
						if (currentGroup.getName().equalsIgnoreCase(groupName))
						{
							distinct = false;
							break;
						}
						else
						{
							distinct = true;
						}
								
					}
							
					// If group name is distinct, add the group, else report that it is not distinct
					if (distinct)
					{
						// Add the group
						magicMachine.addGroup(groupName, groupFaculty, groupYear, "N");
						
						// Notify the user
						label_grouphelp.setForeground(new Color(44, 158, 42));
						label_grouphelp.setText("Group Added!");
						
						// Clear the fields
						txt_groupName.setText("");
						txt_groupFaculty.setText("");
						txt_groupYear.setText("");
						
						// Refresh the group table!
						groupsTableModel.setRowCount(0);
							
						for (int i = 0; i < magicMachine.list.all.size(); i++)
						{
							String rowContent[] = magicMachine.getGroupData(i);
							groupsTableModel.addRow(rowContent);
						}
						
						// Update the target choices!
						String[] allGroupNames = magicMachine.getAllGroupNames();
						
						cbox_target1.setModel(new DefaultComboBoxModel(allGroupNames));
						cbox_target2.setModel(new DefaultComboBoxModel(allGroupNames));
						cbox_target3.setModel(new DefaultComboBoxModel(allGroupNames));
					}
					else
					{
						label_grouphelp.setForeground(new Color(255, 58, 58));
						label_grouphelp.setText("Please use a distinct group name!");
					}
				}
			}
		});
				
		btn_addGroup.setToolTipText("Adds a new group to the list below");
		btn_addGroup.setFont(new Font("Arial", Font.PLAIN, 16));
		btn_addGroup.setBounds(590, 110, 150, 30);
		GroupSettings.add(btn_addGroup);
		
		// -----------------------------------------------------
		
		//----------------------------------------------
		// BUTTON THAT DELETES GROUPS 
		JButton btn_deleteGroup = new JButton("Delete Selected");
		
		btn_deleteGroup.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Deletes the selected groups
				int[] selection = groupsTable.getSelectedRows();
				
				// selection is now in terms of the underlying TableModel
				for (int i = 0; i < selection.length; i++) 
				{
				   selection[i] = groupsTable.convertRowIndexToModel(selection[i]);
				}
				
				// For every selected item...
				for (int i = 0; i < selection.length; i++)
				{
					String groupName = (String) groupsTable.getValueAt(selection[i],0);
					label_grouphelp.setText("Group Deleted!");
					label_grouphelp.setForeground(new Color(44, 158, 42));
					
					// Find it in all the groups and delete it 
					for (int j = 0; j < magicMachine.list.all.size(); j++)
					{
						Group currentGroup = magicMachine.list.all.get(j);
						
						if (currentGroup.getName().equalsIgnoreCase(groupName))
						{
							if (currentGroup.getTier().equalsIgnoreCase("T"))
							{
								magicMachine.list.topTier.remove(currentGroup);
								magicMachine.list.all.remove(currentGroup);
							}
							else
							{
								magicMachine.list.midTier.remove(currentGroup);
								magicMachine.list.all.remove(currentGroup);
							}
						}
					}
					
				}
				
				// Reload the table
				groupsTableModel.setRowCount(0);
				
				for (int i = 0; i < magicMachine.list.all.size(); i++)
				{
					String rowContent[] = magicMachine.getGroupData(i);
					groupsTableModel.addRow(rowContent);
				}
				
				// Update the target group choices
				String[] allGroupNames = magicMachine.getAllGroupNames();
				
				cbox_target1.setModel(new DefaultComboBoxModel(allGroupNames));
				cbox_target2.setModel(new DefaultComboBoxModel(allGroupNames));
				cbox_target3.setModel(new DefaultComboBoxModel(allGroupNames));
				
			}
		});
		
		btn_deleteGroup.setToolTipText("Deletes the selected group from the table above");
		btn_deleteGroup.setFont(new Font("Arial", Font.PLAIN, 16));
		btn_deleteGroup.setBounds(590, 480, 150, 30);
		GroupSettings.add(btn_deleteGroup);
		
		//----------------------------------------------
		
		// SCHEDULE OUTPUT PANEL STUFF -----------------
		JPanel ScheduleOutput = new JPanel();
		tabbedPane.addTab("Schedule", null, ScheduleOutput, null);
		ScheduleOutput.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 130, 700, 303);
		ScheduleOutput.add(scrollPane);
		
		final JLabel EventName = new JLabel("Event Name: ");
		EventName.setFont(new Font("Arial", Font.PLAIN, 16));
		EventName.setBounds(40, 80, 300, 30);
		ScheduleOutput.add(EventName);
		
		final JLabel EventDate = new JLabel("Event Date: ");
		EventDate.setFont(new Font("Arial", Font.PLAIN, 16)); 
		EventDate.setBounds(400, 80, 350, 30);
		ScheduleOutput.add(EventDate);
		
		final JLabel Stat1 = new JLabel("Total number of times to be shared: ");
		Stat1.setFont(new Font("Arial", Font.PLAIN, 16));
		Stat1.setBounds(40, 450, 300, 30);
		ScheduleOutput.add(Stat1);
		
		final JLabel Stat2 = new JLabel("Total number of ACTUAL shares: ");
		Stat2.setFont(new Font("Arial", Font.PLAIN, 16));
		Stat2.setBounds(40, 480, 300, 30);
		ScheduleOutput.add(Stat2);
		
		final JLabel Stat3 = new JLabel("Total number of slots this week: ");
		Stat3.setFont(new Font("Arial", Font.PLAIN, 16));
		Stat3.setBounds(440, 450, 300, 30);
		ScheduleOutput.add(Stat3);
		
		//----------------------------------------------
		// TABLE THAT DISPLAYS THE FINAL SCHEDULE OUTPUT
		
		String s_col[] = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5", "Day 6", "Day 7"};
		
		final DefaultTableModel scheduleTableModel = new DefaultTableModel(s_col, 0);
		// The 0 argument is the number of rows
		
		JTable scheduleTable = new JTable(scheduleTableModel)
		{
			// This makes the first two rows look prettier
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
		    {
		        Component returnComp = super.prepareRenderer(renderer, row, column);
		        
		        // Light Yellow = Color(252,242,206)
		        
		        Color alternateColor = new Color(252,242,206);
		        Color whiteColor = Color.WHITE;
		        
		        if (!returnComp.getBackground().equals(getSelectionBackground()))
		        {
		        	Color bg = whiteColor;
		        	
		        	if (row == 0 || row == 1)
		        	{
		        		bg = alternateColor;
		        	}
		        	else
		        	{
		        		bg = whiteColor;
		        	}
		        	
		            returnComp .setBackground(bg);
		            bg = null;
		        }
		        
		        return returnComp;
		    }
		};
		
		// This makes the table look prettier
		scheduleTable.setFont(new Font("Arial", Font.PLAIN, 14));
		scheduleTable.setRowHeight(35);
		
		// This prevents the user from changing the look of the schedule
		scheduleTable.getColumnModel().getColumn(0).setResizable(false);
		scheduleTable.getColumnModel().getColumn(1).setResizable(false);
		scheduleTable.getColumnModel().getColumn(2).setResizable(false);
		scheduleTable.getColumnModel().getColumn(3).setResizable(false);
		scheduleTable.getColumnModel().getColumn(4).setResizable(false);
		scheduleTable.getColumnModel().getColumn(5).setResizable(false);
		scheduleTable.getColumnModel().getColumn(6).setResizable(false);
		
		scheduleTable.getTableHeader().setReorderingAllowed(false);
		
		// This makes everything in the table centered
		for (int x=0 ;x<scheduleTable.getColumnCount(); x++)
		{
			scheduleTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		} 
		
		scrollPane.setViewportView(scheduleTable);
		
		JLabel label_Schedule = new JLabel("Schedule");
		label_Schedule.setFont(new Font("Arial", Font.PLAIN, 24));
		label_Schedule.setBounds(40, 30, 210, 30);
		ScheduleOutput.add(label_Schedule);
		
		JPanel Credits = new JPanel();
		tabbedPane.addTab("Help & Info", null, Credits, null);
		Credits.setLayout(null);
		
		JLabel label_helpAndInfo = new JLabel("Help and Information");
		label_helpAndInfo.setBounds(40, 30, 300, 30);
		label_helpAndInfo.setFont(new Font("Arial", Font.PLAIN, 24));
		Credits.add(label_helpAndInfo);
		
		JLabel lblThankYouFor = new JLabel("Thank you for downloading and using this program!");
		lblThankYouFor.setFont(new Font("Arial", Font.PLAIN, 15));
		lblThankYouFor.setBounds(40, 70, 700, 20);
		Credits.add(lblThankYouFor);
		
		JLabel label_content2 = new JLabel("Most of the functions of this generator are quite self-explanatory, but if you are stuck or confused, here");
		label_content2.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content2.setBounds(40, 100, 700, 20);
		Credits.add(label_content2);
		
		JLabel label_content3 = new JLabel("is a quick 3 step guideline on how to create your first sharing schedule!");
		label_content3.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content3.setBounds(40, 120, 700, 20);
		Credits.add(label_content3);
		
		JLabel lblStepCreate = new JLabel("Step 1: Create at least 3 groups under \u201CGroup Settings\u201D OR directly enable one of the built-in presets");
		lblStepCreate.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStepCreate.setBounds(40, 150, 700, 20);
		Credits.add(lblStepCreate);
		
		JLabel label_content5 = new JLabel("under \u201CEvent Settings\u201D.");
		label_content5.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content5.setBounds(90, 170, 600, 20);
		Credits.add(label_content5);
		
		JLabel label_content6 = new JLabel("Step 2: Pick an event name, date, and 3 target groups under \u201CEvent Settings\u201D. Target groups are groups\r\n");
		label_content6.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content6.setBounds(40, 200, 700, 20);
		Credits.add(label_content6);
		
		JLabel label_content7 = new JLabel(" that your event will be shared with the most (higher frequency). \r\n");
		label_content7.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content7.setBounds(90, 220, 600, 20);
		Credits.add(label_content7);
		
		JLabel label_content8 = new JLabel("Step 3: Click \u201CGenerate Schedule\u201D under \u201CEvent Settings\u201D and voila! Your generated schedule can be\r\n");
		label_content8.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content8.setBounds(40, 250, 700, 20);
		Credits.add(label_content8);
		
		JLabel label_content9 = new JLabel("viewed under \u201CSchedule\u201D.\r\n");
		label_content9.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content9.setBounds(90, 270, 600, 20);
		Credits.add(label_content9);
		
		JLabel label_content10 = new JLabel("Using the built-in marketing algorithm, the schedule will feature 7 days before your event, detailing which\r\n\r\n");
		label_content10.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content10.setBounds(40, 300, 700, 20);
		Credits.add(label_content10);
		
		JLabel label_content11 = new JLabel("groups you should share your event with each day. By having a sharing schedule, this ensures that you\r\n");
		label_content11.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content11.setBounds(40, 320, 700, 20);
		Credits.add(label_content11);
		
		JLabel label_content12 = new JLabel("won\u2019t overshare or spam the groups. This program was originally designed for sharing an event to\r\n\r\n");
		label_content12.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content12.setBounds(40, 340, 700, 20);
		Credits.add(label_content12);
		
		JLabel label_content13 = new JLabel("different Facebook groups but you can also apply the same tactic with groups on other platforms. \r\n\r\n");
		label_content13.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content13.setBounds(40, 360, 700, 20);
		Credits.add(label_content13);
		
		JLabel label_content14 = new JLabel("If you have any further questions or any bugs to report, please send them via scheng.ca/#contact");
		label_content14.setFont(new Font("Arial", Font.PLAIN, 15));
		label_content14.setBounds(40, 390, 700, 20);
		Credits.add(label_content14);
		
		JLabel label_credit = new JLabel("Credits");
		label_credit.setFont(new Font("Arial", Font.PLAIN, 24));
		label_credit.setBounds(40, 430, 300, 30);
		Credits.add(label_credit);
		
		JLabel label_credit1 = new JLabel("Program created and designed by: Sheena Cheng.");
		label_credit1.setFont(new Font("Arial", Font.PLAIN, 15));
		label_credit1.setBounds(40, 465, 500, 20);
		Credits.add(label_credit1);
		
		JLabel label_credit2 = new JLabel("Copyright \u00A9 2018, Sheena (Xinyue) Cheng. All rights reserved.");
		label_credit2.setFont(new Font("Arial", Font.PLAIN, 15));
		label_credit2.setBounds(40, 490, 500, 20);
		Credits.add(label_credit2);
		
		JLabel label_version = new JLabel("Program Version: 1.0");
		label_version.setHorizontalAlignment(SwingConstants.TRAILING);
		label_version.setFont(new Font("Arial", Font.PLAIN, 15));
		label_version.setBounds(610, 500, 150, 20);
		Credits.add(label_version);
		
		// -------------------------------------------
		
		// THE IMPORTANT BUTTON THAT GENERATES THE SCHEDULE
		// -------------------------------------------
		btnGenerateSchedule.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Update the Event Name
				magicMachine.eventName = txt_eventName.getText();
				EventName.setText("Event Name: " + magicMachine.eventName);
				
				// Check if target groups are distinct!
				String targetGroup1 = (String) cbox_target1.getSelectedItem();
				String targetGroup2 = (String) cbox_target2.getSelectedItem();
				String targetGroup3 = (String) cbox_target3.getSelectedItem();
				
				// Generate the schedule
				if (dateChooser.getDate() == null)
				{
					label_help.setForeground(new Color(255, 58, 58));
					label_help.setText("Please enter a date!");
				}
				else if (magicMachine.list.all.size() < 2)
				{
					label_help.setForeground(new Color(255, 58, 58));
					label_help.setText("Please add at least 3 groups!");
				}
				else if (targetGroup1.equalsIgnoreCase(targetGroup2) ||
						targetGroup1.equalsIgnoreCase(targetGroup3) ||
						targetGroup2.equalsIgnoreCase(targetGroup3))
				{
					label_help.setForeground(new Color(255, 58, 58));
					label_help.setText("Target groups must be distinct!");
				}
				else
				{		
					// Report no errors
					label_help.setText("Schedule generated successfully!");
					label_help.setForeground(new Color(44, 158, 42));
					
					// Clear the table and machine's old Data
					scheduleTableModel.setRowCount(0);
					
					//magicMachine.list.deleteAll(); OLD
					
					magicMachine.totalSlots = 0;
					magicMachine.totalToShare = 0;
					magicMachine.actualShares = 0;
					
					// Update the Event Date
					magicMachine.eventDate = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					EventDate.setText("Event Date: " + magicMachine.eventDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, YYYY")));
					
					// If there are presets used
					if (magicMachine.useBizTech2019_2020Preset)
					{
						// RESET SHARE TIMES!
						for (int i = 0; i < magicMachine.list.all.size(); i++)
						{
							Group currentGroup = magicMachine.list.all.get(i);
							currentGroup.resetShareTimes();
						}
						//label_help.setText("A PRESET IS BEING USED!");
					}
					else
					{
						//label_help.setText("No Preset");
						magicMachine.buildGroup();
					}
					
					// Update the Target Groups
					magicMachine.resetTargetGroups();
					
					magicMachine.setTargetGroup(targetGroup1);
					magicMachine.setTargetGroup(targetGroup2);
					magicMachine.setTargetGroup(targetGroup3);
					
					//magicMachine.list.all.clear(); OLD
					//magicMachine.list.compile(); OLD
					
					// Magic Machine stuff
					magicMachine.generateWeek();
					
					// Update the group's table
					groupsTableModel.setRowCount(0);
					
					for (int i = 0; i < magicMachine.list.all.size(); i++)
					{
						String rowContent[] = magicMachine.getGroupData(i);
						groupsTableModel.addRow(rowContent);
					}
					
					// Continue Magic Machine Stuff
					magicMachine.compileStatsBefore();
					magicMachine.buildSchedule();
					magicMachine.compileStatsAfter();
					
					// Fill the table with new Data
					String[] Dates = magicMachine.getDates();
					String[] DayofWeek = magicMachine.getDayofWeek();
					
					String[] G1 = magicMachine.getScheduleRow(0);
					String[] G2 = magicMachine.getScheduleRow(1);
					String[] G3 = magicMachine.getScheduleRow(2);
					String[] Filler = {};
					
					scheduleTableModel.addRow(Dates);
					scheduleTableModel.addRow(DayofWeek);
					
					scheduleTableModel.addRow(G1);
					scheduleTableModel.addRow(G2);
					scheduleTableModel.addRow(G3);
					scheduleTableModel.addRow(Filler);
					scheduleTableModel.addRow(Filler);
					scheduleTableModel.addRow(Filler);
					
					// Update the Schedule Statistics
					Stat1.setText("Total number of times to be shared: " + magicMachine.totalToShare);
					Stat2.setText("Total number of ACTUAL shares: " + magicMachine.actualShares);
					Stat3.setText("Total number of slots this week: " + magicMachine.totalSlots);
					
				}
				
			}
		}); 
		
		// -------------------------------------------
	}
} 

