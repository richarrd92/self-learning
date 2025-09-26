
class ThreadX extends Thread
{
	public void run()
	{
		Thread t = Thread.currentThread();
		t.setName("ThreadX");
		for(int i=1;i<=10;i++)
		{
			System.out.println(t.getName()+":"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class ThreadY extends Thread
{
	public void run()
	{
		Thread t = Thread.currentThread();
		t.setName("ThreadY");
		for(int i=10;i>=1;i--)
		{
			System.out.println(t.getName()+":"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class MultiThreadDemo {
	public static void main(String[] args) {
		ThreadX t1 = new ThreadX();
		t1.start();
		//t1.start();//IllegalThreadStateException
		ThreadY t2 = new ThreadY();
		t2.start();
	}

}
