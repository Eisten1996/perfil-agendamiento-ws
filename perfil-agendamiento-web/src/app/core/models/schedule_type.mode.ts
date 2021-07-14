import { Service } from './services.model';

export interface ScheduleType {
    id?: string;
    name?: string;
    services?: Service[];
}