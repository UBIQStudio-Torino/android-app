/*
 * Copyright 2016 UBIQ Studio S.n.c.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ubiqstudio;

import android.content.res.Configuration;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class StaticActionBarDrawerToggle implements DrawerLayout.DrawerListener {

    private final DrawerLayout mDrawerLayout;
    private final Toolbar mToolbar;
    @DrawableRes
    private final int mDrawableRes;
    @StringRes
    private final int mOpenDrawerContentDescRes;
    @StringRes
    private final int mCloseDrawerContentDescRes;

    public StaticActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar, @DrawableRes int drawableRes,
                                       @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
        mDrawerLayout = drawerLayout;
        mToolbar = toolbar;
        mDrawableRes = drawableRes;
        mOpenDrawerContentDescRes = openDrawerContentDescRes;
        mCloseDrawerContentDescRes = closeDrawerContentDescRes;

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    public void syncState() {
        mToolbar.setNavigationIcon(mDrawableRes);
        mToolbar.setNavigationContentDescription(mDrawerLayout.isDrawerOpen(GravityCompat.START) ? mCloseDrawerContentDescRes : mOpenDrawerContentDescRes);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        syncState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null && item.getItemId() == android.R.id.home) {
            toggle();
            return true;
        }
        return false;
    }

    private void toggle() {
        int drawerLockMode = mDrawerLayout.getDrawerLockMode(GravityCompat.START);
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mToolbar.setNavigationContentDescription(mCloseDrawerContentDescRes);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mToolbar.setNavigationContentDescription(mOpenDrawerContentDescRes);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }
}
