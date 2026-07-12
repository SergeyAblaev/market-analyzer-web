package com.example.cryptoanalyzer.web.api.v1;

import com.example.cryptoanalyzer.rules.AlertRule;
import com.example.cryptoanalyzer.rules.service.AlertRulesService;
import com.example.cryptoanalyzer.web.model.AlertRuleUpdateDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alertrules")
@Tag(name = "Alert Rules API v1")
public class AlertRulesV1Controller {

    private final AlertRulesService service;


    @GetMapping("/all")
    public List<AlertRule> getRules() {
        return service.getRules();
    }

    @PatchMapping("/update")
    public AlertRule update(AlertRuleUpdateDto dto) {
        return service.update(dto);
    }
}
