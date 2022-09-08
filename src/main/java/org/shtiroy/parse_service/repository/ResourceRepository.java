package org.shtiroy.parse_service.repository;

import org.shtiroy.parse_service.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    Resource findByResourceName(String resourceName);

    List<Resource> findByResourceIsActive(boolean resourceIsActive);
}
