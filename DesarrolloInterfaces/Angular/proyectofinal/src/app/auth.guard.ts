import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { DataService } from './data.service';

export const authGuard: CanActivateFn = (route, state) => {
  const dataService = inject(DataService);
  const router = inject(Router);

  if (dataService.hasValidSession()) {
    return true;
  }

 
  router.navigate(['/login'], { queryParams: { unauthorized: true } });
  return false;
};
