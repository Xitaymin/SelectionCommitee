package ua.training;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//Второй – всех, но рандомно от 1 до 5 дел
public class GeneralInstitute implements Runnable {
    private List<Document> acceptedDocs = new LinkedList<>();
    private final BlockingQueue<Document> queue;
    private ThreadLocalRandom random;

    public GeneralInstitute(BlockingQueue<Document> queue,
                            ThreadLocalRandom random) {
        this.queue = queue;
        this.random = random;
    }

    @Override
    public void run() {
        int n;
        try {
            Thread.sleep(NUMBER_CONSTANTS.DELAY);
            while (!queue.isEmpty()) {
                n = random.nextInt(6);
                synchronized (queue) {
                    for (int i = 0; i < n; i++) {
                        Document document = queue.poll(NUMBER_CONSTANTS.DELAY, TimeUnit.MILLISECONDS);
                        //rewrite using Optional
                        if (document != null) {
                            System.out.println("General institute takes " + document);
                            acceptedDocs.add(document);
                        }
                    }
                }
                Thread.sleep(NUMBER_CONSTANTS.DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("General institute accepted documents: " +acceptedDocs);
    }

}

