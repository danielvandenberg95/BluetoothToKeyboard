/*
 * Copyright (c) 2016. Daniël van den Berg.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express
 * or implied, including but not limited to the warranties of
 * merchantability, fitness for a particular purpose, title and
 * non-infringement. In no event shall the copyright holders or anyone
 * distributing the software be liable for any damages or other liability,
 * whether in contract, tort or otherwise, arising from, out of or in
 * connection with the software or the use or other dealings in the
 * software.
 */

package com.gmail.dvandenberg95.bluetoothtopc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Daniel on 25/8/2016.
 */
public class BluetoothService extends Service {
    private final Binder BINDER = new BluetoothServiceBinder();
    private final BluetoothStringSender bluetoothStringSender = new BluetoothStringSender();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return BINDER;
    }

    private void sendString(final String string) {
        bluetoothStringSender.sendString(string, this);
    }

    public class BluetoothServiceBinder extends Binder {
        public void sendString(final String string) {
            BluetoothService.this.sendString(string);
        }

        public void sendString(final int textResource) {
            sendString(getResources().getString(textResource));
        }
    }
}
