export interface Information {
  title: string;
  items: Array<InformationOption>;
  type: InformationType;
}

export interface InformationOption {
  content: string;
  redirectTo: string;
  avaliabled: boolean;
}

export enum InformationType {
  ICON,
  TEXT,
}
