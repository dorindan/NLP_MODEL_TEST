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

    @RequestMapping(value = "/{author}/{bookname}/{bookpath}", method= RequestMethod.POST)
    public WholeBook getEventUsers(@PathVariable String author, @PathVariable String bookname, @PathVariable String bookpath){
        return wholeBookService.saveWholeBook(author, bookname, bookpath);
    }

    @RequestMapping(value = "/bookname/{bookname}", method= RequestMethod.GET)
    public WholeBook getBookByName(@PathVariable String bookname){
        return wholeBookService.findByName(bookname);
    }

    @RequestMapping(value = "/NER/people/{bookname}", method= RequestMethod.GET)
    public ExecutionStatus getPeopleNER(@PathVariable String bookname) throws IOException {
        return wholeBookService.getPeopleNER(bookname);
    }
}
