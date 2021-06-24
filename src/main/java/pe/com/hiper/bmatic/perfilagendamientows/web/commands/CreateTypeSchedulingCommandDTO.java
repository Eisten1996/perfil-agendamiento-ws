package pe.com.hiper.bmatic.perfilagendamientows.web.commands;

import pe.com.hiper.bmatic.perfilagendamientows.domain.scheduling.model.TypeScheduling;

import java.util.List;

public class CreateTypeSchedulingCommandDTO {
    private String branchId;
    private List<TypeSchedulingCommandDTO> typeSchedulingList;

//    public CreateTypeSchedulingCommandDTO(TypeScheduling typeScheduling, List<TypeSchedulingCommandDTO> typeSchedulingList) {
//        this.branchId = typeScheduling.getBranchId();
//        this.typeSchedulingList = typeSchedulingList;
//    }

    public String getBranchId() {
        return branchId;
    }

    public List<TypeSchedulingCommandDTO> getTypeSchedulingList() {
        return typeSchedulingList;
    }
}
