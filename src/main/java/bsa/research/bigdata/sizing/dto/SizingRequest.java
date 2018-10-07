package bsa.research.bigdata.sizing.dto;

import java.math.BigDecimal;

public class SizingRequest {
    private final BigDecimal objectsCount;
    private final BigDecimal objectDataFrequency;
    private final BigDecimal historyHorizon;
    private final BigDecimal dataRecordSize;
    private final BigDecimal replicationFactor;
    private final BigDecimal diskVolume;
    private final BigDecimal diskWorkPeriod;
    private final BigDecimal diskBuyPeriod;

    public SizingRequest(BigDecimal objectsCount, BigDecimal objectDataFrequency, BigDecimal historyHorizon, BigDecimal dataRecordSize, BigDecimal replicationFactor, BigDecimal diskVolume, BigDecimal diskWorkPeriod, BigDecimal diskBuyPeriod) {
        this.objectsCount = objectsCount;
        this.objectDataFrequency = objectDataFrequency;
        this.historyHorizon = historyHorizon;
        this.dataRecordSize = dataRecordSize;
        this.replicationFactor = replicationFactor;
        this.diskVolume = diskVolume;
        this.diskWorkPeriod = diskWorkPeriod;
        this.diskBuyPeriod = diskBuyPeriod;
    }

    public BigDecimal getObjectsCount() {
        return objectsCount;
    }

    public BigDecimal getObjectDataFrequency() {
        return objectDataFrequency;
    }

    public BigDecimal getHistoryHorizon() {
        return historyHorizon;
    }

    public BigDecimal getDataRecordSize() {
        return dataRecordSize;
    }

    public BigDecimal getReplicationFactor() {
        return replicationFactor;
    }

    public BigDecimal getDiskVolume() {
        return diskVolume;
    }

    public BigDecimal getDiskWorkPeriod() {
        return diskWorkPeriod;
    }

    public BigDecimal getDiskBuyPeriod() {
        return diskBuyPeriod;
    }
}
