package smart.smartbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import smart.smartbook.model.ExecutionStatus;
import smart.smartbook.model.WholeBook;
import smart.smartbook.service.WholeBookService;

import java.io.IOException;

@RestController
@RequestMapping(value="/wholebook")
public class WholeBookController {

    @Autowired
    WholeBookService wholeBookService;

    @RequestMapping(value = "/id/{id}", method= RequestMethod.GET)
    public WholeBook getBookById(@PathVariable Integer id){
        return wholeBookService.getBookById(id).get();
    }

    @RequestMapping(value = "/save/{author}/{bookname}/{bookpathName}", method= RequestMethod.POST)
    public WholeBook getEventUsers(@PathVariable String author, @PathVariable String bookname, @PathVariable String bookpathName){
        return wholeBookService.saveWholeBook(author, bookname, bookpathName);
    }

    @RequestMapping(value = "/bookname/{bookname}", method= RequestMethod.GET)
    public WholeBook getBookByName(@PathVariable String bookname){
        return wholeBookService.findByName(bookname);
    }

    @RequestMapping(value = "/NER/people/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getPeopleNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getPeopleNER(bookname);
    }

    @RequestMapping(value = "/NER/date/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getDateNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getDateNER(bookname);
    }

    @RequestMapping(value = "/NER/location/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getLocationNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getLocationNER(bookname);
    }

    @RequestMapping(value = "/NER/money/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getMoneyNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getMoneyNER(bookname);
    }

    @RequestMapping(value = "/NER/organization/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getOrganizationNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getOrganizationNER(bookname);
    }
    @RequestMapping(value = "/NER/percentage/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getPercentageNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getPercentageNER(bookname);
    }
    @RequestMapping(value = "/NER/time/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getTimeNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getTimeNER(bookname);
    }

}
