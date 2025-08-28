export interface PrinterDevice {
    /**
     * Device name or identifier
     */
    name: string;
    /**
     * Device ID
     */
    deviceId: string;
    /**
     * Connection type
     */
    type: 'usb' | 'bluetooth' | 'tcp';
}
export interface PrintOptions {
    /**
     * Text content to print
     */
    text: string;
    /**
     * Device to print to
     */
    device?: PrinterDevice;
    /**
     * Printer DPI (dots per inch)
     * @default 203
     */
    dpi?: number;
    /**
     * Printer width in millimeters
     * @default 48
     */
    widthMM?: number;
    /**
     * Number of characters per line
     * @default 32
     */
    charactersPerLine?: number;
}
export interface PrintImageOptions {
    /**
     * Base64 encoded image data
     */
    base64Image: string;
    /**
     * Device to print to
     */
    device?: PrinterDevice;
    /**
     * Printer DPI (dots per inch)
     * @default 203
     */
    dpi?: number;
    /**
     * Printer width in millimeters
     * @default 48
     */
    widthMM?: number;
    /**
     * Image alignment
     * @default 'center'
     */
    imageAlign?: 'left' | 'center' | 'right';
    /**
     * Force image to full width
     * @default false
     */
    imageFullWidth?: boolean;
}
export interface CapacitorPrintersPlugin {
    /**
     * Get list of available USB printers
     */
    getUsbPrinters(): Promise<{
        printers: PrinterDevice[];
    }>;
    /**
     * Print formatted text to a printer
     */
    print(options: PrintOptions): Promise<void>;
    /**
     * Print base64 encoded image to a printer
     */
    printBase64Image(options: PrintImageOptions): Promise<void>;
    /**
     * Check if USB printer permissions are granted
     */
    checkUsbPermissions(): Promise<{
        granted: boolean;
    }>;
    /**
     * Request USB printer permissions
     */
    requestUsbPermissions(): Promise<{
        granted: boolean;
    }>;
}
