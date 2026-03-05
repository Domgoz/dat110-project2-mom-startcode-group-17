package no.hvl.dat110.messages;

public class SubscribeMsg extends Message {

    private String topic;

    public SubscribeMsg(String user, String topic) {
        super(MessageType.SUBSCRIBE, user);
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
        return "Subscribing[user=" + getUser() + ", topic=" + topic + "]";
    }
		
}
