package by.glavdel.digital.controller.position.response;
;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionResponse {
    private Long positionId;
    private String name;
    private String description;
    private Long actualCount;
    private Boolean isRemove;
}
