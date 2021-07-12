package ru.sbt.toybank;

import java.util.concurrent.CountDownLatch;

public class Warmer implements Runnable {
	long money;
	long timeout;
	String name;
	BackEnd back;
	CountDownLatch cdl;

	Warmer(String name, BackEnd back, CountDownLatch cdl) {
		this.name = name;
		this.back = back;
		this.cdl = cdl;

		money = (long)(Math.random() * 100000);
		timeout = 5000 + (long)(Math.random() * 5000); //5-10 сек
	}

	@Override
	public void run() {
		try {
			Thread.sleep(timeout);
			back.warmup(money);
			cdl.countDown();
			System.out.printf("%s[%f s]:в банк добавлено %d\n", name, timeout / 1000.0, money);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
