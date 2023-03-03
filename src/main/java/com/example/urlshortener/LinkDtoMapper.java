package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import org.springframework.stereotype.Service;

@Service
public class LinkDtoMapper {
     Link map (LinkCreateDto linkCreateDto){
        Link newLink = new Link();
        newLink.setName(linkCreateDto.getName());
        newLink.setTargetUrl(linkCreateDto.getTargetUrl());
        newLink.setVisits(0);
        newLink.setRedirectUrl("");
        return newLink;
    }

     LinkDto map (Link link){
        LinkDto linkDto = new LinkDto();
        linkDto.setId(link.getId());
        linkDto.setName(link.getName());
        linkDto.setRedirectUrl(link.getRedirectUrl());
        linkDto.setTargetUrl(linkDto.getTargetUrl());
        linkDto.setVisits(link.getVisits());
        return linkDto;
    }
}
