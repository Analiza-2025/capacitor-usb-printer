package io.ionic.cap.plugin.plugins.CapacitorPrinters;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorPrinters")
public class CapacitorPrintersPlugin extends Plugin {

    private CapacitorPrinters implementation;

    @Override
    public void load() {
        implementation = new CapacitorPrinters(getActivity());
    }

    @PluginMethod()
    public void getUsbPrinters(PluginCall call) {
        try {
            JSArray printers = implementation.getUsbPrinters();
            JSObject ret = new JSObject();
            ret.put("printers", printers);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Error getting USB printers: " + e.getMessage(), e);
        }
    }

    @PluginMethod()
    public void print(PluginCall call) {
        String text = call.getString("text");
        if (text == null) {
            call.reject("Text parameter is required");
            return;
        }

        Integer dpi = call.getInt("dpi");
        Double widthMM = call.getDouble("widthMM");
        Integer charactersPerLine = call.getInt("charactersPerLine");

        try {
            implementation.print(
                text,
                dpi != null ? dpi : 203,
                widthMM != null ? widthMM.floatValue() : 48f,
                charactersPerLine != null ? charactersPerLine : 32
            );
            call.resolve();
        } catch (Exception e) {
            call.reject("Print error: " + e.getMessage(), e);
        }
    }

    @PluginMethod()
    public void printBase64Image(PluginCall call) {
        String base64Image = call.getString("base64Image");
        if (base64Image == null) {
            call.reject("Base64 image parameter is required");
            return;
        }

        Integer dpi = call.getInt("dpi");
        Double widthMM = call.getDouble("widthMM");
        Integer charactersPerLine = call.getInt("charactersPerLine");

        try {
            implementation.printBase64Image(
                base64Image,
                dpi != null ? dpi : 203,
                widthMM != null ? widthMM.floatValue() : 48f,
                charactersPerLine != null ? charactersPerLine : 32
            );
            call.resolve();
        } catch (Exception e) {
            call.reject("Print image error: " + e.getMessage(), e);
        }
    }

    @PluginMethod()
    public void checkUsbPermissions(PluginCall call) {
        boolean granted = implementation.checkUsbPermissions();
        JSObject ret = new JSObject();
        ret.put("granted", granted);
        call.resolve(ret);
    }

    @PluginMethod()
    public void requestUsbPermissions(PluginCall call) {
        try {
            boolean granted = implementation.requestUsbPermissions();
            JSObject ret = new JSObject();
            ret.put("granted", granted);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Error requesting USB permissions: " + e.getMessage(), e);
        }
    }
}