import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';
import { AppConstants } from '../util/app_constants';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor() {}

  commonMessage(
    title: string,
    message: string,
    icon: any = AppConstants.sweetIcons.info
  ) {
    Swal.fire({
      title: title,
      html: message,
      imageUrl: icon,
      imageWidth: 100,
      imageHeight: 100,
      confirmButtonText: 'Aceptar',
    });
  }
}
