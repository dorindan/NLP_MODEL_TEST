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

    public WholeBook saveWholeBook(String author, String bookname, String bookpath) {
        try {
            File file = new File("src/main/resources/books/" + bookpath);
            PDDocument document = PDDocument.load(new File("src/main/resources/books/" + bookpath));
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

    public ExecutionStatus getPeopleNER(String bookName) throws IOException {
        ExecutionStatus executionStatus = new ExecutionStatus();
        executionStatus.setAlgorithm("NER");
        executionStatus.setBookName(bookName);

        WholeBook wholeBook = findByName(bookName);

        long startTime = System.nanoTime();
        InputStream inputStreamNameFinder = new FileInputStream("src/main/resources/models/en-ner-person.bin");
        long endTime = System.nanoTime();
        TokenNameFinderModel model = new TokenNameFinderModel(
                inputStreamNameFinder);
        NameFinderME nameFinderME = new NameFinderME(model);
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(wholeBook.getBookString());
        List<Span> spans = Arrays.asList(nameFinderME.find(tokens));


        executionStatus.setModel("en-ner-person.bin");
        executionStatus.setExecutionTime(endTime - startTime);
        return executionStatus;
    }
}
