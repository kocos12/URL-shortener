package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private final LinkDtoMapper linkDtoMapper;
    private final LinkRepository linkRepository;

    public LinkService(LinkDtoMapper linkDtoMapper, LinkRepository linkRepository) {
        this.linkDtoMapper = linkDtoMapper;
        this.linkRepository = linkRepository;
    }

    public LinkDto addNewLink (LinkCreateDto newLink){
        Link linkToSave = linkDtoMapper.map(newLink);
        Link savedLink = linkRepository.save(linkToSave);
        return linkDtoMapper.map(savedLink);
    }
}
