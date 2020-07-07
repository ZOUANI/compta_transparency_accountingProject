package com.zsmart.accountingProject.ws.rest.vo;

import java.math.BigDecimal;
import java.util.List;

public class ChartData {
    private List<String> labels;
    private List<BigDecimal> totalCharge;
    private List<BigDecimal> totalGain;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<BigDecimal> getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(List<BigDecimal> totalCharge) {
        this.totalCharge = totalCharge;
    }

    public List<BigDecimal> getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(List<BigDecimal> totalGain) {
        this.totalGain = totalGain;
    }
}
