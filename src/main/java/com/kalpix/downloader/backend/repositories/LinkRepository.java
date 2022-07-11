package com.kalpix.downloader.backend.repositories;

import com.kalpix.downloader.backend.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<Link, UUID> {
}
