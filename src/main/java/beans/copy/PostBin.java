package beans.copy;

public class PostBin {
	private int postid;
	private String theme;
	private String text;
	private String user;
	private String tags;
	
	public int getId() {
		return postid;
	}
	public void setId(int postid) {
		this.postid = postid;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
	        return "Post [postid=" + postid + ", text=" + text
	                + ", user=" + user + ", tags=" + tags + "]";
	    }    
	
}
