import { BranchType } from "./branch.model";

export interface Scheduling {
  id?: number;
  branchId?: string;
  branchName?: string;
  typeAttention?: BranchType;
  minDays?: number;
  maxDays?: number;
  toleranceTime?: number;
  multipleBookings?: number;
  confirmEmail?: number;
  confirmTime?: number;
  unidConfirmTime?: string;
  services?: string;
}
