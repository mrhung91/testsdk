package com.profilegame.manager.tracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cuuchau.sdk9chau.tracking.InstallationReceiver;

/**
 * Created by thuan.nt on 6/12/2015.
 */
public class Install extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        InstallationReceiver installationReceiver = new InstallationReceiver();
        installationReceiver.onReceive(context,intent);
    }
}
