package ru.sbt.toybank;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		BackEnd back = new BackEnd();
		FrontEnd front = new FrontEnd();

		CountDownLatch cdl = new CountDownLatch(3);

		ExecutorService warmers = Executors.newFixedThreadPool(3);

		warmers.execute(new Warmer("Warmer1", back, cdl));
		warmers.execute(new Warmer("Warmer2", back, cdl));
		warmers.execute(new Warmer("Warmer3", back, cdl));

		warmers.shutdown();

		cdl.await();

		ExecutorService clients = Executors.newFixedThreadPool(4);
		clients.execute(new Client(front, "Client1"));
		clients.execute(new Client(front, "Client2"));
		clients.execute(new Client(front, "Client3"));
		clients.execute(new Client(front, "Client4"));


		ExecutorService handlers = Executors.newFixedThreadPool(2);

		handlers.execute(new Handler("Handler1", back, front));
		handlers.execute(new Handler("Handler2", back, front));


		Thread.sleep(3000);

		clients.shutdownNow();
		handlers.shutdownNow();
	}
}
