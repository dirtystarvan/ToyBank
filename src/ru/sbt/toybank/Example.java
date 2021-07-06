package ru.sbt.toybank;

public class Example {
	public static void main(String[] args) {
		Market market = new Market();
		Producer producer = new Producer(market);
		Consumer consumer = new Consumer(market);

		Thread th1 = new Thread(producer);
		Thread th2 = new Thread(consumer);
		th1.start();
		th2.start();
	}
}

class Market {
	private int breadCount = 0;

	public synchronized void getBread() {
		while (breadCount < 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		breadCount--;
		System.out.println("Потребитель купил хлеб");
		System.out.println("Количество хлеба в магазине:" + breadCount);
		notify();
	}

	public synchronized void putBread() {
		while (breadCount >= 5) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			breadCount++;

			System.out.println("Производитель добавил на витрину 1 хлеб");
			System.out.println("Количество хлеба в магазине:" + breadCount);
		}
	}
}

class Producer implements Runnable {
	Market market;

	Producer(Market market) {
		this.market = market;
	}

	public  void run() {
		for (int i = 0; i < 10; i++)
			market.putBread();
	}
}

class Consumer implements Runnable {
	Market market;

	Consumer(Market market) {
		this.market = market;
	}

	public  void run() {
		for (int i = 0; i < 10; i++)
			market.getBread();
	}
}
