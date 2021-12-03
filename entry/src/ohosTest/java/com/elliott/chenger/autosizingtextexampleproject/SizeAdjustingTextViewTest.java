package com.elliott.chenger.autosizingtextexampleproject;

import com.omitneedlesscode.sizeadjustingtextview.SizeAdjustingTextView;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * SizeAdjustingTextView is a TextView that adjusts text size
 * depending if or if not it will fit in the view bounds.
 * Copyright (C) 2014 Elliott Chenger
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author Elliott Chenger
 * @author Chase Colburn
 * @since Jan 18, 2014
 * @since Apr 4, 2011
 */
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