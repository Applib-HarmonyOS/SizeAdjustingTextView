/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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