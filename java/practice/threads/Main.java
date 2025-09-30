public class Main {
    public static void main(String[] args) {
        // pretend the file is 30 bytes
        // split into chunks of 10
        // 3 chunks: 0-9, 10-19, 20-29
        Thread t1 = new Thread(new FileDownloadTask(0, 9), "Thread-1");
        Thread t2 = new Thread(new FileDownloadTask(10, 19), "Thread-2");
        Thread t3 = new Thread(new FileDownloadTask(20, 29), "Thread-3");

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Download finished in " + (endTime - startTime) + " ms");
    }
}
