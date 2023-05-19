package com.example.palindrom.entity;

import java.util.Arrays;
import java.util.Objects;

//@Component
public class ResponseStats {
    private Object[] responses;
    public ResponseStats(Object[] responses) {
        this.responses = responses;
    }
    private Integer min;
    private Integer max;
    private Double aver;
    public Integer getMin() { return min; }
    public Integer getMax() { return max; }
    public Double getAver() { return aver; }
    public Object[] getResponses() { return responses; }
    public void setMin(Integer min) { this.min = min; }
    public void setMax(Integer max) { this.max = max; }
    public void setAver(Double aver) { this.aver = aver; }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseStats)) return false;
        ResponseStats other = (ResponseStats) o;
        return Arrays.equals(this.responses, other.responses)
                && Objects.equals(this.min, other.min)
                && Objects.equals(this.max, other.max)
                && Objects.equals(this.aver, other.aver);
    }
}
