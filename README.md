# capacitor-usb-printer

Capacitor plugin for USB thermal printer support using ESC/POS commands.

## Install

```bash
npm install https://github.com/yourusername/capacitor-usb-printer
npx cap sync
```

## API

<docgen-index>

* [`getUsbPrinters()`](#getusbprinters)
* [`print(...)`](#print)
* [`checkUsbPermissions()`](#checkusbpermissions)
* [`requestUsbPermissions()`](#requestusbpermissions)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getUsbPrinters()

```typescript
getUsbPrinters() => Promise<{ printers: PrinterDevice[]; }>
```

Get list of available USB printers

**Returns:** <code>Promise&lt;{ printers: PrinterDevice[]; }&gt;</code>

----

### print(...)

```typescript
print(options: PrintOptions) => Promise<void>
```

Print formatted text to a printer

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#printoptions">PrintOptions</a></code> |

----

### checkUsbPermissions()

```typescript
checkUsbPermissions() => Promise<{ granted: boolean; }>
```

Check if USB printer permissions are granted

**Returns:** <code>Promise&lt;{ granted: boolean; }&gt;</code>

----

### requestUsbPermissions()

```typescript
requestUsbPermissions() => Promise<{ granted: boolean; }>
```

Request USB printer permissions

**Returns:** <code>Promise&lt;{ granted: boolean; }&gt;</code>

----

### Interfaces

#### PrinterDevice

| Prop           | Type                                    | Description            |
| -------------- | --------------------------------------- | ---------------------- |
| **`name`**     | <code>string</code>                     | Device name or identifier |
| **`deviceId`** | <code>string</code>                     | Device ID              |
| **`type`**     | <code>'usb' \| 'bluetooth' \| 'tcp'</code> | Connection type        |

#### PrintOptions

| Prop                     | Type                                                    | Description                     | Default        |
| ------------------------ | ------------------------------------------------------- | ------------------------------- | -------------- |
| **`text`**               | <code>string</code>                                     | Text content to print           |                |
| **`device`**             | <code><a href="#printerdevice">PrinterDevice</a></code> | Device to print to              |                |
| **`dpi`**                | <code>number</code>                                     | Printer DPI (dots per inch)    | <code>203</code> |
| **`widthMM`**            | <code>number</code>                                     | Printer width in millimeters   | <code>48</code>  |
| **`charactersPerLine`**  | <code>number</code>                                     | Number of characters per line  | <code>32</code>  |

</docgen-api>