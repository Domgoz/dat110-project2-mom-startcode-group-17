package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODONE - start
		// create a client object and use it to
		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		// - connect to the broker - user "sensor" as the user name
		  client.connect();
		// - publish the temperature(s)
		  TemperatureSensor ts = new TemperatureSensor();
		  for (int p = 0; p < COUNT; p++) {
				int temp = ts.read();
				client.publish(Common.TEMPTOPIC, String.valueOf(temp));
				try {
					  Thread.sleep(500);
				} catch (InterruptedException ignored) {}
		  }
		// - disconnect from the broker
		client.disconnect();
		// TODONE - end

		System.out.println("Temperature device stopping ... ");

		//throw new UnsupportedOperationException(TODO.method());

	}
}
