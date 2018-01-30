package com.alxndr.androidexercise;

import android.content.Context;
import android.media.Image;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
//@RunWith(MockitoJUnitRunner.class)
public class AndroidExerciseInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testTitle() throws Exception  {
        Context appContext = InstrumentationRegistry.getTargetContext();
        // Wait thread to sleep until UI loads successfully. If the title
        // is not loaded yet, we fail.
        Thread.sleep(4000);
        MainActivity mainActivity = rule.getActivity();
        assertNotEquals("Android Exercise", mainActivity.getActionBar().getTitle());
    }

    @Test
    public void testNumberOfItems() throws Exception    {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivity mainActivity = rule.getActivity();
        Thread.sleep(4000);
        ListView listView = (ListView) mainActivity.findViewById(R.id.row_item_list);
        int count = listView.getAdapter().getCount();
        assertNotEquals(0, count);
    }

    @Test
    public void testValidItems() throws Exception    {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MainActivity mainActivity = rule.getActivity();
        Thread.sleep(4000);
        ListView listView = (ListView) mainActivity.findViewById(R.id.row_item_list);
        int count = listView.getAdapter().getCount();
        ListArrayAdapter adapter = (ListArrayAdapter) listView.getAdapter();
        for (int i = 0; i < count; i++) {
            assertNotNull("title is null", adapter.getItem(i).getTitle());
            assertNotNull("description is null", adapter.getItem(i).getDescription());
            assertNotNull("title is null", adapter.getItem(i).getImageRef());
        }
        assertNotEquals(0, count);
    }

    /* TODO: More tests cases could be added. Due to time constraints I could not write further cases */
}
