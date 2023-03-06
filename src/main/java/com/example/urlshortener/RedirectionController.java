package com.example.urlshortener;


import com.example.urlshortener.dto.LinkDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/redir")
public class RedirectionController {

    private final LinkService linkService;

    public RedirectionController(LinkService linkService) {
        this.linkService = linkService;
    }
    @GetMapping("/{id}")
    ResponseEntity<?> redirection(@PathVariable String id){
        return linkService.incrementVisit(id)
                .map(LinkDto::getTargetUrl)
                .map(targetUrl -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create(targetUrl))
                        .build())
                .orElse(ResponseEntity.notFound().build());
    }
}
