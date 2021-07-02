import { CounterType } from './counter_type.model';

export interface CounterBooking {
  branchId?: string;
  bookingTypeList?: CounterType[];
}
