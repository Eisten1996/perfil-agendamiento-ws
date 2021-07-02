import { Service } from './services.model';

export interface Branch {
  id?: string;
  name?: string;
  services?: Service[];
}

export enum BranchType {
  PRESENCIAL = '0',
  VIRTUAL = '1'
}