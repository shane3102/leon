package pl.leon.form.application.leon.repository.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.NANOS;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration == null ? 0 : duration.toNanos();
    }

    @Override
    public Duration convertToEntityAttribute(Long seconds) {
        return Duration.of(seconds, NANOS);
    }
}
