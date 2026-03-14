package no.hvl.dat110.broker.multiDispatcherExt;

import no.hvl.dat110.broker.ClientSession;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.DisconnectMsg;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messagetransport.Connection;

import java.util.function.Consumer;

import static no.hvl.dat110.messages.MessageUtils.receive;

public class ClientDispatcher extends Dispatcher {
	  private Consumer<String> removeOp;
	  private ClientSession client;

	  public ClientDispatcher(Storage storage, Consumer<String> removeOp) {
			super(storage);
			this.name = null;
			this.removeOp = removeOp;
	  }

	  @Override
	  public void doProcess() {
			if (this.client == null) {
				  throw new ThatWasNotAllowedException("that was not allowed: attempting to start " + this.getClass() + "-thread with no corresponding connected client");
			}
			Message msg = this.client.receive();
			this.dispatch(client, msg);
	  }

	  @Override
	  public void onConnect(ConnectMsg cmsg, Connection connection) {
			super.onConnect(cmsg, connection);
			this.name = cmsg.getUser();
			this.client = this.getStorage().getSession(this.name);
			this.start();
	  }

	  @Override
	  public void onDisconnect(DisconnectMsg dmsg) {
			super.onDisconnect(dmsg);
			removeOp.accept(this.name);
			this.client = null;
			this.doStop();
	  }


}
