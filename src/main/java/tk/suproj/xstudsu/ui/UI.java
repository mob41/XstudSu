package tk.suproj.xstudsu.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import tk.suproj.xstudsu.SU;

import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class UI {

	public static JTextArea logs;
	public static JCheckBoxMenuItem chckbxmntmUnpatchWhenClose;
	private JFrame frame;
	private Thread t;
	static File[] oldListRoot = File.listRoots();
	private JToggleButton tglbtnPatch;
	private JMenuItem mntmPatch;
	private JMenuItem mntmUnpatch;
	private DetectDevice r;
	private JLabel lblStatus;
	private JLabel lblXstudentVer;
	private JCheckBoxMenuItem chckbxmntmTransparentIcon;
	private JCheckBoxMenuItem chckbxmntmPatchOnDevice;

	/**
	 * Launch the application.
	 */
	public static void main() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
				            UIManager.getCrossPlatformLookAndFeelClassName());
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/icn/su.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (SU.isPatched()){
					if (chckbxmntmUnpatchWhenClose.isSelected()){
						SU.start();
					}
				}
			}
		});
		frame.setTitle("XstudSu");
		frame.setBounds(100, 100, 424, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mntmPatch = new JMenuItem("Patch");
		tglbtnPatch = new JToggleButton("Patch");
		tglbtnPatch.setEnabled(false);
		chckbxmntmUnpatchWhenClose = new JCheckBoxMenuItem("Unpatch when close");
		mntmUnpatch = new JMenuItem("Unpatch");
		chckbxmntmUnpatchWhenClose.setSelected(true);
		JScrollPane scrollPane = new JScrollPane();
		mntmPatch.setEnabled(false);
		mntmUnpatch.setEnabled(false);
		JLabel lblXstudsu = new JLabel("XstudSu 2.2.0-BETA");
		lblXstudsu.setHorizontalAlignment(SwingConstants.CENTER);
		lblXstudsu.setFont(new Font("Tahoma", Font.PLAIN, 29));
		mntmUnpatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SU.start()){
					tglbtnPatch.setSelected(false);
					tglbtnPatch.setText("Patch");
					mntmPatch.setEnabled(true);
					mntmUnpatch.setEnabled(false);
				}
			}
		});
		mntmPatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SU.stop()){
					tglbtnPatch.setSelected(true);
					tglbtnPatch.setText("Unpatch");
					mntmPatch.setEnabled(false);
					mntmUnpatch.setEnabled(true);
				}
			}
		});
		
		lblXstudentVer = new JLabel("Xstudent Ver.: Couldn't be detected.");
		
		lblStatus = new JLabel("Status: Unknown");
		

		tglbtnPatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if (SU.isPatched()){
					if (SU.start()){
						tglbtnPatch.setSelected(false);
						tglbtnPatch.setText("Patch");
						mntmPatch.setEnabled(true);
						mntmUnpatch.setEnabled(false);
					}
					else
					{
						tglbtnPatch.setSelected(true);
						tglbtnPatch.setText("Unpatch");
						mntmPatch.setEnabled(false);
						mntmUnpatch.setEnabled(true);
					}
				}
				else
				{
					if (SU.stop()){
						tglbtnPatch.setSelected(true);
						tglbtnPatch.setText("Unpatch");
						mntmPatch.setEnabled(false);
						mntmUnpatch.setEnabled(true);
					}
					else
					{
						tglbtnPatch.setSelected(false);
						tglbtnPatch.setText("Patch");
						mntmPatch.setEnabled(true);
						mntmUnpatch.setEnabled(false);
					}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblXstudsu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnPatch, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblXstudentVer, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblXstudsu)
					.addGap(29)
					.addComponent(tglbtnPatch, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblXstudentVer)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		
		logs = new JTextArea();
		logs.setWrapStyleWord(true);
		logs.setEditable(false);
		scrollPane.setViewportView(logs);
		logs.append("NOTE: XstudSU started. Ready for SU Maintenances.\n");
		tglbtnPatch.setEnabled(false);
		tglbtnPatch.setSelected(false);
		mntmPatch.setEnabled(false);
		mntmUnpatch.setEnabled(false);
		switch(SU.checkInstall()){
		case 0:
			logs.append("WARN: XstudSU couldn't detect any Xstudent installed.\n");
			chckbxmntmUnpatchWhenClose.setSelected(false);
			chckbxmntmUnpatchWhenClose.setEnabled(false);
			tglbtnPatch.setEnabled(false);
			lblStatus.setText("Status: Xstudent is not installed.");
			break;
		case 1:
			logs.append("NOTE: Xstudent 32-bit installed.\n");
			lblXstudentVer.setText("Xstudent Ver.: 32-bit");
			lblStatus.setText("Status: Ready for patching.");
			tglbtnPatch.setSelected(false);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Patch");
			mntmPatch.setEnabled(true);
			break;
		case 2:
			logs.append("NOTE: Xstudent 64-bit installed.\n");
			lblXstudentVer.setText("Xstudent Ver.: 64-bit");
			lblStatus.setText("Status: Ready for patching.");
			tglbtnPatch.setSelected(false);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Patch");
			mntmPatch.setEnabled(true);
			break;
		case 3:
			logs.append("NOTE: Xstudent 32-bit installed.\n");
			logs.append("WARN: XstudSU detected that Xstudent was patched.\n");
			lblStatus.setText("Status: Xstudent was patched and stopped.");
			tglbtnPatch.setSelected(true);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Unpatch");
			mntmUnpatch.setEnabled(true);
			break;
		case 4:
			logs.append("NOTE: Xstudent 64-bit installed.\n");
			logs.append("WARN: XstudSU detected that Xstudent was patched.\n");
			lblStatus.setText("Status: Xstudent was patched and stopped.");
			tglbtnPatch.setSelected(true);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Unpatch");
			mntmUnpatch.setEnabled(true);
			break;
		}
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);
		


		mnSystem.add(mntmPatch);
		
		
		mnSystem.add(mntmUnpatch);
		
		JMenuItem mntmUpdatePatchInfo = new JMenuItem("Update Patch Info");
		mntmUpdatePatchInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatePatchInfo();
			}
		});
		mnSystem.add(mntmUpdatePatchInfo);
		


		mnSystem.add(chckbxmntmUnpatchWhenClose);
		
		JMenu mnSpecial = new JMenu("Special");
		menuBar.add(mnSpecial);
		
		chckbxmntmTransparentIcon = new JCheckBoxMenuItem("Transparent Icon");
		chckbxmntmTransparentIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxmntmTransparentIcon.isSelected()){
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/icn/transparent.png")));
				}
				else
				{
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/icn/su.png")));
				}
			}
		});
		mnSpecial.add(chckbxmntmTransparentIcon);
		
		chckbxmntmPatchOnDevice = new JCheckBoxMenuItem("Patch on device insert");
		chckbxmntmPatchOnDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxmntmPatchOnDevice.isSelected()){
					waitforDevice();
				} else {
					r.requestKill();
					t.interrupt();
				}
			}
		});
		mnSpecial.add(chckbxmntmPatchOnDevice);
	}
	
	private void updatePatchInfo(){
		tglbtnPatch.setEnabled(false);
		tglbtnPatch.setSelected(false);
		mntmPatch.setEnabled(false);
		mntmUnpatch.setEnabled(false);
		switch(SU.checkInstall()){
		case 0:
			logs.append("WARN: XstudSU couldn't detect any Xstudent installed.\n");
			chckbxmntmUnpatchWhenClose.setSelected(false);
			chckbxmntmUnpatchWhenClose.setEnabled(false);
			tglbtnPatch.setEnabled(false);
			lblStatus.setText("Status: Xstudent is not installed.");
			break;
		case 1:
			logs.append("NOTE: Xstudent 32-bit installed.\n");
			lblXstudentVer.setText("Xstudent Ver.: 32-bit");
			lblStatus.setText("Status: Ready for patching.");
			tglbtnPatch.setSelected(false);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Patch");
			mntmPatch.setEnabled(true);
			mntmUnpatch.setEnabled(false);
			break;
		case 2:
			logs.append("NOTE: Xstudent 64-bit installed.\n");
			lblXstudentVer.setText("Xstudent Ver.: 64-bit");
			lblStatus.setText("Status: Ready for patching.");
			tglbtnPatch.setSelected(false);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Patch");
			mntmPatch.setEnabled(true);
			mntmUnpatch.setEnabled(false);
			break;
		case 3:
			logs.append("NOTE: Xstudent 32-bit installed.\n");
			logs.append("WARN: XstudSU detected that Xstudent was patched.\n");
			lblStatus.setText("Status: Xstudent was patched and stopped.");
			tglbtnPatch.setSelected(true);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Unpatch");
			mntmPatch.setEnabled(false);
			mntmUnpatch.setEnabled(true);
			break;
		case 4:
			logs.append("NOTE: Xstudent 64-bit installed.\n");
			logs.append("WARN: XstudSU detected that Xstudent was patched.\n");
			lblStatus.setText("Status: Xstudent was patched and stopped.");
			tglbtnPatch.setSelected(true);
			tglbtnPatch.setEnabled(true);
			tglbtnPatch.setText("Unpatch");
			mntmPatch.setEnabled(false);
			mntmUnpatch.setEnabled(true);
			break;
		}
	}
	
	private void waitforDevice(){
		r = new DetectDevice();
		t = new Thread(r);
	    t.start();
	}
		
	class DetectDevice implements Runnable{
		public boolean isRunning = false;
		
		public void requestKill(){
			isRunning = false;
		}
		
        public void run() {
        	if (!isRunning){
        		logs.append("NOTE: Waiting for devices now...\n");
        		isRunning = true;
	            while (isRunning) {
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException ignore) {}
	                if (File.listRoots().length > oldListRoot.length) {
	                	logs.append("NOTE: New device detected: " + oldListRoot[oldListRoot.length-1] + "\n");
	                	logs.append("NOTE: Patching..." + "\n");
	                	updatePatchInfo();
	                	if (SU.checkInstall() == 0){
	                		logs.append("NOTE: Couldn't find Xstudent. Giving up.\n");
	                	} else {
	                		if (!SU.isPatched()){
		                		if (SU.start()){
									tglbtnPatch.setSelected(false);
									tglbtnPatch.setText("Patch");
									mntmPatch.setEnabled(true);
									mntmUnpatch.setEnabled(false);
								}
								else
								{
									tglbtnPatch.setSelected(true);
									tglbtnPatch.setText("Unpatch");
									mntmPatch.setEnabled(false);
									mntmUnpatch.setEnabled(true);
								}
		                	} else {
		                		logs.append("NOTE: Xstudent is already patched. Giving up.\n");
		                	}
	                	}
	                    System.out.println("New drive detected");
	                    oldListRoot = File.listRoots();
	                    System.out.println("Drive ("+oldListRoot[oldListRoot.length-1]+") detected");

	                } else if (File.listRoots().length < oldListRoot.length) {
	    System.out.println(oldListRoot[oldListRoot.length-1]+" drive removed");
	                    oldListRoot = File.listRoots();
	                    logs.append("NOTE: Device removed: " + oldListRoot[oldListRoot.length-1] + "\n");
	                	logs.append("NOTE: Unpatching...\n");
	                	updatePatchInfo();
	                	if (SU.checkInstall() == 0){
	                		logs.append("NOTE: Couldn't find Xstudent. Giving up.\n");
	                	} else {
	                		if (SU.isPatched()){
		                		if (SU.stop()){
									tglbtnPatch.setSelected(true);
									tglbtnPatch.setText("Unpatch");
									mntmPatch.setEnabled(false);
									mntmUnpatch.setEnabled(true);
								}
								else
								{
									tglbtnPatch.setSelected(false);
									tglbtnPatch.setText("Patch");
									mntmPatch.setEnabled(true);
									mntmUnpatch.setEnabled(false);
								}
		                	} else {
		                		logs.append("NOTE: Xstudent isn't patched. Giving up.\n");
		                	}
	                	}

	                }

	            }
	            logs.append("NOTE: Stopped to wait for device.\n");
        	}
        }
	}
}
