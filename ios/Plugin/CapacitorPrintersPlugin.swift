import Foundation
import Capacitor

@objc(CapacitorPrintersPlugin)
public class CapacitorPrintersPlugin: CAPPlugin {
    
    @objc func getUsbPrinters(_ call: CAPPluginCall) {
        // USB printing is not supported on iOS
        call.resolve([
            "printers": []
        ])
    }
    
    @objc func print(_ call: CAPPluginCall) {
        // USB printing is not supported on iOS
        call.reject("USB printing not supported on iOS")
    }
    
    @objc func checkUsbPermissions(_ call: CAPPluginCall) {
        // USB permissions are not needed on iOS for this use case
        call.resolve([
            "granted": true
        ])
    }
    
    @objc func requestUsbPermissions(_ call: CAPPluginCall) {
        // USB permissions are not needed on iOS for this use case
        call.resolve([
            "granted": true
        ])
    }
}