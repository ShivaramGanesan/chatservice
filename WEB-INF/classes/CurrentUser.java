

public class CurrentUser {
	public static String name;
	public static String email;
	String password;
	
	public CurrentUser() {
	
	}
	public CurrentUser(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}public void setEmail(String email) {
		this.email = email;
	}public String getPassword() {
		return password;
	}public String getEmail() {
		return email;
	}public String getName() {
		return name;
	}
}
