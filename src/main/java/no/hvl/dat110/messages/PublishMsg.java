package no.hvl.dat110.messages;

public class PublishMsg extends Message {
	
    private String user;
    private String topic;
	private String message;

	public PublishMsg(String user, String topic, String message) {
		setUser(user);
		setTopic(topic);
		setMessage(message);
	}
	public String getUser() {
        return user;
    }
	
	public void setUser(String user) {
        this.user = user;
    }

	public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

	public String getMessage() {
        return message;
    }
	
	public void setMessage(String message) {
        this.message = message;
    }
	
    @Override
    public String toString() {
        return "PublishMsg[user=" + user + ", topic=" + topic + ", message=" + message + "]";
    }
}
