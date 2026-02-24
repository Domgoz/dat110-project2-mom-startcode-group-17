package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {

    private String user;
    private String topic;

    public CreateTopicMsg(String user, String topic) {
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
        if (topic == null || topic.isBlank()) {
            throw new IllegalArgumentException("topic is required");
        }
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "CreateTopicMsg[user=" + user + ", topic=" + topic + "]";
    }
}