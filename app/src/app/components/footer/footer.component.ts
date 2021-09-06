import { Component, OnInit } from '@angular/core';
import { InformationType } from 'src/app/models/option-information';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent implements OnInit {
  private footerOptions = {
    row1: {
      title: 'POLÍTICAS',
      type: InformationType.TEXT,
      items: [
        {
          content: 'Políticas de privacidad',
          redirectTo: '',
          avaliabled: false,
        },
        { content: 'Políticas de cambio', redirectTo: '', avaliabled: false },
        { content: 'Políticas de envío', redirectTo: '', avaliabled: false },
        {
          content: 'Términos y condiciones',
          redirectTo: '',
          avaliabled: false,
        },
        {
          content: 'Responsabilidad social',
          redirectTo: '',
          avaliabled: false,
        },
      ],
    },
    row2: {
      title: 'SOBRE NOSOTROS',
      type: InformationType.TEXT,
      items: [
        { content: 'Encuentra tu tienda', redirectTo: '', avaliabled: false },
        { content: 'Contáctanos', redirectTo: '', avaliabled: false },
        { content: 'Trabaja con nosotros', redirectTo: '', avaliabled: false },
      ],
    },
    row3: {
      title: 'SÍGUENOS EN:',
      type: InformationType.ICON,
      items: [
        {
          content: 'assets/icons/facebook.svg',
          redirectTo: 'https://www.facebook.com/',
          avaliabled: true,
        },
        {
          content: 'assets/icons/twitter.svg',
          redirectTo: 'https://twitter.com/',
          avaliabled: true,
        },
        {
          content: 'assets/icons/instagram.svg',
          redirectTo: 'https://www.instagram.com/',
          avaliabled: true,
        },
        {
          content: 'assets/icons/youtube.svg',
          redirectTo: 'https://www.youtube.com/',
          avaliabled: true,
        },
      ],
    },
  };

  constructor() {}

  ngOnInit(): void {}

  get politics() {
    return this.footerOptions.row1;
  }

  get aboutUs() {
    return this.footerOptions.row2;
  }

  get followUs() {
    return this.footerOptions.row3;
  }
}
