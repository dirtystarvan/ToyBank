package ru.sbt.toybank;

//1.	���������� �� ������� wait() � notifyAll().
//2.	�� ������ ������������ ������� �� concurrent. ������� ������� ��������������. � ������������ � ���� ���������� �����.
//3.	��� �������� ���������� ������� ������������ ����.
//4.	��� ���������� ��������� atomic?
//5.	��������� ���������� ����������. �������� ��������. �������, ��� ������ ����� ������������ �� ������ ������ �� 3 ��������� ������. ������ ������� ��������� ��������� ����� ��� ������ ���������� (5-10 ������). ���������� ����������� ������ ������ �� ������ (������� ����������� ������� ���������� ������, � ������� ���� �����, ������������ ������������ ����� ����� ������������ �������). �����, ��� ������� ��������� ��������� ������� � ������������ ������� �������� � ����������� ������ ����� � ������ ����� ����� ��������� ������ �������� � ����������� ������.


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
