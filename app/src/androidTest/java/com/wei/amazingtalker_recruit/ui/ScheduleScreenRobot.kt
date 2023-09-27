package com.wei.amazingtalker_recruit.ui

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.wei.amazingtalker_recruit.MainActivity
import com.wei.amazingtalker_recruit.feature.teacherschedule.R as FeatureTeacherScheduleR
import kotlin.properties.ReadOnlyProperty

/**
 * 遵循此模型，找到測試使用者介面元素、檢查其屬性、和透過測試規則執行動作：
 * composeTestRule{.finder}{.assertion}{.action}
 *
 * Testing cheatsheet：
 * https://developer.android.com/jetpack/compose/testing-cheatsheet
 */
internal fun scheduleScreenRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: ScheduleScreenRobot.() -> Unit
) = ScheduleScreenRobot(composeTestRule).apply(func)

internal open class ScheduleScreenRobot(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) {
    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val scheduleTopAppBarTag by composeTestRule.stringResource(FeatureTeacherScheduleR.string.tag_schedule_top_app_bar)


    private val scheduleTopAppBar by lazy {
        composeTestRule.onNodeWithTag(
            scheduleTopAppBarTag,
            useUnmergedTree = true
        )
    }

    fun verifyScheduleTopAppBarDisplayed() {
        scheduleTopAppBar.assertExists().assertIsDisplayed()
    }

    fun isScheduleTopAppBarDisplayed(): Boolean {
        return try {
            verifyScheduleTopAppBarDisplayed()
            true
        } catch (e: AssertionError) {
            false
        }
    }
}