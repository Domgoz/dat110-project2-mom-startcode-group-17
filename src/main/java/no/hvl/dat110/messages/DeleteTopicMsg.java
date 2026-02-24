package no.hvl.dat110.messages;

public class DeleteTopicMsg extends Message {

    private String user;
    private String topic;

    public DeleteTopicMsg(String user, String topic) {
		setUser(user);
		setTopic(topic);
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

    @Override
    public String toString() {
        return "Deleting Msg[user=" + user + ", topic=" + topic + "]";
    }

}
