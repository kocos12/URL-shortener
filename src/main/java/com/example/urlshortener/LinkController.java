package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import com.example.urlshortener.dto.LinkUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PatchMapping("/{id}")
    ResponseEntity<?> updateLinkName (@PathVariable String id,
                                      @RequestBody LinkUpdateDto linkUpdateDto){
       if(linkService.checkPasswd(id, linkUpdateDto)){
           linkService.updateLink(id, linkUpdateDto);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.badRequest().build();
    }
}
