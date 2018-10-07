package bsa.research.bigdata.sizing.dto;

public class SizingResponse {
    private final String description;
    private final Sizing result;

    private SizingResponse(String description, Sizing result) {
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public Sizing getResult() {
        return result;
    }

    public static SizingResponse error(String error) {
        return new SizingResponse(error, null);
    }

    public static SizingResponse ok(Sizing dto) {
        return new SizingResponse("ok", dto);
    }
}
