import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss'],
})
export class LoadingComponent implements OnInit {
  progress = 50;
  interval: any;
  constructor(
    public dialogRef: MatDialogRef<LoadingComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnDestroy(): void {
    clearInterval(this.interval);
  }

  ngOnInit(): void {
    let ctx = this;
    this.interval = setInterval(() => {
      ctx.progress = Math.floor(Math.random() * 100 + 1);
    }, 600);
  }
}
