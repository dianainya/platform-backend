package sadiva.mpi.platformbackend.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sadiva.mpi.platformbackend.service.PlatformService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final PlatformService platformService;

    @Scheduled(cron = "${cron.distribute-person}")
    public void distributePrisoners() {
        log.info("Запущена периодическая задача по распределению заключенных по этажам");
        platformService.distributePrisoners();
        log.info("Периодическая задача по распределению заключенных по этажам отработала");
    }
}
