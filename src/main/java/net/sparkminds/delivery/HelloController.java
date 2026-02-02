package net.sparkminds.delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot 🚀";
    }


    @GetMapping("/hello-json")
    public Map<String, String> helloJson() {
        return Map.of(
                "message", "Hello Spring Boot",
                "status", "success"
        );
    }
}
