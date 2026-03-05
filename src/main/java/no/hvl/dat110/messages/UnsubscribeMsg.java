package no.hvl.dat110.messages;

public class UnsubscribeMsg extends Message {

    private String topic;

    public UnsubscribeMsg(String user, String topic) {
        super(MessageType.UNSUBSCRIBE, user);
        setTopic(topic);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Unsubscribing[user=" + getUser() + ", topic=" + topic + "]";
    }
	
}
