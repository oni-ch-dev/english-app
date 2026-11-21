package io.github.onichdev.backend.expression;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expressions")
public class ExpressionController {
    private final ExpressionService expressionService;

    public ExpressionController(ExpressionService expressionService){
        this.expressionService = expressionService;
    }

    @PostMapping
    public ResponseEntity<ExpressionResponse> create(@Valid @RequestBody CreateExpressionRequest request){
        ExpressionResponse response = expressionService.create(request);
        return ResponseEntity
            .created(URI.create("/expressions/" + response.id()))
            .body(response);
    }
}
