package ua.training;

import java.util.List;
import java.util.Queue;

//Первый только биологов (пока не встретит математика),
public class BiologicalInstitute implements Runnable, NUMBER_CONSTANTS {
    private List<Document> acceptedDocs;
    private final Queue<Document> queue;

    public BiologicalInstitute(List<Document> acceptedDocs,
                               Queue<Document> queue) {
        this.acceptedDocs = acceptedDocs;
        this.queue = queue;
    }

    @Override
    public void run() {
        Document document;
        try {
            Thread.sleep(NUMBER_CONSTANTS.DELAY);
            while (!queue.isEmpty()) {
                synchronized (queue) {
                    while ((queue.peek()!= null) && (queue.peek().getSpeciality().equals(Speciality.BIOLOGY))) {
                        document = queue.poll();
                        acceptedDocs.add(document);
                        System.out.println("Biological institute takes " + document);
                    }
                }
                Thread.sleep(NUMBER_CONSTANTS.DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Biological institute accepted documents: " + acceptedDocs);

    }
}
