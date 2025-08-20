import { WebPlugin } from '@capacitor/core';
import type { CapacitorPrintersPlugin, PrinterDevice, PrintOptions } from './definitions';

export class CapacitorPrintersWeb extends WebPlugin implements CapacitorPrintersPlugin {
  async getUsbPrinters(): Promise<{ printers: PrinterDevice[] }> {
    console.log('CapacitorPrinters: getUsbPrinters not supported on web');
    return { printers: [] };
  }

  async print(options: PrintOptions): Promise<void> {
    console.log('CapacitorPrinters: print called with options:', options);
    console.log('CapacitorPrinters: Print not supported on web platform');
  }

  async checkUsbPermissions(): Promise<{ granted: boolean }> {
    console.log('CapacitorPrinters: checkUsbPermissions not needed on web');
    return { granted: true };
  }

  async requestUsbPermissions(): Promise<{ granted: boolean }> {
    console.log('CapacitorPrinters: requestUsbPermissions not needed on web');
    return { granted: true };
  }
}