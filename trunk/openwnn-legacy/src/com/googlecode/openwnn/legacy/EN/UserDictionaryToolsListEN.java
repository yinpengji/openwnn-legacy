/*
 * Copyright (C) 2008,2009  OMRON SOFTWARE Co., Ltd.
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

package com.googlecode.openwnn.legacy.EN;

import android.view.View;
import android.view.Window;
import java.util.Comparator;

import com.googlecode.openwnn.legacy.*;

/**
 * The user dictionary tool class for English IME.
 *
 * @author Copyright (C) 2009 OMRON SOFTWARE CO., LTD.  All Rights Reserved.
 */
public class UserDictionaryToolsListEN extends UserDictionaryToolsList {
    /**
     * Constructor
     */
    public UserDictionaryToolsListEN() {
		if (OpenWnnEN.getInstance() == null) {
			new OpenWnnEN(this);
		}
        mListViewName = "com.googlecode.openwnn.legacy.EN.UserDictionaryToolsListEN";
        mEditViewName = "com.googlecode.openwnn.legacy.EN.UserDictionaryToolsEditEN";
        mPackageName  = "com.googlecode.openwnn.legacy";
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsList#headerCreate */
    @Override protected void headerCreate() {
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
      R.layout.user_dictionary_tools_list_header);
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsList#createUserDictionaryToolsEdit */
    @Override protected UserDictionaryToolsEdit createUserDictionaryToolsEdit(View view1, View view2) {
        return new UserDictionaryToolsEditEN(view1, view2);
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsList#sendEventToIME */
    @Override protected boolean sendEventToIME(OpenWnnEvent ev) {
        try {
            return OpenWnnEN.getInstance().onEvent(ev);
        } catch (Exception ex) {
            /* do nothing if an error occurs */
        }
        return false;
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsList#getComparator */
    @Override protected Comparator<WnnWord> getComparator() {
    	return new ListComparatorEN();
    }

    /** Comparator class for sorting the list of English user dictionary */
    protected class ListComparatorEN implements Comparator<WnnWord>{
        public int compare(WnnWord word1, WnnWord word2) {
            return word1.stroke.compareTo(word2.stroke);
        };
    }
}
