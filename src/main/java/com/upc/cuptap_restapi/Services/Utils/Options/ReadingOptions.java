package com.upc.cuptap_restapi.Services.Utils.Options;

import com.upc.cuptap_restapi.Models.Utils.PageParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingOptions implements ParamOptions {

    private LocalDate dateLimit;
    private PageParams pageParams;
    private boolean isLazy;

    public ReadingOptions withDateLimit(LocalDate dateLimit) {
        this.dateLimit = dateLimit;
        return this;
    }

    public ReadingOptions withPageParams(PageParams pageRequest) {
        this.pageParams = pageRequest;
        return this;
    }

    public ReadingOptions withLazy(boolean lazy) {
        isLazy = lazy;
        return this;
    }
}
