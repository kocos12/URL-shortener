package com.example.urlshortener;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface LinkRepository extends CrudRepository<Link, String> {
}
