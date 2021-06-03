package Models;

public class User {
	String UserId;
	String Name;
	String Email;
	String Password;
	
	public User() {
		super();
	}
	public User(String userId, String name, String email, String password) {
		super();
		UserId = userId;
		Name = name;
		Email = email;
		Password = password;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", Name=" + Name + ", Email=" + Email + ", Password=" + Password + "]";
	}
	
}
