package ru.sbt.toybank;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		BackEnd back = new BackEnd();
		FrontEnd front = new FrontEnd();

		Client client1 = new Client(front, "Client1");
		Client client2 = new Client(front, "Client2");
		Client client3 = new Client(front, "Client3");
		Client client4 = new Client(front, "Client4");

		client1.start();
		client2.start();
		client3.start();
		client4.start();

		Handler handler1 = new Handler("Handler1", back, front);
		Handler handler2 = new Handler("Handler2", back, front);

		handler1.start();
		handler2.start();

		Thread.sleep(3000);

		handler1.interrupt();
		handler2.interrupt();
	}
}
