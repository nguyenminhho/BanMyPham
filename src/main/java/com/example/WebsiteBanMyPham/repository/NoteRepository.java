package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
