package ua.training;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class DocumentCollector implements Runnable {
    private final int threshold = 25;
    private final Queue<Document> biologistsDocs = new LinkedList<>();
    private final Queue<Document> mathematicianDocs = new LinkedList<>();
    private BlockingQueue<Document> queue;
    private ThreadLocalRandom random;

    public DocumentCollector(BlockingQueue<Document> queue, ThreadLocalRandom random) {
        this.queue = queue;
        this.random = random;

        for (int i = 0; i <200 ; i++) {
            biologistsDocs.offer(new Document(Speciality.BIOLOGY,"Document №" + i));
        }
        for (int i = 0; i <250 ; i++) {
            mathematicianDocs.offer(new Document(Speciality.MATH,"Document №" + i));
        }
    }

    @Override
    public void run() {

//        System.out.println("Queue size is " + queue.size());
//        while (!biologistsDocs.isEmpty()||!mathematicianDocs.isEmpty()){
//            if (queue.size() < threshold){
//                while (queue.remainingCapacity()>0){
//                    String document = getRandomDocument();
//                    if (queue.offer(document)) {
//                        System.out.println(document + " was successfully added in the queue.");
//                    } else {
//                        System.out.println(document + " wasn't added in the queue.");
//                    }
//                }
//            }
//            else Thread.yield();
//        }

        while ((!mathematicianDocs.isEmpty())||(!biologistsDocs.isEmpty())){
            if(queue.size()<threshold){
                int remainingCapacity = queue.remainingCapacity();
                System.out.println("Remaining capacity is " + remainingCapacity);
                for (int i = 0; i < remainingCapacity; i++) {
                    refillQueue();
                }
                System.out.println("Queue size is " + queue.size());
            }
        }
        }

    private Document getRandomDocument() {
            int n = random.nextInt(0, 2);
            if (n == 0) {
                return biologistsDocs.poll();
            } else return mathematicianDocs.poll();
    }

    private void refillQueue(){
        if(mathematicianDocs.isEmpty()&&(biologistsDocs.isEmpty())){}
        else if(mathematicianDocs.isEmpty()){queue.offer(biologistsDocs.poll());}
        else if(biologistsDocs.isEmpty()){queue.offer(mathematicianDocs.poll());}
        else queue.offer(getRandomDocument());
    }
    }



