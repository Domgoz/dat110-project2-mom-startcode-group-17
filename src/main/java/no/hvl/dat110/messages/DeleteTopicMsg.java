package no.hvl.dat110.messages;

public class DeleteTopicMsg extends Message {

    private String topic;

    public DeleteTopicMsg(String user, String topic) {
        super(MessageType.DELETETOPIC, user);
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
        return "Deleting Msg[user=" + super.toString() + ", topic=" + topic + "]";
    }

}
