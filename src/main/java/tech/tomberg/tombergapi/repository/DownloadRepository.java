package tech.tomberg.tombergapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.tomberg.tombergapi.entity.Download;

public interface DownloadRepository extends JpaRepository<Download, Long> {
}