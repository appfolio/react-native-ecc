package com.rn.ecc;

/**
 * Created by appfolio on 8/16/17.
 */

import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

import com.facebook.react.bridge.Callback;

/**
 * Small helper class to manage text/icon around fingerprint authentication UI.
 */
public class ECCFingerprint {
    private final FingerprintManager mFingerprintManager;
    private CancellationSignal mCancellationSignal;
    private FingerprintManager.AuthenticationCallback mCallback;

    private boolean mSelfCancelled;

    /**
     * Constructor for {@link ECCFingerprint}.
     */
    ECCFingerprint(FingerprintManager fingerprintManager, FingerprintManager.AuthenticationCallback callback) {
        mFingerprintManager = fingerprintManager;
        mCallback = callback;
    }

    public boolean isFingerprintAuthAvailable() {
        // The line below prevents the false positive inspection from Android Studio
        // noinspection ResourceType
        return mFingerprintManager.isHardwareDetected()
                && mFingerprintManager.hasEnrolledFingerprints();
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject) {
        if (!isFingerprintAuthAvailable()) {
            return;
        }
        mCancellationSignal = new CancellationSignal();
        mSelfCancelled = false;
        // The line below prevents the false positive inspection from Android Studio
        // noinspection ResourceType
        mFingerprintManager
                .authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, mCallback, null);
    }

    public void stopListening() {
        if (mCancellationSignal != null) {
            mSelfCancelled = true;
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
    }

    public boolean isSelfCancelled() {
        return mSelfCancelled;
    }
}
