package dev.flima.portfolio.modules.message.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

@Service
public class ContactMessageMetricsService {

    private final Counter successCounter;
    private final Counter errorCounter;
    private final Timer processingTimer;

    public ContactMessageMetricsService(MeterRegistry registry) {
        this.successCounter = Counter.builder("contact.message.processed.success")
                .description("Mensagens de contato processadas com sucesso")
                .register(registry);

        this.errorCounter = Counter.builder("contact.message.processed.error")
                .description("Mensagens de contato com erro")
                .register(registry);

        this.processingTimer = Timer.builder("contact.message.processing.time")
                .description("Tempo de processamento de mensagens de contato")
                .publishPercentileHistogram()
                .minimumExpectedValue(Duration.ofMillis(10))
                .maximumExpectedValue(Duration.ofSeconds(10))
                .register(registry);
    }

    public void incrementSuccess() {
        successCounter.increment();
    }

    public void incrementError() {
        errorCounter.increment();
    }

    public void recordProcessing(Runnable runnable) {
        processingTimer.record(runnable);
    }

    public <T> T recordProcessing(Supplier<T> supplier) {
        return processingTimer.record(supplier);
    }
}
