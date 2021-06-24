package pe.com.hiper.bmatic.perfilagendamientows.web.commands;


import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.TypeScheduling;

public class TypeSchedulingCommandDTO {

    private Integer schedulingId;
    private String counterId;
    private String typeBooking;

//    public TypeSchedulingCommandDTO(TypeScheduling typeScheduling) {
//        this.schedulingId = typeScheduling.getSchedulingId();
//        this.counterId = typeScheduling.getCounterId();
//        this.typeBooking = typeScheduling.getTypeBooking();
//    }

    public Integer getSchedulingId() {
        return schedulingId;
    }

    public String getCounterId() {
        return counterId;
    }

    public String getTypeBooking() {
        return typeBooking;
    }
}
