package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by bilga on 22-10-2022
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FX {

    private FXMerchant merchant;
}
