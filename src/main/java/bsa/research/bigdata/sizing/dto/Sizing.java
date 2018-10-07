package bsa.research.bigdata.sizing.dto;

import java.math.BigDecimal;

public class Sizing {
    private final BigDecimal dataNodeHDD;
    private final BigDecimal metadataMegabytes;
    private final BigDecimal disksCount;
    private final BigDecimal dayDataReadTime;

    public Sizing(BigDecimal dataNodeHDD, BigDecimal metadataMegabytes, BigDecimal disksCount, BigDecimal dayDataReadTime) {
        this.dataNodeHDD = dataNodeHDD;
        this.metadataMegabytes = metadataMegabytes;
        this.disksCount = disksCount;
        this.dayDataReadTime = dayDataReadTime;
    }

    public BigDecimal getDataNodeHDD() {
        return dataNodeHDD;
    }

    public BigDecimal getMetadataMegabytes() {
        return metadataMegabytes;
    }

    public BigDecimal getDisksCount() {
        return disksCount;
    }

    public BigDecimal getDayDataReadTime() {
        return dayDataReadTime;
    }
}
