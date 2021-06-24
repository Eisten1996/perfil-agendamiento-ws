package pe.com.hiper.bmatic.perfilagendamientows.web.commands;

import java.util.List;

public class CreateCounterBookingCommandDTO {
    private String branchId;
    private List<BookingTypeCommandDTO> bookingTypeList;

    public String getBranchId() {
        return branchId;
    }

    public List<BookingTypeCommandDTO> getBookingTypeList() {
        return bookingTypeList;
    }
}
