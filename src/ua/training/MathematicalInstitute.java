package ua.training;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MathematicalInstitute implements Runnable {
    private List<Document> acceptedDocs = new LinkedList<>();
    private final Queue<Document> queue;

    public MathematicalInstitute(Queue<Document> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Document document;
        try {
            Thread.sleep(NUMBER_CONSTANTS.DELAY);
            while (!queue.isEmpty()) {
                synchronized (queue) {
                    while ((queue.peek()!= null) && (queue.peek().getSpeciality().equals(Speciality.MATH))) {
                        document = queue.poll();
                        acceptedDocs.add(document);
                        System.out.println("Mathematical institute takes " + document);
                    }
                }
                Thread.sleep(NUMBER_CONSTANTS.DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mathematical institute accepted documents: " + acceptedDocs);

    }

}
