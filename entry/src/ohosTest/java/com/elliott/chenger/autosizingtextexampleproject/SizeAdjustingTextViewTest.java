package com.elliott.chenger.autosizingtextexampleproject;

import com.omitneedlesscode.sizeadjustingtextview.SizeAdjustingTextView;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SizeAdjustingTextViewTest {

    Context context;

    @Test
    public void maxTextSizeTest() {
        context= AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        float size =50.0f;
        SizeAdjustingTextView sizeAdjustingTextView=new SizeAdjustingTextView(context);
        sizeAdjustingTextView.setMaxTextSize(size);
        assertEquals(size,sizeAdjustingTextView.getMaxTextSize(),0.1);
    }

    @Test
    public void minTextSizeTest() {
        context= AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        float size = 10.0f;
        SizeAdjustingTextView sizeAdjustingTextView=new SizeAdjustingTextView(context);
        sizeAdjustingTextView.setMinTextSize(size);
        assertEquals(size,sizeAdjustingTextView.getMaxTextSize(),0.1);
    }

}