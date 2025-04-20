package bg.fmi.uni.inventorysystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dummy")
public class DummyController {

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }
}
