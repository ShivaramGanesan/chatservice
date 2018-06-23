import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.*;  

public class HelperClass {

	public static boolean isValidEmail(String email) {
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(email);
		if(m.find())return true;
		else return false;
		
	}
	public static boolean isUser(String name){
		File f = new File("D:/database/users.txt");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line;
		try {
			while((line = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line, ":");
				String s="";
				if(st.hasMoreTokens()){
					s = st.nextToken();
				}
				if(s.equals(name)){
					return true;//user exists
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;//user does not exists
	}
	
	public static boolean isCommunity(String communityName){
		File f = new File("D:/database/communities.txt");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
		String line;
		try {
			while((line = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line, "\n");
				String s="";
				if(st.hasMoreTokens()){
					s = st.nextToken();
				}
				if(s.equals(communityName)){
					return true;//grp exists
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;//grp does not exists
	}
	
	public static boolean amImember(String communityName){
		File f = new File("D:/database/"+CurrentUser.name+".txt");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
		String line;
		try {
			while((line = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line, "\n");
				String s="";
				if(st.hasMoreTokens()){
					s = st.nextToken();
				}
				if(s.equals(communityName)){
					return true;//user is member of grp
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;//user is not a member of grp
	}
}
