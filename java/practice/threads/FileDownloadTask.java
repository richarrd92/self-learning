class FileDownloadTask implements Runnable {
    private int start;
    private int end;

    FileDownloadTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " downloading byte " + i);
            try {
                Thread.sleep(100); // simulate time to download one byte
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished chunk " + start + "-" + end);
    }
}
