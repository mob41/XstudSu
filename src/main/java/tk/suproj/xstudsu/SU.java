package tk.suproj.xstudsu;

import java.io.File;
import java.io.IOException;

import tk.suproj.xstudsu.ui.UI;

public class SU {
	
	private static boolean ver32_64 = false;
	private static boolean patched = false;
	private static final String path32 = "C:\\Program Files (x86)\\Sun-Tech\\XCLASS EVO\\student";
	private static final String path64 = "C:\\Program Files\\Sun-Tech\\XCLASS EVO\\student";
	private static String path;
	
	public static boolean isPatched(){
		switch (checkInstall()){
		case 3:
		case 4:
			patched = true;
			return true;
		default:
			patched = false;
			return false;
		}
	}
	
	/***
	 * Get the version of XCLASS EVO is 32 or 64 bit.<br>
	 * Return integer 32 if 32bit, 64 if 64bit.
	 * @return 32 / 64 bit
	 */
	public static int getVer32_64bit(){
		switch (checkInstall()) {
		case 1:
		case 3:
			return 32;
		case 2:
		case 4:
			return 64;
		default:
			return -1;
		}
	}
	
	/***
	 * Check installation of XCLASS EVO<br>
	 * Return <b>1</b> if NOT patched and 32 bit.<br>
	 * Return <b>2</b> if NOT patched and 64 bit.<br>
	 * Return <b>3</b> if PATCHED and 32 bit.<br>
	 * Return <b>4</b> if PATCHED and 64 bit.<br>
	 * Return <b>0</b> if XCLASS EVO could not be found.
	 * @return a integer indicating the installation status of XCLASS
	 */
	public static int checkInstall(){
		File file = new File(path32 + "\\xstudent.exe");
		if(file.exists()){
			ver32_64 = true;
			patched = false;
			return 1;
		}
		file = new File(path64 + "\\xstudent.exe");
		if(file.exists()){
			ver32_64 = false;
			patched = false;
			return 2;
		}
		file = new File(path32 + "\\xstudent-bak.exe");
		if(file.exists()){
			ver32_64 = true;
			patched = true;
			return 3;
		}
		file = new File(path64 + "\\xstudent-bak.exe");
		if(file.exists()){
			ver32_64 = false;
			patched = true;
			return 4;
		}
		return 0;
	}
	
	/***
	 * Unpatch / start the XCLASS EVO application (xstudent.exe)
	 * @return whether the application started successfully via XstudSu
	 */
	public static boolean start(){
		UI.logs.append("NOTE: Starting xstudent...\n");
		switch (getVer32_64bit()){
		case 32:
			path = path32;
			break;
		case 64:
			path = path64;
			break;
		default:
			UI.logs.append("ERR: XCLASS could not be found.\n");
			return false;
		}
		File file = new File(path + "\\xstudent-bak.exe");
		if(!file.exists()){
			UI.logs.append("ERR: Could not find Xstudent! Can't start it.\n");
			patched = false;
			return false;
		}
		try {
			Runtime.getRuntime().exec("cmd /C rename \"" + path + "\\xstudent-bak.exe\" xstudent.exe");
			Runtime.getRuntime().exec("cmd /C \""+ path + "\\xstudent.exe\"");
		} catch (IOException e) {
			UI.logs.append("ERR: Error occurred when starting xstudent.\n");
			e.printStackTrace();
			patched = false;
			return false;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignore){}
		file = new File(path + "\\xstudent.exe");
		if(!file.exists()){
			UI.logs.append("ERR: Could not start Xstudent.\n");
			patched = false;
			return false;
		}
		UI.logs.append("NOTE: Xstudent is started.\n");
		patched = false;
		return true;
	}
	
	/***
	 * Patch / stop the XCLASS EVO application (xstudent.exe)
	 * @return whether the application stopped successfully via XstudSu.
	 */
	public static boolean stop(){
		UI.logs.append("NOTE: Stopping xstudent...\n");
		switch (getVer32_64bit()){
		case 32:
			path = path32;
			break;
		case 64:
			path = path64;
			break;
		default:
			UI.logs.append("ERR: XCLASS could not be found.\n");
			return false;
		}
		File file = new File(path + "\\xstudent.exe");
		if(!file.exists()){
			UI.logs.append("ERR: Could not find Xstudent! Can't stop it.\n");
			patched = false;
			return false;
		}
		try {
			//Force stop it for 10 times
			for (int i = 0; i < 10; i++){
				Runtime.getRuntime().exec("cmd /C taskkill /IM xstudent.exe");
				Thread.sleep(500);
			}
			Runtime.getRuntime().exec("cmd /C rename \"" + path + "\\xstudent.exe\" xstudent-bak.exe");
			System.out.println("cmd /C rename \"" + path + "\\xstudent.exe\" xstudent-bak.exe");
		} catch (IOException e) {
			UI.logs.append("ERR: Error occurred when stopping xstudent.\n");
			e.printStackTrace();
			patched = false;
			return false;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ignore){}
		file = new File(path + "\\xstudent-bak.exe");
		if(!file.exists()){
			UI.logs.append("ERR: Could not stop Xstudent. Access denied?\n");
			patched = false;
			return false;
		}
		UI.logs.append("NOTE: Xstudent is stopped now.\n");
		patched = true;
		return true;
	}
}
