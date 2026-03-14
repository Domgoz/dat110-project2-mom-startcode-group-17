package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from a topic to set of subscribed users
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;

	//for storing pending messages to disconnected user
	protected ConcurrentHashMap<String, ConcurrentLinkedQueue<PublishMsg>> buffer;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		buffer = new ConcurrentHashMap<String, ConcurrentLinkedQueue<PublishMsg>>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();
	}

	public ClientSession getSession(String user) {

		// get the session object for a given user
		// session object can be used to send a message to the user

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// See ClientSession class
		
		ClientSession client = new ClientSession(user, connection);
		clients.put(user, client);
		

	}

	public void removeClientSession(String user) {

		// and remove client session for user from the storage
		
		clients.remove(user);
		
	}

	public void createTopic(String topic) {

	subscriptions.putIfAbsent(topic, ConcurrentHashMap.newKeySet());

	}

	public void deleteTopic(String topic) {

		subscriptions.remove(topic);		
	}

	public void addSubscriber(String user, String topic) {
		
		Set<String> subs = subscriptions.get(topic);
		if (subs == null) {
			subs = ConcurrentHashMap.newKeySet();
			subscriptions.put(topic, subs);
		}
		subs.add(user);

	}

	public void removeSubscriber(String user, String topic) {

		Set<String> subsC = getSubscribers(topic);
		subscriptions.remove(user, subsC);
		subsC.remove(user);

	}

	public void buffer(String user, PublishMsg pmsg) {

		  // add pending message for disconnected subscriber

		  if (buffer.get(user) == null) {
				buffer.put(user, new ConcurrentLinkedQueue<PublishMsg>());
		  }
		  buffer.get(user).add(pmsg);
	}

	public ConcurrentLinkedQueue<PublishMsg> getBuffered(String user) {
		  ConcurrentLinkedQueue<PublishMsg> buffered = buffer.get(user);
		  if (buffered == null || buffered.isEmpty()) {
				return null;
		  }
		  return buffered;
		  }
	}

