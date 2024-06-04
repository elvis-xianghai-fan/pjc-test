package com.elvfan.pjc_test.service;

import com.elvfan.pjc_test.entity.PjcTest;
import com.elvfan.pjc_test.repository.PjcTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PjcTestService {

    @Autowired
    private PjcTestRepository repository;

    private static final int BATCH_SIZE = 10000;
    private final Map<String, String> idBatch = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(100);

    public void addIdToBatch(String input, String encrypted) {
        idBatch.put(input, encrypted);
        if (idBatch.size() >= BATCH_SIZE) {
            synchronized (idBatch) {
                saveBatch();
            }
        }
    }

    public void saveBatch() {
        List<PjcTest> pjcTests = new ArrayList<>();
        for (Map.Entry<String, String> p : idBatch.entrySet()) {
            PjcTest pjcTest = new PjcTest();
            pjcTest.setInput(p.getKey());
            pjcTest.setEncrypted(p.getValue());
            pjcTests.add(pjcTest);
        }
        idBatch.clear();

        executor.submit(() -> {
            repository.saveAll(pjcTests);
        });
    }
}
