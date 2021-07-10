package ru.sbt.toybank;

//1.	Избавиться от методов wait() и notifyAll().
//2.	На фронте использовать очередь из concurrent. Очередь выбрать самостоятельно. В комментариях в коде обосновать выбор.
//3.	Для создания нескольких потоков использовать пулы.
//4.	Где необходимо применить atomic?
//5.	Усложнить функционал приложения. Добавить «прогрев». Считаем, что баланс банка складывается на основе данных из 3 различных систем. Каждой системе требуется различное время для выдачи информации (5-10 секунд). Необходимо имитировать работу каждой из систем (сделать максимально простую реализацию класса, в котором есть метод, возвращающий произвольное число через определенный таймаут). Далее, при запуске программы требуется сначала в параллельных потоках получить и посчитаться баланс банка и только после этого запускать потоки клиентов и обработчики заявок.


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		BackEnd back = new BackEnd();
		FrontEnd front = new FrontEnd();

//		Client client1 = new Client(front, "Client1");
//		Client client2 = new Client(front, "Client2");
//		Client client3 = new Client(front, "Client3");
//		Client client4 = new Client(front, "Client4");

		ExecutorService clients = Executors.newFixedThreadPool(4);
		clients.execute(new Client(front, "Client1"));
		clients.execute(new Client(front, "Client2"));
		clients.execute(new Client(front, "Client3"));
		clients.execute(new Client(front, "Client4"));

//		client1.start();
//		client2.start();
//		client3.start();
//		client4.start();

		ExecutorService handlers = Executors.newFixedThreadPool(2);

		handlers.execute(new Handler("Handler1", back, front));
		handlers.execute(new Handler("Handler2", back, front));

//		Handler handler1 = new Handler("Handler1", back, front);
//		Handler handler2 = new Handler("Handler2", back, front);

//		handler1.start();
//		handler2.start();

		Thread.sleep(3000);

//		handler1.interrupt();
//		handler2.interrupt();

		clients.shutdownNow();
		handlers.shutdownNow();
	}
}
