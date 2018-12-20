package ivosdc.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
@Api(tags = "Demo")
public class DemoController {
    private final DemoService demoService;

    DemoController(final DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/helloworld")
    @ApiOperation(value = "Gets a HelloWorld.")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping()
    @ApiOperation(value = "Gets all Demos.",
    notes = "Returns a list of all Demos stored in the Database")
    public ResponseEntity<List<Demo>> getDemos() {

        return ResponseEntity.ok(demoService.getAll());
    }

    @GetMapping(value = "/{id:[0-9]+}")
    @ApiOperation(value = "Gets a Demo-object by id.",
            notes = "Returns a Demo-object stored in the Database")
    public ResponseEntity<Demo> getDemo(@PathVariable("id") long demoId) {
        return ResponseEntity.ok(demoService.getDemo(demoId));
    }

    @PostMapping
    @ApiOperation(value = "Create a new Demo-object",
            notes = "Creates a new demo entry in the database")
    public ResponseEntity<Demo> createDemo(@RequestBody final CreateDemoRequest createDemoRequest) {
        return ResponseEntity.ok(
                demoService.create(
                        createDemoRequest.getName(),
                        createDemoRequest.getDescription()
                ));
    }

    @Data
    @NoArgsConstructor
    @ApiModel
    private static class CreateDemoRequest {
        @ApiModelProperty(
                value = "The name of the demo.",
                required = true)
        private String name;

        @ApiModelProperty(
                value = "A short text that describes the demo", allowEmptyValue = true)
        private String description;
    }
}
