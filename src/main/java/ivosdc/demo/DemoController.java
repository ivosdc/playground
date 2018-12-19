package ivosdc.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@Api(tags = "Demo")
public class DemoController {
    DemoController() {
    }

    @GetMapping("/helloworld")
    @ApiOperation(value = "Gets a HelloWorld.")
    public String getHelloWorld() {
        return "Hello World";
    }
}
