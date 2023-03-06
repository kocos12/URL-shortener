package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    ResponseEntity<LinkDto> addLink (@RequestBody LinkCreateDto linkCreateDto){
        LinkDto addedLink = linkService.addNewLink(linkCreateDto);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedLink.getId())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(addedLink);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getLinkInfo (@PathVariable String id){
        Optional<LinkDto> linkDto = linkService.findLinkById(id);
        return ResponseEntity.of(linkDto);
    }
}
