package no.hvl.dat110.broker.multiDispatcherExt;

import no.hvl.dat110.broker.DispatcherComponent;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.DisconnectMsg;
import no.hvl.dat110.messagetransport.Connection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class MultiDispatcher implements DispatcherComponent {
	  private Storage storage;
	  private ConcurrentHashMap<String, ClientDispatcher> pool;

	  public MultiDispatcher(Storage storage) {
			this.storage = storage;
			this.pool = new ConcurrentHashMap<String, ClientDispatcher>();
	  }

	  @Override
	  public void onConnect(ConnectMsg cmsg, Connection connection) {
			ClientDispatcher cd = new ClientDispatcher(storage, this::removeThread);
			this.pool.put(cmsg.getUser(), cd);
			cd.onConnect(cmsg, connection);
	  }

	  private void removeThread(String name) {
			if (name == null || this.pool.get(name) == null) {
				  return;
			}
			this.pool.remove(name, this.pool.get(name));
	  }

	  @Override
	  public void start() {}

	  @Override
	  public void doStop() {
			for (ClientDispatcher cd : this.pool.values()) {
				  cd.doStop();
			}
	  }

	  @Override
	  public void join() throws InterruptedException {
			for (ClientDispatcher cd : this.pool.values()) {
				  cd.join();
			}
	  }
}










