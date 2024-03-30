package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.service.ScenarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoldApplicationRunner implements ApplicationRunner {
    private final ScenarioService scenarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scenarioService.miningForGold();
    }

}
