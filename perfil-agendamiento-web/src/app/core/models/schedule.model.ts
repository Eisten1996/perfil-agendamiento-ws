export interface Schedule {
  id?: string;
  identify?: string;
  schedulingId?: number;
  counterTypeId?: string;
  bookingType?: string;
  counterId?: string;
  day?: number;
  start?: string;
  end?: string;
  date?: string;
  addDating?: number;
}

export enum Day {
  'Domingo',
  'Lunes',
  'Martes',
  'Miércoles',
  'Jueves',
  'Viernes',
  'Sábado',
}
