package com.experimentaly.api.clothesstore.core.model.base;

import static com.experimentaly.api.clothesstore.core.util.DateUtil.DATE_TIME_FORMAT;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class DateAuditModel<T> {

    protected T id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    protected LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    protected LocalDateTime deletedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    protected LocalDateTime updatedAt;

}
