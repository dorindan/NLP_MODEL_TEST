package smart.smartbook.service;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.smartbook.model.ExecutionStatus;
import smart.smartbook.model.WholeBook;
import smart.smartbook.repository.WholeBookRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class WholeBookService {

    @Autowired
    WholeBookRepository wholeBookRepository;

    public Optional<WholeBook> getBookById(Integer id) {
        return wholeBookRepository.findById(id);
    }

    public WholeBook saveWholeBook(String author, String bookname, String bookpathName) {
        try {
            PDDocument document = PDDocument.load(new File("src/main/resources/books/" + bookpathName));
            String text = null;
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
            }
            document.close();
            WholeBook wholeBook = new WholeBook(bookname, author, text);
            wholeBookRepository.save(wholeBook);
            return wholeBook;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WholeBook findByName(String bookName) {
        return wholeBookRepository.findByName(bookName);
    }

    public ExecutionStatus nerAlg(String bookName, String modelPath, String modelName) throws IOException {
        ExecutionStatus executionStatus = new ExecutionStatus();
        executionStatus.setAlgorithm("NER");
        executionStatus.setBookName(bookName);

        WholeBook wholeBook = findByName(bookName);
        List<Long> readVariablesList = new ArrayList<>();
        List<Long> processVariableList = new ArrayList<>();
        List<Span> spans = new ArrayList<>();
        String tokens[] = new String[0];

        for (int i = 0; i < 10; i++) {
            long readStartTime = System.nanoTime();
            InputStream inputStreamNameFinder = new FileInputStream(modelPath);
            TokenNameFinderModel model = new TokenNameFinderModel(
                    inputStreamNameFinder);
            NameFinderME nameFinderME = new NameFinderME(model);
            long readEndTime = System.nanoTime();

            long processStartTime = System.nanoTime();
            SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
            tokens = tokenizer.tokenize(wholeBook.getBookString());
            spans = Arrays.asList(nameFinderME.find(tokens));
            long processEndTime = System.nanoTime();
            long readTime = readEndTime - readStartTime;
            long processTime = processEndTime - processStartTime;
            readVariablesList.add(readTime);
            processVariableList.add(processTime);
        }
        executionStatus.setModelReadExecutionTime1(readVariablesList.get(0));
        executionStatus.setModelReadExecutionTime2(readVariablesList.get(1));
        executionStatus.setModelReadExecutionTime3(readVariablesList.get(2));
        executionStatus.setModelReadExecutionTime4(readVariablesList.get(3));
        executionStatus.setModelReadExecutionTime5(readVariablesList.get(4));
        executionStatus.setModelReadExecutionTime6(readVariablesList.get(5));
        executionStatus.setModelReadExecutionTime7(readVariablesList.get(6));
        executionStatus.setModelReadExecutionTime8(readVariablesList.get(7));
        executionStatus.setModelReadExecutionTime9(readVariablesList.get(8));
        executionStatus.setModelReadExecutionTime10(readVariablesList.get(9));

        executionStatus.setModelprocessExecutionTime1(processVariableList.get(0));
        executionStatus.setModelprocessExecutionTime2(processVariableList.get(1));
        executionStatus.setModelprocessExecutionTime3(processVariableList.get(2));
        executionStatus.setModelprocessExecutionTime4(processVariableList.get(3));
        executionStatus.setModelprocessExecutionTime5(processVariableList.get(4));
        executionStatus.setModelprocessExecutionTime6(processVariableList.get(5));
        executionStatus.setModelprocessExecutionTime7(processVariableList.get(6));
        executionStatus.setModelprocessExecutionTime8(processVariableList.get(7));
        executionStatus.setModelprocessExecutionTime9(processVariableList.get(8));
        executionStatus.setModelprocessExecutionTime10(processVariableList.get(9));

        executionStatus.setModelReadExecutionTimeAverage(readVariablesList.stream().mapToLong(d -> d).average().orElse(new Long(0)));
        executionStatus.setModelprocessExecutionTimeAverage(processVariableList.stream().mapToLong(d -> d).average().orElse(new Long(0)));
        executionStatus.setModel(modelName);
        List<String> results = new ArrayList<>();
        for (Span s : spans) {
            results.add(tokens[s.getStart()]);
        }
        executionStatus.setResults(results);
        return executionStatus;
    }

    public ExecutionStatus getPeopleNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-person.bin", "en-ner-person");
    }

    public ExecutionStatus getDateNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-date.bin", "en-ner-date");
    }

    public ExecutionStatus getLocationNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-location.bin", "en-ner-location");
    }

    public ExecutionStatus getMoneyNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-money.bin", "en-ner-money");
    }

    public ExecutionStatus getOrganizationNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-organization.bin", "en-ner-organization");
    }

    public ExecutionStatus getPercentageNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-percentage.bin", "en-ner-percentage");
    }

    public ExecutionStatus getTimeNER(String bookName) throws IOException {
        return nerAlg(bookName, "src/main/resources/models/en-ner-time.bin", "en-ner-time");
    }
}
