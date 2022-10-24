package com.ravensoftware.reporting.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by bilga
 */
@Data
public class ReportRequest implements Serializable {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate toDate;

    private Long merchantId;

    private Long acquirerId;

}
