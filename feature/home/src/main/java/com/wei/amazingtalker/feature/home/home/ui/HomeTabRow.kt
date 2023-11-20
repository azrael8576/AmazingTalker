package com.wei.amazingtalker.feature.home.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wei.amazingtalker.core.designsystem.theme.AtTheme
import com.wei.amazingtalker.feature.home.R
import com.wei.amazingtalker.feature.home.home.HomeViewState
import com.wei.amazingtalker.feature.home.home.Tab

@Composable
fun HomeTabRow(
    uiStates: HomeViewState,
    onTabSelected: (Tab) -> Unit,
) {
    val currentSelectedTab by rememberUpdatedState(uiStates.selectedTab)
    val shouldDisplayChatCount by rememberUpdatedState(uiStates.shouldDisplayChatCount)
    val chatCountDisplay by rememberUpdatedState(uiStates.chatCountDisplay)

    ScrollableTabRow(
        selectedTabIndex = currentSelectedTab.ordinal,
        edgePadding = 16.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        MyCoursesTab(
            isSelected = currentSelectedTab == Tab.MY_COURSES,
            onTabClick = { onTabSelected(Tab.MY_COURSES) },
        )

        ChatTab(
            isSelected = currentSelectedTab == Tab.CHATS,
            count = chatCountDisplay,
            shouldDisplayChatCount = shouldDisplayChatCount,
            onTabClick = { onTabSelected(Tab.CHATS) },
        )

        TutorsTab(
            isSelected = currentSelectedTab == Tab.TUTORS,
            onTabClick = { onTabSelected(Tab.TUTORS) },
        )
    }
}

@Composable
fun MyCoursesTab(
    isSelected: Boolean,
    onTabClick: () -> Unit,
) {
    val myCourses = stringResource(id = R.string.my_courses)

    Tab(
        selected = isSelected,
        onClick = onTabClick,
        modifier = Modifier.semantics {
            contentDescription = myCourses
        },
    ) {
        Text(
            text = myCourses,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
fun ChatTab(
    isSelected: Boolean,
    count: String,
    shouldDisplayChatCount: Boolean,
    onTabClick: () -> Unit,
) {
    val chats = stringResource(R.string.chats)

    Tab(
        selected = isSelected,
        onClick = onTabClick,
        modifier = Modifier.semantics {
            contentDescription = chats
        },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = chats,
                style = MaterialTheme.typography.titleMedium,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
            )
            Spacer(modifier = Modifier.width(4.dp))
            if (shouldDisplayChatCount) {
                ChatCountBadge(count)
            }
        }
    }
}

@Composable
fun ChatCountBadge(count: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        Text(
            text = count,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
        )
    }
}

@Composable
fun TutorsTab(isSelected: Boolean, onTabClick: () -> Unit) {
    val tutors = stringResource(id = R.string.tutors)

    Tab(
        selected = isSelected,
        onClick = onTabClick,
        modifier = Modifier.semantics {
            contentDescription = tutors
        },
    ) {
        Text(
            text = stringResource(R.string.tutors),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabRowPreview() {
    AtTheme {
        HomeTabRow(
            uiStates = HomeViewState(
                selectedTab = Tab.MY_COURSES,
                chatCount = 999,
            ),
            onTabSelected = {},
        )
    }
}
