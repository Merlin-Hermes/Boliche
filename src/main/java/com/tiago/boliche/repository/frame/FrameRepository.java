package com.tiago.boliche.repository.frame;

import com.tiago.boliche.entity.Frame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepository extends JpaRepository<Frame, Long> {
}
