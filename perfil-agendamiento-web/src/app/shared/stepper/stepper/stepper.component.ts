import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Stepper } from 'src/app/core/models/stepper.model';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.scss'],
})
export class StepperComponent implements OnInit {
  @Input()
  public steps: number;

  @Input()
  public titles: string[];

  @Input()
  public selected: number;

  @Output()
  public selectedEvent: EventEmitter<number> = new EventEmitter<number>();

  @Input()
  public steppers: Stepper[];

  constructor() {}

  ngOnInit(): void {}

  public selectStep(step: Stepper) {
    if (step.step !== this.selected) {
      this.selectedEvent.emit(step.step);
    }
  }
}
