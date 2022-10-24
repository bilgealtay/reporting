package com.ravensoftware.reporting.transaction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by bilga on 22-10-2022
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
