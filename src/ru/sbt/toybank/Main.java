package ru.sbt.toybank;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//есть сущность бэк системы - выполняет операцию со счетом банка увеличить или уменьшить, если возможно
		//содержит поле общего денежного баланса с синхронизированным доступом
		// (синхронизированне методы по объекту для увеличения и уменьшения баланса)

		//есть сущность клиента
		//является отдельным потоком. Миниум 4 клиента.
		//клиент должен иметь возможность подать только одну заявку увеличить или уменьшить баланс

		//есть сущность заявки, содержит имя потока (клиента), сумму, тип операции

		//есть сущность фронтальной системы
		//это очередь с методами добавления заявок для клиентов и методами получения для обработчиков
		//реализация через wait и notifyAll. Не может содержать более двух заявок

		//есть сущность обработчика заявок - постоянно опрашивает фронт на наличие заявок,
		//если заявка есть то получает и передает в бэк
		//является отдельным потоком. Минимум 2 обработчика.
		//

		//вывести в консоль всю ключевую информацию процесса (см. картинки)

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

		Thread.sleep(5000);

		handler1.interrupt();
		handler2.interrupt();
	}
}
