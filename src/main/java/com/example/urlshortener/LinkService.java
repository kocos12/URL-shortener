package com.example.urlshortener;

import com.example.urlshortener.dto.LinkCreateDto;
import com.example.urlshortener.dto.LinkDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Optional<LinkDto> findLinkById (String id){
        return linkRepository.findById(id)
                .map(LinkDtoMapper::map);
    }
    @Transactional
    public Optional<LinkDto> incrementVisit (String id){
        Optional<Link> linkById = linkRepository.findById(id);
        linkById.ifPresent(l -> l.setVisits(l.getVisits()+1));
        return linkById.map(LinkDtoMapper::map);
    }
}
