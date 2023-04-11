import java.io.FileOutputStream;
    import java.io.InputStream;
    import java.net.URL;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

public class TestEx6_2 {

    public static class DownloadThread implements Runnable {

        private String url;

        public DownloadThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            try {
                URL fileUrl = new URL(url);
                InputStream inputStream = fileUrl.openStream();
                FileOutputStream outputStream = new FileOutputStream(fileName);

                byte[] buffer = new byte[2048];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }

                inputStream.close();
                outputStream.close();

                System.out.println(fileName + " 다운로드 완료");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] urls = {"https://nhnacademy.dooray.com/page-files/3481345949028747701",
            "https://nhnacademy.dooray.com/page-files/3481345949029027955",
            "https://nhnacademy.dooray.com/page-files/3481345949033727759",
            "https://nhnacademy.dooray.com/page-files/3481345949034048654",
            "https://nhnacademy.dooray.com/page-files/3481345949034798740",
            "https://nhnacademy.dooray.com/page-files/3481345949034913349"
        };
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (String url : urls) {
            Runnable worker = new DownloadThread(url);
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("모든 다운로드가 완료되었습니다.");
    }
}
