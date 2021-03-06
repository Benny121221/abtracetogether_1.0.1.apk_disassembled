package ca.albertahealthservices.contacttracing.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import ca.albertahealthservices.contacttracing.Preference;
import ca.albertahealthservices.contacttracing.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lca/albertahealthservices/contacttracing/onboarding/PreOnboardingActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PreOnboardingActivity.kt */
public final class PreOnboardingActivity extends FragmentActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity_onboarding);
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tv_app_version);
        if (appCompatTextView != null) {
            appCompatTextView.setText("App Version: 1.0.1");
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.btn_onboardingStart);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new PreOnboardingActivity$onCreate$1(this));
        }
        Preference preference = Preference.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        if (preference.getUUIDRetryAttempts(applicationContext) > 2) {
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.btn_onboardingStart);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "btn_onboardingStart");
            linearLayout2.setEnabled(false);
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tv_desc);
            if (appCompatTextView2 != null) {
                appCompatTextView2.setText(getString(R.string.tv_uuid_retry_error));
            }
            AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.tv_desc);
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTextColor(ContextCompat.getColor(this, R.color.error));
            }
        }
    }
}
