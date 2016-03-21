package tk.suproj.xstudsu;

import java.io.File;
import java.io.IOException;

import tk.suproj.xstudsu.ui.UI;

public class SU {
	
	public static boolean ver32_64 = false;
	public static boolean patched = false;
	private static final String path32 = "C:\\Program Files (x86)\\Sun-Tech\\XCLASS EVO\\student";
	private static final String path64 = "C:\\Program Files\\Sun-Tech\\XCLASS EVO\\student";
	private static String path;
	
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
	
	public static boolean start(){
		UI.logs.append("NOTE: Starting xstudent...\n");
		if(ver32_64){
			path = path32;
		}
		else
		{
			path = path64;
		}
		
		System.out.println("Location: " + path);
		
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static boolean stop(){
		UI.logs.append("NOTE: Stopping xstudent...\n");
		if(ver32_64){
			path = path32;
		}
		else
		{
			path = path64;
		}
		System.out.println("Location: " + path);
		File file = new File(path + "\\xstudent.exe");
		if(!file.exists()){
			UI.logs.append("ERR: Could not find Xstudent! Can't stop it.\n");
			patched = false;
			return false;
		}
		try {
			Runtime.getRuntime().exec("cmd /C taskkill /IM xstudent.exe");
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
