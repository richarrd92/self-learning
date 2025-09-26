//Methods of Thread class

class MyThread extends Thread
{
	public void run()
	{
		//System.out.println("I am a thread");
		Thread t = Thread.currentThread();
		System.out.println(t);//Thread[#29,Thread-0,5,main]
		t.setName("MyThread");
		System.out.println(t);//Thread[#29,MyThread,5,main]
		System.out.println(t.getName());//MyThread
		t.setPriority(8);
		/*
		 If the priority is <1 or >10 then it will throw IllegalArgumentException
		 */
		System.out.println(t);//Thread[#29,MyThread,8,main]
		System.out.println(t.getPriority());//8
	}
}
public class ThreadMethodsDemo {
	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();//creates a thread and invokes run()
	}
}
