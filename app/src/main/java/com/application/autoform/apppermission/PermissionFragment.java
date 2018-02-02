package com.application.autoform.apppermission;

import android.support.v4.app.Fragment;


/**
 * Created by Sandeep on 21/01/2017.
 */

abstract public class PermissionFragment extends Fragment {
    private static final int REQUEST_CODE = 0x1;

    public boolean getPermission(String permission, int requestcode) {
        if (PermissionsUtils.isPermissionAvailable(getActivity(), permission)) {
            return true;
        } else {
            PermissionsUtils.requestPermission(this, permission,
                    requestcode);
        }
        return false;
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (PermissionsUtils.handlePermissionRequestResult(this, permissions, grantResults)) {
            setPermission(requestCode, true);
        } else {
            setPermission(requestCode, false);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public abstract void setPermission(int requestCode, boolean isGranted);
}
