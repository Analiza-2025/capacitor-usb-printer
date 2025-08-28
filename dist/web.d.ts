import { WebPlugin } from '@capacitor/core';
import { CapacitorPrintersPlugin, PrinterDevice, PrintOptions, PrintImageOptions } from './definitions';
export declare class CapacitorPrintersWeb extends WebPlugin implements CapacitorPrintersPlugin {
    getUsbPrinters(): Promise<{
        printers: PrinterDevice[];
    }>;
    print(options: PrintOptions): Promise<void>;
    checkUsbPermissions(): Promise<{
        granted: boolean;
    }>;
    requestUsbPermissions(): Promise<{
        granted: boolean;
    }>;
    printBase64Image(options: PrintImageOptions): Promise<void>;
}
