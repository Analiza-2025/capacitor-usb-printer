#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

CAP_PLUGIN(CapacitorPrintersPlugin, "CapacitorPrinters",
           CAP_PLUGIN_METHOD(getUsbPrinters, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(print, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(checkUsbPermissions, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(requestUsbPermissions, CAPPluginReturnPromise);
)