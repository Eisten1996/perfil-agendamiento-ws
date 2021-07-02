import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'configuration',
    loadChildren: () =>
      import('./pages/configuration/configuration.module').then(
        (m) => m.ConfigurationModule
      ),
  },
  {
    path: '',
    loadChildren: () =>
      import('./pages/scheduling/scheduling.module').then(
        (m) => m.SchedulingModule
      ),
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
})
export class AppRoutingModule {}
