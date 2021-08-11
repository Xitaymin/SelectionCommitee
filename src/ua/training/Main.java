package ua.training;

import java.util.LinkedList;
import java.util.concurrent.*;

public class Main implements NUMBER_CONSTANTS {

    //Есть документы 200 биологов и 250 математиков которые по падают в
    // очередь обработки по сл. схеме – если в очереди находится менее 25
    // документов – рандомно добавляется до 50 .

    //С очереди документы забирают 3 института (опрашивая через определенные
    // промежутки).
    //Первый только биологов (пока не встретит математика),
    //Второй – всех, но рандомно от 1 до 5 дел
    //Третий математиков (пока не встретит биолога)

    public static void main(String[] args) {
	// write your code here
        final BlockingQueue <Document> queue = new LinkedBlockingQueue<>(CAPACITY);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        DocumentCollector documentCollector = new DocumentCollector(queue,random);
        GeneralInstitute generalInstitute = new GeneralInstitute(queue,random);
        BiologicalInstitute biologicalInstitute = new BiologicalInstitute(new LinkedList<>(),queue);
        MathematicalInstitute mathematicalInstitute = new MathematicalInstitute(queue);

        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(documentCollector);
        service.execute(generalInstitute);
        service.execute(biologicalInstitute);
        service.execute(mathematicalInstitute);

        service.shutdown();
//        service.awaitTermination(200, TimeUnit.MILLISECONDS);
//        Thread t1 = new Thread(documentCollector);
//        Thread t2 = new Thread(generalInstitute);
//        Thread t3 = new Thread(biologicalInstitute);
//        Thread t4 = new Thread(mathematicalInstitute);
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
    }
}
