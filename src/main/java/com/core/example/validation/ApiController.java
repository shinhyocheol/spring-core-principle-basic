package com.core.example.validation;

import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ApiController {

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> createUser(@Validated @RequestBody User user) {
        var result = new HashMap<String, Object>();
        result.put("user", user);

        return ResponseEntity.ok().body(result);
    }
}
