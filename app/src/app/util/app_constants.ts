export const AppConstants = {
  siteNotAvaliabled: function (option: string) {
    return `Lo sentimos!!.  ${option} está en construcción`;
  },
  sweetIcons: {
    success: 'success',
    error: 'error',
    warning: 'warning',
    info: 'info',
    question: 'question',
  },
  building: 'assets/icons/building.gif',
  titleSorry: 'Lo sentimos!',
  unknowError:
    'Un error desconocido a ocurrido, porfavor comuniquese con soporte técnico',
  globalError: 'Error',
};

export const firebaseConfig = {
  apiKey: 'AIzaSyB7uEQVIcwS_gou7lJqHVa9zNSz-V6ZoZo',
  authDomain: 'clothestore-fb4bf.firebaseapp.com',
  projectId: 'clothestore-fb4bf',
  storageBucket: 'clothestore-fb4bf.appspot.com',
  messagingSenderId: '1086906317801',
  appId: '1:1086906317801:web:7db5540d8d9ff2f3e8a978',
  measurementId: 'G-2FKYSGML41',
};
