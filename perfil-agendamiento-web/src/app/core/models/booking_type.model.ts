export interface BookingType {
    id?: string;
    name?: string;
}

export enum BookingTypeId {
    NOT_BOOKING = 'N',
    AGENDAMIENTO = 'A',
    CITA = 'C'
}

export enum BookingTypeName {
    NOT_BOOKING = 'NO PERMITE RESERVAS',
    AGENDAMIENTO = 'AGENDAMIENTO',
    CITA = 'CITA'
}