import { registerPlugin } from '@capacitor/core';

import type { CapacitorPrintersPlugin } from './definitions';

const CapacitorPrinters = registerPlugin<CapacitorPrintersPlugin>(
  'CapacitorPrinters',
  {
    web: () => import('./web').then(m => new m.CapacitorPrintersWeb()),
  },
);

export * from './definitions';
export { CapacitorPrinters };