package no.hvl.dat110.broker;

import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messagetransport.Connection;

public interface DispatcherComponent {

	  public void onConnect(ConnectMsg cmsg, Connection connection);

	  public void start();

	  public void doStop();

	  public void join() throws InterruptedException;

}
