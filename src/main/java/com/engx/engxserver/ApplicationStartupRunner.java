package com.engx.engxserver;

import com.engx.engxserver.repository.BookRecordRepository;
import com.engx.engxserver.repository.BookRepository;
import com.engx.engxserver.repository.UnitRecordRepository;
import com.engx.engxserver.repository.UnitRepository;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.repository.WordRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UnitRepository unitRepository;
    private final WordRepository wordRepository;
    private final BookRecordRepository bookRecordRepository;
    private final UnitRecordRepository unitRecordRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        doSeed();
    }

    void doSeed() throws Exception {
        logger.info("Database seeding !!");
        String line = "";
        String splitBy = ",";

        BufferedReader usersBuffer = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource("classpath:dbseed/users.csv").getInputStream()));
        usersBuffer.readLine();
        while ((line = usersBuffer.readLine()) != null) {
            String[] data = line.split(splitBy); // use comma as separator
            userRepository.insertCSV(Long.valueOf(data[0]), data[1], data[2], data[3], data[4], data[5]);
        }

        BufferedReader booksBuffer = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource("classpath:dbseed/books.csv").getInputStream()));
        booksBuffer.readLine();
        while ((line = booksBuffer.readLine()) != null) {
            String[] data = line.split(splitBy); // use comma as separator
            bookRepository.insertCSV(Long.valueOf(data[0]), data[1], Long.valueOf(data[2]));
        }

        BufferedReader unitsBuffer = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource("classpath:dbseed/units.csv").getInputStream()));
        unitsBuffer.readLine();
        while ((line = unitsBuffer.readLine()) != null) {
            String[] data = line.split(splitBy); // use comma as separator
            unitRepository.insertCSV(Long.valueOf(data[0]), data[1], Long.valueOf(data[2]));
        }

        BufferedReader wordsBuffer = new BufferedReader(new InputStreamReader(
                resourceLoader.getResource("classpath:dbseed/words.csv").getInputStream()));
        wordsBuffer.readLine();
        while ((line = wordsBuffer.readLine()) != null) {
            String[] data = line.split(splitBy); // use comma as separator
            wordRepository.insertCSV(Long.valueOf(data[0]), data[1], Long.valueOf(data[2]));
        }
    }
}
