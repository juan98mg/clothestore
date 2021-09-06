import { Component, Input, OnInit } from '@angular/core';
import {
  Information,
  InformationOption,
  InformationType,
} from 'src/app/models/option-information';
import { MessageService } from 'src/app/services/message.service';
import { AppConstants } from 'src/app/util/app_constants';
@Component({
  selector: 'app-footer-column-text',
  templateUrl: './footer-column-text.component.html',
  styleUrls: ['./footer-column-text.component.scss'],
})
export class FooterColumnTextComponent implements OnInit {
  @Input() information: Information | undefined;
  typeText = InformationType.TEXT;
  typeIcon = InformationType.ICON;
  showDetail = false;
  private delays = [
    'animate__custom-delay-1',
    'animate__custom-delay-2',
    'animate__custom-delay-3',
    'animate__custom-delay-4',
    'animate__custom-delay-5',
  ];
  constructor(private window: Window, private messageService: MessageService) {
    this.showDetail = window.innerWidth >= 430;
  }

  ngOnInit(): void {}

  get title() {
    return this.information ? this.information?.title : 'NO HA DEFINIDO TÍTULO';
  }

  get options() {
    return this.information ? this.information.items : [];
  }

  changeVisibility() {
    this.showDetail = !this.showDetail;
  }

  classByIndex(index: number) {
    return index > 5 ? this.delays[5] : this.delays[index];
  }

  get type() {
    return this.information ? this.information.type : InformationType.TEXT;
  }

  navigate(item: InformationOption) {
    if (item.avaliabled) {
      this.window.location.href = item.redirectTo;
    } else {
      this.messageService.commonMessage(
        AppConstants.titleSorry,
        AppConstants.siteNotAvaliabled(item.content),
        AppConstants.building
      );
    }
  }
}
