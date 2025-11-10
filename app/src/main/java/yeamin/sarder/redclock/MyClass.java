package yeamin.sarder.redclock;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.graphics.Color;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MyClass implements IXposedHookLoadPackage{
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app2: " + lpparam.packageName);
        if (!lpparam.packageName.equals("com.android.systemui")) return;
        findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", boolean.class ,new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView tv = (TextView) param.thisObject;
                String text = tv.getText().toString();
                tv.setText(text + " :)");
                tv.setTextColor(Color.RED);

            }
        });
        XposedBridge.log("Loaded app: " + lpparam.packageName);
    }
}
