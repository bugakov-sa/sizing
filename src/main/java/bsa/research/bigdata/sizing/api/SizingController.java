package bsa.research.bigdata.sizing.api;

import bsa.research.bigdata.sizing.dto.Sizing;
import bsa.research.bigdata.sizing.dto.SizingRequest;
import bsa.research.bigdata.sizing.dto.SizingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class SizingController {

    private final BigDecimal YEAR_MONTHES = BigDecimal.valueOf(12);
    private final BigDecimal YEAR_DAYS = BigDecimal.valueOf(365);
    private final BigDecimal DAY_SECONDS = BigDecimal.valueOf(24 * 60 * 60);
    private final BigDecimal YEAR_SECONDS = YEAR_DAYS.multiply(DAY_SECONDS);
    private final BigDecimal TERABYTE_BYTES = BigDecimal.valueOf(1024L * 1024L * 1024L * 1024L);
    private final BigDecimal BLOCK_BYTES = BigDecimal.valueOf(128L * 1024L * 1024L);
    private final BigDecimal BLOCK_METADATA_BYTES = BigDecimal.valueOf(150);
    private final BigDecimal MEGABYTE_BYTES = BigDecimal.valueOf(1024L * 1024L);
    private final BigDecimal BYTES_PER_SECOND_FROM_ONE_DISK = BigDecimal.valueOf(7L * 1024L * 1024L * 1024L / 2L);

    @GetMapping("calculate")
    @ResponseBody
    public SizingResponse calculateSizing(SizingRequest dto) {

        //Расчет объема реплицированных данных в терабайтах
        BigDecimal historySeconds = YEAR_SECONDS.multiply(dto.getHistoryHorizon()).divide(YEAR_MONTHES, RoundingMode.CEILING);
        BigDecimal records = historySeconds.divide(dto.getObjectDataFrequency(), RoundingMode.CEILING);
        BigDecimal dataBytes = records.multiply(dto.getDataRecordSize()).multiply(dto.getObjectsCount());
        BigDecimal dataTerabytes = dataBytes.divide(TERABYTE_BYTES, RoundingMode.CEILING);
        BigDecimal replicatedDataTerabytes = dataTerabytes.multiply(dto.getReplicationFactor());

        //Расчет числа блоков и объема метаданных в мегабайтах
        BigDecimal blocksCount = dataBytes.divide(BLOCK_BYTES, RoundingMode.CEILING);
        BigDecimal metadataBytes = blocksCount.multiply(BLOCK_METADATA_BYTES);
        BigDecimal metadataMegabytes = metadataBytes.divide(MEGABYTE_BYTES, RoundingMode.CEILING);

        //Расчет количества дисков c учетом выхода дисков из строя
        BigDecimal minDisks = replicatedDataTerabytes.divide(dto.getDiskVolume(), RoundingMode.CEILING);
        BigDecimal disks = minDisks.multiply(dto.getDiskWorkPeriod().add(dto.getDiskBuyPeriod()))
                .divide(dto.getDiskWorkPeriod(), RoundingMode.CEILING);

        //Расчет длительности параллельного чтения дневного объема данных
        BigDecimal historyDays = YEAR_DAYS.multiply(dto.getHistoryHorizon()).divide(YEAR_MONTHES, RoundingMode.CEILING);
        BigDecimal dayDataBytes = dataBytes.divide(historyDays, RoundingMode.CEILING);
        BigDecimal bytesPerSecond = BYTES_PER_SECOND_FROM_ONE_DISK.multiply(minDisks);
        BigDecimal dayDataReadDuration = dayDataBytes.divide(bytesPerSecond, RoundingMode.HALF_UP);

        return SizingResponse.ok(new Sizing(replicatedDataTerabytes, metadataMegabytes, disks, dayDataReadDuration));
    }
}
