import { WebPlugin as r } from "@capacitor/core";
class s extends r {
  async getUsbPrinters() {
    return console.log("CapacitorPrinters: getUsbPrinters not supported on web"), { printers: [] };
  }
  async print(e) {
    console.log("CapacitorPrinters: print called with options:", e), console.log("CapacitorPrinters: Print not supported on web platform");
  }
  async checkUsbPermissions() {
    return console.log("CapacitorPrinters: checkUsbPermissions not needed on web"), { granted: !0 };
  }
  async requestUsbPermissions() {
    return console.log("CapacitorPrinters: requestUsbPermissions not needed on web"), { granted: !0 };
  }
  async printBase64Image(e) {
    console.log("CapacitorPrinters: printBase64Image called with options:", e), console.log("CapacitorPrinters: Print not supported on web platform");
  }
}
export {
  s as CapacitorPrintersWeb
};
