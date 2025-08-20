package io.ionic.cap.plugin.plugins.CapacitorPrinters;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.usb.UsbConnection;
import com.dantsu.escposprinter.connection.usb.UsbPrintersConnections;

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