import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { LoadingComponent } from '../components/loading/loading.component';

@Injectable({
  providedIn: 'root',
})
export class LoadingService {
  constructor(private dialog: MatDialog) {}

  loadingRef: MatDialogRef<any, any> | undefined;

  shown = false;
  showLoding(text = 'cargando') {
    this.shown = true;
    this.loadingRef = this.dialog.open(LoadingComponent, {
      data: { message: text },
      disableClose: true,
    });
  }

  closeLoading() {
    this.shown = false;
    if (this.loadingRef) this.loadingRef.close();
  }
}
