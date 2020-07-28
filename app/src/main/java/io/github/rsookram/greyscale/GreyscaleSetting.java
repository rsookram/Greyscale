package io.github.rsookram.greyscale;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;

public class GreyscaleSetting {

    private static final String DISPLAY_DALTONIZER_ENABLED = "accessibility_display_daltonizer_enabled";
    private static final String DISPLAY_DALTONIZER = "accessibility_display_daltonizer";

    private final Context context;

    public GreyscaleSetting(Context context) {
        this.context = context;
    }

    public boolean canChange() {
        int check = context.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS");
        return check == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isEnabled() {
        ContentResolver resolver = context.getContentResolver();
        return Secure.getInt(resolver, DISPLAY_DALTONIZER_ENABLED, 0) == 1
                && Secure.getInt(resolver, DISPLAY_DALTONIZER, 0) == 0;
    }

    public void setEnabled(boolean enabled) {
        ContentResolver resolver = context.getContentResolver();
        Secure.putInt(resolver, DISPLAY_DALTONIZER_ENABLED, enabled ? 1 : 0);
        Secure.putInt(resolver, DISPLAY_DALTONIZER, enabled ? 0 : -1);
    }
}
