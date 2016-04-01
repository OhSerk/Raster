public void credits() {  
  background(175);
  image(CC, 280*width/640, 100*height/360, 80*width/640, 80*width/640);
  //image(icon, 280*width/640, 190*height/360, 80*width/640, 80*width/640);
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 100*height/360 && mouseY <= 180*height/360) {
    background(175);
    text("Github (link)", 320*width/640, 220*height/360);
    image(CC, 280*width/640, 100*height/360, 80*width/640, 80*width/640);
  }
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 190*height/360 && mouseY <= 270*height/360) {
    //background(175);
    //text("Iconfinder (link)", 300*width/640, 160*height/360);
    //image(icon, 280*width/640, 190*height/360, 80*width/640, 80*width/640);
  }
  textSize(20*width/640);
  text("Credits: \nOhSerK", 320*width/640, 50*height/360);
}

public void creditRL() {
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 190*height/360 && mouseY <= 270*height/360);
    //link("https://www.iconfinder.com");
    //link("http://creativecommons.org/licenses/by/3.0/");
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 100*height/360 && mouseY <= 180*height/360)
    link("https://github.com/OhSerk/Raster");
}


//Bluetooth
public void provaBt() {/*
  if (!bluetoothAdapter.isEnabled()) {
    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); 
    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
  }
/*
  Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
  // If there are paired devices
  if (pairedDevices.size() > 0) {
    // Loop through paired devices
    for (BluetoothDevice device : pairedDevices) {
      // Add the name and address to an array adapter to show in a ListView
      mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
    }
  }
}
/*
  final BroadcastReceiver mReceiver = new BroadcastReceiver() {
 public void onReceive(Context context, Intent intent) {
 String action = intent.getAction();
 // When discovery finds a device
 if (BluetoothDevice.ACTION_FOUND.equals(action)) {
 // Get the BluetoothDevice object from the Intent
 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
 // Add the name and address to an array adapter to show in a ListView
 mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
 }
 }
 };
 // Register the BroadcastReceiver
 IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
 registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy*/
}