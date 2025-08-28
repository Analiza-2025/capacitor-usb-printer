package io.ionic.cap.plugin.plugins.CapacitorPrinters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Base64;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.usb.UsbConnection;
import com.dantsu.escposprinter.connection.usb.UsbPrintersConnections;
import com.dantsu.escposprinter.textparser.PrinterTextParserImg;

public class CapacitorPrinters {

    private Activity activity;
    private UsbManager usbManager;

    public CapacitorPrinters(Activity activity) {
        this.activity = activity;
        this.usbManager = (UsbManager) activity.getSystemService(Context.USB_SERVICE);
    }

    public JSArray getUsbPrinters() {
        JSArray printersArray = new JSArray();
        
        try {
            UsbPrintersConnections usbPrintersConnections = new UsbPrintersConnections(activity);
            UsbConnection[] connections = usbPrintersConnections.getList();
            
            for (UsbConnection connection : connections) {
                UsbDevice device = connection.getDevice();
                JSObject printer = new JSObject();
                printer.put("name", device.getDeviceName());
                printer.put("deviceId", String.valueOf(device.getDeviceId()));
                printer.put("type", "usb");
                printersArray.put(printer);
            }
        } catch (Exception e) {
            // Return empty array if there's an error
        }
        
        return printersArray;
    }

    public void print(String text, int dpi, float widthMM, int charactersPerLine) throws Exception {
        UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(activity);
        
        if (usbConnection == null) {
            throw new Exception("No USB printer connected");
        }

        // Check if we have permission for this device
        if (usbManager != null && !usbManager.hasPermission(usbConnection.getDevice())) {
            throw new Exception("USB permission not granted for this device");
        }

        EscPosPrinter printer = new EscPosPrinter(usbConnection, dpi, widthMM, charactersPerLine);
        printer.printFormattedText(text);
        printer.disconnectPrinter();
    }

    public boolean checkUsbPermissions() {
        try {
            UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(activity);
            if (usbConnection == null) {
                return false;
            }
            return usbManager != null && usbManager.hasPermission(usbConnection.getDevice());
        } catch (Exception e) {
            return false;
        }
    }

    public void printBase64Image(String base64Image, int dpi, float widthMM, int charactersPerLine) throws Exception {
        UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(activity);
        
        if (usbConnection == null) {
            throw new Exception("No USB printer connected");
        }

        // Check if we have permission for this device
        if (usbManager != null && !usbManager.hasPermission(usbConnection.getDevice())) {
            throw new Exception("USB permission not granted for this device");
        }

        EscPosPrinter printer = new EscPosPrinter(usbConnection, dpi, widthMM, charactersPerLine);
        
        try {
            // Decode base64 string to bitmap
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            
            if (decodedBitmap == null) {
                throw new Exception("Failed to decode base64 image");
            }

            int width = decodedBitmap.getWidth();
            int height = decodedBitmap.getHeight();

            StringBuilder textToPrint = new StringBuilder();
            
            // Process image in chunks of 256 pixels height to avoid memory issues
            for (int y = 0; y < height; y += 256) {
                int chunkHeight = (y + 256 >= height) ? height - y : 256;
                Bitmap bitmap = Bitmap.createBitmap(decodedBitmap, 0, y, width, chunkHeight);
                textToPrint.append("[C]<img>")
                          .append(PrinterTextParserImg.bitmapToHexadecimalString(printer, bitmap))
                          .append("</img>\n");
            }
            
            textToPrint.append("[C]Printed!!!\n");
            printer.printFormattedTextAndCut(textToPrint.toString());
            
        } finally {
            printer.disconnectPrinter();
        }
    }

    public boolean requestUsbPermissions() throws Exception {
        UsbConnection usbConnection = UsbPrintersConnections.selectFirstConnected(activity);
        
        if (usbConnection == null) {
            throw new Exception("No USB printer connected");
        }

        if (usbManager != null && usbManager.hasPermission(usbConnection.getDevice())) {
            return true;
        }

        // For now, we'll assume permission is needed and return false
        // In a full implementation, you'd use PendingIntent to request permission
        throw new Exception("USB permission needed. Please grant permission in device settings.");
    }
}