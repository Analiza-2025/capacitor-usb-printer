import { registerPlugin as t } from "@capacitor/core";
const i = t(
  "CapacitorPrinters",
  {
    web: () => import("./web-gUSUYmfL.mjs").then((r) => new r.CapacitorPrintersWeb())
  }
);
export {
  i as CapacitorPrinters
};
