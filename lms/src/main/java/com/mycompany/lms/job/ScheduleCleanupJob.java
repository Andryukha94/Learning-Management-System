package com.mycompany.lms.job;

import com.mycompany.lms.dao.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleCleanupJob {

    private final ScheduleRepository scheduleRepository;

    @Scheduled(cron = "${scheduler.schedule-cleanup.cron}")
    @Transactional
    public void cleanupOldSchedules() {

        log.info("Schedule cleanup job started");

        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);

        int deleted = scheduleRepository.deleteOlderThan(oneYearAgo);

        log.info("Schedule cleanup job finished. Deleted records: {}", deleted);
    }
}
