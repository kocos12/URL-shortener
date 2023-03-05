package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import org.springframework.stereotype.Service;

@Service
public class LinkDtoMapper {
     Link map (LinkCreateDto linkCreateDto){
        Link newLink = new Link();
        String randomId = RandomIdGenerator.generateId();
        newLink.setId(randomId);
        newLink.setName(linkCreateDto.getName());
        newLink.setTargetUrl(linkCreateDto.getTargetUrl());
        newLink.setVisits(0);
        newLink.setRedirectUrl("http://localhost:8080/redir/" + randomId);
        return newLink;
    }

     static LinkDto map(Link link){
        LinkDto linkDto = new LinkDto();
        linkDto.setId(link.getId());
        linkDto.setName(link.getName());
        linkDto.setRedirectUrl(link.getRedirectUrl());
        linkDto.setTargetUrl(link.getTargetUrl());
        linkDto.setVisits(link.getVisits());
        return linkDto;
    }
}
